import javax.swing.*;

public class SpeedSlider extends JSlider {
    public SpeedSlider(){
        setMinimum(Karel.minDelay);
        setMaximum(Karel.maxDelay);
        setValue(Karel.speed);

        setBounds(2*Karel.paddingX + Karel.panelWidth,Karel.paddingY+Karel.cellHeight,195,30);
    }

}
