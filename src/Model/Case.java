/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hugo
 */
public class Case {
    private boolean wall = false;
    Phantom phantom = null;
    PacMan pacman = null;
    List<Consumable> consumables;
    
    Case()
    {
        this.consumables = new ArrayList<>();
    }
    
    Case(Case c)
    {
        wall = c.wall;
        phantom = c.phantom.deepCopy();
        pacman = c.pacman.deepCopy();
        consumables = new ArrayList<>();
        for(Consumable con : c.consumables)
            consumables.add((Consumable)con.deepCopy());
    }
    
    void activate()
    {
        if(phantom != null)
            phantom.activate();
        
        if(pacman != null)
            pacman.activate();
        
        for(Consumable con : consumables)
            con.activate();
    }
    
    Case deepCopy()
    {
        return new Case(this);
    }
    
    boolean isWall()
    {
        return wall;
    }
    
    void isWall(boolean wall)
    {
        this.wall = wall;
    }
    
    void add(PacMan pacman)
    {
        this.pacman = pacman;
    }
    
    void add(Phantom phantom)
    {
        this.phantom = phantom;
    }
    
    void add(Consumable c)
    {
        consumables.add(c);
    }
    
    void remove(PacMan pacman)
    {
        if(this.pacman == pacman)
            this.pacman = null;
        else
            throw new RuntimeException("There is no pacman to be removed");
    }
    
    void remove(Phantom phantom)
    {
        if(this.phantom == phantom)
            this.phantom = null;
        else
            throw new RuntimeException("There is no phantom to be removed");
    }
    
    void remove(Consumable c)
    {
        if(!consumables.remove(c))
            throw new RuntimeException("There is no consumable to be removed");
    }
    
    PacMan getPacMan()
    {
        return pacman;
    }
    
    Phantom getPhantom()
    {
        return phantom;
    }
    
    List<Consumable> getConsumables()
    {
        return consumables;
    }
    
    ArrayList<Type> getTypeList()
    {
        ArrayList<Type> al = new ArrayList<>();
        
        if(!wall)
        {
            for(Consumable c : consumables)
                al.add(c.getType());

            if(phantom != null)
                al.add(phantom.getType());
            
            if(pacman != null)
                al.add(pacman.getType());
        }
        else
            al.add(Type.WALL);
        
        return al;
    }
}
