/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Control.Type;
import Control.Dir;
import java.util.ArrayList;

/**
 *
 * @author Hugo
 */
public class Phantom implements Character{

    private static ArrayList<Phantom> phantoms = new ArrayList<>();
    
    Phantom()
    {
        phantoms.add(this);
    }
    
    public void kill()
    {
        phantoms.remove(this);
    }
    
    public static ArrayList<Phantom> getPhantoms()
    {
        return phantoms;
    }
    
    public static int getTotal()
    {
        return phantoms.size();
    }
    
    @Override
    public void move(Dir d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Type getType() {
        return Type.PHANTOM;
    }
    
}
