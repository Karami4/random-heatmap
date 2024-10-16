public class Constants {
    // Class for storing constants
    public static final int TIMER_DELAY=80;
    public static final int WINDOW_WIDTH = 820;
    public static final int WINDOW_HEIGHT = 820;
    public static final int ONE_BLOCK=40;
    public static final int GRID_OFFSET = 10;
    public static final int CHARACTER_MAX_Y = (Constants.WINDOW_HEIGHT - Constants.GRID_OFFSET) - Constants.ONE_BLOCK; //810 - 40
    public static final int CHARACTER_MAX_X = (Constants.WINDOW_WIDTH - Constants.GRID_OFFSET) - Constants.ONE_BLOCK; //810 - 40
    public static final int AMOUNT_OF_BLOCKS_X = (Constants.WINDOW_WIDTH - (GRID_OFFSET * 2)) / Constants.ONE_BLOCK;
    public static final int AMOUNT_OF_BLOCKS_Y = (Constants.WINDOW_HEIGHT - (GRID_OFFSET * 2)) / Constants.ONE_BLOCK;
}
