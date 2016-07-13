package model;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public abstract class GameFigure {
    
    public float x;
    public float y;
    public int width;
    public int height;
    
    public GameFigure(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public abstract void render(Graphics2D g);
    
    public BufferedImage extractImage(String image) { 
        BufferedImage img = null;
        try {
            img = ImageIO.read(getClass().getResource(image));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open " + image);
            System.exit(-1);
        }
        return img;
    }
    //Below code retrieved and modifiied from http://stackoverflow.com/questions/13742365/how-do-i-flip-an-image-horizontally-flip-with-glreadpixels-bufferedimage-and-o
    public BufferedImage flipImage(BufferedImage image) {         BufferedImage flippedImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        AffineTransform at = AffineTransform.getTranslateInstance(image.getWidth(), 0);
        AffineTransform flip = AffineTransform.getScaleInstance(-1d, 1d);
        at.concatenate(flip);
        Graphics2D g = flippedImage.createGraphics();
        g.setTransform(at);
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return flippedImage;
    }
    
    
}
