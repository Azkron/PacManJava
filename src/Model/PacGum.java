/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

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
        GameState.addScore(1);
        --total;
    }
    


    @Override
    public ViewType getType() {
        return ViewType.PACGUM;
    }
    
}
