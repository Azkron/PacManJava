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
public class Mushroom implements Consumable{

    @Override
    public void Consume() {
        // NOT YET IMPLENTED IN THIS ITERATION
    }

    @Override
    public ViewType getType() {
        return ViewType.MUSHROOM;
    }
    
}
