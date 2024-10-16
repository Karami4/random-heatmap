import javax.swing.*;

public class HeatMapFrame extends JFrame {
    public HeatMapFrame() {
        HeatMapPanel panel = new HeatMapPanel();
        this.add(panel);
        this.setTitle("HeatMap");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack(); // this will take the jframe and fit it snugly around all of the components
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
