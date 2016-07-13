package controller;

import model.enemies.GroundEnemy;
import model.MovableFigure;
import model.Player;
import model.enemies.BossEnemy;

public class Animator implements Runnable {
    
    public boolean running = true;
    public static boolean paused;
    public static boolean gameOver;
    public static boolean gameWon;
    private final int FRAMES_PER_SECOND = 20;
    public static int loading;

    @Override
    public void run() {
        
        Sounds.play("sounds/songLoop.wav");
        loading = 60;
        
        while (running){
            long startTime = System.currentTimeMillis();
            
            if (!paused && !gameOver) {
                if (loading == 0) {
                    processCollisions();
                    Main.gameData.update();
                } else {
                    loading--;   
                }
                Main.gamePanel.gameRender();
                Main.gamePanel.printScreen();
            }
            
            long endTime = System.currentTimeMillis();
            int sleepTime = (int) (1.0/FRAMES_PER_SECOND*1500)-(int)(endTime-startTime);
            
            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e){}
            }
        }
        System.exit(0); 
    }
        
    private synchronized void processCollisions() {
        for (MovableFigure f : Main.gameData.friendFigures) {
            for (MovableFigure e : Main.gameData.enemyFigures) {
                if (e.getCollisionBox().intersects(f.getCollisionBox())
                        && (e.state == e.alive || e.state == e.hurt)){
                    handleCollisions(e,f); 
                }
            }
        }
    }
    
    private synchronized void handleCollisions(MovableFigure e, MovableFigure f) {
        if (f instanceof Player) {
            if (Main.gameData.player.spriteState == Player.MELEE_LEFT || 
                    Main.gameData.player.spriteState == Player.MELEE_RIGHT) { 
                if (e instanceof BossEnemy) {
                    BossEnemy boss = (BossEnemy) e;
                    boss.hurt(); 
                } else if (Main.gameData.player.spriteState == Player.MELEE_LEFT
                        && e.spriteState < GroundEnemy.DEAD_LEFT 
                        && e.x > Main.gameData.player.x) {
                    Main.gameData.player.hurt(); 
                } else if (Main.gameData.player.spriteState == Player.MELEE_RIGHT
                        && e.spriteState > GroundEnemy.DEAD_LEFT
                        && e.x < Main.gameData.player.x) {
                    Main.gameData.player.hurt(); 
                } else {
                    e.state = e.dying;
                }
            } else if (Main.gameData.player.descend || Main.gameData.player.ascend) {
                if (e instanceof model.enemies.GroundEnemy){
                    GroundEnemy enemy = (GroundEnemy) e;
                    if (enemy.type == 'A') {
                        e.state = e.dying;
                        Main.gameData.player.bounceOff();
                    } else {
                        Main.gameData.player.hurt();
                        Main.gameData.player.bounceBack();
                    }
                } else {
                    Main.gameData.player.hurt();
                }
            } else {
                Main.gameData.player.hurt();
                if (e instanceof model.Projectile){
                    e.state = e.dying;
                }
            }
        } else {
            if (e instanceof BossEnemy){
                BossEnemy boss = (BossEnemy) e;
                boss.hurt();
            } else {
                e.state = e.dying;
                f.state = f.dying;
            }
        }
    }
    
}