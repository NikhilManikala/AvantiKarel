import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

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
        drawKarel(g, 0, 4, 0, cellWidth, cellHeight);
        drawKarel(g, 1, 4, 1, cellWidth, cellHeight);
        drawKarel(g, 2, 4, 2, cellWidth, cellHeight);
        drawKarel(g, 3, 4, 3, cellWidth, cellHeight);
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

    private void drawKarel(Graphics g, int x, int y, int dir, int cellWidth, int cellHeight){
        double[] xShiftList = {0.3,  0.8,  0.9,  0.9, 0.4, 0.3,  0.45, 0.75, 0.75, 0.45, 0.6, 0.75, 0.75, 0.6,  0.6, 0.7, 0.7, 0.8, 0.8, 0.6, 0,   0.3, 0.3, 0.1, 0.1, 0  };
        double[] yShiftList = {0.01, 0.01, 0.15, 0.7, 0.7, 0.55, 0.1,  0.1,  0.5,  0.5,  0.6, 0.6,  0.62, 0.62, 0.7, 0.7, 0.9, 0.9, 1,   1,   0.4, 0.4, 0.5, 0.5, 0.6, 0.7};

        for (int id = 0; id < xShiftList.length; id++) {
            double workingX=xShiftList[id];
            double workingY=yShiftList[id];
            for (int i = 0; i < dir; i++) {
                double tempX = workingX;
                double tempY = workingY;
                workingX = (-1)*tempY;
                workingY = tempX;
            }
            if (dir == 0){

            } else if (dir == 1) {
                workingX ++;
            } else if (dir == 2) {
                workingX ++;
                workingY ++;
            } else if (dir == 3) {
                workingY ++;
            }
            xShiftList[id] = workingX;
            yShiftList[id] = workingY;
        }
        System.out.println(Arrays.toString(xShiftList));
        System.out.println(Arrays.toString(yShiftList));

        int[] xCoordList = new int[xShiftList.length];
        int[] yCoordList = new int[yShiftList.length];

        for (int id = 0; id < xShiftList.length; id++) {
            xCoordList[id] = (int) ((x + xShiftList[id])*cellWidth);
            yCoordList[id] = (int) ((y + yShiftList[id])*cellHeight);
        }

        System.out.println(Arrays.toString(xCoordList));
        System.out.println(Arrays.toString(yCoordList));

        g.setColor(Color.black);
        g.drawPolygon(splice(xCoordList,0, 5), splice(yCoordList, 0, 5), 6);
        g.drawPolygon(splice(xCoordList,6, 9), splice(yCoordList, 6, 9), 4);
        g.fillPolygon(splice(xCoordList,10, 13), splice(yCoordList, 10, 13), 4);
        g.fillPolygon(splice(xCoordList,14, 19), splice(yCoordList, 14, 19), 6);
        g.fillPolygon(splice(xCoordList,20, 25), splice(yCoordList, 20, 25), 6);
    }

    private static int[] splice(int[] list, int start, int end){
        int[] toReturn = new int[end-start+1];
        for (int i = 0; i < (end-start+1); i++) {
            toReturn[i]=list[i+start];
        }
        System.out.println(Arrays.toString(toReturn));
        return toReturn;
    }
}