import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public abstract class Karel {

    static Constants constants = new Constants();

    protected int speed = 250;
    private int firstError;

    //Karel Current Position Initialisation
    protected int techPosX;
    protected int techPosY;
    protected int techCurrentDirection;

    protected int graphPosX;
    protected int graphPosY;
    protected int graphCurrentDirection;

    protected ArrayList<String> toDraw = new ArrayList<>();

    protected KarelFrame f;

    protected int[][] techBeepers = new int[constants.rows][constants.columns];
    protected int[][] graphBeepers = new int[constants.rows][constants.columns];

    public Karel() {
        f = new KarelFrame(this);
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
        switch (graphCurrentDirection) {
            case 0 -> graphPosX++;
            case 1 -> graphPosY++;
            case 2 -> graphPosX--;
            case 3 -> graphPosY--;
        }
    }

    protected void move() {
        switch (techCurrentDirection) {
            case 0 -> techPosX++;
            case 1 -> techPosY++;
            case 2 -> techPosX--;
            case 3 -> techPosY--;
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
        return techBeepers[techPosY][techPosX] > 0;
    }

    protected void turnRight() {
        techCurrentDirection = (techCurrentDirection + 1)%4;
        toDraw.add("TurnRight");
    }

    protected abstract void run();

}