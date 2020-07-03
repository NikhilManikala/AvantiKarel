public class karelTest extends Karel{
    public static void run(){
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

    private static void turnLeft() {
        for (int i = 0; i < 3; i++) {
            turnRight();
        }
    }
}
