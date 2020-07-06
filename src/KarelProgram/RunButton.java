package KarelProgram;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RunButton extends JButton {
    Karel k;

    ActionListener runPress = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            k.speed = k.f.speedSlider.getValue();
            k.toDraw = new ArrayList<>();
            System.out.println("Karel is processing your instructions...");
            k.run();
            System.out.println("Karel is executing your instructions...");
            k.draw();
            System.out.println("Karel has executed all of your instructions.");
        }
    };


    public RunButton(Karel parameterKarel){
        k = parameterKarel;
        setBounds(2*k.constants.paddingX+k.constants.panelWidth, k.constants.paddingY, 95, 30);
        setText("Run");
        addActionListener(runPress);
    }
}
