#!/bin/bash
set -euo pipefail

cd "$(dirname "$0")"

KEYSTORE="$HOME/javaKeystore.jks"
KEYALIAS="JavaCodeSigningKey"
TMP_DIR="target/temp_jar_extract"

echo "Please enter your keystore password:"
read -s STOREPASS
echo "Please enter your key password:"
read -s KEYPASS

FINAL_NAME=$(mvn help:evaluate -Dexpression=project.build.finalName -q -DforceStdout)
JAR_NAME="target/${FINAL_NAME}.jar"
FINAL_JAR_NAME="target/${FINAL_NAME}-final.jar"

echo "Running Maven build"
mvn clean package

if [[ ! -f "$JAR_NAME" ]]; then
  echo "Error: JAR file $JAR_NAME not found after build"
  exit 1
fi

echo "Preparing temporary extraction directory"
if [[ -e "$TMP_DIR" && -d "$TMP_DIR" ]]; then
  find "$TMP_DIR" -mindepth 1 -exec rm -f {} +
else
  mkdir -p "$TMP_DIR"
fi

pushd "$TMP_DIR" > /dev/null

echo "Extracting JAR"
jar xf "../${FINAL_NAME}.jar"

echo "Removing symlinks"
find . -type l -exec rm -v {} +

echo "Verifying removal of symlinks"
if find . -type l | grep -q .; then
  echo "Error: Symlinks still present after cleanup"
  exit 1
fi

popd > /dev/null

echo "Repacking cleaned JAR"
jar cf "$FINAL_JAR_NAME" -C "$TMP_DIR" .

echo "Cleaning up original JAR"
rm -f "$JAR_NAME"

echo "Signing the cleaned JAR"
jarsigner -keystore "$KEYSTORE" -storepass "$STOREPASS" -keypass "$KEYPASS" -tsa http://timestamp.sectigo.com/ "$FINAL_JAR_NAME" "$KEYALIAS"

echo "Verifying signed JAR"
jarsigner -verify "$FINAL_JAR_NAME"

echo "Build and signing complete: $FINAL_JAR_NAME"
