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
    

    @Override
    void Consume() {
        super.Consume();
        // NOT YET IMPLENTED IN THIS ITERATION
    }
    
    @Override
    Type getType() {
        return Type.MUSHROOM;
    }
    
//    public Mushroom deepCopy(Mushroom m) {
//        return new Mushroom(m.x, m.y);
//    }


    @Override
    Mushroom deepCopy(GameObject g) {
        return new Mushroom(g.x, g.y);
    }
    
}
