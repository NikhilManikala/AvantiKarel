import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RunButton extends JButton {
    Karel k;
    static Constants constants = new Constants();

    ActionListener runPress = e -> {
        k.speed = k.f.speedSlider.getValue();
        k.toDraw = new ArrayList<>();
        karelTest.run();
        k.draw();
    };

    public RunButton(Karel parameterKarel){
        k = parameterKarel;
        setBounds(2*constants.paddingX+constants.panelWidth, constants.paddingY, 95, 30);
        setText("Run");
        addActionListener(runPress);
    }
}
