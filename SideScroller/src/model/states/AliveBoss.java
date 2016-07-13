package model.states;

import controller.Sounds;
import model.states.FigureState;
import model.enemies.BossEnemy;

public class AliveBoss implements FigureState {
    
    public BossEnemy boss;

    public AliveBoss(BossEnemy boss) {
        this.boss = boss;
    }

    @Override
    public void update() { 
        if (boss.immuneTimer > 0){
            boss.state = boss.hurt;
            if (boss.spriteState == boss.FACE_LEFT){
                boss.spriteState = boss.HURT_LEFT;
            } else {
                boss.spriteState = boss.HURT_RIGHT;
            }
        }
        
        if (boss.attackTimer < 0){
            boss.fireProjectile();
            Sounds.play("sounds/gunSound.wav");
            boss.attackTimer = 100;
        }
        
    }
    
}
