public class KarelTest extends Karel{
    public static void main(String[] args) {
        new KarelTest();
    }

    public void run(){
        move();
        move();
        move();
        move();
        move();
        pickBeeper();
        move();
        move();
    }

    private void turnRight() {
        for (int i = 0; i < 3; i++) {
            this.turnLeft();
        }
    }
}
