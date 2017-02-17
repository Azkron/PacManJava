/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Control.ControllerFX;
import Control.Type;
import Control.Dir;

/**
 *
 * @author Hugo
 */
public class PacMan implements Character{

    private int x,y, startX, startY;
    // Coordonnees x et y du PacMAn dans le labyrinthe

    private Labyrinth lab;
    private static boolean superPacMan;
    private static int superCount;
    
    private static PacMan INSTANCE = null;
    
    public static PacMan getInstance()
    {
        return INSTANCE;
    }
    
    public PacMan(int x, int y, Labyrinth lab)
    {
        if(INSTANCE == null)
            INSTANCE = this;
        else
            throw new RuntimeException("There can only be one PacMan");
        
        this.lab = lab;
        startX = x;
        startY = y;
        this.x = startX;
        this.y = startY;
    }
    
    
    public static void makeSuper()
    {
        superPacMan = true;
        superCount = 5000;
    }
    
    public static void updateSuper()
    {
        if(superPacMan)
        {
            superCount -= ControllerFX.getFrameTime();
            if(superCount <= 0)
            {
                superCount = 0;
                superPacMan = false;
            }
        }
    }
    
    public static boolean getSuper()
    {
        return superPacMan;
    }
    
    
    @Override
    public void move(Dir d) {
        int nextY = getNextY(d);
        int nextX = getNextX(d);
        
        if(nextX >= 0 && nextX < lab.getXsize() && nextY >=0 && nextY < lab.getYsize()) 
        {
            Phantom p = Phantom.phantomInPos(nextX, nextY);
            boolean killed = false;
            
            
            // eheck phantom collision
            if(p != null)
            {
                while(p!= null)
                {
                    if(getSuper()) 
                    {
                        p.kill();
                        GameState.addScore(20);
                    }
                    else
                    {
                        killed = true;
                        this.kill();
                    }
                    
                    p = Phantom.phantomInPos(nextX, nextY);
                }
            }
            
            // move only if not killed by phantom
            if(!killed)
            {
                Case c = lab.get(nextX, nextY);

                if(c == null)
                    moveInLab(nextX, nextY);
                else
                    switch(c.getType())
                    {
                        case PACGUM:
                            ((Consumable)c).Consume();
                            moveInLab(nextX, nextY);
                            break;
                        case FRUIT:
                            ((Consumable)c).Consume();
                            moveInLab(nextX, nextY);
                            break;
                        case MUSHROOM:
                            ((Consumable)c).Consume();
                            moveInLab(nextX, nextY);
                            break;
                        default:
                            break;
                }
            }
        }
                
    }
    
    @Override
    public Type getType() {
        return Type.PACMAN;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
    
    @Override
    public int getStartX() {
        return startX;
    }

    @Override
    public int getStartY() {
        return startY;
    }

    @Override
    public void kill() {
        moveToStart();
        GameState.looseLife();
    }

    @Override
    public Labyrinth getLab() {
        return lab;
    }

    @Override
    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
}
