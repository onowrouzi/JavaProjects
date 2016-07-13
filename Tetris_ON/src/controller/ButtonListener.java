package controller;

import static controller.Tetris.gameOver;
import static controller.Tetris.level;
import static controller.Tetris.lines;
import static controller.Tetris.score;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
import model.Sounds;
import view.GameWindow;

public class ButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();

        if (source == GameWindow.NEW_GAME_BUTTON) { //Reset game.
            Main.game.GRID_PIECES.clear();
            gameOver = false;
            Main.game.currentShape = Main.game.generateShape();
            Main.game.currentShape.x = 360;
            Main.game.currentShape.y = 0;
            Main.game.currentShape.setState();
            Main.game.nextShape = Main.game.generateShape();
            Main.game.nextShape.x = 640;
            Main.game.nextShape.y = 140;
            Main.game.nextShape.setState();
            score = 0;
            lines = 0;
            level = 1;
            Tetris.rest = 1500;
        } else if (source == GameWindow.PAUSE_BUTTON) { //Toggle paused state.
            Tetris.paused = !Tetris.paused;
        } else if (source == GameWindow.MUTE_BUTTON) { //Toggle background music.
            if (Sounds.backgroundMusic.isRunning()) {
                Sounds.backgroundMusic.stop();
                GameWindow.MUTE_BUTTON.setText("Music OFF");
            } else {
                Sounds.backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
                Sounds.backgroundMusic.start();
                GameWindow.MUTE_BUTTON.setText("Music ON");
            }
        } else if (source == GameWindow.SOUNDS_BUTTON) { //Toggle sound effects.
            if (Tetris.mute) {
                Tetris.mute = false;
                GameWindow.SOUNDS_BUTTON.setText("Sounds ON");
            } else {
                Tetris.mute = true;
                GameWindow.SOUNDS_BUTTON.setText("Sounds OFF");
            }
        } else if (source == GameWindow.CONTROLS_BUTTON) { //Show controls.
            Tetris.paused = true;
            JOptionPane.showMessageDialog(null,
                    "     Move left = left arrow \n" + 
                    "    Move right = right arrow \n" +
                    "    Move down = down arrow \n" + 
                    "     Turn shape = up arrow", "Controls" , 
                    JOptionPane.INFORMATION_MESSAGE);
            Tetris.paused = false;
        }

        GameWindow.DRAW_PANEL.draw();

    }

}