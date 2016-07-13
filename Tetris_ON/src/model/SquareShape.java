package model;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

public class SquareShape extends TetrisShape {
    
    public SquareShape(){
        super(Color.RED, 360, 0);
        pieces.add(new Rectangle2D.Float(x,y,SIZE,SIZE));
        pieces.add(new Rectangle2D.Float(x+SIZE,y,SIZE,SIZE));
        pieces.add(new Rectangle2D.Float(x,y+SIZE,SIZE,SIZE));
        pieces.add(new Rectangle2D.Float(x+SIZE,y+SIZE,SIZE,SIZE));
        width = 40;
        height = 40;
    }

    @Override
    public void setState() {
        pieces.get(0).setRect(x, y, SIZE, SIZE);
        pieces.get(1).setRect(x + SIZE, y, SIZE, SIZE);
        pieces.get(2).setRect(x, y + SIZE, SIZE, SIZE);
        pieces.get(3).setRect(x + SIZE, y + SIZE, SIZE, SIZE);
    }
    
}