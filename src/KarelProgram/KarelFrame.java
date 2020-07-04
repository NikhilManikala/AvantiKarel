package KarelProgram;

import javax.swing.*;
import java.awt.*;

public class KarelFrame extends JFrame {
    static Constants constants = new Constants();
    Karel k;

    protected KarelPanel panel;

    protected final SpeedSlider speedSlider;
    protected final RunButton run;
    protected final ResetButton reset;

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
        speedSlider = new SpeedSlider(k);
        run = new RunButton(k);
        reset = new ResetButton(k);
        add(speedSlider);
        add(run);
        add(reset);

//      Setup
        setSize(constants.panelWidth + constants.paddingMultiplierX * constants.paddingX,
                constants.panelHeight + constants.paddingMultiplierY * constants.paddingY);
        setLayout(null);
        setVisible(true);
    }

}
