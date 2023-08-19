package main;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class Game_Panel extends JPanel implements Runnable{
    // Screen Settings
    final int orgTileSize = 16; //16x16 pixels;
    final int scale = 3;
    public final int tileSize = orgTileSize * scale;//48x48 px
    final int  maxScreenCol= 20;
    // Разликата трябва да е 4
    final int maxScreenRow = 16;
    final int screenWidth = tileSize * maxScreenCol ; // 960px
    final int screenHeight = tileSize * maxScreenRow ; // 768px

    //FPS
    int FPS = 60;
    KeyControls keyC = new KeyControls();
    Thread gameThread;
    Player player = new Player(this,keyC);


    // default position
    int playerX=100;
    int playerY=100;
    int playerSpeed=4;

    public Game_Panel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyC);
        this.setFocusable(true);
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    //Construct a "sleep" game loop
   // @Override
   // public void run() {
       /* double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        // Game Loop
        while (gameThread !=null){
           // System.out.println("Runs"); test 1

            // step 1 update char position.
                update();
            // step 2 DRAW screen whit updated info.
                repaint();

                try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;
                if(remainingTime<0){
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }*/
    //Construct a "delta" game loop
    @Override
    public void run(){
        double drawInterval=1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime ;
        long timer = 0 ;
        int drawCount = 0;
        while (gameThread!=null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer +=(currentTime-lastTime);
            lastTime = currentTime;
            if (delta >=1){
                update();
                repaint();
                delta --;
                drawCount++;
            }if (timer>=1000000000){
                System.out.println("FPS:"+drawCount);
                drawCount = 0;
                timer = 0;
                }

        }
    }
    public void update (){
        player.update();

    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        player.draw(g2);
        g2.dispose();
    }
}
