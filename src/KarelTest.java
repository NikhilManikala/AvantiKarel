public class KarelTest extends Karel{
    public static void main(String[] args) {
        new KarelTest();
    }

    public void run(){
        moveToNewspaper();
        pickupNewspaper();
        returnHome();
    }

    /**
     * Moves Karel to the newspaper
     * Move forward until he reaches the door
     * Turn right move forward one space
     * Turn left to face doorway
     *
     */
    private void moveToNewspaper(){
        while (frontIsClear()){
            move();
        }
        turnRight();
        move();
        turnLeft();
    }

    /**
     * Moves Karel forward to pickup the beeper
     */
    private void pickupNewspaper(){
        move();
        pickBeeper();
    }

    /**
     * Turns Karel back around
     * Moves Karel into the doorway
     * Turns Karel to the right to face the wall
     * Moves Karel until he hits the wall
     * Turns left
     * Moves Karel until he reaches his starting point
     * Turns Karel around to face his starting location
     */
    private void returnHome(){
        turnAround();
        move();
        turnRight();
        while(frontIsClear()){
            move();
        }
        turnLeft();
        while(frontIsClear()){
            move();
        }
        turnAround();
    }

    private void turnRight(){
        for (int i = 0; i < 3; i++) {
            turnLeft();
        }
    }
    private void turnAround(){
        for (int i = 0; i < 2; i++) {
            turnLeft();

        }
    }

}
