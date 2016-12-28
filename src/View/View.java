/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Hugo
 */
public class View implements Observer{
    private static final View instance = new View();
    
    public static View getInstance()
    {
        return instance;
    }
    
    private View()
    {
        
    }
    
    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
