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
    private static final int startPosY = 0;
    private static final int startDirection = 1;

    //Karel Current Position Initialisation
    protected static int posX;
    protected static int posY;
    protected static int currentDirection;

    private static KarelPanel panel;

    public Karel() {
        setupJFrame();
        posX = startPosX;
        posY = startPosY;
        currentDirection = startDirection;
    }

    private void setupJFrame() {

        JFrame f= new JFrame("Karel R");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        panel= new KarelPanel(paddingX, paddingY, cellWidth, cellHeight, rows, columns);

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
//        move();
    }

    public static void move() {
        int delay = 500;
        Timer timer = new Timer(delay, e -> {
            if (currentDirection == 0) {
                posX++;
            } else if (currentDirection == 1) {
                posY++;
            } else if(currentDirection == 2) {
                posX--;
            } else if (currentDirection == 3) {
                posY--;
            }
            panel.repaint();
        });
        timer.setRepeats(false);
        timer.start();

    }
}