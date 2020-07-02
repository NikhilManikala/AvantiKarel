import javax.swing.*;
import java.util.Arrays;
import java.util.Random;

public class Karel {
    private static int rows=5;
    private static int columns=5;
    private static int[][] Beepers = new int[rows][columns];

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(Beepers));

        JFrame frame = new JFrame("Demo");
        frame.setSize(550, 250);
        frame.setVisible(true);

        int delay = 500;
        Timer timer = new Timer( delay, e -> {
            Render renderState = new Render();
            renderState.setBeepers(Beepers);
            frame.add(renderState);
            frame.setVisible(true);
            System.out.println("Hello World!");
            addRandomBeeper();
        }
        );

        timer.start();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static void addRandomBeeper() {
        int rnd1 = new Random().nextInt(Beepers.length);
        int rnd2 = new Random().nextInt(Beepers[rnd1].length);
        Beepers[rnd1][rnd2]++;
    }
}
