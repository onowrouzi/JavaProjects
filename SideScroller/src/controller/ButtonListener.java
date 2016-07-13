package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.GameData;
import view.MainWindow;

public class ButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == MainWindow.pauseButton) {
            Animator.paused = !Animator.paused;
            Main.gamePanel.gameRender();
            Main.gamePanel.printScreen();
        } else if (ae.getSource() == MainWindow.startButton) {
            Main.gameData.setStageOne();
        } else if (ae.getSource() == MainWindow.bossButton) {
            Main.gameData.setBossStage();
            
        } else if (ae.getSource() == MainWindow.muteButton){
            Sounds.mute = !Sounds.mute;
            if (Sounds.mute){
                Sounds.backgroundMusic.stop();
            } else {
                if (GameData.stage == 1) {
                    Sounds.play("sounds/songLoop.wav");
                } else {
                    Sounds.play("sounds/bossLoop.wav");
                }
            }
        } else if (ae.getSource() == MainWindow.creditsButton){
            Animator.paused = true;
            JOptionPane.showMessageDialog(null,
                    "\n               Game by Omid Nowrouzi \n \n" + 
                    "    Sprites downloaded from opengameart.org \n" +
                    "    Sounds downloaded from freesound.org \n \n" , "Credits" , 
                    JOptionPane.INFORMATION_MESSAGE);
            Animator.paused = false;
        } else if (ae.getSource() == MainWindow.exitButton){
            System.exit(0);
        }
    }
    
}
