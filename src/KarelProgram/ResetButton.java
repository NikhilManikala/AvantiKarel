package KarelProgram;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ResetButton extends JButton {
    Karel k;

    ActionListener runPress = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Karel is resetting...");

            k.techBeepers = new int[k.constants.rows][k.constants.columns];
            k.graphBeepers = new int[k.constants.rows][k.constants.columns];

            k.techBeepers = k.constants.addBeepers();
            k.graphBeepers = k.constants.addBeepers();

            k.toDraw = new ArrayList<>();
            k.techPosX = k.constants.startPosX;
            k.techPosY = k.constants.startPosY;
            k.techCurrentDirection = k.constants.startDirection;

            k.graphPosX = k.constants.startPosX;
            k.graphPosY = k.constants.startPosY;
            k.graphCurrentDirection = k.constants.startDirection;

            k.f.panel.repaint();

            System.out.println("Karel has reset.");
        }
    };

    public ResetButton(Karel parameterKarel){
        k = parameterKarel;
        setBounds(2*k.constants.paddingX+k.constants.panelWidth, (int) (k.constants.paddingY + (0.5*k.constants.cellHeight)),
                95, 30);
        setText("Reset");
        addActionListener(runPress);
    }
}
