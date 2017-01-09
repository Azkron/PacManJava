/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Control.Type;
import Model.GameState;
import java.util.ArrayList;
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
public class ViewFX extends Application implements Observer{
    private static final ViewFX instance = new ViewFX();
    
    private final Image imgPacGum = new Image("file:images/boule-de-gomme.png");
    private final Image imgMushroom = new Image("file:images/champignon.png");
    private final Image imgPhantom = new Image("file:images/enemy_normal.png");
    private final Image imgFruit = new Image("file:images/fruit.png");
    private final Image imgPacMan = new Image("file:images/pacman.png");
    private final Image imgpacman_invincible = new Image("file:images/pacman_invincible.png");
    private final Image img = new Image("file:images/boule-de-gomme.png");
    private final Image img = new Image("file:images/boule-de-gomme.png");
    private final Image img = new Image("file:images/boule-de-gomme.png");

    
    private final Canvas canvas = new Canvas();
    GraphicsContext gc = canvas.getGraphicsContext2D();
    private static final int TAILLE = 20;
    
    public static ViewFX getInstance()
    {
        return instance;
    }
    
    private ViewFX()
    {
        
    }
    
    @Override
    public void update(Observable o, Object arg) {
        drawLabyrinth((GameState) o);
    }
    
    private void drawLabyrinth(GameState g) {
        ArrayList<ArrayList<Type>> lab = g.getLabView();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for(int y=0; y < lab.size(); y++ ) 
            for(int x=0; x < lab.get(y).size(); x++ ) 
                drawType(lab.get(y).get(x), x, y);
        displayInfo();
    }
    
    public void drawType(Type t, int x, int y) {
        String s = "";
        switch(t) 
        {
            case PACMAN : s = "O";
                break;
            case PHANTOM : s = "A";
                break;
            case PACGUM : s = "°";
                break;
            case FRUIT : s = "F";
                break;
            case MUSHROOM : s = "M";
                break;
            case WALL : s = "#";
                break;
            case EMPTY: s = " ";
                break;
            default : s = " ";
                break;
        }
        System.out.print(s);
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

    @Override
    public void start(Stage primaryStage) throws Exception {
       
        Pane root = new Pane() {
            @Override
            protected void layoutChildren() {
                canvas.setWidth(100);
                canvas.setHeight(500);
                gc.drawImage(
                    img, 
                    (getWidth() - TAILLE) / 2, 
                    (getHeight() - TAILLE) / 2, 
                    TAILLE, 
                    TAILLE
                );

            }
        };

        root.getChildren().add(canvas);

        Scene scene = new Scene(root, 400, 200);

        primaryStage.setTitle("Exemple d'affichage d'une Image sur un Canvas");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
