import java.util.Arrays;

public class Constants {

    //Technical Constants
    protected final int rows = 5;
    protected final int columns = 7;
    protected final int minSpeed = 0;
    protected final int maxSpeed = 500;
    protected final int defaultSpeed = 250;

    //Graphical Constants
    protected final int paddingX = 40;
    protected final int paddingY = 40;
    protected final int cellWidth = 100;
    protected final int cellHeight = 100;
    protected final int panelWidth = cellWidth * columns;
    protected final int panelHeight = cellHeight * rows;
    protected final int paddingMultiplierX = 10;
    protected final int paddingMultiplierY = 3;

    protected final int wallThickness = 2;

    //Karel Starting Position
    protected final int startPosX = 2;
    protected final int startPosY = 1;
    protected final int startDirection = 0;

    protected final boolean[][] horizontalWalls = new boolean[rows+1][columns];
    protected final boolean[][] verticalWalls = new boolean[rows][columns+1];

    public Constants(){
        for (int row = 0; row < horizontalWalls.length; row++) {
            Arrays.fill(horizontalWalls[row], row == 0 || row == rows);

        }
        for (int row = 0; row < verticalWalls.length; row++) {
            for (int col = 0; col < verticalWalls[row].length; col++) {
                if (col == 0 || col == columns){
                    verticalWalls[row][col]=true;
                }
            }
        }
        for (int i = 1; i < 4; i++) {
            verticalWalls[i][2]=true;
        }
        for (int i = 2; i < 5; i++) {
            horizontalWalls[1][i]=true;
            horizontalWalls[4][i]=true;
        }
        verticalWalls[1][5]=true;
        verticalWalls[3][5]=true;
    }

    public int[][] addBeepers(int[][] beeperList){
        beeperList[2][5]=1;
        return beeperList;
    }

}
