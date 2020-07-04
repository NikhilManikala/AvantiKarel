package KarelProgram;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Constants {

    //Technical Constants
    protected int rows;
    protected int columns;
    protected int minSpeed;
    protected int maxSpeed;
    protected int defaultSpeed;

    //Graphical Constants
    protected int paddingX;
    protected int paddingY;
    protected int cellWidth;
    protected int cellHeight;
    protected int panelWidth;
    protected int panelHeight;
    protected int paddingMultiplierX;
    protected int paddingMultiplierY;

    protected int wallThickness;

    //Karel Starting Position
    protected int startPosX;
    protected int startPosY;
    protected int startDirection;

    protected boolean[][] horizontalWalls;
    protected boolean[][] verticalWalls;

    public Constants(){
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("worlds/NewspaperWorld.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONObject jObj = (JSONObject) obj;

            rows = (int)(long) jObj.get("rows");
            System.out.println(rows);
            columns = (int)(long) jObj.get("columns");
            System.out.println(columns);
            minSpeed = 0;
            maxSpeed = 500;
            defaultSpeed = 250;

            paddingX = (int)(long) jObj.get("paddingX");
            System.out.println(paddingX);
            paddingY = (int)(long) jObj.get("paddingY");
            System.out.println(paddingY);
            cellWidth = (int)(long) jObj.get("cellWidth");
            System.out.println(cellWidth);
            cellHeight = (int)(long) jObj.get("cellHeight");
            System.out.println(cellHeight);
            panelWidth = cellWidth * columns;
            panelHeight = cellHeight * rows;
            paddingMultiplierX = 10;
            paddingMultiplierY = 3;

            wallThickness = 2;

            startPosX = (int)(long) jObj.get("startPosX");
            startPosY = (int)(long) jObj.get("startPosY");
            startDirection = (int)(long) jObj.get("startDirection");

            horizontalWalls = new boolean[rows+1][columns];
            verticalWalls = new boolean[rows][columns+1];

            JSONArray jsonVertWalls = (JSONArray) jObj.get("verticalWalls");
            JSONArray jsonHorWalls = (JSONArray) jObj.get("horizontalWalls");

            for (int row = 0; row < jsonVertWalls.size(); row++) {
                JSONArray eachRow = (JSONArray) jsonVertWalls.get(row);
                for (int col = 0; col < eachRow.size(); col++) {
                    verticalWalls[row][col] = (boolean) eachRow.get(col);
                }
            }

            for (int row = 0; row < jsonHorWalls.size(); row++) {
                JSONArray eachRow = (JSONArray) jsonHorWalls.get(row);
                for (int col = 0; col < eachRow.size(); col++) {
                    horizontalWalls[row][col] = (boolean) eachRow.get(col);
                }
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public int[][] addBeepers(int[][] beeperList){
        beeperList[2][5]=1;
        return beeperList;
    }

}
