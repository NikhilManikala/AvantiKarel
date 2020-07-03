import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ResetButton extends JButton {
    ActionListener runPress = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Karel.techBeepers = new int[Karel.rows][Karel.columns];
            Karel.graphBeepers = new int[Karel.rows][Karel.columns];
            Karel.toDraw = new ArrayList<String>();
            Karel.techPosX = Karel.startPosX;
            Karel.techPosY = Karel.startPosY;
            Karel.techCurrentDirection = Karel.startDirection;

            Karel.graphPosX = Karel.startPosX;
            Karel.graphPosY = Karel.startPosY;
            Karel.graphCurrentDirection = Karel.startDirection;

            KarelFrame.panel.repaint();

        }
    };

    public ResetButton(){
        setBounds(2*Karel.paddingX+Karel.panelWidth, (int) (Karel.paddingY + (0.5*Karel.cellHeight)), 95, 30);
        setText("Reset");
        addActionListener(runPress);
    }
}
