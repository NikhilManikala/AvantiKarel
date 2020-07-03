import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class Karel {
//    Graphical Constants
    private static final int paddingX = 40;
    private static final int paddingY = 40;

    private static final int cellWidth = 100;
    private static final int cellHeight = 100;

    private static final int speed = 500;

//    Technical Constants
    private static final int rows = 5;
    private static final int columns = 5;
    private static final int delay = 500;


//    Calculated Constants
    private static final int panelWidth = columns*cellWidth;
    private static final int panelHeight = rows*cellHeight;

    private static final int windowWidth = panelWidth + 10*paddingX;
    private static final int windowHeight = panelHeight + 3*paddingY;

    //Karel Starting Position
    private static final int startPosX = 0;
    private static final int startPosY = 0;
    private static final int startDirection = 0;

    //Karel Current Position Initialisation
    protected static int techPosX;
    protected static int techPosY;
    protected static int techCurrentDirection;

    protected static int graphPosX;
    protected static int graphPosY;
    protected static int graphCurrentDirection;

    protected static ArrayList<String> toDraw = new ArrayList<String>();

    private static KarelPanel panel;

    public Karel() {
        setupJFrame();
        techPosX = startPosX;
        techPosY = startPosY;
        techCurrentDirection = startDirection;
        graphPosX = startPosX;
        graphPosY = startPosY;
        graphCurrentDirection = startDirection;
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
        move();
        move();
        move();
        turnRight();
        run();
    }

    public static void run() {
        Timer timer = new Timer(speed, new ActionListener() {
            private int counter;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (toDraw.get(counter).equals("Move")) {
                    drawMove();
                } else if (toDraw.get(counter).equals("TurnRight")){
                    drawTurnRight();
                }
                panel.repaint();
                counter++;

                if (counter == toDraw.size()) {
                    ((Timer)e.getSource()).stop();
                }
            }
        });
        timer.start();

    }

    private static void drawTurnRight() {
        graphCurrentDirection = (graphCurrentDirection + 1)%4;
    }

    private static void drawMove() {
        if (graphCurrentDirection == 0) {
            graphPosX++;
        } else if (graphCurrentDirection == 1) {
            graphPosY++;
        } else if(graphCurrentDirection == 2) {
            graphPosX--;
        } else if (graphCurrentDirection == 3) {
            graphPosY--;
        }
    }

    public static void move() {
//        System.out.println("Move");
        if (techCurrentDirection == 0) {
            techPosX++;
        } else if (techCurrentDirection == 1) {
            techPosY++;
        } else if(techCurrentDirection == 2) {
            techPosX--;
        } else if (techCurrentDirection == 3) {
            techPosY--;
        }
        toDraw.add("Move");
    }

    public static void turnRight() {
        techCurrentDirection = (techCurrentDirection + 1)%4;
        toDraw.add("TurnRight");
//        System.out.println(toDraw);
    }

}