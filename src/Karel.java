import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class Karel {

    static Constants constants = new Constants();

    protected int speed = 250;
    private int firstError;

    //Calculated Constants
//    protected final int panelWidth = columns*cellWidth;
//    protected final int panelHeight = rows*cellHeight;
//
//    protected final int windowWidth = panelWidth + 10*paddingX;
//    protected final int windowHeight = panelHeight + 3*paddingY;

    //Karel Current Position Initialisation
    protected static int techPosX;
    protected static int techPosY;
    protected static int techCurrentDirection;

    protected static int graphPosX;
    protected static int graphPosY;
    protected static int graphCurrentDirection;

    protected ArrayList<String> toDraw = new ArrayList<>();

    private static KarelFrame f;

    protected int[][] techBeepers = new int[constants.rows][constants.columns];
    protected int[][] graphBeepers = new int[constants.rows][constants.columns];


    public static void main(String[] args) {
        f = new KarelFrame();
        techPosX = constants.startPosX;
        techPosY = constants.startPosY;
        techCurrentDirection = constants.startDirection;
        graphPosX = constants.startPosX;
        graphPosY = constants.startPosY;
        graphCurrentDirection = constants.startDirection;
    }

    protected void draw() {
        Timer timer = new Timer(speed, new ActionListener() {
            private int counter;

            @Override
            public void actionPerformed(ActionEvent e) {
                switch (toDraw.get(counter)) {
                    case "Move" -> drawMove();
                    case "TurnRight" -> drawTurnRight();
                    case "PutBeeper" -> drawPutBeeper();
                    case "PickBeeper" -> drawPickBeeper();
                    case "NoBeeperError" -> JOptionPane.showMessageDialog(f, "There is no Beeper Present Here");
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

    private void drawPutBeeper() {
        graphBeepers[graphPosX][graphPosY]++;
    }
    private void drawPickBeeper() {
        graphBeepers[graphPosX][graphPosY]--;
    }

    private void drawTurnRight() {
        graphCurrentDirection = (graphCurrentDirection + 1)%4;
    }

    private void drawMove() {
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

    protected void move() {
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

    protected void putBeeper(){
        techBeepers[techPosY][techPosX]++;
        toDraw.add("PutBeeper");
    }

    protected void pickBeeper(){
        if (beepersPresent()){
            techBeepers[techPosY][techPosX]--;
            toDraw.add("PickBeeper");
        } else {
            toDraw.add("NoBeeperError");
            firstError = toDraw.size();
        }

    }

    protected boolean beepersPresent() {
        if (techBeepers[techPosY][techPosX]>0){
            return true;
        } else {
            return false;
        }
    }

    protected void turnRight() {
        techCurrentDirection = (techCurrentDirection + 1)%4;
        toDraw.add("TurnRight");
    }

}