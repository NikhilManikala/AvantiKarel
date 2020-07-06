package KarelProgram;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Constants {

    //Technical KarelProgram.Constants
    protected int rows;
    protected int columns;
    protected int minSpeed;
    protected int maxSpeed;
    protected int defaultSpeed;
    protected int timeout;

    //Graphical KarelProgram.Constants
    protected int paddingX;
    protected int paddingY;
    protected int cellWidth;
    protected int cellHeight;
    protected int panelWidth;
    protected int panelHeight;
    protected int paddingMultiplierX;
    protected int paddingMultiplierY;

    protected int wallThickness;

    //KarelProgram.Karel Starting Position
    protected int startPosX;
    protected int startPosY;
    protected int startDirection;
    protected int[][] startingBeepers;

    protected boolean[][] horizontalWalls;
    protected boolean[][] verticalWalls;

    public Constants(String world){
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("worlds/" + world + ".json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONObject jObj = (JSONObject) obj;

            rows = (int)(long) jObj.get("rows");
            columns = (int)(long) jObj.get("columns");
            minSpeed = 0;
            maxSpeed = 500;
            defaultSpeed = 250;

            paddingX = (int)(long) jObj.get("paddingX");
            paddingY = (int)(long) jObj.get("paddingY");
            cellWidth = (int)(long) jObj.get("cellWidth");
            cellHeight = (int)(long) jObj.get("cellHeight");
            panelWidth = cellWidth * columns;
            panelHeight = cellHeight * rows;
            paddingMultiplierX = 10;
            paddingMultiplierY = 3;
            timeout = 500;

            wallThickness = 2;

            startPosX = (int)(long) jObj.get("startPosX");
            startPosY = (int)(long) jObj.get("startPosY");
            startDirection = (int)(long) jObj.get("startDirection");

            horizontalWalls = new boolean[rows+1][columns];
            verticalWalls = new boolean[rows][columns+1];
            startingBeepers = new int[rows][columns];

            JSONArray jsonVertWalls = (JSONArray) jObj.get("verticalWalls");
            JSONArray jsonHorWalls = (JSONArray) jObj.get("horizontalWalls");
            JSONArray jsonBeepers = (JSONArray) jObj.get("beepers");

            for (int row = 0; row < jsonVertWalls.size(); row++) {
                JSONArray eachRow = (JSONArray) jsonVertWalls.get(row);
                for (int col = 0; col < eachRow.size(); col++) {
                    verticalWalls[row][col] = boolify(eachRow.get(col));
                }
            }

            for (int row = 0; row < jsonHorWalls.size(); row++) {
                JSONArray eachRow = (JSONArray) jsonHorWalls.get(row);
                for (int col = 0; col < eachRow.size(); col++) {
                    horizontalWalls[row][col] = boolify(eachRow.get(col));
                }
            }

            for (int row = 0; row < jsonBeepers.size(); row++) {
                JSONArray eachRow = (JSONArray) jsonBeepers.get(row);
                for (int col = 0; col < eachRow.size(); col++) {
                    startingBeepers[row][col] = (int) (long) eachRow.get(col);
                }
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private boolean boolify(Object o) {
        return (long) o == 1;
    }

    public int[][] addBeepers(){
        int[][] toReturn = new int[rows][columns];
        for (int row = 0; row < startingBeepers.length; row++) {
            System.arraycopy(startingBeepers[row], 0, toReturn[row], 0, startingBeepers[row].length);
        }
        return toReturn;
    }

}
