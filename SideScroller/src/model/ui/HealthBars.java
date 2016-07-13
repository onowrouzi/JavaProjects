package model.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import model.GameFigure;
import model.Player;

public class HealthBars extends GameFigure implements Observer {
    
    private int health;
    private int immuneTimer;
    public BufferedImage heart;

    public HealthBars(float x, float y, int width, int height, Player player) {
        super(x,y,width,height);
        health = 5;
        heart = super.extractImage("images/heart.png");
        player.attach(this);
    }
    
    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Courier New", Font.BOLD, 30));
        g.drawString("HEALTH: ", x, y);
        for (int i = 0; i < health; i++){
            g.drawImage(heart, (int)x+i*30+110,0, null);
        }
    }

    @Override
    public void updateObserver(int count, int timer) {
        health = count;
        immuneTimer = timer;
    }
    
}
