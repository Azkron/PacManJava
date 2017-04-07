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
    
    public Consumable(int x, int y) {
        super(x, y);
    }
    
    Labyrinth lab()
    {
        return Labyrinth.getInstance();
    }
    
    void Consume()
    {
        lab().remove(getX(), getY(), this);
    }
}
