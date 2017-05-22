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
        guardian = Guardian.getInstance();
        for(int i = 0; i < 5; ++i)
            createMemento();
    }
    
    public void movePacman(Dir d) 
    {
        if(PacMan.getInstance().move(d))// if pacman gets killed we load the memento, so no need to notify the view
        {
            setChanged();
            notifyObservers(getLabView());
        }
    }
    
    public void updateGameState()
    {
        if(Phantom.movePhantoms())// if pacman gets killed we load the memento, so no need to notify the view
        {
            setChanged();
            notifyObservers(getLabView());
        }
    } 
    
    void createInitialMemento()
    {
        Memento m = new Memento(lab.deepCopy(), score, Phantom.getRandomSeed());
        guardian.addMemento(m);
    }
    
    void createMemento()
    {
        Memento m = new Memento(lab.deepCopy(), score, Phantom.newRandomSeed());
        guardian.addMemento(m);
    }
    
    static void setMemento(Memento m)
    {
        System.out.println("set memento");
        score = m.getScore();
        Phantom.setRandomSeed(m.getSeed());
        lab.deactivate();// to clear the old labyrinth, otherwise the garbage collector can take some time and create bugs
        lab = m.getLab();
        lab.activate();
    }
    
    public static void looseLifes(int amount) 
    {
        System.out.println("Loosed: " + amount);
        Memento m = guardian.getMemento(amount);
        if(m != null)
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
