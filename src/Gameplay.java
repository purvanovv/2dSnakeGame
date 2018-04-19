import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Gameplay extends JPanel implements KeyListener,ActionListener{
    private int[] snakexLength=new int[750];
    private int[] snakeyLength=new int[750];
    private int moves=0;

    private boolean left=false;
    private boolean right=false;
    private boolean up=false;
    private boolean down=false;

    private  ImageIcon leftmouth;
    private  ImageIcon rigthmouth;
    private  ImageIcon upmouth;
    private  ImageIcon downmouth;
    private ImageIcon enemyimage;

    private int[] enemyxPos={25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,
                            475,500,525,600,625,650,675,700,725,750,775,800,825,850};
    private int[] enemyyPos={75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,
                            475,500,525,600,625};
    private Random random=new Random();
    private int xpos=random.nextInt(34);
    private int ypos=random.nextInt(23);

    private int lengthOfSnake=3;
    private int scores=0;

    private Timer timer;
    private int delay=100;

    private ImageIcon snakeImage;
    private ImageIcon titleImage;

    public Gameplay(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer=new Timer(delay,this);
        timer.start();

    }
    public void paint(Graphics g){
        if(moves==0){
            snakexLength[2]=50;
            snakexLength[1]=75;
            snakexLength[0]=100;

            snakeyLength[2]=100;
            snakeyLength[1]=100;
            snakeyLength[0]=100;
        }

        //draw title image border
        g.setColor(Color.white);
        g.drawRect(24,10,851,55);
        //draw the title image
        titleImage=new ImageIcon("snaketitle.jpg");
        titleImage.paintIcon(this,g,25,11);
        //draw border for gameplay
        g.setColor(Color.white);
        g.drawRect(24,74,851,577);
        //draw background for the gameplay
        g.setColor(Color.black);
        g.fillRect(25,75,850,575);
        //draw scores
        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.PLAIN,14));
        g.drawString("Scores:"+scores,780,30);
        //draw length
        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.PLAIN,14));
        g.drawString("Length:"+lengthOfSnake,780,50);


        rigthmouth=new ImageIcon("rightmouth.png");
        rigthmouth.paintIcon(this,g,snakexLength[0],snakeyLength[0]);

        for (int a = 0; a < lengthOfSnake; a++) {
            if(a==0 && right){
                rigthmouth=new ImageIcon("rightmouth.png");
                rigthmouth.paintIcon(this,g,snakexLength[0],snakeyLength[0]);
            }
            if(a==0 && left){
                leftmouth=new ImageIcon("leftmouth.png");
                leftmouth.paintIcon(this,g,snakexLength[0],snakeyLength[0]);
            }
            if(a==0 && up){
                upmouth=new ImageIcon("upmouth.png");
                upmouth.paintIcon(this,g,snakexLength[0],snakeyLength[0]);
            }
            if(a==0 && down){
                downmouth=new ImageIcon("downmouth.png");
                downmouth.paintIcon(this,g,snakexLength[0],snakeyLength[0]);
            }
            if(a!=0){
                snakeImage=new ImageIcon("snakeimage.png");
                snakeImage.paintIcon(this,g,snakexLength[a],snakeyLength[a]);
            }
        }
        enemyimage=new ImageIcon("enemy.png");
        if(enemyxPos[xpos]==snakexLength[0]&&enemyyPos[ypos]==snakeyLength[0]){
            lengthOfSnake++;
            scores++;
            xpos=random.nextInt(34);
            ypos=random.nextInt(23);
        }
        enemyimage.paintIcon(this,g,enemyxPos[xpos],enemyyPos[ypos]);
        for (int b = 1; b < lengthOfSnake; b++) {
            if(snakexLength[b]==snakexLength[0]&&snakeyLength[b]==snakeyLength[0]){
                right=false;
                left=false;
                up=false;
                down=false;
                g.setColor(Color.white);
                g.setFont(new Font("ariel",Font.BOLD,50));
                g.drawString("Game Over!You are Noob!",200,300);

                g.setColor(Color.white);
                g.setFont(new Font("ariel",Font.BOLD,20));
                g.drawString("Space to restart!",350,340);
            }

        }
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(right){
            for (int r = lengthOfSnake-1; r >= 0; r--) {
                snakeyLength[r+1]=snakeyLength[r];
            }
            for (int r = lengthOfSnake; r >=0 ; r--) {
                if(r==0){
                    snakexLength[r]=snakexLength[r]+25;
                }
                else {
                    snakexLength[r]=snakexLength[r-1];
                }
                if(snakexLength[r]>850){
                    snakexLength[r]=25;
                }
            }
            repaint();
        }
        if(left){
            for (int r = lengthOfSnake-1; r >= 0; r--) {
                snakeyLength[r+1]=snakeyLength[r];
            }
            for (int r = lengthOfSnake; r >=0 ; r--) {
                if(r==0){
                    snakexLength[r]=snakexLength[r]-25;
                }
                else {
                    snakexLength[r]=snakexLength[r-1];
                }
                if(snakexLength[r]<25){
                    snakexLength[r]=850;
                }
            }
            repaint();
        }
        if(down){
            for (int r = lengthOfSnake-1; r >= 0; r--) {
                snakexLength[r+1]=snakexLength[r];
            }
            for (int r = lengthOfSnake; r >=0 ; r--) {
                if(r==0){
                    snakeyLength[r]=snakeyLength[r]+25;
                }
                else {
                    snakeyLength[r]=snakeyLength[r-1];
                }
                if(snakeyLength[r]>625){
                    snakeyLength[r]=75;
                }
            }
            repaint();
        }
        if(up){
            for (int r = lengthOfSnake-1; r >= 0; r--) {
                snakexLength[r+1]=snakexLength[r];
            }
            for (int r = lengthOfSnake; r >=0 ; r--) {
                if(r==0){
                    snakeyLength[r]=snakeyLength[r]-25;
                }
                else {
                    snakeyLength[r]=snakeyLength[r-1];
                }
                if(snakeyLength[r]<75){
                    snakeyLength[r]=625;
                }
            }
            repaint();

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_SPACE){
            moves=0;
            scores=0;
            lengthOfSnake=3;
            repaint();
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            moves++;
            right=true;
            if(!left){
                right=true;
            }
            else {
                right=false;
                left=true;
            }
            up=false;
            down=false;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            moves++;
            left=true;
            if(!right){
                left=true;
            }
            else {
                left=false;
                right=true;
            }
            up=false;
            down=false;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            moves++;
            up=true;
            if(!down){
                up=true;
            }
            else {
                up=false;
                down=true;
            }
            left=false;
            right=false;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            moves++;
            down=true;
            if(!up){
                down=true;
            }
            else {
                down=false;
                up=true;
            }
            left=false;
            right=false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
