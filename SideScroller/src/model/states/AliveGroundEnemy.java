package model.states;

import model.enemies.Enemy;
import model.enemies.GroundEnemy;
import static model.enemies.GroundEnemy.DEAD_LEFT;

public class AliveGroundEnemy implements FigureState {

    public Enemy enemy;
    
    public AliveGroundEnemy(GroundEnemy enemy) {
        this.enemy = enemy;
    }

    @Override
    public void update() {
        if (enemy.spriteState < DEAD_LEFT) {
            enemy.travelLeft();
        } else {
            enemy.travelRight();
        }
    }
    
}
