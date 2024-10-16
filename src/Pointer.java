import java.awt.*;

public class Pointer {

    private int currentX;
    private int currentY;

    public Pointer() {
        currentX = Constants.AMOUNT_OF_BLOCKS_X /2 * Constants.ONE_BLOCK + Constants.GRID_OFFSET;
        currentY = Constants.AMOUNT_OF_BLOCKS_Y /2 * Constants.ONE_BLOCK + Constants.GRID_OFFSET;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(currentX, currentY, Constants.ONE_BLOCK, Constants.ONE_BLOCK);
    }
}
