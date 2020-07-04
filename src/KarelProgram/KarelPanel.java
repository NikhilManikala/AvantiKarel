package KarelProgram;

import javax.swing.*;
import java.awt.*;

public class KarelPanel extends JPanel {

    static Constants constants = new Constants();
    Karel k;

    public KarelPanel(Karel parameterKarel){
        k = parameterKarel;

        setBackground(Color.WHITE);
        setBounds(constants.paddingX, constants.paddingY, constants.panelWidth, constants.panelHeight);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawGrid(g);
        drawAllBeepers(g);
        drawKarel(g, k.graphPosX, k.graphPosY, k.graphCurrentDirection,
                constants.cellWidth, constants.cellHeight);
        drawAllVerticalWalls(g);
        drawAllHorizontalWalls(g);
    }

    private void drawAllVerticalWalls(Graphics g) {
        for (int row = 0; row < constants.verticalWalls.length; row++) {
            for (int col = 0; col < constants.verticalWalls[row].length; col++) {
                if (constants.verticalWalls[row][col]){
                    drawVerticalWall(g, col, row);
                }
            }
        }
    }
    private void drawAllHorizontalWalls(Graphics g) {
        for (int row = 0; row < constants.horizontalWalls.length; row++) {
            for (int col = 0; col < constants.horizontalWalls[row].length; col++) {
                if (constants.horizontalWalls[row][col]){
                    drawHorizontalWall(g, col, row);
                }
            }
        }
    }

    private void drawHorizontalWall(Graphics g, int x, int y){
        g.fillRect(x*constants.cellWidth, y*constants.cellHeight-constants.wallThickness,
                constants.cellWidth, 2*constants.wallThickness);
    }
    private void drawVerticalWall(Graphics g, int x, int y){
        g.fillRect(x*constants.cellWidth-constants.wallThickness, y*constants.cellHeight,
                2*constants.wallThickness, constants.cellHeight);
    }

    private void drawAllBeepers(Graphics g) {
        for (int row = 0; row < k.graphBeepers.length; row++) {
            for (int col = 0; col < k.graphBeepers[row].length; col++) {
                if (k.graphBeepers[row][col]>0){
                    drawBeeper(g, col, row, constants.cellWidth, constants.cellHeight, k.graphBeepers[row][col]);
                }
            }
        }
    }

    private void drawGrid(Graphics g) {
        int rad = 2;
        for (int row = 0; row < constants.rows; row++) {
            int centerY = (int) ((row+0.5) * constants.cellHeight);
            for (int col = 0; col < constants.columns; col++) {
                int centerX = (int) ((col+0.5) * constants.cellWidth);
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
        int y3 = ((y + 1) * cellHeight);
        int y4 = (int) ((y + 0.5) * cellHeight);

        g.setColor(Color.lightGray);
        g.fillPolygon(new int[]{x1, x2, x3, x4}, new int[]{y1, y2, y3, y4}, 4);
        if (number>1){
            g.setColor(Color.black);
            g.drawString(Integer.toString(number), x1, y2);
        }
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
                workingX = (1)*tempY;
                workingY = (-1)*tempX;
            }

            if (dir == 1) {
                workingY++;
            } else if (dir == 2) {
                workingX++;
                workingY++;
            } else if (dir == 3) {
                workingX++;
            }

            xShiftList[id] = workingX;
            yShiftList[id] = workingY;
        }

        int[] xCoordList = new int[xShiftList.length];
        int[] yCoordList = new int[yShiftList.length];

        for (int id = 0; id < xShiftList.length; id++) {
            xCoordList[id] = (int) ((x + xShiftList[id])*cellWidth);
            yCoordList[id] = (int) ((y + yShiftList[id])*cellHeight);
        }

        g.setColor(Color.black);
        g.drawPolygon(splice(xCoordList,0, 5), splice(yCoordList, 0, 5), 6);
        g.drawPolygon(splice(xCoordList,6, 9), splice(yCoordList, 6, 9), 4);
        g.fillPolygon(splice(xCoordList,10, 13), splice(yCoordList, 10, 13), 4);
        g.fillPolygon(splice(xCoordList,14, 19), splice(yCoordList, 14, 19), 6);
        g.fillPolygon(splice(xCoordList,20, 25), splice(yCoordList, 20, 25), 6);
    }

    private static int[] splice(int[] list, int start, int end){
        int[] toReturn = new int[end-start+1];
        if (end - start + 1 >= 0) System.arraycopy(list, start, toReturn, 0, end - start + 1);
        return toReturn;
    }
}