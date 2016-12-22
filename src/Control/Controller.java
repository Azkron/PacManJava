/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.GameState;
import View.View;

/**
 *
 * @author Hugo
 */
public class Controller {
    private final GameState state;
    private final View view;
    private final Input input;
    
    public Controller()
    {
        state = new GameState();
        view = new View();
        input = new Input();
    }
    
    private void Initialize()
    {
        
    }
    
    private void MainLoop()
    {
        
    }
    
}
