import javax.swing.*;
import java.awt.*;

public class KarelFrame extends JFrame {
    protected static KarelPanel panel = new KarelPanel();

    protected static final RunButton run = new RunButton();
    protected static final ResetButton reset = new ResetButton();
    protected static final SpeedSlider speedSlider = new SpeedSlider();

    public KarelFrame() {

//      Setup Technical Details
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

//      Setup Graphical Data
        setBackground(Color.lightGray);

//      Add Karel Panel to Window
        add(panel);

//      Add Buttons and Sliders

        add(run);
        add(reset);
        add(speedSlider);

//      Setup
        setSize(Karel.windowWidth, Karel.windowHeight);
        setLayout(null);
        setVisible(true);
    }

}
