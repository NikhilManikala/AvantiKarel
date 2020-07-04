public class KarelTest extends Karel{
    public static void main(String[] args) {
        new KarelTest();
    }

    public void run(){
        turnLeft();
    }

    private void turnRight() {
        for (int i = 0; i < 3; i++) {
            this.turnLeft();
        }
    }
}
