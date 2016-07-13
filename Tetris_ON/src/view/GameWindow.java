package view;

import controller.ButtonListener;
import controller.KeyEventListener;
import controller.Main;
import controller.Tetris;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public final class GameWindow extends JFrame {
    
    public static final Canvas DRAW_PANEL = new Canvas();
    public static final JButton NEW_GAME_BUTTON = new JButton("New Game");
    public static final JButton PAUSE_BUTTON = new JButton("Pause");
    public static final JButton MUTE_BUTTON = new JButton("Music ON");
    public static final JButton SOUNDS_BUTTON = new JButton("Sounds ON");
    public static final JButton CONTROLS_BUTTON = new JButton("Controls");
    private final Dimension dimension = new Dimension(125,25);
    
    public GameWindow(){
        //Initialize draw panel.
        DRAW_PANEL.addKeyListener((KeyListener) new KeyEventListener());
        DRAW_PANEL.setFocusable(true);
        DRAW_PANEL.setBackground(Color.BLACK);
        //Add buttons to button panel.
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(NEW_GAME_BUTTON);
        buttonPanel.add(PAUSE_BUTTON);
        buttonPanel.add(MUTE_BUTTON);
        buttonPanel.add(SOUNDS_BUTTON);
        buttonPanel.add(CONTROLS_BUTTON);
        //Initialize buttons.
        ButtonListener buttonListener = new ButtonListener();
        NEW_GAME_BUTTON.addActionListener(buttonListener);
        NEW_GAME_BUTTON.setFocusable(false);
        NEW_GAME_BUTTON.setPreferredSize(dimension);
        PAUSE_BUTTON.addActionListener(buttonListener);
        PAUSE_BUTTON.setFocusable(false);
        PAUSE_BUTTON.setPreferredSize(dimension);
        MUTE_BUTTON.addActionListener(buttonListener);
        MUTE_BUTTON.setFocusable(false);
        MUTE_BUTTON.setPreferredSize(dimension);
        SOUNDS_BUTTON.addActionListener(buttonListener);
        SOUNDS_BUTTON.setFocusable(false);
        SOUNDS_BUTTON.setPreferredSize(dimension);
        CONTROLS_BUTTON.addActionListener(buttonListener);
        CONTROLS_BUTTON.setFocusable(false);
        CONTROLS_BUTTON.setPreferredSize(dimension);
        //Initialize first shape pieces.
        Main.game.currentShape = Main.game.generateShape();
        Main.game.nextShape = Main.game.generateShape();
        Main.game.nextShape.x = 640;
        Main.game.nextShape.y = 140;
        Main.game.nextShape.setState();
        //Initialize game grid.
        for (int i = 0; i < Tetris.BOTTOM; i += 20) {
            for (int j = 300; j < 500; j += 20)
                Main.game.GRID.add(new Rectangle2D.Float(j,i, 20, 20));
        }
        //Initialize empty lines.
        for (int i = 0; i < Tetris.BOTTOM; i+= 20) {
            Main.game.TOTAL_LINES.add(new Rectangle2D.Float(300,i,200,20));
        }
        
        Container contentPane = getContentPane();
        contentPane.add(DRAW_PANEL, "Center");
        contentPane.add(buttonPanel, "South");
        
    }
    
}