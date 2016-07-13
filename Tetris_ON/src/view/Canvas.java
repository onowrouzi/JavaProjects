package view;

import controller.Main;
import controller.Tetris;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;
import model.GridPiece;

public class Canvas extends JPanel {
        
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setFont(new Font("Courier New", Font.BOLD, 24));
            
            if (!Tetris.gameOver && !Tetris.paused) { //Game is still playable.
                
                Main.game.currentShape.draw(g2); //Draw current shape.
                
                for (GridPiece grid: Main.game.GRID_PIECES) //Draw all filled spaces.
                    grid.draw(g2);
                
                if (Main.game.CLEARED_LINES.size() > 0) model.Sounds.play("sounds/clearLine.wav"); //Play clearing line sound.
                
                for (Rectangle2D line : Main.game.CLEARED_LINES) { //Clear rectangles in deleted lines.
                    g2.clearRect((int) line.getX(), (int) line.getY(), 
                                (int) line.getWidth(), (int) line.getHeight());
                    g2.setBackground(Color.BLACK);
                    Main.game.updateStats(); //Update scoreboard.
                }
                //Update board.
                Main.game.updateLines();
                //Draw grid.
                g2.setColor(Color.GRAY);
                for (Rectangle2D gr : Main.game.GRID) 
                    g2.draw(gr);
                //Draw scoreboard.
                g2.setColor(Color.WHITE);
                g2.drawString("Score: " + Integer.toString(Tetris.score), 100, 100);
                g2.drawString("Lines: " + Integer.toString(Tetris.lines), 100, 200);
                g2.drawString("Level: " + Integer.toString(Tetris.level), 100, 300);
                g2.drawString("Next Shape:", 580, 100);
                Main.game.nextShape.draw(g2);
                //Draw next shape grid.
                g2.setColor(Color.GRAY);
                for (int i = 120; i < 240; i += 20) {
                    for (int j = 600; j < 720; j+= 20) {
                        g2.drawRect(j,i,20,20);
                    }
                }
            } else  if (Tetris.paused){ //Draw pause screen.
                g2.setColor(Color.WHITE);
                g2.drawString("PAUSED", 360, 275);
            } else { //Draw Game Over screen.
                g2.setColor(Color.WHITE);
                g2.drawString("GAME OVER", 320, 275);
            }
            
        }
        
        public void draw(){
            repaint();
        }

    }