/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.GameState;
import View.ViewFX;
import java.util.Observable;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author 2610titoure
 */
public class ControllerFX extends Application{

    private static GameState gameState;
    private static ViewFX viewFX;
    
    
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
    public void start(Stage primaryStage) throws Exception {
        
        viewFX = viewFX.getInstance();
        gameState = GameState.getInstance();
        gameState.addObserver(viewFX.getInstance());
        viewFX.initialize(primaryStage);
        gameState.updateGameState(Dir.NONE);
    }
    
    
    public static void main(String[] args) 
    {
        launch(args);
    }
    

    
    public void processInput(Observable inputObj, Object arg) {
        
        Dir d = ((Input)inputObj).getDir();
        
        if(d != Dir.NONE)
        {
            gameState.updateGameState(d);
            checkGameOver();
        }
        else
            System.exit(0);
    }
    
    private void checkGameOver() {
        if(gameState.getLives() == 0)
        {
            System.out.println("No more lives. GAME-OVER!!!!!!!");
            System.exit(0);
        }
        else if(gameState.getPhantoms() == 0)
        {
            System.out.println("No more phantoms. YOU WIN!!!!!!! CONGRATS");
            System.exit(0);
        }
    }
    
}
