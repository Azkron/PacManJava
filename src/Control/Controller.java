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
        gameState = GameState.getInstance();
        gameState.addObserver(View.getInstance());
        input = new Input();
        input.addObserver(Controller.getInstance());
        input.getInput();
        // getInput() calls an infinite loop to get the input and send it 
        // to the Controller via notifyObserver()
    }
    

    @Override
    public void update(Observable inputObj, Object arg) {
        
        Dir d = ((Input)inputObj).getDir();
        
            System.out.println("exit");
        if(d != null)
            gameState.updateGameState(d);
        else
        {
            System.exit(0);
        }
    }
    
    
}

