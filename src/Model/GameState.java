/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Control.Dir;
import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author Hugo
 */
public class GameState extends Observable {
    private static int score;
    private static Guardian guardian;
    
    private static Labyrinth lab;
    
    private static GameState INSTANCE;
    
    
    
    public static GameState getInstance()
    {   
        if(INSTANCE == null)
            INSTANCE = new GameState();
        
        return INSTANCE;
    }
    
    private GameState()
    {
        lab = Labyrinth.getInstance();
        guardian = new Guardian();
    }
    
    public void movePacman(Dir d) 
    {
        PacMan.getInstance().move(d);
        
        setChanged();
        notifyObservers(getLabView());
    }
    
    public void updateGameState()
    {
        Phantom.movePhantoms();
        
        setChanged();
        notifyObservers(getLabView());
    } 
    
    void createMemento()
    {
        Memento m = new Memento(lab.deepCopy());
        guardian.addMemento(m);
    }
    
    static void setMemento(Memento m)
    {
        lab = m.getLab();
        lab.activate();
    }
    
    public static void looseLifes(int amount) 
    {
        Memento m = guardian.getMemento(amount);
        setMemento(m);
    }
    
    public ArrayList<Type>[][] getLabView()
    {
        return lab.getLabView();
    }
    
    public static int getScore()
    {
        return score;
    }
    
    public static void addScore(int s)
    {
        score += s;
    }
    
    public static boolean isSuperPacMan()
    {
        return PacMan.getInstance().isSuper();
    }
    
    public static int getLives()
    {
        return guardian.getSize();
    }
    
    public static int getPhantoms()
    {
        return Phantom.getTotal();
    }
    
    public static int getFruits()
    {
        return Fruit.getTotal();
    }
    
    public static int getPacGum()
    {
        return PacGum.getTotal();
    }
}
