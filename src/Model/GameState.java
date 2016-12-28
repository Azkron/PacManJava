/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

/**
 *
 * @author Hugo
 */
public class GameState implements Observable {
    private int score, lives;

    @Override
    public void addListener(InvalidationListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int getScore()
    {
        return score;
    }
    
    public int getLives()
    {
        return lives;
    }
    
    public int getPhantoms()
    {
        return Phantom.total;
    }
    
    public int getFruits()
    {
        return Fruit.total;
    }
    
    public int getPacGum()
    {
        return PacGum.total;
    }
    
}
