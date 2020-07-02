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
    private static final int windowHeight = panelHeight + 3*paddingY;

    //Karel Starting Position
    private static final int startPosX = 0;
    private static final int startPosY = 4;
    private static final int startDirection = 0;

    //Karel Current Position Initialisation
    protected static int posX;
    protected static int posY;
    protected static int currentDirection;

    public Karel() {
        setupJFrame();
        posX = startPosX;
        posY = startPosY;
        currentDirection = startDirection;
    }

    private void setupJFrame() {

        JFrame f= new JFrame("Karel R");
        KarelPanel panel= new KarelPanel(paddingX, paddingY, cellWidth, cellHeight, rows, columns);

        f.setBackground(Color.LIGHT_GRAY);

        panel.setBounds(paddingX, paddingY, panelWidth, panelHeight);
        panel.setBackground(Color.WHITE);

        f.add(panel);
        f.setSize(windowWidth, windowHeight);


        f.setLayout(null);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        new Karel();
    }

    public static void move() {
        if (currentDirection == 0) {
            posX++;
        }
    }
}