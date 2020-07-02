import java.awt.*;
import javax.swing.*;
public class Karel {
//    Graphical Constants
    private static final int paddingX = 40;
    private static final int paddingY = 40;

    private static final int cellWidth = 100;
    private static final int cellHeight = 100;


//    Technical Constants
    private static final int rows = 5;
    private static final int columns = 5;


//    Calculated Constants
    private static final int panelWidth = columns*cellWidth;
    private static final int panelHeight = rows*cellHeight;

    private static final int windowWidth = panelWidth + 10*paddingX;
    private static final int windowHeigth = panelHeight + 3*paddingY;

    public Karel() {
        setupJFrame();
    }

    private void setupJFrame() {

        JFrame f= new JFrame("Karel R");
        KarelPanel panel= new KarelPanel(paddingX, paddingY, cellWidth, cellHeight, rows, columns);

        f.setBackground(Color.LIGHT_GRAY);

        panel.setBounds(paddingX, paddingY, panelWidth, panelHeight);
        panel.setBackground(Color.WHITE);

        f.add(panel);
        f.setSize(windowWidth, windowHeigth);


        f.setLayout(null);
        f.setVisible(true);
    }

    public static void main(String args[])
    {
        new Karel();
    }
}

class KarelPanel extends JPanel{
    int paddingX;
    int paddingY;
    int cellWidth;
    int cellHeight;
    int rows;
    int columns;

    public KarelPanel(int paraPaddingX, int paraPaddingY, int paraCellWidth, int paraCellHeight, int paraRows, int paraCols){
        paddingX = paraPaddingX;
        paddingY = paraPaddingY;
        cellWidth = paraCellWidth;
        cellHeight = paraCellHeight;
        rows = paraRows;
        columns = paraCols;

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawGrid(g);
        drawBeeper(g, 2, 3,cellWidth, cellHeight, 3);
    }

    private void drawGrid(Graphics g) {
        int rad = 2;
        for (int row = 0; row < rows; row++) {
            int centerX = (int) ((row+0.5)*cellWidth);
            for (int col = 0; col < columns; col++) {
                int centerY = (int) ((col+0.5)*cellHeight);
                g.fillOval(centerX-rad, centerY-rad, 2*rad, 2*rad);
            }
        }
    }

    private void drawBeeper(Graphics g, int x, int y, int cellWidth, int cellHeight, int number){
        int x1 = (int) ((x + 0.5) * cellWidth);
        int x2 = ((x + 1) * cellWidth);
        int x3 = (int) ((x + 0.5) * cellWidth);
        int x4 = ((x) * cellWidth);
        int y1 = ((y) * cellHeight);
        int y2 = (int) ((y + 0.5) * cellHeight);
        int y3 = (int) ((y + 1) * cellHeight);
        int y4 = (int) ((y + 0.5) * cellHeight);

        g.setColor(Color.lightGray);
        g.fillPolygon(new int[]{x1, x2, x3, x4}, new int[]{y1, y2, y3, y4}, 4);

        g.setColor(Color.black);
        g.drawString(Integer.toString(number), x1, y2);
    }
}