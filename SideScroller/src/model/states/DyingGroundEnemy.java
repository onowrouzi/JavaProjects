package model.states;

import controller.Sounds;
import model.enemies.Enemy;
import model.enemies.GroundEnemy;
import static model.enemies.GroundEnemy.DEAD_LEFT;
import static model.enemies.GroundEnemy.DEAD_RIGHT;

public class DyingGroundEnemy implements FigureState {

    public Enemy enemy;
    
    public DyingGroundEnemy(GroundEnemy enemy) {
        this.enemy = enemy;
    }

    @Override
    public void update() {
        if (enemy.spriteState < DEAD_LEFT) {
            enemy.spriteState = DEAD_LEFT;
        } else {
            enemy.spriteState = DEAD_RIGHT;
        }
        Sounds.play("sounds/dyingGroundEnemySound.wav");
        enemy.state = enemy.done;
    }
    
}
