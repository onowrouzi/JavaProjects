package controller;

import model.IShape;
import model.TShape;
import model.JShape;
import model.TetrisShape;
import model.ZShape;
import model.SquareShape;
import model.LShape;
import model.SShape;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import model.GridPiece;
import view.GameWindow;

public final class Tetris implements Runnable{
    
    public final ArrayList<GridPiece> GRID_PIECES = new ArrayList<>();
    public final ArrayList<Rectangle2D> GRID = new ArrayList<>();
    public final ArrayList<Rectangle2D> CLEARED_LINES = new ArrayList<>();
    public final ArrayList<Rectangle2D> TOTAL_LINES = new ArrayList<>();
    public boolean[] filled = new boolean[260];
    public TetrisShape currentShape;
    public TetrisShape nextShape;
    public static int score = 0;
    public static int lines = 0;
    public static int level = 1;
    public static boolean gameOver;
    private final boolean running = true;
    public static boolean paused;
    public static boolean mute;
    public static final int BOTTOM = 500;
    public static int rest = 1500;
    
    public TetrisShape generateShape() { //Randomly select next shape.
        int random = (int) (Math.random() * 7);
        switch (random){
            case 0:
                return new SquareShape();
            case 1:
                return new LShape();
            case 2: 
                return new IShape();
            case 3:
                return new ZShape();
            case 4:
                return new SShape();
            case 5: 
                return new TShape();
            case 6:
                return new JShape();
            default:
                return new SquareShape();
        }
    }
    
    public void getNextShape(){ //Switch out current and next shapes.
        currentShape = nextShape;
        currentShape.x = 360;
        currentShape.y = 0;
        nextShape = generateShape();
        nextShape.x = 640;
        nextShape.y = 140;
        nextShape.setState();
        currentShape.setState();
    }

    public void updateStats(){ //Update score.
        lines += 1;
        score += 20;
        if (lines % 10 == 0) {
            level++;
            rest -= 200; //Make falling faster if level increases.
        }
    }
    
    public synchronized void updateLines() { //Move pieces and check new filled status of grid. 
        for (int i = 0; i < filled.length; i++) { //Reset filled attribute of grid.
            filled[i] = false;
        }
        
        for (GridPiece piece: GRID_PIECES) { //Check if grid is filled at each spot.
            for (int i = 0; i < GRID.size(); i++) {
                if (piece.pieces.get(0).intersects(GRID.get(i)) ||
                        piece.pieces.get(0).contains(GRID.get(i))) {
                    filled[i] = true;
                }
            }
            piece.setState(); 
        }
        
        if (CLEARED_LINES.size() > 0) { //Move or remove pieces after clearing lines.
            for (Rectangle2D line : CLEARED_LINES) {
                int n = GRID_PIECES.size();
                for (int i = 0; i < n; i++) {
                    if (GRID_PIECES.get(i).pieces.get(0).intersects(line)) {
                        GRID_PIECES.remove(i);
                        i--;
                        n--;
                    } else if (line.getY() > GRID_PIECES.get(i).pieces.get(0).getY()) {
                        GRID_PIECES.get(i).y += 20;
                        GRID_PIECES.get(i).setState();
                    } 
                }
            }
        }
        CLEARED_LINES.clear(); //Reset cleared lines.
    }
    
    public void checkLines(){ //Check for new lines to delete.
        int l = 0;
        for (int i = 0, n = 0; i < filled.length; i+=10, n++) {
            for (int j = 0; j < 10; j++) {
                if (!filled[j + i]){
                    l = -1;
                    break;
                } else l = n;
            }
            if (l >= 0) CLEARED_LINES.add(TOTAL_LINES.get(l));
        }
    }
    
    public void landShape() { //Land shape and transform shape to grid piece.
        for (int i = 0; i < GRID.size(); i++) {
            for (Rectangle2D piece : currentShape.pieces) {
                if (piece.intersects(GRID.get(i))) {
                    filled[i] = true;  
                    GRID_PIECES.add(new GridPiece(currentShape.color,(int)piece.getX(),(int)piece.getY()));
                    GRID_PIECES.get(GRID_PIECES.size()-1).setState();
                } 
            }
        }
        getNextShape(); //Switch out current and next shapes.
        checkLines(); //Check for new lines to delete.
        model.Sounds.play("sounds/landShape.wav"); //Play land shape sound.
    }
    
    @Override
    public void run() { //Main game loop.
        long startTime = System.currentTimeMillis();
        long sumTime = startTime;
        model.Sounds.play("sounds/songLoop.wav"); //Initialize background music.
        
        while (running){
            
            long timePassed = System.currentTimeMillis() - sumTime;
            sumTime += timePassed;
            
            if (!paused) { //Check if paused.
                if (currentShape.y + currentShape.height < BOTTOM) { //Check if shape can still be moved.
                    int y = currentShape.y;
                    currentShape.moveShape(20, 'y');
                    if (currentShape.y == y) {
                        if (y == 0)
                            gameOver = true;
                        else
                            landShape();
                    }
                } else {
                    landShape();
                }
            }

            GameWindow.DRAW_PANEL.draw(); //Refresh screen.

            try {
                if (CLEARED_LINES.size() > 0) {
                    Thread.sleep(200); //Rest only .2 seconds for clearing lines.
                } else {
                    Thread.sleep(rest); //Rest for the variable time of 'rest'.
                }
            } catch (Exception ex) {}

        }
    }

}