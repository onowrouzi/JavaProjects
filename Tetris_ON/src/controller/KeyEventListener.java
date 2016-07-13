package controller;

import static controller.Tetris.BOTTOM;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import model.TetrisShape;
import view.GameWindow;

    public class KeyEventListener implements KeyListener{

        @Override
        public void keyTyped(KeyEvent ke) {}

        @Override
        public void keyPressed(KeyEvent ke) {
            if (!Main.game.paused) { //Check if paused.
                switch (ke.getKeyCode()) {
                    case KeyEvent.VK_DOWN: //Check move down.
                        if (Main.game.currentShape.y + Main.game.currentShape.height < BOTTOM) {
                            Main.game.currentShape.moveShape(20, 'y');
                            if (Main.game.currentShape.y + Main.game.currentShape.height == BOTTOM)
                                Main.game.landShape();
                        }
                        break;
                    case KeyEvent.VK_LEFT: //Check move left.
                        if (Main.game.currentShape.x > 300) 
                            Main.game.currentShape.moveShape(-20, 'x');
                        break;
                    case KeyEvent.VK_RIGHT: //Check move right.
                        if (Main.game.currentShape.x + Main.game.currentShape.width < 500) 
                            Main.game.currentShape.moveShape(20, 'x');
                        break;
                    case KeyEvent.VK_UP: //Turn shape.
                        Main.game.currentShape.turnShape();
                        break;
                }
            }
            GameWindow.DRAW_PANEL.draw();
        }

        @Override
        public void keyReleased(KeyEvent ke) {}
        
    }