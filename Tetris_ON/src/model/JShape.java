package model;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

public class JShape extends TetrisShape {
    
    public JShape(){
        super(Color.ORANGE, 360, 0);
        pieces.add(new Rectangle2D.Float(x+SIZE,y,SIZE,SIZE));
        pieces.add(new Rectangle2D.Float(x+SIZE,y+SIZE,SIZE,SIZE));
        pieces.add(new Rectangle2D.Float(x+SIZE,y+SIZE*2,SIZE,SIZE));
        pieces.add(new Rectangle2D.Float(x,y+SIZE*2,SIZE,SIZE));
        width = 40;
        height = 60;
    }

    @Override
    public void setState() {
        switch (state){
            case 0:
                pieces.get(0).setRect(x+SIZE,y,SIZE,SIZE);
                pieces.get(1).setRect(x+SIZE,y+SIZE,SIZE,SIZE);
                pieces.get(2).setRect(x+SIZE,y+SIZE*2,SIZE,SIZE);
                pieces.get(3).setRect(x,y+SIZE*2,SIZE,SIZE);
                width = 40;
                height = 60;
                break;
            case 1:
                pieces.get(0).setRect(x,y,SIZE,SIZE);
                pieces.get(1).setRect(x,y+SIZE,SIZE,SIZE);
                pieces.get(2).setRect(x+SIZE,y+SIZE,SIZE,SIZE);
                pieces.get(3).setRect(x+SIZE*2,y+SIZE,SIZE,SIZE);
                width = 60;
                height = 40;
                break;
            case 2:
                pieces.get(0).setRect(x,y,SIZE,SIZE);
                pieces.get(1).setRect(x+SIZE,y,SIZE,SIZE);
                pieces.get(2).setRect(x,y+SIZE,SIZE,SIZE);
                pieces.get(3).setRect(x,y+SIZE*2,SIZE,SIZE);
                width = 40;
                height = 60;
                break;
            case 3:
                pieces.get(0).setRect(x,y,SIZE,SIZE);
                pieces.get(1).setRect(x+SIZE,y,SIZE,SIZE);
                pieces.get(2).setRect(x+SIZE*2,y,SIZE,SIZE);
                pieces.get(3).setRect(x+SIZE*2,y+SIZE,SIZE,SIZE);
                width = 60;
                height = 40;
                break;
        }
    }
    
}