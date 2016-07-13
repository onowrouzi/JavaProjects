package model.states;

import model.enemies.FlyingEnemy;

public class AliveFlyingEnemy implements FigureState {

    public FlyingEnemy enemy;
    
    public AliveFlyingEnemy(FlyingEnemy enemy) {
        this.enemy = enemy;
    }

    @Override
    public void update() {
        if (enemy.spriteState <= FlyingEnemy.END_FLAP_RIGHT) {
            enemy.travelRight();
        } else {
            enemy.travelLeft();
        }
        if ((int)enemy.x == enemy.fireHere){ 
            enemy.fireProjectile();
        }
    }
    
}
