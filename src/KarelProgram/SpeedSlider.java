package KarelProgram;

import javax.swing.*;

public class SpeedSlider extends JSlider {
    Karel k;

    public SpeedSlider(Karel parameterKarel){
        k = parameterKarel;

        setMinimum(k.constants.minSpeed);
        setMaximum(k.constants.maxSpeed);
        setValue(k.constants.defaultSpeed);

        setBounds(2*k.constants.paddingX + k.constants.panelWidth,k.constants.paddingY+k.constants.cellHeight,
                195,30);
    }

}
