package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class KeyController implements KeyListener {
    
    private final ArrayList<Integer> keys = new ArrayList<>();

    @Override
    public void keyPressed(KeyEvent ke) {
        
        keys.add(ke.getKeyCode());
        for (int i = 0; i < keys.size()-1; i++){
            if (keys.get(i) == ke.getKeyCode()){
                keys.remove(keys.size()-1);
            } 
        }
        
        switch (ke.getKeyCode()) {
            case KeyEvent.VK_D:
                Main.gameData.player.travelRight();
                break;
            case KeyEvent.VK_A:
                Main.gameData.player.travelLeft();
                break;
            case KeyEvent.VK_W:
                if (!Main.gameData.player.ascend && !Main.gameData.player.descend) {
                    Sounds.play("sounds/jumpSound.wav");
                }
                for (Integer i : keys) {
                    if (i == KeyEvent.VK_D || i == KeyEvent.VK_A)
                        Main.gameData.player.jump(i);
                    else 
                        Main.gameData.player.jump(0);
                }
                break;
            case KeyEvent.VK_SPACE:
                Main.gameData.player.meleeAttack();
                Sounds.play("sounds/swordSound.wav");
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {}
    
    @Override
    public void keyReleased(KeyEvent ke) {
        for (int i = 0; i < keys.size(); i++) {
            if (keys.get(i) == ke.getKeyCode()){
                keys.remove(i);
            }
        }
    }
    
}
