/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Hugo
 */

public class Labyrinth {
    private Case[][] lab;
    private int xSize, ySize;
    
    private static Labyrinth INSTANCE = new Labyrinth();
    
    static Labyrinth getInstance()
    {
        return INSTANCE;
    }
    
    private Labyrinth()
    {
        initLab();
    }
    
    Labyrinth(Labyrinth l)
    {
        xSize = l.xSize;
        ySize = l.ySize;
        lab = new Case[ySize][xSize];
        
        for(int y = 0; y < ySize; ++y)
            for(int x = 0; x < xSize; ++x)             
                lab[y][x] = l.lab[y][x].deepCopy();
    }
    
    Labyrinth deepCopy()
    {
        return new Labyrinth(this);
    }
    
    void activate()
    {
        INSTANCE = this;
        for(int y = 0; y < ySize; ++y)
            for(int x = 0; x < xSize; ++x)             
                lab[y][x].activate();
    }
    
    Case get(int x, int y)
    {
        return lab[y][x];
    }
    
    void set(int x, int y, Case c)
    {
        lab[y][x] = c;
    }
    
    void add(int x, int y, PacMan pacman)
    {
        get(x,y).add(pacman);
    }
    
    void add(int x, int y, Phantom phantom)
    {
        get(x,y).add(phantom);
    }
    
    void remove(int x, int y, PacMan pacman)
    {
        get(x,y).remove(pacman);
    }
    
    void remove(int x, int y, Phantom phantom)
    {
        get(x,y).remove(phantom);
    }
    
    void remove(int x, int y, Consumable consumable)
    {
        get(x,y).remove(consumable);
    }
    
    public ArrayList<Type>[][] getLabView()
    {
        ArrayList<Type>[][] labView = new ArrayList[ySize][xSize];
        
        for(int y = 0; y < ySize; ++y)
            for(int x = 0; x < xSize; ++x)
                    labView[y][x] = get(x,y).getTypeList();
        
        return labView;
    }
    
    private void initLab()
    {
        int[][] tab = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1},
            {1, 5, 1, 1, 1, 4, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 4, 1, 1, 1, 5, 1},
            {1, 4, 1, 1, 1, 4, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 4, 1, 1, 1, 4, 1},
            {1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1},
            {1, 4, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 4, 1},
            {1, 4, 4, 4, 4, 4, 1, 4, 1, 1, 1, 1, 1, 1, 1, 4, 1, 4, 4, 4, 4, 4, 1},
            {1, 1, 1, 1, 1, 4, 1, 4, 4, 4, 4, 1, 4, 4, 4, 4, 1, 4, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 4, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 4, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 4, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 4, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 4, 1, 4, 1, 0, 0, 0, 0, 0, 1, 4, 1, 4, 1, 1, 1, 1, 1},
            {1, 4, 4, 4, 4, 4, 6, 4, 1, 3, 3, 0, 3, 3, 1, 4, 6, 4, 4, 4, 4, 4, 1},
            {1, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 4, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 4, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 1},
            {1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1},
            {1, 4, 1, 1, 1, 4, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 4, 1, 1, 1, 4, 1},
            {1, 5, 4, 4, 1, 4, 4, 4, 4, 4, 4, 2, 4, 4, 4, 4, 4, 4, 1, 4, 4, 5, 1},
            {1, 1, 1, 4, 1, 4, 1, 4, 1, 1, 1, 1, 1, 1, 1, 4, 1, 4, 1, 4, 1, 1, 1},
            {1, 4, 4, 4, 4, 4, 1, 4, 4, 4, 4, 1, 4, 4, 4, 4, 1, 4, 4, 4, 4, 4, 1},
            {1, 4, 1, 1, 1, 1, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 1, 1, 1, 1, 4, 1},
            {1, 4, 1, 1, 1, 1, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 1, 1, 1, 1, 4, 1},
            {1, 6, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 6, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };
        
        xSize = tab[0].length;
        ySize = tab.length;
        
        lab = new Case[ySize][xSize];
        for(int y = 0; y < ySize; ++y)
            for(int x = 0; x < xSize; ++x)             
                lab[y][x] = caseFromTab(tab[y][x], x, y);
         
        
        //randomizePhantoms();
    }
  
    
//    private void randomizePhantoms()
//    {
//        for(Phantom p : Phantom.getPhantoms())
//        {
//            boolean placed = false;
//            Random r = new Random();
//            
//            while(!placed)
//            {
//                int x = r.nextInt(xSize), y = r.nextInt(ySize);
//                if(get(x,y) == null)
//                    placed = true;
//                else
//                {
//                    Type t = get(x,y).getType();
//                    
//                    if(t == Type.PACGUM || t == Type.FRUIT || t == Type.MUSHROOM)
//                        placed = true;
//                }
//                
//                if(placed)
//                    p.moveInLab(x,y);
//            }
//        }
//        
//    }
    
    private Case caseFromTab(int i, int x, int y)
    {
        //0 : rien
        //1 : mur
        //2 : pacman
        //3 : fantôme
        //4 : gomme
        //5 : fruit
        //6 : champignon
        Case c = new Case();
        switch(i)
        {
            case 0 : break;
            case 1 : c.isWall(true);
                break;
            case 2 : c.add(new PacMan(x,y));
                break;
            case 3 : c.add(new Phantom(x, y));
                break;
            case 4 : c.add(new PacGum(x, y));
                break;
            case 5 : c.add(new Fruit(x, y));
                break;
            case 6 : c.add(new Mushroom(x, y));
                break;
            default : throw new RuntimeException("Initial array value not recognized");
        }
        
        return c;
    }
    
    public int getXsize() {
        return xSize;
    }
    
    public int getYsize() {
        return ySize;
    }
}
