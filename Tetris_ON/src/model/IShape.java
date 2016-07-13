package model;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

public class IShape extends TetrisShape {
    
    public IShape(){
        super(Color.GREEN, 360, 0);
        pieces.add(new Rectangle2D.Float(x,y,SIZE,SIZE));
        pieces.add(new Rectangle2D.Float(x+SIZE,y,SIZE,SIZE));
        pieces.add(new Rectangle2D.Float(x+SIZE*2,y,SIZE,SIZE));
        pieces.add(new Rectangle2D.Float(x+SIZE*3,y,SIZE,SIZE));
        width = 20;
        height = 80;
    }

    @Override
    public void setState() {
        switch(state) {
            case 0:
            case 2:
                pieces.get(0).setRect(x,y,SIZE,SIZE);
                pieces.get(1).setRect(x,y+SIZE,SIZE,SIZE);
                pieces.get(2).setRect(x,y+SIZE*2,SIZE,SIZE);
                pieces.get(3).setRect(x,y+SIZE*3,SIZE,SIZE);
                width = 20;
                height = 80;
                break;
            case 1:
            case 3:
                pieces.get(0).setRect(x,y,SIZE,SIZE);
                pieces.get(1).setRect(x+SIZE,y,SIZE,SIZE);
                pieces.get(2).setRect(x+SIZE*2,y,SIZE,SIZE);
                pieces.get(3).setRect(x+SIZE*3,y,SIZE,SIZE);
                width = 80;
                height = 20;
                break;
        }
    }
    
}