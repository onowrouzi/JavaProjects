package controller;

import javax.swing.JFrame;
import view.GameWindow;

public class Main {
    
    public static GameWindow window;
    public static Tetris game; 
    
    public static void main(String[] args) {
        
        game = new Tetris();
        window = new GameWindow();
        window.setSize(800,600);
        window.setResizable(false);
        window.setLocation(100, 100);
        window.setTitle("Tetris");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        new Thread(game).start();
    }
    
}