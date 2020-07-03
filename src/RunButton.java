import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RunButton extends JButton {
    Karel k;
    static Constants constants = new Constants();
//    karelTest F = new karelTest();

    ActionListener runPress = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            k.speed = k.f.speedSlider.getValue();
            k.toDraw = new ArrayList<>();
            k.run();
            k.draw();
        }
    };


    public RunButton(Karel parameterKarel){
        k = parameterKarel;
        setBounds(2*constants.paddingX+constants.panelWidth, constants.paddingY, 95, 30);
        setText("Run");
        addActionListener(runPress);
    }
}
