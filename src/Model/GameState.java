/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Control.Dir;
import Control.Type;
import java.util.ArrayList;
import javafx.beans.InvalidationListener;
import java.util.Observable;

/**
 *
 * @author Hugo
 */
public class GameState extends Observable {
    private static int score, lives;
    
    private Labyrinth lab;
    
    private static final GameState INSTANCE = new GameState();
    
    private PacMan pacMan;
    
    public static GameState getInstance()
    {   
        return INSTANCE;
    }
    
    private GameState()
    {
        lab = Labyrinth.getInstance();
        pacMan = PacMan.getInstance();
    }
    
    public void updateGameState(Dir d)
    {
        pacMan.move(d);
        setChanged();
        notifyObservers();
    }
    
    public ArrayList<ArrayList<Type>> getLabView()
    {
        return lab.getLabView();
    }
    
    public int getScore()
    {
        return score;
    }
    
    public static void addScore(int s)
    {
        score += s;
    }
    
    public int getLives()
    {
        return lives;
    }
    
    public int getPhantoms()
    {
        return Phantom.getTotal();
    }
    
    public int getFruits()
    {
        return Fruit.getTotal();
    }
    
    public int getPacGum()
    {
        return PacGum.getTotal();
    }
    
}
