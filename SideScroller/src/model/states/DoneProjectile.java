package model.states;

import controller.Main;
import model.Projectile;

public class DoneProjectile implements FigureState {

    public Projectile projectile;
    
    public DoneProjectile(Projectile projectile) {
        this.projectile = projectile;
    }

    @Override
    public void update() {
        if (projectile.isFriendly){
            Main.gameData.friendFigures.remove(projectile);
        } else {
            Main.gameData.enemyFigures.remove(projectile);
        }
    }
    
}
