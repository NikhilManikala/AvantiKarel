import javax.swing.*;
import java.awt.*;

public class KarelPanel extends JPanel {
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
        drawKarel(g, 1, 4, cellWidth, cellHeight);
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

    private void drawKarel(Graphics g, int x, int y, int cellWidth, int cellHeight){
        int x1 = (int) ((x+0.3)* cellWidth);
        int x2 = (int) ((x+0.8)* cellWidth);
        int x3 = (int) ((x+0.9)* cellWidth);
        int x4 = (int) ((x+0.9)* cellWidth);
        int x5 = (int) ((x+0.4)* cellWidth);
        int x6 = (int) ((x+0.3)* cellWidth);

        int y1 = (int) ((y)*cellHeight);
        int y2 = (int) ((y)*cellHeight);
        int y3 = (int) ((y+0.15)*cellHeight);
        int y4 = (int) ((y+0.7)*cellHeight);
        int y5 = (int) ((y+0.7)*cellHeight);
        int y6 = (int) ((y+0.55)*cellHeight);

        g.setColor(Color.black);
        g.drawPolygon(new int[]{x1, x2, x3, x4, x5, x6}, new int[]{y1, y2, y3, y4, y5, y6}, 6);

        int x7 = (int) ((x+0.45)*cellWidth);
        int x8 = (int) ((x+0.75)*cellWidth);
        int x9 = (int) ((x+0.75)*cellWidth);
        int x10 = (int) ((x+0.45)*cellWidth);

        int y7 = (int) ((y+0.1)*cellHeight);
        int y8 = (int) ((y+0.1)*cellHeight);
        int y9 = (int) ((y+0.5)*cellHeight);
        int y10 = (int) ((y+0.5)*cellHeight);

        g.drawPolygon(new int[]{x7, x8, x9, x10}, new int[]{y7, y8, y9, y10}, 4);

        int x11 = (int) ((x+0.6)*cellWidth);
        int x12 = (int) ((x+0.7)*cellWidth);
        int x13 = (int) ((x+0.7)*cellWidth);
        int x14 = (int) ((x+0.6)*cellWidth);

        int y11 = (int) ((y+0.6)*cellHeight);
        int y12 = (int) ((y+0.6)*cellHeight);
        int y13 = (int) ((y+0.62)*cellHeight);
        int y14 = (int) ((y+0.62)*cellHeight);

        g.fillPolygon(new int[]{x11, x12, x13, x14}, new int[]{y11, y12, y13, y14}, 4);

//        int x15 = (int) ((x+))
    }
}