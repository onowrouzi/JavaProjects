package model;

import controller.Animator;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import model.ui.BulletCount;
import model.ui.HealthBars;
import model.ui.Observer;

public class Player extends MovableFigure implements Travel {
    
    public static final int STAND_RIGHT = 0;
    public static final int WALK_RIGHT = 1;
    public static final int MELEE_RIGHT = 2;
    public static final int SHOOT_RIGHT = 3;
    public static final int STAND_LEFT = 4;
    public static final int WALK_LEFT = 5;
    public static final int MELEE_LEFT = 6;
    public static final int SHOOT_LEFT = 7;
    public boolean ascend;
    public boolean descend;
    public boolean jumpLeft;
    public boolean jumpRight;
    public boolean melee;
    public int bulletCount;
    public int bulletRegenCounter;
    private final ArrayList<Observer> observers = new ArrayList<>();
    
    public Player(float x, float y, int width, int height){
        super(x,y,width,height);
        health = 5;
        immuneTimer = 0;
        bulletCount = 10;
        bulletRegenCounter = 100;
        
        sprites = new BufferedImage[8];
        
        BufferedImage playerImages = super.extractImage("images/player.png");
        for (int i = 0; i < 8; i++){
            sprites[i] = playerImages.getSubimage(i*128, 0, 128, 125);
        }
        
    }
    
    @Override
    public void render(Graphics2D g) {
        if (immuneTimer % 2 == 0)
            g.drawImage(sprites[spriteState], (int)x, (int)y, width, height, null);
    }

    @Override
    public void update() {
        handleImmuneTimer();
        handleBulletCount();
        handleJump();
        handleMelee();
        notifyObservers();
    }
    
    @Override
    public void travelLeft() {
        if (spriteState == STAND_LEFT){
            spriteState = WALK_LEFT;
        } else {
            spriteState = STAND_LEFT;
        }
        
        if (x > 0) x -= 20;
    }
    
    @Override
    public void travelRight() {
        if (spriteState == STAND_RIGHT){
            spriteState = WALK_RIGHT;
        } else {
            spriteState = STAND_RIGHT;
        }
        
        if (x + width < 800){
            x += 20;
        } else if (GameData.stage1) {
            GameData.background.moveBackground();
        }
    }
    
    public void meleeAttack() {
        if (spriteState < STAND_LEFT) {
            spriteState = MELEE_RIGHT;
        } else {
            spriteState = MELEE_LEFT;
        }
    }
    
    public void jump(int key){
        if (spriteState < STAND_LEFT) {
            spriteState = WALK_RIGHT;
        } else {
            spriteState = WALK_LEFT;
        }
        if (!ascend && !descend) ascend = true;
        if (key == KeyEvent.VK_D) jumpRight = true;
        if (key == KeyEvent.VK_A) jumpLeft = true;
    }
    
    public void hurt(){
        if (immuneTimer == 0) {
            health--;
            immuneTimer = 20;
        }
        if (health == 0){
            Animator.gameOver = true;
        }
    }
    
    public float getXofMissileShoot() {
        if (spriteState == SHOOT_LEFT) {
            return super.x;
        }
        else return super.x+super.width;
    }

    public float getYofMissileShoot() {
        return super.y + height/2;
    }
    
    public void resetPlayer(){
        health = 5;
        immuneTimer = 0;
        bulletCount = 10;
        bulletRegenCounter = 100;
        x = y = 350;
        spriteState = 0;
        ascend = descend = false;
    }
    
    @Override
    public Rectangle2D getCollisionBox() {
        if (spriteState == MELEE_LEFT) 
            return new Rectangle2D.Float(x,y+height/4,width*.7f,height/4*3);
        else if (spriteState == MELEE_RIGHT) 
            return new Rectangle2D.Float(x+width/3,y+height/4,width*.7f,height/4*3);
        return new Rectangle2D.Float(x+width*.3f,y+height/4,width*.4f,height/4*3);
    }
    
    public void bounceBack(){
        descend = jumpLeft = jumpRight = false;
        if (spriteState >= STAND_LEFT) {
            jumpRight = true;
        } else {
            jumpLeft = true;
        }
        jump(0);
    }
    
    public void bounceOff(){
        descend = jumpLeft = jumpRight = false;
        if (spriteState < STAND_LEFT) {
            jumpRight = true;
        } else {
            jumpLeft = true;
        }
        jump(0);
    }
    
    public void attach(Observer observer){
        observers.add(observer);
    }
    
    public void notifyObservers(){
        for (Observer o : observers){
            if (o instanceof BulletCount){
                o.updateObserver(bulletCount, bulletRegenCounter);
            } else if (o instanceof HealthBars){ 
                o.updateObserver(health, immuneTimer);
            }
        }
    }

    public void handleJump() {
        if (ascend) {
            if (y > GameData.ground.y - height*2) {
                y -= 20;
            } else {
                ascend = false;
                descend = true;
            }
            if (jumpLeft) x -= 20;
            if (jumpRight) {
                if (x+width*2 < 800) {
                    x += 20;
                } else if (GameData.stage1) {
                    GameData.background.moveBackground();
                }
            }
        } else if (descend) {
            if (y + height == GameData.ground.y) {
                descend = false;
                jumpLeft = false;
                jumpRight = false;
            } else {
                y += 20;
            }
            if (jumpLeft && x>0) x -= 20;
            if (jumpRight){
                if (x+width*2 < 800) {
                    x += 20;
                } else if (GameData.stage1) {
                    GameData.background.moveBackground();
                }
            }
        }
    }

    public void handleBulletCount() {
        if (bulletCount == 0) {
            bulletRegenCounter--;
        }

        if (bulletRegenCounter == 0) {
            bulletCount = 10;
            bulletRegenCounter = 100;
        }
    }

    public void handleMelee() {
        if (melee){
            melee = false;
            spriteState--;
        } else if (spriteState == MELEE_LEFT || spriteState == MELEE_RIGHT){
            melee = true;
        }
    }

    public void handleImmuneTimer() {
        if (immuneTimer > 0) {
            immuneTimer--;
        }
    }
    
}
