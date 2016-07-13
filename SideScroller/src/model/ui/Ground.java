package model.ui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import model.GameFigure;

public class Ground extends GameFigure {

    public BufferedImage groundImage; 
    
    public Ground(float x, float y, int width, int height) {
        super(x, y, width, height);
        
        groundImage = super.extractImage("images/ground.png");
        groundImage = groundImage.getSubimage(0, 1327, 3072, 200);
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(groundImage, (int)super.x, (int)super.y, width, super.height, null);
    }
    
}
