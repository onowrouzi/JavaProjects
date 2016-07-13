package model.enemies;

import model.states.DoneEnemy;
import model.states.AliveGroundEnemy;
import model.states.DyingGroundEnemy;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class GroundEnemy extends Enemy {
    
    public final static int STAND_LEFT = 0;
    public final static int WALK_LEFT = 1;
    public final static int DEAD_LEFT = 2;
    public final static int STAND_RIGHT = 3;
    public final static int WALK_RIGHT = 4;
    public final static int DEAD_RIGHT = 5;
    public char type;
    
    public GroundEnemy(float x, float y, int width, int height, char type) {
        super(x,y,width,height);
        
        sprites = new BufferedImage[6];
        this.type = type;
        
        for (int i = 0; i < 3; i++) {
            sprites[i] = super.extractImage("images/groundEnemy" + type + Integer.toString(i+1) + ".png");
            sprites[i+3] = super.flipImage(sprites[i]);
        }
        
        alive = new AliveGroundEnemy(this);
        dying = new DyingGroundEnemy(this);
        done = new DoneEnemy(this);
        
        state = alive;
    }
    
    @Override
    public void render(Graphics2D g) {
        g.drawImage(sprites[spriteState], (int)super.x, (int)super.y, width, height, null);
    }

    @Override
    public void update() {
        state.update();
    }

    @Override
    public Rectangle2D getCollisionBox() {
        return new Rectangle2D.Float(x+5, y+10, width*.9f, height);
    }

    @Override
    public void travelLeft() {
        if (spriteState == STAND_LEFT) {
            spriteState = WALK_LEFT;
        } else {
            spriteState = STAND_LEFT;
        }
        
        x -= 5;
    }

    @Override
    public void travelRight() {
        if (spriteState == STAND_RIGHT) {
            spriteState = WALK_RIGHT;
        } else {
            spriteState = STAND_RIGHT;
        }
        
        x += 5;
    }

}
