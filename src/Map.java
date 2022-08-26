import java.awt.BasicStroke; //states colors in the default sRGB color space or colors in arbitrary color spaces identified by a ColorSpace
import java.awt.Color;
import java.awt.Graphics2D;

public class Map
{
    public int map[][];
    public int bricksWidth;
    public int bricksHeight;
    public Map(int row , int col){
        map = new int[row][col];
        for(int i = 0; i < map.length; i++){
            for(int j = 0;j < map[0].length; j++){
                map[i][j] = 1;
            }
        }
        bricksWidth = 540/col;
        bricksHeight = 150/row;
    }
    public void draw(Graphics2D g) {
    	
    	//1st row
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 0) {
                    g.setColor(Color.pink);
                    g.fillRect(j * bricksWidth + 80, i * bricksHeight + 50, bricksWidth, bricksHeight);

                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
                    g.drawRect(j * bricksWidth + 80, i * bricksHeight + 50, bricksWidth, bricksHeight);

                }
            }

        }
        
        //2nd row
        
        for (int i = 1; i < 2; i++) {
            for (int j =0; j < map[0].length; j++) {
                if (map[i][j] > 0) {
                    g.setColor(Color.orange);
                    g.fillRect(j * bricksWidth + 80, i * bricksHeight + 50, bricksWidth, bricksHeight);

                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
                    g.drawRect(j * bricksWidth + 80, i * bricksHeight + 50, bricksWidth, bricksHeight);

                }
            }

        }
    
    //3rd Row 
    for (int i = 2; i < 3; i++) {
        for (int j = 0; j < map[0].length; j++) {
            if (map[i][j] > 0) {
                g.setColor(Color.red);
                g.fillRect(j * bricksWidth + 80, i * bricksHeight + 50, bricksWidth, bricksHeight);

                g.setStroke(new BasicStroke(3));
                g.setColor(Color.black);
                g.drawRect(j * bricksWidth + 80, i * bricksHeight + 50, bricksWidth, bricksHeight);

            }
        }

    }
    
    //4th row
    for (int i = 3; i < 4; i++) {
        for (int j = 0; j < map[0].length; j++) {
            if (map[i][j] > 0) {
                g.setColor(Color.green);
                g.fillRect(j * bricksWidth + 80, i * bricksHeight + 50, bricksWidth, bricksHeight);

                g.setStroke(new BasicStroke(3));
                g.setColor(Color.black);
                g.drawRect(j * bricksWidth + 80, i * bricksHeight + 50, bricksWidth, bricksHeight);

            }
        }

    }
    }
    public void setBricksValue(int value,int row,int col)
    {
        map[row][col] = value;

    }
}
