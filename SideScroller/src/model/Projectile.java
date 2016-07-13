package model;

import model.states.ActiveProjectile;
import model.states.ExplodingProjectile;
import model.states.DoneProjectile;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Projectile extends MovableFigure {

    public int size = 10;
    public static final int MAX_EXPLOSION_SIZE = 30;
    public float dx; // displacement at each frame
    public float dy; // displacement at each frame
    public boolean isFriendly;
    public MovableFigure owner;
    
    public Color primaryColor, secondaryColor;
    public Point2D.Float target;

    private static final int UNIT_TRAVEL_DISTANCE = 15; // per frame move
    
    public Projectile(float sx, float sy, float tx, float ty, 
            Color primaryColor, Color secondaryColor, MovableFigure owner) {
        
        super(sx, sy, 10, 10);
        
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.owner = owner;
        
        double angle = Math.atan2(Math.abs(ty - sy), Math.abs(tx - sx));
        dx = (float) (UNIT_TRAVEL_DISTANCE * Math.cos(angle));
        dy = (float) (UNIT_TRAVEL_DISTANCE * Math.sin(angle));

        if (tx > sx && ty < sy) { // target is upper-right side
            dy = -dy; // dx > 0, dx < 0
        } else if (tx < sx && ty < sy) { // target is upper-left side
            dx = -dx;
            dy = -dy;
        } else if (tx < sx && ty > sy) { // target is lower-left side
            dx = -dx;
        } 
        
        if (this.owner instanceof Player) {
            isFriendly = true;
        } else {
            isFriendly = false;
        }
        
        alive = new ActiveProjectile(this);
        dying = new ExplodingProjectile(this);
        done = new DoneProjectile(this);
        
        state = alive;
    }

    @Override
    public void render(Graphics2D g) {
            g.setColor(primaryColor);
            g.fillOval((int) (super.x - size), (int) (super.y - size), size * 2, size * 2);
            g.setColor(secondaryColor);
            g.fillOval((int) (super.x - size / 2), (int) (super.y - size / 2), size, size);
    }

    @Override
    public void update() {
        state.update();
    }
    
    @Override
    public Rectangle2D getCollisionBox() {
        return new Rectangle2D.Double(super.x-size/2,super.y-size/2,((size)*0.9),((size)*0.9));
    }
    
}
