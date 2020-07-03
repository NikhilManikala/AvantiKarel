public class KarelTest extends Karel{
    public static void main(String[] args) {
        new KarelTest();
    }

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
