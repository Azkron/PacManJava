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
public class ComposedPhantom extends Phantom{
    
    public ComposedPhantom(int x, int y, Labyrinth lab, int power) {
        super(x, y, lab);
        this.power = power;
    }
    
}
