import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.swing.*;

public class Karel {
//    Graphical Constants
    private static final int paddingX = 40;
    private static final int paddingY = 40;

    private static final int cellWidth = 100;
    private static final int cellHeight = 100;

    private static int speed = 250;

//    Technical Constants
    private static final int rows = 5;
    private static final int columns = 5;
    private static final int minDelay = 0;
    private static final int maxDelay = 500;

    private static int firstError;

    //    Calculated Constants
    private static final int panelWidth = columns*cellWidth;
    private static final int panelHeight = rows*cellHeight;

    private static final int windowWidth = panelWidth + 10*paddingX;
    private static final int windowHeight = panelHeight + 3*paddingY;

    //Karel Starting Position
    private static final int startPosX = 0;
    private static final int startPosY = 0;
    private static final int startDirection = 0;

    //Karel Current Position Initialisation
    protected static int techPosX;
    protected static int techPosY;
    protected static int techCurrentDirection;

    protected static int graphPosX;
    protected static int graphPosY;
    protected static int graphCurrentDirection;

    protected static ArrayList<String> toDraw = new ArrayList<String>();

    private static KarelPanel panel;

    protected static int[][] techBeepers = new int[rows][columns];
    protected static int[][] graphBeepers = new int[rows][columns];

    private static void setupJFrame() {

        JFrame f= new JFrame("Karel R");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        panel= new KarelPanel(paddingX, paddingY, cellWidth, cellHeight, rows, columns);

        f.setBackground(Color.LIGHT_GRAY);

        panel.setBounds(paddingX, paddingY, panelWidth, panelHeight);
        panel.setBackground(Color.WHITE);

        f.add(panel);
        f.setSize(windowWidth, windowHeight);

        JSlider speedSlider = new JSlider(minDelay, maxDelay, speed);
        speedSlider.setBounds(2*paddingX + panelWidth,paddingY+cellHeight,195,30);

        Hashtable<Integer, JLabel> position = new Hashtable<Integer, JLabel>();
        position.put(0, new JLabel("0"));
        position.put(50, new JLabel("50"));
        position.put(100, new JLabel("100"));
        speedSlider.setLabelTable(position);

        f.add(speedSlider);

        JButton b=new JButton("Run");
        b.setBounds(2*paddingX + panelWidth,paddingY,95,30);
        b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                speed = speedSlider.getValue();
                toDraw = new ArrayList<String>();
                karelTest.run();
                draw(f);
            }
        });
        f.add(b);

        JButton c=new JButton("Reset");
        c.setBounds(2*paddingX + panelWidth, (int) (paddingY +(0.5*cellHeight)),95,30);
        c.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                techBeepers = new int[rows][columns];
                graphBeepers = new int[rows][columns];
                toDraw = new ArrayList<String>();
                techPosX=startPosX;
                techPosY=startPosY;
                techCurrentDirection=startDirection;

                graphPosX=startPosX;
                graphPosY=startPosY;
                graphCurrentDirection=startDirection;

                panel.repaint();
            }
        });
        f.add(c);


        f.setLayout(null);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        setupJFrame();
        techPosX = startPosX;
        techPosY = startPosY;
        techCurrentDirection = startDirection;
        graphPosX = startPosX;
        graphPosY = startPosY;
        graphCurrentDirection = startDirection;
    }

    public static void draw(Frame f) {
        Timer timer = new Timer(speed, new ActionListener() {
            private int counter;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (toDraw.get(counter).equals("Move")) {
                    drawMove();
                } else if (toDraw.get(counter).equals("TurnRight")){
                    drawTurnRight();
                } else if (toDraw.get(counter).equals("PutBeeper")){
                    drawPutBeeper();
                } else if (toDraw.get(counter).equals("PickBeeper")){
                    drawPickBeeper();
                } else if (toDraw.get(counter).equals("NoBeeperError")){
                    JOptionPane.showMessageDialog(f, "There is no Beeper Present Here");
                }
                panel.repaint();
                counter++;

                if (counter == toDraw.size() || counter == firstError) {
                    ((Timer)e.getSource()).stop();
                }
            }
        });
        timer.start();

    }

    private static void drawPutBeeper() {
        graphBeepers[graphPosX][graphPosY]++;
    }
    private static void drawPickBeeper() {
        graphBeepers[graphPosX][graphPosY]--;
    }

    private static void drawTurnRight() {
        graphCurrentDirection = (graphCurrentDirection + 1)%4;
    }

    private static void drawMove() {
        if (graphCurrentDirection == 0) {
            graphPosX++;
        } else if (graphCurrentDirection == 1) {
            graphPosY++;
        } else if(graphCurrentDirection == 2) {
            graphPosX--;
        } else if (graphCurrentDirection == 3) {
            graphPosY--;
        }
    }

    public static void move() {
        if (techCurrentDirection == 0) {
            techPosX++;
        } else if (techCurrentDirection == 1) {
            techPosY++;
        } else if(techCurrentDirection == 2) {
            techPosX--;
        } else if (techCurrentDirection == 3) {
            techPosY--;
        }
        toDraw.add("Move");
    }

    public static void putBeeper(){
        techBeepers[techPosY][techPosX]++;
        toDraw.add("PutBeeper");
    }

    public static void pickBeeper(){
        if (beepersPresent()){
            techBeepers[techPosY][techPosX]--;
            toDraw.add("PickBeeper");
        } else {
            toDraw.add("NoBeeperError");
            firstError = toDraw.size();
        }

    }

    public static boolean beepersPresent() {
        if (techBeepers[techPosX][techPosY]>0){
            return true;
        } else {
            return false;
        }
    }

    public static void turnRight() {
        techCurrentDirection = (techCurrentDirection + 1)%4;
        toDraw.add("TurnRight");
//        System.out.println(toDraw);
    }

}