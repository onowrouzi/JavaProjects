package controller;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import model.Projectile;

public class MouseController extends MouseAdapter {
    private int px;
    private int py;
    
    @Override
    public void mousePressed(MouseEvent me) {
        px = me.getX();
        py = me.getY();

        if (Main.gameData.player.x < px) {
            Main.gameData.player.spriteState = 3;
        } else {
            Main.gameData.player.spriteState = 7;
        }
        
        if (Main.gameData.player.bulletCount > 0) {
            Projectile p = new Projectile(
                    Main.gameData.player.getXofMissileShoot(),
                    Main.gameData.player.getYofMissileShoot(),
                    px, py, // target location where the missile explodes
                    Color.BLACK, Color.LIGHT_GRAY, Main.gameData.player);

            synchronized (Main.gameData.friendFigures) {
                Main.gameData.friendFigures.add(p);
            }

            Sounds.play("sounds/gunSound.wav");

            Main.gameData.player.bulletCount--;
        } else {
            Sounds.play("sounds/gunEmptySound.wav");
        }
    }
}
