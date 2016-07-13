package model.states;

import controller.Main;
import controller.Sounds;
import model.enemies.BossEnemy;

public class HurtBoss implements FigureState {

    public BossEnemy boss; 
    public boolean attack;
    
    public HurtBoss(BossEnemy boss) {
        this.boss = boss;
    }

    @Override
    public void update() {
        if (boss.health == 0){
            boss.state = boss.dying;
            Sounds.play("sounds/ufoExplosion.wav");
        } else if (boss.immuneTimer > 0){
            boss.immuneTimer--;
            if (attack) {
                boss.dropAttack();
            } else if (boss.x < Main.gameData.player.x + Main.gameData.player.width/2
                && boss.x > Main.gameData.player.x - Main.gameData.player.width/2
                && !boss.hasAttacked) {
                attack = true;
            } else if (boss.spriteState <= boss.HURT_LEFT){
                boss.travelLeft();
            } else {
                boss.travelRight();
            }
        } else {
            boss.state = boss.alive;
            boss.hasAttacked = false;
            attack = false;
            if (boss.x <= Main.gameData.player.x) {
                boss.spriteState = boss.FACE_RIGHT;
            } else {
                boss.spriteState = boss.FACE_LEFT;
            }
        }
    }
    
}
