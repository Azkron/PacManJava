/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Control.ControllerFX;
import Model.ViewType;
import Model.GameState;
import java.util.HashMap;
import java.util.Map;
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
    ViewType t = ViewType.PACGUM;
    private Map<ViewType, Image> imageMap;
    private Image imgPacManInvincible, imgPacMan;
    
    private final Label lScore = new Label();
    private final Label lLives = new Label();
    private final Label lPhantoms = new Label();
    private final Label lPacGums = new Label();
    
    
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
    
    private ViewFX() {}
    
    
    public void initialize(Stage primaryStage){
        
        ViewType[][] lab = GameState.getInstance().getLabView();
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
        
        loadImages();
        
        primaryStage.setTitle("PAC MAN FX GR1");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
    private void loadImages()
    {
        imgPacManInvincible = new Image("file:images/pacman_invincible.png");
        imgPacMan = new Image("file:images/pacman_normal.png");
        imageMap = new HashMap<>();
        imageMap.put(ViewType.PACMAN, imgPacMan);
        //imageMap.put(Type.PACMAN, new Image("file:images/pacman.png"));
        imageMap.put(ViewType.PACGUM, new Image("file:images/boule_de_gomme.png"));
        imageMap.put(ViewType.MUSHROOM, new Image("file:images/champignon.png"));
        imageMap.put(ViewType.PHANTOM, new Image("file:images/enemy_normal.png"));
        imageMap.put(ViewType.FRUIT, new Image("file:images/fruit.png"));
        imageMap.put(ViewType.WALL, new Image("file:images/wall.png"));
        imageMap.put(ViewType.EMPTY, new Image("file:images/white.gif"));
    }
    
    public void setMainPaneInput(Pane p)
    {
        p.setOnKeyPressed((KeyEvent event) -> {
            setChanged();
            notifyObservers(event.getCode());
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
        drawLabyrinth((GameState) o, (ViewType[][]) arg);
    }
    
    private void drawLabyrinth(GameState g, ViewType[][] lab) {
        
        setPacManImage(g);// checks if the PacMan is super and changes the image if so
            
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, width, height);
        
        for(int y=0; y < lab.length; y++ ) 
            for(int x=0; x < lab[y].length; x++ ) 
                drawType(lab[y][x], x*SIZE, y*SIZE);
        
        displayInfo();
    }
    
    private void setPacManImage(GameState g)
    {
        if(g.getSuperPacMan())
            imageMap.put(ViewType.PACMAN, imgPacManInvincible);
        else
            imageMap.put(ViewType.PACMAN, imgPacMan);
    }
    
    public void drawType(ViewType t, int x, int y) {
        gc.drawImage(imageMap.get(t), x, y,SIZE, SIZE);
    }
    
    public void displayInfo() {
        lPacGums.setText("   Pac-Gums: " +GameState.getPacGum());
        lPhantoms.setText("   - Phantoms: " +GameState.getPhantoms());
        lLives.setText("   - Lives: " +GameState.getLives());
        lScore.setText("   - Score: " +GameState.getScore());
        
    }
      
 }
