package model.enemies;

import model.states.AliveFlyingEnemy;
import model.states.DoneEnemy;
 import model.states.DyingFlyingEnemy;
import controller.Main;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import model.Projectile;

public class FlyingEnemy extends Enemy {
     
    private int px;
    private int py;
    public int fireHere;
    public static final int START_FLAP_RIGHT = 0;
    public static final int END_FLAP_RIGHT = 3;
    public static final int DEAD_RIGHT = 4;
    public static final int END_RIGHT = 7;
    public static final int START_FLAP_LEFT = 8;
    public static final int END_FLAP_LEFT = 11;
    public static final int DEAD_LEFT = 12;
    public static final int END_LEFT = 15;
    
    public FlyingEnemy(float x, float y, int width, int height) {
        super(x,y,width,height);
        
        fireHere = (int) (Math.random()*8) * 100;
        sprites = new BufferedImage[16];
        
        for (int i = 0; i < 8; i++) {
            sprites[i] = super.extractImage("images/flyingEnemy" + Integer.toString(i+1) + ".png");
            sprites[i+8] = super.flipImage(sprites[i]);
        }
        
        alive = new AliveFlyingEnemy(this);
        dying = new DyingFlyingEnemy(this);
        done = new DoneEnemy(this);
        
        state = alive;
        
    }
    
    @Override
    public void render(Graphics2D g) {
        g.drawImage(sprites[spriteState], (int)x, (int)y, width, height, null);
    } 

    @Override
    public void update() {
        state.update(); 
    }
    
    public void fireProjectile() {
        px = (int)Main.gameData.player.x+(int)Main.gameData.player.width/2;
        py = (int)Main.gameData.player.y+Main.gameData.player.height;

        Projectile m = new Projectile (
                x+width/2, 
                y+height,
                px, py,
                Color.RED, Color.ORANGE, this);

        synchronized (Main.gameData.enemyFigures) {
            Main.gameData.enemyFigures.add(m);
        }
    }

    @Override
    public Rectangle2D getCollisionBox() {
        return new Rectangle2D.Float(x+10, y+10, super.width*.8f, super.height*.8f);
    }

    @Override
    public void travelLeft() {
        if (spriteState < END_FLAP_LEFT) {
            spriteState++;
        } else {
            spriteState =  START_FLAP_LEFT;
        }
        x -= 5;
    }

    @Override
    public void travelRight() {
        if (spriteState < END_FLAP_RIGHT){
            spriteState++;
        } else {
            spriteState = START_FLAP_RIGHT;
        }
        x += 5;
    }

}
