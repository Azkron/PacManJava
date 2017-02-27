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
public abstract class Consumable extends GameObject{
    
    
    private static Labyrinth lab = null;

    public Consumable(int x, int y) {
        super(x, y);
    }
    
    Labyrinth lab()
    {
        if(lab == null)
            lab = Labyrinth.getInstance();
        
        return lab;
    }
    
    void Consume()
    {
        lab().remove(getX(), getY(), this);
    }
}
