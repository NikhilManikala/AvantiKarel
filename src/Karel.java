import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.swing.*;

public class Karel {
//    Graphical Constants
    protected  static final int paddingX = 40;
    protected  static final int paddingY = 40;

    protected  static final int cellWidth = 100;
    protected  static final int cellHeight = 100;

    protected static int speed = 250;

//    Technical Constants
    protected  static final int rows = 5;
    protected  static final int columns = 5;
    protected static final int minDelay = 0;
    protected static final int maxDelay = 500;

    private static int firstError;

    //    Calculated Constants
    protected static final int panelWidth = columns*cellWidth;
    protected static final int panelHeight = rows*cellHeight;

    private static final int windowWidth = panelWidth + 10*paddingX;
    private static final int windowHeight = panelHeight + 3*paddingY;

    //Karel Starting Position
    protected static final int startPosX = 0;
    protected static final int startPosY = 0;
    protected static final int startDirection = 0;

    //Karel Current Position Initialisation
    protected static int techPosX;
    protected static int techPosY;
    protected static int techCurrentDirection;

    protected static int graphPosX;
    protected static int graphPosY;
    protected static int graphCurrentDirection;

    protected static ArrayList<String> toDraw = new ArrayList<String>();

    private static KarelPanel panel;
    private static KarelFrame f;

    protected static int[][] techBeepers = new int[rows][columns];
    protected static int[][] graphBeepers = new int[rows][columns];

    private static void setupJFrame() {

        KarelFrame f = new KarelFrame();

        f.setSize(windowWidth, windowHeight);

        JSlider speedSlider = new JSlider(minDelay, maxDelay, speed);

        f.setLayout(null);
        f.setVisible(true);
    }



    public static void main(String[] args) {
        setupJFrame();
        techPosX = startPosX;
        techPosY = startPosY;
        techCurrentDirection = startDirection;
        graphPosX = startPosX;
        graphPosY = startPosY;
        graphCurrentDirection = startDirection;
    }

    public static void draw() {
        Timer timer = new Timer(speed, new ActionListener() {
            private int counter;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (toDraw.get(counter).equals("Move")) {
                    drawMove();
                } else if (toDraw.get(counter).equals("TurnRight")){
                    drawTurnRight();
                } else if (toDraw.get(counter).equals("PutBeeper")){
                    drawPutBeeper();
                } else if (toDraw.get(counter).equals("PickBeeper")){
                    drawPickBeeper();
                } else if (toDraw.get(counter).equals("NoBeeperError")){
                    JOptionPane.showMessageDialog(f, "There is no Beeper Present Here");
                }
                f.panel.repaint();
                counter++;

                if (counter == toDraw.size() || counter == firstError) {
                    ((Timer)e.getSource()).stop();
                }
            }
        });
        timer.start();

    }

    private static void drawPutBeeper() {
        graphBeepers[graphPosX][graphPosY]++;
    }
    private static void drawPickBeeper() {
        graphBeepers[graphPosX][graphPosY]--;
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

    public static void putBeeper(){
        techBeepers[techPosY][techPosX]++;
        toDraw.add("PutBeeper");
    }

    public static void pickBeeper(){
        if (beepersPresent()){
            techBeepers[techPosY][techPosX]--;
            toDraw.add("PickBeeper");
        } else {
            toDraw.add("NoBeeperError");
            firstError = toDraw.size();
        }

    }

    public static boolean beepersPresent() {
        if (techBeepers[techPosX][techPosY]>0){
            return true;
        } else {
            return false;
        }
    }

    public static void turnRight() {
        techCurrentDirection = (techCurrentDirection + 1)%4;
        toDraw.add("TurnRight");
//        System.out.println(toDraw);
    }

}