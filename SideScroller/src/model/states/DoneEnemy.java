package model.states;

import controller.Animator;
import controller.Main;
import controller.Sounds;
import model.enemies.BossEnemy;
import model.enemies.Enemy;
import model.ui.Score;

public class DoneEnemy implements FigureState {

    public Enemy enemy;
    
    public DoneEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    @Override
    public void update() {
        Score.score += 10;
        if (enemy instanceof BossEnemy) {
            Animator.gameWon = true;
            Sounds.play("sounds/victory.wav");
            Sounds.backgroundMusic.stop();
        }
        Main.gameData.enemyFigures.remove(enemy);
    }
    
}
