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
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 *
 * @author 2610titoure
 */
public class ControllerFX extends Application implements Observer{

    private static GameState gameState;
    private static ViewFX viewFX;
    private static InputFX inputFX;
    
    
    private static ControllerFX instance;
    
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

    @Override
    public void start(Stage primaryStage) throws Exception  {
        
        primaryStage.setResizable(false);
        viewFX = viewFX.getInstance();
        viewFX.initialize(primaryStage);
        viewFX.addObserver(this);
        
        gameState = GameState.getInstance();
        gameState.addObserver(viewFX);
        
        inputFX = InputFX.getInstance();
        
        gameState.updateGameState(Dir.NONE);
    }
    
    
    
    public static void main(String[] args) 
    {
        System.out.println("You can press \"x\" for exit");
        launch(args);
    }
    

    @Override
    public void update(Observable inputObj, Object arg) {
        
        Dir d = inputFX.processInput(((ViewFX)inputObj).getKeyPressed());
        
        if(d == null)
            Platform.exit();
            //System.exit(0);
        else
        {
            gameState.updateGameState(d);
            checkGameOver();
        }
    }
    
    private void checkGameOver() {
        if(gameState.getLives() == 0)
        {
            System.out.println("No more lives. GAME-OVER!!!!!!!");
            Platform.exit();
            //System.exit(0);
        }
        else if(gameState.getPhantoms() == 0)
        {
            System.out.println("No more phantoms. YOU WIN!!!!!!! CONGRATS");
            Platform.exit();
            //System.exit(0);
        }
    }
    
}
