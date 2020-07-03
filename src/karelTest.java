public class karelTest extends Karel{
    public static void run(){
        move();
        turnRight();
        move();
        turnLeft();
    }

    private static void turnLeft() {
        for (int i = 0; i < 3; i++) {
            turnRight();
        }
    }
}
