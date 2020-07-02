import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Render extends JPanel {
    int[][] Beepers;
    int height = 400;
    int width = 400;
    int spaceHeight;
    int spaceWidth;

    public void setBeepers(int[][] beepers) {
        this.Beepers = beepers;
        this.updateSpaces();
    }

    private void updateSpaces(){
        spaceWidth = width/Beepers.length;
        spaceHeight = height/Beepers[0].length;
    }

    private static Font monoFont = new Font("Monospaced", Font.PLAIN, 12);

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        for (int row = 0; row < Beepers.length; row++) {
            for (int col = 0; col < Beepers[row].length; col++) {
                if (Beepers[row][col] != 0) {

                    g.setColor(Color.lightGray);
                    g.setFont(monoFont);
                    g.fillOval(row*spaceWidth, col*spaceHeight, spaceWidth, spaceHeight);

                    FontMetrics fm = g.getFontMetrics();
                    int w = fm.stringWidth(Integer.toString(Beepers[row][col]));
                    int h = fm.getAscent();

                    int xPos = (int) ((row)*spaceWidth -w/2+ (0.5*spaceWidth));
                    int yPos = (int) ((col)*spaceHeight+ (0.5*spaceHeight));

                    g.setColor(Color.BLACK);
                    g.drawString(Integer.toString(Beepers[row][col]), xPos, yPos);
                }
            }
        }
    }

}