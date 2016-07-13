package model.states;

import controller.Sounds;
import model.enemies.Enemy;
import model.enemies.FlyingEnemy;

public class DyingFlyingEnemy implements FigureState {

    public Enemy enemy;
    public boolean fallingLeft;
    public boolean fallingRight;
    
    public DyingFlyingEnemy(FlyingEnemy enemy) {
        
        this.enemy = enemy;
    }

    @Override
    public void update() {
        if (!fallingLeft && !fallingRight) {
            if (enemy.spriteState < FlyingEnemy.DEAD_RIGHT) {
                enemy.spriteState = FlyingEnemy.DEAD_RIGHT;
                fallingRight = true;
            } else {
                enemy.spriteState = FlyingEnemy.DEAD_LEFT;
                fallingLeft = true;
            }
            Sounds.play("sounds/dyingFlyingEnemySound.wav");
        } else {
            if (fallingLeft && enemy.spriteState < FlyingEnemy.END_LEFT){
                enemy.spriteState++;
            } else if (fallingRight && enemy.spriteState < FlyingEnemy.END_RIGHT) {
                enemy.spriteState++;
            }
            enemy.y += 20;
        }
        if (enemy.y > 600) {
            enemy.state = enemy.done;
        }
    }
    
}
