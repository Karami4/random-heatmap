import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class HeatMapPanel extends JPanel implements ActionListener {

    private boolean isRunning;
    private final Timer timer;
    private final Random random;
    private final Pointer pointer;
    private final int[][] blockMatrix;

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
                blockMatrix[i][j] = 255;
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (isRunning) {
            int direction = random.nextInt(Direction.values().length);
            Direction directionEnum = Direction.values()[direction];

            int pointerX = pointer.getMatrixX();
            int pointerY = pointer.getMatrixY();

            if(pointer.movePosition(directionEnum)) {
                if (blockMatrix[pointerX][pointerY] + Constants.INTENSITY_INCREMENT <= 255 &&
                        blockMatrix[pointerX][pointerY] + Constants.INTENSITY_INCREMENT >= 0) {
                    blockMatrix[pointerX][pointerY] = blockMatrix[pointerX][pointerY] + Constants.INTENSITY_INCREMENT;
                }
            } else {
                isRunning = false;
            }

        } else { // Update snake only if game is running
            System.out.println("Program End");
            timer.stop();
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i=0; i <= 20; i++) {

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
}
