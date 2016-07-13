package view;

import controller.Animator;
import controller.Main;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.JPanel;
import model.GameData;
import model.GameFigure;

public class GamePanel extends JPanel {
    
    public static int width;
    public static int height;
    
    private Graphics2D g2;
    private Image doubleBufferImage = null;
    
    public void gameRender(){
        width = getSize().width;
        height = getSize().height;
        if (doubleBufferImage == null) {
            doubleBufferImage = createImage(width,height);
            if (doubleBufferImage == null) {
                System.out.println("ERROR: FAILED TO INITIALIZE DOUBLE BUFFER IMAGE");
                System.exit(1);
            } else {
                g2 = (Graphics2D) doubleBufferImage.getGraphics();
            }
        }
        
        g2.clearRect(0, 0, width, height);
        if(Main.animator.running) {
            
            if (Animator.gameWon) {
                
                g2.setColor(Color.WHITE);
                g2.setFont(new Font("Impact", Font.BOLD, 100));
                g2.drawString("YOU WON!!!", width/2-250, height/2);
                
            } else if (Animator.loading > 0){
                
                g2.setBackground(Color.BLACK);
                g2.setColor(Color.WHITE);
                g2.setFont(new Font("Impact", Font.BOLD, 50));
                g2.drawString("LOADING STAGE " + Integer.toString(GameData.stage), width/3-50, height/2-100);
                g2.drawString(" IN " + Integer.toString(Animator.loading/10), width/2-50, height/2-25);
                g2.setFont(new Font("Impact", Font.BOLD, 25));
                g2.drawString("    Controls: ", width/3, height/2+30);
                g2.drawString("    W = JUMP", width/3, height/2+80);
                g2.drawString("    A = LEFT", width/3, height/2+110);
                g2.drawString("    D = RIGHT", width/3, height/2+140);
                g2.drawString("    SPACE = MELEE", width/3, height/2+170);
                g2.drawString("    LEFT CLICK = SHOOT", width/3, height/2+200);
                
            } else if (!Animator.gameOver) {
                
                synchronized (Main.gameData.uiFigures){
                    for (GameFigure u : Main.gameData.uiFigures) {
                        u.render(g2);
                    }
                }
                
                synchronized (Main.gameData.friendFigures) {
                    for (GameFigure f : Main.gameData.friendFigures) {
                        f.render(g2);
                    }
                }

                synchronized (Main.gameData.enemyFigures) {
                    for (GameFigure e : Main.gameData.enemyFigures) {
                        e.render(g2);
                    }
                }
                
                if (Animator.paused) {
                    g2.setColor(Color.BLACK);
                    g2.setFont(new Font("Impact", Font.BOLD, 100));
                    g2.drawString("PAUSED", width / 2 - 175, height / 2);
                }
                
            } else {
                
                g2.setColor(Color.WHITE);
                g2.setFont(new Font("Impact", Font.BOLD, 100));
                g2.drawString("GAME OVER", width/2-250, height/2);
                
            } 
        }
    }
    
    public void printScreen() {
        Graphics g;
        try {
            g = this.getGraphics();
            if ((g != null) && (doubleBufferImage != null)){
                g.drawImage(doubleBufferImage,0,0,null);
            }
            Toolkit.getDefaultToolkit().sync();
            if (g!= null) {
                g.dispose();
            }
        } catch (Exception e) {
            System.out.println("Graphics error: " + e);
        }
    }
    
}

