/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Control.Type;
import Control.Dir;

/**
 *
 * @author Hugo
 */
public class PacMan implements Character{

    private static boolean superPacMan;
    
    private static final PacMan INSTANCE = new PacMan();
    
    public static PacMan getInstance()
    {
        return INSTANCE;
    }
    
    private PacMan()
    {
        // IS SET TO PRIVATE TO PREVENT INSTANTIATION
    }
    
    public static void makeSuper()
    {
        superPacMan = true;
    }
    
    public static boolean getSuper()
    {
        return superPacMan;
    }
    
    
    @Override
    public void move(Dir d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type getType() {
        return Type.PACMAN;
    }
    
}
