package model.states;

import model.Projectile;

public class ExplodingProjectile implements FigureState {

    public Projectile projectile;
    
    public ExplodingProjectile(Projectile projectile) {
        this.projectile = projectile;
    }

    @Override
    public void update() {
        if (projectile.size < Projectile.MAX_EXPLOSION_SIZE){
            projectile.size += 10;
        } else {
            projectile.state = projectile.done;
        }
    }
    
}
