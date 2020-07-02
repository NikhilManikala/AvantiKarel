import java.awt.*;
import javax.swing.*;
public class Karel {
//    Graphical Constants
    private static final int paddingX = 40;
    private static final int paddingY = 40;

    private static final int cellWidth = 100;
    private static final int cellHeight = 100;


//    Technical Constants
    private static final int rows = 5;
    private static final int columns = 5;


//    Calculated Constants
    private static final int panelWidth = columns*cellWidth;
    private static final int panelHeight = rows*cellHeight;

    private static final int windowWidth = panelWidth + 10*paddingX;
    private static final int windowHeigth = panelHeight + 3*paddingY;

    public Karel() {
        setupJFrame();
    }

    private void setupJFrame() {

        JFrame f= new JFrame("Karel R");
        KarelPanel panel= new KarelPanel(paddingX, paddingY, cellWidth, cellHeight, rows, columns);

        f.setBackground(Color.LIGHT_GRAY);

        panel.setBounds(paddingX, paddingY, panelWidth, panelHeight);
        panel.setBackground(Color.WHITE);

        f.add(panel);
        f.setSize(windowWidth, windowHeigth);


        f.setLayout(null);
        f.setVisible(true);
    }

    public static void main(String args[])
    {
        new Karel();
    }
}