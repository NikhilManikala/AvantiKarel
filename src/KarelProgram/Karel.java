package KarelProgram;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;

public abstract class Karel {

    protected Constants constants;

    protected int speed = 250;

    //Karel Current Position Initialisation
    protected int techPosX;
    protected int techPosY;
    protected int techCurrentDirection;

    protected int graphPosX;
    protected int graphPosY;
    protected int graphCurrentDirection;

    protected ArrayList<String> toDraw = new ArrayList<>();

    protected KarelFrame f;

    protected int[][] techBeepers;

    protected int[][] graphBeepers;
    protected static String world = "DefaultWorld";

    public Karel() {
        constants = new Constants(world);
        techBeepers = new int[constants.rows][constants.columns];

        graphBeepers = new int[constants.rows][constants.columns];


        f = new KarelFrame(this);
        techBeepers = constants.addBeepers();
        graphBeepers = constants.addBeepers();
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
                if (toDraw.size()>0){
                    switch (toDraw.get(counter)) {
                        case "Move" -> drawMove();
                        case "TurnLeft" -> drawTurnLeft();
                        case "PutBeeper" -> drawPutBeeper();
                        case "PickBeeper" -> drawPickBeeper();
                        case "NoBeeperError" -> {
                            JOptionPane.showMessageDialog(f, "There is no Beeper Present Here");
                            ((Timer)e.getSource()).stop();
                        }
                        case "BlockedError" -> {
                            JOptionPane.showMessageDialog(f, "Ouch! You have hit a wall!");
                            ((Timer)e.getSource()).stop();
                        }
                    }
                    f.panel.repaint();
                    counter++;
                    if (counter == toDraw.size()) {
                        ((Timer)e.getSource()).stop();
                    }
                }
            }
        });
        timer.start();

    }

    private void drawPutBeeper() {
        System.out.println("drawing");
        System.out.println(Arrays.deepToString(graphBeepers));
        graphBeepers[graphPosY][graphPosX]++;
        System.out.println(Arrays.deepToString(graphBeepers));
    }
    private void drawPickBeeper() {
        graphBeepers[graphPosY][graphPosX]--;
    }

    private void drawTurnLeft() {
        graphCurrentDirection = (graphCurrentDirection + 1)%4;
    }

    private void drawMove() {
        switch (graphCurrentDirection) {
            case 0 -> graphPosX++;
            case 1 -> graphPosY--;
            case 2 -> graphPosX--;
            case 3 -> graphPosY++;
        }
    }

    protected void move() {
        if (frontIsClear()){
            switch (techCurrentDirection) {
                case 0 -> techPosX++;
                case 1 -> techPosY--;
                case 2 -> techPosX--;
                case 3 -> techPosY++;
            }
            toDraw.add("Move");
        } else {
            toDraw.add("BlockedError");
        }
    }

    protected void putBeeper(){
        System.out.println(techPosY);
        System.out.println(techPosX);
        System.out.println("running put beeper");
        techBeepers[techPosY][techPosX]++;
        System.out.println(Arrays.deepToString(techBeepers));
        toDraw.add("PutBeeper");
    }

    protected void pickBeeper(){
        if (beepersPresent()){
            techBeepers[techPosY][techPosX]--;
            toDraw.add("PickBeeper");
        } else {
            toDraw.add("NoBeeperError");
        }

    }

    protected boolean beepersPresent() {
        return techBeepers[techPosY][techPosX] > 0;
    }

    protected boolean noBeepersPresent() {
        return !beepersPresent();
    }

    protected boolean facingEast() {
        return techCurrentDirection == 0;
    }

    protected boolean facingNorth() {
        return techCurrentDirection == 1;
    }

    protected boolean facingWest() {
        return techCurrentDirection == 2;
    }

    protected boolean facingSouth() {
        return techCurrentDirection == 3;
    }

    protected boolean notFacingEast() {
        return !facingEast();
    }

    protected boolean notFacingNorth() {
        return !facingNorth();
    }

    protected boolean notFacingWest() {
        return !facingWest();
    }

    protected boolean notFacingSouth() {
        return !facingSouth();
    }

    protected boolean beepersInBag() {
        return true;
    }

    protected boolean noBeepersInBag() {
        return !beepersInBag();
    }

    protected boolean frontIsBlocked(){
        return switch (techCurrentDirection){
            case 0 -> eastIsBlocked();
            case 1 -> northIsBlocked();
            case 2 -> westIsBlocked();
            case 3 -> southIsBlocked();
            default -> throw new IllegalStateException("Unexpected value: " + techCurrentDirection);
        };
    }

    protected boolean leftIsBlocked(){
        return switch (techCurrentDirection){
            case 0 -> northIsBlocked();
            case 1 -> westIsBlocked();
            case 2 -> southIsBlocked();
            case 3 -> eastIsBlocked();
            default -> throw new IllegalStateException("Unexpected value: " + techCurrentDirection);
        };
    }

    protected boolean rightIsBlocked(){
        return switch (techCurrentDirection){
            case 0 -> southIsBlocked();
            case 1 -> eastIsBlocked();
            case 2 -> northIsBlocked();
            case 3 -> westIsBlocked();
            default -> throw new IllegalStateException("Unexpected value: " + techCurrentDirection);
        };
    }

    private boolean eastIsBlocked(){
        return constants.verticalWalls[techPosY][techPosX+1];
    }
    private boolean northIsBlocked(){
        return constants.horizontalWalls[techPosY][techPosX];
    }
    private boolean westIsBlocked(){
        return constants.verticalWalls[techPosY][techPosX];
    }
    private boolean southIsBlocked(){
        return constants.horizontalWalls[techPosY+1][techPosX];
    }
    private boolean eastIsClear(){
        return !eastIsBlocked();
    }
    private boolean northIsClear(){
        return !northIsBlocked();
    }
    private boolean westIsClear(){
        return !westIsBlocked();
    }
    private boolean southIsClear(){
        return !southIsBlocked();
    }

    protected boolean frontIsClear(){
        return !frontIsBlocked();
    }

    protected boolean leftIsClear(){
        return !leftIsBlocked();
    }

    protected boolean rightIsClear(){
        return !rightIsBlocked();
    }

    protected void turnLeft() {
        techCurrentDirection = (techCurrentDirection + 1)%4;
        toDraw.add("TurnLeft");
    }

    protected abstract void run();

}