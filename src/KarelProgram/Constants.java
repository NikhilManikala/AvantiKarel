package KarelProgram;

import java.util.Arrays;

import java.io.FileNotFoundException;
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
    protected final int minSpeed = 0;
    protected final int maxSpeed = 500;
    protected final int defaultSpeed = 250;

    //Graphical Constants
    protected int paddingX;
    protected int paddingY;
    protected int cellWidth;
    protected int cellHeight;
    protected final int panelWidth = cellWidth * columns;
    protected final int panelHeight = cellHeight * rows;
    protected final int paddingMultiplierX = 10;
    protected final int paddingMultiplierY = 3;

    protected final int wallThickness = 2;

    //Karel Starting Position
    protected int startPosX;
    protected int startPosY;
    protected int startDirection;

    protected final boolean[][] horizontalWalls = new boolean[rows+1][columns];
    protected final boolean[][] verticalWalls = new boolean[rows][columns+1];

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

            paddingX = (int)(long) jObj.get("paddingX");
            System.out.println(paddingX);
            paddingY = (int)(long) jObj.get("paddingY");
            System.out.println(paddingY);
            cellWidth = (int)(long) jObj.get("cellWidth");
            System.out.println(cellWidth);
            cellHeight = (int)(long) jObj.get("cellHeight");
            System.out.println(cellHeight);

            startPosX = (int)(long) jObj.get("startPosX");
            startPosY = (int)(long) jObj.get("startPosY");
            startDirection = (int)(long) jObj.get("startDirection");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public int[][] addBeepers(int[][] beeperList){
        beeperList[2][5]=1;
        return beeperList;
    }

}
