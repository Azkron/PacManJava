/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.GameState;
import View.ViewFX;
import java.util.Observable;
import java.util.Observer;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author 2610titoure
 */
public class ControllerFX extends Application implements Observer{

    private static GameState gameState;
    private static ViewFX viewFX;
    private static InputFX inputFX;
    
    private static double frameTime = 100;
    
    private static ControllerFX instance;
    
    private static Dir dir = Dir.NONE;
    
    public static ControllerFX getInstance()
    {
        return instance;
    }
    
    public ControllerFX()
    {
        if(instance == null)
            instance = this;
        else
            throw new RuntimeException("There can only be one controller");
    }
    
    public static void main(String[] args) 
    {
        System.out.println("You can press \"x\" for exit");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception  {
        
        primaryStage.setResizable(false);
        viewFX = viewFX.getInstance();
        viewFX.initialize(primaryStage);
        viewFX.addObserver(this);
        
        gameState = GameState.getInstance();
        gameState.addObserver(viewFX);
        
        inputFX = InputFX.getInstance();
        
        play();
    }
    
    
    private void play() {
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(frameTime),
                ae -> nextFrame())
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();                    
    }
    
    private void nextFrame()
    {
        gameState.updateGameState(dir);
        dir = Dir.NONE;            
        checkGameOver();
    }

    @Override
    public void update(Observable inputObj, Object arg) {
        
        dir = inputFX.processInput((KeyCode)arg);
        
        if(dir == null) // user pressed x
            Platform.exit();
            //System.exit(0);
    }
    
    public static double getFrameTime()
    {
        return frameTime;
    }
    
    private void checkGameOver() {
        
        String endMsg = null;
        if(gameState.getLives() <= 0)
            endMsg = "No more lives. GAME-OVER!!!!!!!";
        else if(gameState.getPhantoms() == 0)
            endMsg = "No more phantoms. YOU WIN!!!!!!! CONGRATS";
        
        if(endMsg != null)
        {
            System.out.println(endMsg);
            Platform.exit();
            //System.exit(0);
        }
    }
    
}
