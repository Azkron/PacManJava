/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.GameState;
import View.View;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Hugo
 */
public class Controller implements Observer{
    
    private static GameState gameState;
    private static Input input;
    
    
    private static final Controller INSTANCE = new Controller();
    
    public static Controller getInstance()
    {
        return INSTANCE;
    }
    
    private Controller()
    {
    }
    
    
    public static void main(String[] args)
    {
        Controller.getInstance().initialize();
        input.getInput();
        // getInput() calls an infinite loop to get the input and send it 
        // to the Controller via notifyObserver()q
        
    }
    
    private void initialize()
    {
        gameState = GameState.getInstance();
        gameState.addObserver(View.getInstance());
        gameState.updateGameState(Dir.NONE);
        input = new Input();
        input.addObserver(Controller.getInstance());
    }

    @Override
    public void update(Observable inputObj, Object arg) {
        
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

