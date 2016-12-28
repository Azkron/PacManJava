/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Control.Type;

/**
 *
 * @author Hugo
 */
public class PacGum implements Consumable{

    static private int total;
    
    PacGum()
    {
        ++total;
    }
    
    public static int getTotal()
    {
        return total;
    }
    
    @Override
    public void Consume() {
        GameState.addScore(5);
        --total;
    }
    
    private void addScore()
    {
        GameState.addScore(5);
    }


    @Override
    public Type getType() {
        return Type.PACGUM;
    }
    
}
