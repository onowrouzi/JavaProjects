package model.states;

import model.enemies.BossEnemy;

public class DyingBoss implements FigureState {
    
    public BossEnemy boss;
    public int dyingTimer;
    public int random;
    
    public DyingBoss(BossEnemy boss) {
        this.boss = boss;
        dyingTimer = 100;
        boss.eSize = 10;
        boss.ex = boss.x + (int) (Math.random() * boss.width);
        boss.ey = boss.y + (int) (Math.random() * boss.height);
    }

    @Override
    public void update() {
        if (dyingTimer == 0){
            boss.state = boss.done;
        } else {
            dyingTimer--;
        }
        
        if (boss.eSize < boss.MAX_EXP_SIZE){
            boss.eSize += 5;
        } else {
            boss.ex = boss.x + (int) (Math.random() * boss.width);
            boss.ey = boss.y + (int) (Math.random() * boss.height);
        }
    }
    
}
