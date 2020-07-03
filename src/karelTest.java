public class karelTest extends Karel{
    public void run(){
        putBeeper();
        move();
        turnRight();
        move();
        turnLeft();
        putBeeper();
        putBeeper();
        pickBeeper();
        move();
    }

    private void turnLeft() {
        for (int i = 0; i < 3; i++) {
            turnRight();
        }
    }
}
