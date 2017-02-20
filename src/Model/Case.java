/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Hugo
 */
public class Case {
    private boolean wall;
    private Phantom phantom;
    private PacMan pacman;
    private List<Consumable> consumables;
    
    public Case(boolean wall, Phantom phantom, PacMan pacman, Consumable... consumables)
    {
        this.wall = wall;
        this.phantom = phantom;
        this.pacman = pacman;
        this.consumables = new ArrayList<>();
        this.consumables.addAll(Arrays.asList(consumables));
    }
    
    public boolean isWall()
    {
        return wall;
    }
    
    public Phantom getPhantom()
    {
        return phantom;
    }
    
    public List<Consumable> getConsumables()
    {
        return consumables;
    }
    
    public ViewCase getViewCase()
    {
        return new ViewCase();
    }
}
