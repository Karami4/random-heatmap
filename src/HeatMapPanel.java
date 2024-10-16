import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class HeatMapPanel extends JPanel implements ActionListener {

    private boolean isRunning = false;
    private Timer timer;
    private Random random;
    private Pointer pointer;
    private int[][] blockMatrix;

    public HeatMapPanel() {
       timer = new Timer(Constants.TIMER_DELAY, this);
        random = new Random();
        timer.start();
        pointer = new Pointer();
        this.setPreferredSize(new Dimension(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
        this.setBackground(new Color(33, 39, 48));
        this.setFocusable(true);
        this.isRunning = true;
        this.blockMatrix = new int[Constants.AMOUNT_OF_BLOCKS_X][Constants.AMOUNT_OF_BLOCKS_Y];

        for (int i = 0; i < Constants.AMOUNT_OF_BLOCKS_X; i++) {
            for (int j = 0; j < Constants.AMOUNT_OF_BLOCKS_Y; j++) {
                blockMatrix[i][j] = 0;
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (isRunning) {
            int direction = random.nextInt(Direction.values().length);
            Direction directionEnum = Direction.values()[direction];
            System.out.println("Direction: " + directionEnum);

        } else { // Update snake only if game is running
            System.out.println("GAME OVER");
            timer.stop();
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i=0; i <= 20; i++) {
            int verticalX = (Constants.ONE_BLOCK * i) + Constants.GRID_OFFSET;
            int verticalY1 = Constants.GRID_OFFSET;
            int verticalY2 = Constants.WINDOW_HEIGHT - Constants.GRID_OFFSET;

            g.setColor(Color.WHITE);
            g.drawLine(verticalX, verticalY1, verticalX, verticalY2); // Vertical lines

            int horizontalX1 = Constants.GRID_OFFSET;
            int horizontalX2 = Constants.WINDOW_WIDTH - Constants.GRID_OFFSET;
            int horizontalY = (Constants.ONE_BLOCK * i) + Constants.GRID_OFFSET;
            g.drawLine(horizontalX1, horizontalY, horizontalX2, horizontalY);

            for (int j = 0; j < Constants.AMOUNT_OF_BLOCKS_X; j++) {
                for (int k = 0; k < Constants.AMOUNT_OF_BLOCKS_Y; k++) {
                    int intensity = blockMatrix[j][k];
                    g.setColor(new Color(intensity, intensity, intensity));
                    g.fillRect(Constants.ONE_BLOCK * j + Constants.GRID_OFFSET,
                            Constants.ONE_BLOCK * k + Constants.GRID_OFFSET,
                            Constants.ONE_BLOCK, Constants.ONE_BLOCK);
                }
            }
            pointer.draw(g);
        }
    }

    public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(font);
        // Determine the X coordinate for the text
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        // Set the font
        g.setFont(font);
        // Draw the String
        g.drawString(text, x, y);
    }
}
