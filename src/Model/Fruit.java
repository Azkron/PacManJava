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
public class Fruit extends Consumable{

    private static int total;
    
    Fruit(int x, int y)
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
        makeSuper();
        total -= 1;
    }
    
    private void makeSuper()
    {
        PacMan.makeSuper();
    }

    @Override
    Type getType() {
        return Type.FRUIT;
    }

    @Override
    Fruit deepCopy(GameObject g) {
        return new Fruit(g.x, g.y);
    }
    
}
