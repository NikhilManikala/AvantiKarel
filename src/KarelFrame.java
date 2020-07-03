import javax.swing.*;
import java.awt.*;

public class KarelFrame extends JFrame {
    static Constants constants = new Constants();
    Karel k;

    protected KarelPanel panel;

    protected final RunButton run = new RunButton();
    protected final ResetButton reset = new ResetButton(k);
    protected final SpeedSlider speedSlider = new SpeedSlider();

    public KarelFrame(Karel parameterKarel) {
        k = parameterKarel;
        panel = new KarelPanel(k);

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
        setSize(constants.panelWidth + constants.paddingMultiplierX * constants.paddingX,
                constants.panelHeight + constants.paddingMultiplierY * constants.paddingY);
        setLayout(null);
        setVisible(true);
    }

}
