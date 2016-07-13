package model.enemies;

import model.MovableFigure;
import model.Travel;

public abstract class Enemy extends MovableFigure  implements Travel {
    
    public Enemy (float x, float y, int width, int height) {
        super(x,y,width,height);
    }

}
