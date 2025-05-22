import java.awt.*;

public class Pointer {

    private int currentX;
    private int currentY;

    private int matrixX;
    private int matrixY;

    public Pointer() {
        matrixX = Constants.AMOUNT_OF_BLOCKS_X / 2;
        matrixY = Constants.AMOUNT_OF_BLOCKS_Y / 2;

        convertToActualCoordinates();
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(currentX, currentY, Constants.ONE_BLOCK, Constants.ONE_BLOCK);
    }

    private void convertToActualCoordinates() {
        currentX = matrixX * Constants.ONE_BLOCK + Constants.GRID_OFFSET;
        currentY = matrixY * Constants.ONE_BLOCK + Constants.GRID_OFFSET;
    }

    public int getMatrixY() {
        return matrixY;
    }

    public int getMatrixX() {
        return matrixX;
    }

    public boolean movePosition(Direction direction) {
        if (direction == null) {
            return false;
        }

        switch (direction) {
            case UP:
                if (matrixY > 0) {
                    matrixY--;
                } else {
                    return false;
                }
                break;
            case DOWN:
                if (matrixY < Constants.AMOUNT_OF_BLOCKS_Y - 1) {
                    matrixY++;
                } else {
                    return false;
                }
                break;
            case LEFT:
                if (matrixX > 0) {
                    matrixX--;
                } else {
                    return false;
                }
                break;
            case RIGHT:
                if (matrixX < Constants.AMOUNT_OF_BLOCKS_X - 1) {
                    matrixX++;
                } else {
                    return false;
                }
                break;
            default:
                return false;
        }
        convertToActualCoordinates();
        return true;
    }
}