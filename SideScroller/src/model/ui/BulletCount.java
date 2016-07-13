package model.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import model.GameFigure;
import model.Player;

public class BulletCount extends GameFigure implements Observer {
    
    BufferedImage bullet;
    private int bullets;
    private int reloadTimer;

    public BulletCount(float x, float y, int width, int height, Player player) {
        super(x,y,width,height);
        
        player.attach(this);
        
        bullet = super.extractImage("images/bulletCount.png");
        bullet = bullet.getSubimage(35, 0, 65, 127);
        bullets = 10;
    }
    
    @Override
    public void render(Graphics2D g) {
        g.setFont(new Font("Impact", Font.BOLD, 30));
        g.setColor(Color.BLACK);
        if (bullets > 0) {
            for (int i = 0; i < bullets; i++) {
                g.drawImage(bullet, (int)x, (int)y+i*30, 20, 30, null);
            }
        } else if (reloadTimer % 4 == 0) {
            g.drawString("RELOADING...", x, y+20);
        }
    }

    @Override
    public void updateObserver(int count, int timer) {
        bullets = count;
        reloadTimer = timer;
    }
    
}
