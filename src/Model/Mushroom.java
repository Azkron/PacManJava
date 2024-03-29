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
public class Mushroom extends Consumable{

    Mushroom(int x, int y) {
        super(x, y);
    }
    
    // Constructeur de copie pour le memento
    Mushroom(Mushroom m) {
        super(m.x, m.y);
    }
    
    @Override
    Mushroom deepCopy() {
        return new Mushroom(this);
    }

    @Override
    void Consume() {
        super.Consume();
        GameState.getInstance().createMemento();
    }
    
    @Override
    Type getType() {
        return Type.MUSHROOM;
    }    

    @Override
    void activate() {
        
    }
    
}
