package model;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

public class GridPiece extends TetrisShape {
    
        public GridPiece(Color color, int x, int y) {
            super(color,x,y);
            pieces.add(new Rectangle2D.Float(x,y,SIZE,SIZE));
        }

    @Override
    public void setState() {
        pieces.get(0).setRect(x,y,SIZE,SIZE);
    }

}
