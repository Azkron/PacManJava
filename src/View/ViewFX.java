/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Control.ControllerFX;
import Control.Type;
import Model.GameState;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author 2610titoure
 */
public class ViewFX implements Observer{
    
    ControllerFX controller = null;
    
    private final Image imgPacGum = new Image("file:images/boule_de_gomme.png");
    private final Image imgMushroom = new Image("file:images/champignon.png");
    private final Image imgPhantom = new Image("file:images/enemy_normal.png");
    private final Image imgFruit = new Image("file:images/fruit.png");
    private final Image imgPacMan = new Image("file:images/pacman.png");
    private final Image imgPacmanInvincible = new Image("file:images/pacman_invincible.png");
    private final Image imgBouleDeGomme = new Image("file:images/boule-de-gomme.png");
    private final Image imgPacmanNormal = new Image("file:images/pacman_normal.png");
    private final Image imgWall = new Image("file:images/wall.png");
    private final Image imgWhite = new Image("file:images/white.gif");
    
    private static final int SIZE = 20;
    private Canvas canvas = new Canvas();
    private GraphicsContext gc = canvas.getGraphicsContext2D();
    
    private static ViewFX instance; 
    
    public static ViewFX getInstance()
    {
        if(instance == null)
            instance = new ViewFX();
        
        return instance;
    }
    
    private ViewFX() 
    { 
    }
    
    
    public void initialize(Stage primaryStage){   
        Type[][] lab = GameState.getInstance().getLabView();
        int ySize = lab.length, xSize = lab[0].length, height = ySize * SIZE, width = xSize * SIZE;
        
        Pane root = new Pane() {
            @Override
            protected void layoutChildren() {
                canvas.setWidth(width);
                canvas.setHeight(height);
            }
        };
        
        canvas = new Canvas();
        gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        
        Scene scene = new Scene(root, width, height);
        primaryStage.setTitle("PAC MAN FX GR1");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
    @Override
    public void update(Observable o, Object arg) {
        drawLabyrinth((GameState) o);
    }
    
    private void drawLabyrinth(GameState g) {
        
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        
        Type[][] lab = g.getLabView();
        for(int y=0; y < lab.length; y++ ) 
            for(int x=0; x < lab[y].length; x++ ) 
                drawType(lab[y][x], x*SIZE, y*SIZE);
        
        displayInfo();
    }
    
    public void drawType(Type t, int x, int y) {
        Image img;
        switch(t) 
        {
            case PACMAN : img = imgPacmanNormal;
                break;
            case PHANTOM : img = imgPhantom;
                break;
            case PACGUM : img = imgPacGum;
                break;
            case FRUIT : img = imgFruit;
                break;
            case MUSHROOM : img = imgMushroom;
                break;
            case WALL : img = imgWall;
                break;
            case EMPTY: img = imgWhite;
                break;
            default : img = imgWhite;
                break;
        }
        
        gc.drawImage(img, x, y,SIZE, SIZE);
    }
    
    public void displayInfo() {
        System.out.println("");
        System.out.println("-------------------------------------------------------");
        System.out.print("Lives: " +GameState.getLives());
        System.out.print("   Score: " +GameState.getScore());
        System.out.print("   Phantoms: " +GameState.getPhantoms());
        System.out.print("   Pac-Gums: " +GameState.getPacGum());
        System.out.println("");
        System.out.println("-------------------------------------------------------");
        
    }

    
}
