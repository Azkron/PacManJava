/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Control.ControllerFX;
import Control.InputFX;
import Control.Type;
import Model.GameState;
import Model.PacMan;
import java.util.Observable;
import java.util.Observer;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author 2610titoure
 */
public class ViewFX extends Observable implements Observer{
    
    ControllerFX controller = null;
    
    private final Image imgPacGum = new Image("file:images/boule_de_gomme.png");
    private final Image imgMushroom = new Image("file:images/champignon.png");
    private final Image imgPhantom = new Image("file:images/enemy_normal.png");
    private final Image imgFruit = new Image("file:images/fruit.png");
    //private final Image imgPacMan = new Image("file:images/pacman.png");
    private final Image imgPacmanInvincible = new Image("file:images/pacman_invincible.png");
    private final Image imgPacmanNormal = new Image("file:images/pacman_normal.png");
    private final Image imgWall = new Image("file:images/wall.png");
    private final Image imgWhite = new Image("file:images/white.gif");
    
    private final Label lScore = new Label();
    private final Label lLives = new Label();
    private final Label lPhantoms = new Label();
    private final Label lPacGums = new Label();
    private KeyCode keyPressed = null;
    
    BorderPane mainPane;
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
        
        
        BorderPane mainPane = new BorderPane(); 
        setMainPaneInput(mainPane);
        
        Canvas canvas = new Canvas(width, height);
        gc = canvas.getGraphicsContext2D();
        
        HBox infoBox = createInfoBox();
        
        mainPane.setTop(canvas);
        mainPane.setBottom(infoBox);
        
        Scene scene = new Scene(mainPane, width, height+15);
        
        scene.onKeyPressedProperty().bind(mainPane.onKeyPressedProperty());
        
        primaryStage.setTitle("PAC MAN FX GR1");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
    public KeyCode getKeyPressed()
    {
        return keyPressed;
    }
    
    public void setMainPaneInput(Pane p)
    {
        p.setOnKeyPressed((KeyEvent event) -> {
            keyPressed = event.getCode();
            setChanged();
            notifyObservers();
        });
    }
    
    
    private HBox createInfoBox() {
        HBox infoBox = new HBox();
        infoBox.getChildren().addAll(lPacGums, lPhantoms, lLives, lScore);
        infoBox.setAlignment(Pos.CENTER);
        for(Node n : infoBox.getChildren())
        {
            Label l = (Label)n;
            l.setAlignment(Pos.CENTER);
            l.minWidth(width);
        }
        
        return infoBox;
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
                           if(PacMan.getSuper())
                               img = imgPacmanInvincible;
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
        lPacGums.setText("   Pac-Gums: " +GameState.getPacGum());
        lPhantoms.setText("   - Phantoms: " +GameState.getPhantoms());
        lLives.setText("   - Lives: " +GameState.getLives());
        lScore.setText("   - Score: " +GameState.getScore());
        
    }
      
 }
