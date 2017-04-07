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
    
    PacGum(int x, int y)
    {
        super(x,y);
        ++total;
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
    PacGum deepCopy(GameObject g) {
        return new PacGum(g.x, g.y);
        // just -some changes for the commit
    }
    
}
