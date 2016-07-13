package view;

import controller.ButtonListener;
import controller.KeyController;
import controller.Main;
import controller.MouseController;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JFrame {
    
    public static JButton startButton;
    public static JButton pauseButton;
    public static JButton bossButton;
    public static JButton muteButton;
    public static JButton creditsButton;
    public static JButton exitButton;
    public Dimension dmnsn = new Dimension(125, 30);
    public Font buttonFont = new Font("Impact", Font.PLAIN, 16);
    
    public MainWindow() {
        Container contentPane = getContentPane();
        contentPane.add(Main.gamePanel, "Center");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        
        startButton = new JButton("RESTART");
        pauseButton = new JButton("PAUSE");
        bossButton = new JButton("BOSS LEVEL");
        muteButton = new JButton("MUTE");
        creditsButton = new JButton("CREDITS");
        exitButton = new JButton("EXIT");
        
        startButton.setPreferredSize(dmnsn);
        pauseButton.setPreferredSize(dmnsn);
        bossButton.setPreferredSize(dmnsn);
        muteButton.setPreferredSize(dmnsn);
        creditsButton.setPreferredSize(dmnsn);
        exitButton.setPreferredSize(dmnsn);
        
        startButton.setBackground(Color.BLACK);
        startButton.setForeground(Color.WHITE);
        startButton.setFont(buttonFont);
        pauseButton.setBackground(Color.BLACK);
        pauseButton.setForeground(Color.WHITE);
        pauseButton.setFont(buttonFont);
        bossButton.setBackground(Color.BLACK);
        bossButton.setForeground(Color.WHITE);
        bossButton.setFont(buttonFont);
        muteButton.setBackground(Color.BLACK);
        muteButton.setForeground(Color.WHITE);
        muteButton.setFont(buttonFont);
        creditsButton.setBackground(Color.BLACK);
        creditsButton.setForeground(Color.WHITE);
        creditsButton.setFont(buttonFont);
        exitButton.setBackground(Color.BLACK);
        exitButton.setForeground(Color.WHITE);
        exitButton.setFont(buttonFont);
        
        buttonPanel.add(startButton);
        buttonPanel.add(pauseButton);
        buttonPanel.add(bossButton);
        buttonPanel.add(muteButton);
        buttonPanel.add(creditsButton);
        buttonPanel.add(exitButton);
        
        ButtonListener buttonListener = new ButtonListener();
        startButton.addActionListener(buttonListener);
        pauseButton.addActionListener(buttonListener);
        bossButton.addActionListener(buttonListener);
        muteButton.addActionListener(buttonListener);
        creditsButton.addActionListener(buttonListener);
        exitButton.addActionListener(buttonListener);
        
        MouseController mouseController = new MouseController();
        Main.gamePanel.addMouseListener(mouseController);
        
        KeyController keyListener = new KeyController();
        Main.gamePanel.addKeyListener(keyListener);
        Main.gamePanel.setFocusable(true);
        startButton.setFocusable(false);
        pauseButton.setFocusable(false);
        bossButton.setFocusable(false);
        muteButton.setFocusable(false);
        creditsButton.setFocusable(false);
        exitButton.setFocusable(false);
        
        contentPane.add(buttonPanel, "South");
    }
    
}
