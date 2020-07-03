import javax.swing.*;

public class SpeedSlider extends JSlider {
    Karel k;
    static Constants constants = new Constants();

    public SpeedSlider(Karel parameterKarel){
        k = parameterKarel;

        setMinimum(constants.minSpeed);
        setMaximum(constants.maxSpeed);
        setValue(constants.defaultSpeed);

        setBounds(2*constants.paddingX + constants.panelWidth,constants.paddingY+constants.cellHeight,
                195,30);
    }

}
