/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import Control.Type;
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
    
    private static final Labyrinth INSTANCE = new Labyrinth();
    
    public static Labyrinth getInstance()
    {
        return INSTANCE;
    }
    
    private Labyrinth()
    {
        initLab();
    }
    
    public Case get(int x, int y)
    {
        return lab[y][x];
    }
    
    public void set(int x, int y, Case c)
    {
        lab[y][x] = c;
    }
    
    public Type[][] getLabView()
    {
        Type[][] labView = new Type[ySize][xSize];
        
        for(int y = 0; y < ySize; ++y)
            for(int x = 0; x < xSize; ++x)
            {
                Case c = get(x,y);
                if(c != null)
                    labView[y][x] = c.getType();
                else
                    labView[y][x] = Type.EMPTY;
            }
        
        
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
            {1, 1, 1, 1, 1, 4, 1, 4, 1, 3, 0, 0, 0, 3, 1, 4, 1, 4, 1, 1, 1, 1, 1},
            {1, 4, 4, 4, 4, 4, 6, 4, 1, 3, 0, 0, 0, 3, 1, 4, 6, 4, 4, 4, 4, 4, 1},
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
        
        randomizePhantoms();
    }
    
    private void randomizePhantoms()
    {
        for(Phantom p : Phantom.getPhantoms())
        {
            boolean placed = false;
            Random r = new Random();
            
            while(!placed)
            {
                int x = r.nextInt(xSize), y = r.nextInt(ySize);
                if(get(x,y) == null)
                    placed = true;
                else
                {
                    Type t = get(x,y).getType();
                    
                    if(t == Type.PACGUM || t == Type.FRUIT || t == Type.MUSHROOM)
                        placed = true;
                }
                
                if(placed)
                    p.moveInLab(x,y);
            }
        }
        
    }
    
    private Case caseFromTab(int i, int x, int y)
    {
        //0 : rien
        //1 : mur
        //2 : pacman
        //3 : fantôme
        //4 : gomme
        //5 : fruit
        //6 : champignon
        switch(i)
        {
            case 0 : return null;
            case 1 : return new Wall();
            case 2 : return new PacMan(x,y, this);
            case 3 : return new Phantom(x, y, this);
            case 4 : return new PacGum();
            case 5 : return new Fruit();
            case 6 : return new Mushroom();
            default : return null;
        }
    }
    
    public int getXsize() {
        return xSize;
    }
    
    public int getYsize() {
        return ySize;
    }
}
