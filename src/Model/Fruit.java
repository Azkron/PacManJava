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
    
    static void reset()
    {
        total = 0;
    }
    
    Fruit(int x, int y)
    {
        super(x,y);
        ++total;
    }
    
    // Constructeur de copie pour le memento
    Fruit(Fruit f) {
        super(f.x, f.y);
    }
    
    @Override
    Fruit deepCopy() {
        return new Fruit(this);
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
        PacMan.getInstance().makeSuper();
    }

    @Override
    Type getType() {
        return Type.FRUIT;
    }

    @Override
    void activate() {
        ++total;
    }

    
    
}
