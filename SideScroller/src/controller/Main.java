package controller;

import javax.swing.JFrame;
import model.GameData;
import view.GamePanel;
import view.MainWindow;

public class Main {
    
    public static GamePanel gamePanel;
    public static GameData gameData;
    public static Animator animator;
    
    public static int WINDOW_WIDTH = 800;
    public static int WINDOW_HEIGHT= 600;
    
    public static void main(String[] args) {
        
        animator = new Animator();
        gameData = new GameData();
        gamePanel = new GamePanel();
        
        JFrame game = new MainWindow();
        game.setUndecorated(true);
        game.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        game.setLocation(100,50);
        game.setResizable(false);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setVisible(true);
        
        new Thread(animator).start();
    }
    
}
