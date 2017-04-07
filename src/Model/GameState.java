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
    private static int score, lives = 5;
    
    private Labyrinth lab;
    
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
    
    public static boolean getSuperPacMan()
    {
        return PacMan.getInstance().getSuper();
    }
    
    public static int getLives()
    {
        return lives;
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
    
    public static void looseLifes(int amount) {
        lives -= amount;
    }
}
