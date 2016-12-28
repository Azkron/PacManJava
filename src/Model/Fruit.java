/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Control.Type;

/**
 *
 * @author Hugo
 */
public class Fruit implements Consumable{

    private static int total;
    
    Fruit()
    {
        ++total;
    }
    
    public static int getTotal()
    {
        return total;
    }
    
    @Override
    public void Consume() {
        makeSuper();
        total -= 1;
    }
    
    private void makeSuper()
    {
        PacMan.makeSuper();
    }

    @Override
    public Type getType() {
        return Type.FRUIT;
    }
    
}
