import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RunButton extends JButton {
    ActionListener runPress = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Karel.speed = KarelFrame.speedSlider.getValue();
            Karel.toDraw = new ArrayList<String>();
            karelTest.run();
            Karel.draw();
        }
    };

    public RunButton(){
        setBounds(2*Karel.paddingX+Karel.panelWidth, Karel.paddingY, 95, 30);
        setText("Run");
        addActionListener(runPress);
    }
}
