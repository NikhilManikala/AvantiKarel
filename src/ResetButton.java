import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ResetButton extends JButton {
    Karel k;
    static Constants constants = new Constants();

    ActionListener runPress = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            k.techBeepers = constants.initialBeepers;
            k.graphBeepers = constants.initialBeepers;
            k.toDraw = new ArrayList<>();
            k.techPosX = constants.startPosX;
            k.techPosY = constants.startPosY;
            k.techCurrentDirection = constants.startDirection;

            k.graphPosX = constants.startPosX;
            k.graphPosY = constants.startPosY;
            k.graphCurrentDirection = constants.startDirection;

            k.f.panel.repaint();

        }
    };

    public ResetButton(Karel parameterKarel){
        k = parameterKarel;
        setBounds(2*constants.paddingX+constants.panelWidth, (int) (constants.paddingY + (0.5*constants.cellHeight)),
                95, 30);
        setText("Reset");
        addActionListener(runPress);
    }
}
