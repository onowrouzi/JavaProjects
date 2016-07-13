package model.enemies;

import model.states.AliveBoss;
import controller.Main;
import model.states.HurtBoss;
import model.states.DyingBoss;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import model.Projectile;
import model.states.DoneEnemy;
import model.states.FigureState;
import model.states.DescendingBoss;

public class BossEnemy extends Enemy {
    
    public FigureState drop;
    public int attackTimer;
    public float px;
    public float py;
    public float ex;
    public float ey;
    public int eSize;
    public final int MAX_EXP_SIZE = 50;
    public final int FACE_LEFT = 0;
    public final int HURT_LEFT = 1;
    public final int FACE_RIGHT = 2;
    public final int HURT_RIGHT = 3;
    public boolean hasAttacked;
    
    public BossEnemy(float x, float y, int width, int height){
        super(x,y,width,height);
        
        sprites = new BufferedImage[4];
        sprites[0] = super.extractImage("images/bossEnemy1.png");
        sprites[1] = super.extractImage("images/bossEnemy2.png");
        sprites[2] = super.flipImage(sprites[0]);
        sprites[3] = super.flipImage(sprites[1]);
        
        health = 5; 
        immuneTimer = 0;
        attackTimer = 20;
        
        drop = new DescendingBoss(this);
        alive = new AliveBoss(this);
        hurt = new HurtBoss(this);
        dying = new DyingBoss(this);
        done = new DoneEnemy(this);
        
        state = drop;
    }
    
    @Override
    public void render(Graphics2D g) {
        if (immuneTimer % 2 == 0)
            g.drawImage(sprites[spriteState], (int)x, (int)y, width, height, null);
        if (state == dying){
            g.drawImage(sprites[spriteState], (int)x, (int)y, width, height, null);
            g.setColor(Color.WHITE);
            g.fillOval((int)ex, (int)ey, eSize, eSize);  
        }
    }

    @Override
    public Rectangle2D getCollisionBox() {
        return new Rectangle2D.Float(x+5,y+5,width,height);
    }

    @Override
    public void travelLeft() {
        if (y > 100 && x > 400) {
            y -= 20;
        } else if (x > 20) {
            x -= 20;
        } else if (y+height < 450){
            y += 20;
        }
    }

    @Override
    public void travelRight() {
        if (y > 100 && x < 400) {
            y -= 20;
        } else if (x+width < 780) {
            x += 20;
        } else if (y+height < 450){
            y += 20;
        }
    }

    @Override
    public void update() {
        state.update();
        attackTimer--;
    }
    
    public void hurt() {
        if (immuneTimer == 0) {
            immuneTimer = 75 ;
            health--;
        }
    }
    
    public void dropAttack(){
        if (y+height < 450 && !hasAttacked){
            y += 40;
        } else {
            hasAttacked = true;
        } 
    }
    
    public void fireProjectile() {
        px = (int)Main.gameData.player.x+(int)Main.gameData.player.width/2;
        py = (int)Main.gameData.player.y+Main.gameData.player.height/2;
        float bx;
        if (spriteState <= HURT_LEFT) {
            bx = x+5;
        } else {
            bx = x+width-5;
        }
        
        Projectile m1 = new Projectile (
                bx, 
                y+height/2,
                px, py, 
                Color.BLUE, Color.CYAN, this);
        
        Projectile m2 = new Projectile (
                bx, 
                y+height/2,
                px+60, py+60, 
                Color.BLUE, Color.CYAN, this);
        
        Projectile m3 = new Projectile (
                bx, 
                y+height/2,
                px-60, py-60, 
                Color.BLUE, Color.CYAN, this);
        
        synchronized (Main.gameData.enemyFigures) {
            Main.gameData.enemyFigures.add(m1);
            Main.gameData.enemyFigures.add(m2);
            Main.gameData.enemyFigures.add(m3);
        }
    }
    
    
}
