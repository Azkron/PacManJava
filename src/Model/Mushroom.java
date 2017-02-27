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

    public Mushroom(int x, int y) {
        super(x, y);
    }

    @Override
    public void Consume() {
        super.Consume();
        // NOT YET IMPLENTED IN THIS ITERATION
    }

    @Override
    public Type getType() {
        return Type.MUSHROOM;
    }
    
}
