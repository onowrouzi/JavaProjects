package model.states;

import model.Projectile;

public class ActiveProjectile implements FigureState {

    public Projectile projectile;
    
    public ActiveProjectile(Projectile projectile) {
        this.projectile = projectile;
    }

    @Override
    public void update() {
        projectile.x += projectile.dx;
        projectile.y += projectile.dy;
        
        if (projectile.x > 900 || projectile.x < -100 || 
                projectile.y > 700 || projectile.y < -100) {
            projectile.state = projectile.dying;
        }
    }
    
}
