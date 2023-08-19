package entity;

import main.Game_Panel;
import main.KeyControls;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    Game_Panel gp;
    KeyControls keyC;

    public Player(Game_Panel gp, KeyControls keyC){
        this.gp = gp;
        this.keyC = keyC;
        setDefaultValues();
        getPlayerImage();
        }
        public void setDefaultValues(){

            x=100;
            y=100;
            speed=4;
            direction = "down";
        }
        public void getPlayerImage(){

        try {

            down1 = ImageIO.read(getClass().getResourceAsStream("/Player/Player_down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/Player/Player_down2.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/Player/Player_up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/Player/Player_up2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/Player/Player_left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Player/Player_left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/Player/Player_right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Player/Player_right2.png"));

        }catch (IOException e){
            e.printStackTrace();
        }

        }
        public void update(){

            if (keyC.upPressed == true || keyC.downPressed == true
                    || keyC.leftPressed == true || keyC.rightPressed == true){
                if (keyC.upPressed==true){
                    direction = "up";
                    y -= speed;
                }else if (keyC.downPressed == true){
                    direction = "down";
                    y+=speed;
                }else if (keyC.leftPressed == true){
                    direction = "left";
                    x-=speed;
                }else if (keyC.rightPressed == true){
                    direction = "right";
                    x+=speed;
                }
                spriteCounter++;
                if (spriteCounter > 15){
                    if (spriteNum == 1){
                        spriteNum = 2;
                    } else if (spriteNum == 2) {
                        spriteNum = 1;
                    }
                    spriteCounter = 0;
                }
            }


        }
        public void draw(Graphics2D g2){
           /* g2.setColor(Color.white);
            g2.fillRect(x,y,gp.tileSize,gp.tileSize);*/
            BufferedImage image = null;

            switch (direction){
                case "up":
                    if (spriteNum == 1){
                        image = up1;
                    }
                    if (spriteNum == 2){
                        image = up2;
                    }

                    break;
                case "down":
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2){
                        image = down2;
                    }
                    break;
                case "left":
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2){
                        image = left2;
                    }
                    break;
                case "right":
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2){
                        image = right2;
                    }
            }
            g2.drawImage(image , x , y , gp.tileSize , gp.tileSize , null);
        }
}
