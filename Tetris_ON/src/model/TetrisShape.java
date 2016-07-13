package model;

import controller.Main;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public abstract class TetrisShape {
    
    public final Color color;
    public int x, y, width, height;
    public final int SIZE = 20;
    public int state;
    public ArrayList<Rectangle2D> pieces = new ArrayList<>();
    //Super Class Constructor.
    public TetrisShape(Color color, int x, int y){
        this.x = x;
        this.y = y;
        this.color = color;
        state = 0;
    }
    //Move shape if possible.
    public synchronized void moveShape(int n, char c){
        if (c == 'y') y += n;
        else if (c == 'x') x += n;
        setState();
        for (GridPiece shape : Main.game.GRID_PIECES) {
            if (getCollision(shape)) {
                if (c == 'y') y -= n;
                else if (c == 'x') x -= n;
                setState();
                Main.game.landShape();
                break;
            }
        }
    }
    //Turn shape if possible.
    public synchronized void turnShape(){
        model.Sounds.play("sounds/rotatePiece.wav");
        nextState();
        for (GridPiece shape : Main.game.GRID_PIECES) {
            if (getCollision(shape)) {
                state--;
                setState();
                break;
            }
        }
        if (x + width > 500) {
            state--;
            setState();
        }
    }
    //Switch states to turned shape.
    public void nextState() {
        if (state == 3) {
            state = 0;
        } else {
            state++;
        }
        setState();
    }
    //Check collision.
    public synchronized boolean getCollision(TetrisShape shape){
        if (shape != this) {
            for (int i = 0; i < pieces.size(); i++) {
                for (int j = 0; j < shape.pieces.size(); j++) {
                    if (pieces.get(i).intersects(shape.pieces.get(j)))
                        return true;
                }
            }
        }
        return false;
    }
    //Draw shape.
    public void draw(Graphics2D g){
        g.setColor(color);
        for (Rectangle2D piece : pieces) {
            if (piece.getX() >= 0) 
                g.fill(piece);
        }
    }
    //Abstract method to set each shapes sub pieces.
    public abstract void setState();

}