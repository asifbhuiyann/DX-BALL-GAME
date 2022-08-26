import javax.swing.JPanel;
import javax.swing.Timer; //class provides a method call that is used by a thread to schedule a task
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameCode extends JPanel implements KeyListener, ActionListener {


    private boolean play = false;
    private int score = 0;
    private int totalbricks = 28;
    private Timer Timer;
    private int delay = 5;
    private int playerX = 310; //BAR HORIZONTAL
    private int ballposX = 120; //BALL HORIZONTAL
    private int ballposY = 350; //BALL VERTICAL
    private int ballXdir = -1; //BALL LEFT RIGHT 
    private int ballYdir = -2; // BALL UP DOWN 
    
    
    private Map map; 

    public GameCode() {
        map = new Map(4, 7); // ROW AND COLLUMN 
        addKeyListener(this); 
        setFocusable(true); //it will focused into the bar 
        setFocusTraversalKeysEnabled(false); //setFocusTraversalKeysEnabled() decides whether or not focus traversal keys (TAB key, SHIFT+TAB, etc.) are allowed to be used when the current Component has focus.
        Timer = new Timer(delay, this); //this is addkeyistener performed 
        Timer.start(); //game will start 
        
        
    }

    public void paint(Graphics g) {
        g.setColor(Color.white); //background color
        g.fillRect(1, 1, 692, 592); // x,y,width and height 

        map.draw((Graphics2D) g);

        g.setColor(Color.blue);
        g.fillRect(0, 0, 3, 592); //left border
        g.fillRect(0, 0, 692, 3); //top border
        g.fillRect(691, 0, 3, 592); //right border 

        g.setColor(Color.blue);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("" + score, 590, 30);

        g.setColor(Color.black); //Bar color 
        g.fillRect(playerX, 550, 100, 8);

        g.setColor(Color.GREEN); //Ball
        g.fillOval(ballposX, ballposY, 20, 20); // ball's width and height = 20

        if (ballposY > 570) {
            play = false; //game over 
            ballXdir = 0; //start a new game 
            ballYdir = 0;
            g.setColor(Color.red); //text color 
            g.setFont(new Font("serif", Font.BOLD, 30)); //text
            g.drawString("Game Over  " , 190, 300); 
            g.drawString("Score: "+score,190,330);

            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Press Enter to Restart", 190, 360);
        }
        if(totalbricks == 0){ //win
            play = false;
            ballYdir = -2; //ball direction reset 
            ballXdir = -1;
            g.setColor(Color.red); //text color 
            g.setFont(new Font("serif", Font.BOLD, 30)); //text
            g.drawString("Congratulations!  " , 190, 300);// winning text 
            g.drawString("Score: "+score,190,330);

            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Press Enter to Restart", 190, 360);


        }

        g.dispose(); //causes the JFrame window to be destroyed


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Timer.start(); 

        if (play) { //play=true 
            if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
                ballYdir = -ballYdir; // ball will go from down to up by hitting the bar 
            }

            A: 
            for (int i = 0; i < map.map.length; i++) 
            {
                for (int j = 0; j < map.map[0].length; j++) 
                {
                    if (map.map[i][j] > 0) 
                    {
                        int brickX = j * map.bricksWidth + 80;
                        int brickY = i * map.bricksHeight + 50;
                        int bricksWidth = map.bricksWidth;
                        int bricksHeight = map.bricksHeight;

                        Rectangle rect = new Rectangle(brickX, brickY, bricksWidth, bricksHeight);
                        Rectangle ballrect = new Rectangle(ballposX, ballposY, 20, 20);
                        Rectangle brickrect = rect;

                        if (ballrect.intersects(brickrect)) { //brick will be erased when hit and brick will decrease and score will increase 
                            map.setBricksValue(0, i, j); //which brick is gonna be intersected 
                            totalbricks--;
                            score += 10;// score amount
                            if (ballposX + 19 <= brickrect.x || ballposX + 1 >= brickrect.x + bricksWidth) {
                                ballXdir = -ballXdir; //ball will change the x axis direction when its gonna hit a brick 
                            } else {
                                ballYdir = -ballYdir; //ball will hit the top border will change the y axis direction 
                            }
                            break A;
                        }
                    }


                }
            }


            ballposX += ballXdir; //the x axis direction keeps changing when the ball moves horizontally 
            ballposY += ballYdir; ////the y axis direction keeps changing when the ball moves vertically
            if (ballposX < 0) 
            {
                ballXdir = -ballXdir; //when ball hits the left border then it change the x axis direction
            }
            if (ballposY < 0)
            {
                ballYdir = -ballYdir; //when ball hits the top border then it change the y axis direction
            }
            if (ballposX > 670) 
            {
                ballXdir = -ballXdir; //when ball hits the right border then it change the x axis direction
            }
        }
        repaint(); //it will run until the game overs 
    }

    @Override
    public void keyTyped(KeyEvent e)
    { //we had to write these otherwise it will show an error 

       }


    @Override
    public void keyReleased(KeyEvent e) 
    {

    }

    @Override
    public void keyPressed(KeyEvent e) 
    {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) { //the ball will not cross the right border 
            if (playerX >= 600) {
                playerX = 600;
            } else {
                moveRight();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) { //the ball will not cross the left border 
            if (playerX < 10) {
                playerX = 10;
            } else {
                moveLeft();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!play) { //it will show the screen at first when the game starts 
                ballposX = 120;
                ballposY = 350;
                ballXdir = -1;
                ballYdir = -2;
                score = 0;
                playerX = 310;
                totalbricks = 28;
                map = new Map(4, 7);

                repaint();
            }
        }


        }

        public void moveRight ()
        {
            play = true; //when the game is on
            playerX += 20; //the x axis position of the bar will increase 20
        }
        public void moveLeft ()
        {
            play = true;
            playerX -= 20; //the x axis position of the bar will decrease 20
        }

    }
