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
public class PacGum extends Consumable{

    static private int total;
    
    static void reset()
    {
        total = 0;
    }
    
    PacGum(int x, int y)
    {
        super(x,y);
        ++total;
    }
    
    // Constructeur de copie pour le memento
    PacGum(PacGum p) {
        super(p.x, p.y);
    }
    
    @Override
    PacGum deepCopy() {
        return new PacGum(this);
    }
    
    static int getTotal()
    {
        return total;
    }
    
    @Override
    void Consume() {
        super.Consume();
        GameState.addScore(1);
        --total;
    }
    
    @Override
    Type getType() {
        return Type.PACGUM;
    }    

    @Override
    void activate() {
        ++total;
    }
    
}
