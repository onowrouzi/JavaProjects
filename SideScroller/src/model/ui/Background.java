package model.ui;

import controller.Main;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import model.GameFigure;

public class Background extends GameFigure {
    
    public static BufferedImage b1, b2;
    public int b1X, b2X;
    public final int WIDTH;
    
    public Background() {
        super(0,0,800,600);
        
        b1 = b2 = super.extractImage("images/background1.png");
        b1X = 0;
        b2X = b1.getWidth();
        WIDTH = b2X - 800;
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(b1, b1X, (int)y, b1.getWidth(), height, null);
        if (b2X < b1.getWidth()) {
            g.drawImage(b2, b2X, (int)y, b2.getWidth(), height, null);
        }
    }
    
    public void moveBackground(){
        b1X -= 20;
        b2X -= 20;
        
        if (b2X/10 == 0){
            b1X = 0;
            b2X = b1.getWidth();
        }
        
        for (GameFigure e : Main.gameData.enemyFigures) {
            e.x -= 20;
        }
    }
    
    public void changeBackground(String image){
        b1 = b2 = super.extractImage(image);
        b1X = 0;
    }
    
}
