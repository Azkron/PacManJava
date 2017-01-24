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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
    
    private final TextField tfScore = new TextField();
    private final TextField tfLives = new TextField();
    private final TextField tfPhantoms = new TextField();
    private final TextField tfPacGums = new TextField();
    
    private HBox boxInfo;
    int ySize, xSize, height, width;
    
    private static final int SIZE = 20;
    private GraphicsContext gc;
    
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
        ySize = lab.length;
        xSize = lab[0].length;
        height = ySize * SIZE;
        width = xSize * SIZE;
        
        
        BorderPane root = new BorderPane(); //{
//            @Override
//            protected void layoutChildren() {
//                canvas.setWidth(width);
//                canvas.setHeight(height);
//                
//            }
//        };
        
        Canvas canvas = new Canvas(width, height);
        boxInfo = new HBox();
        layout();
        gc = canvas.getGraphicsContext2D();
        //boxInfo.setPrefHeight(50);
        root.setTop(canvas);

        root.setBottom(boxInfo);
        
        Scene scene = new Scene(root, width, height);
        primaryStage.setTitle("PAC MAN FX GR1");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
    private void layout() {
        //beautify();
        boxInfo.getChildren().addAll(tfScore, tfLives, tfPhantoms, tfPacGums);
    }
    
    private void beautify() {
        tfScore.setPrefColumnCount(3);
        tfLives.setPrefColumnCount(3);
        tfPhantoms.setPrefColumnCount(3);
        tfPacGums.setPrefColumnCount(3);
        
        boxInfo.setSpacing(10);
        boxInfo.setPadding(new Insets(5, 5, 0, 5));
        boxInfo.setAlignment(Pos.BOTTOM_CENTER);

    }
    
    @Override
    public void update(Observable o, Object arg) {
        drawLabyrinth((GameState) o);
    }
    
    private void drawLabyrinth(GameState g) {
        
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, width, height);
        
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
        tfLives.setText("   Lives: " +GameState.getLives());
        tfScore.setText("   Score: " +GameState.getScore());
        tfPhantoms.setText("   Phantoms: " +GameState.getPhantoms());
        tfPacGums.setText("   Pac-Gums: " +GameState.getPacGum());
    }
      
 }
