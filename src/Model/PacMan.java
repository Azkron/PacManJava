/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Control.Type;
import Control.Dir;

/**
 *
 * @author Hugo
 */
public class PacMan implements Character{

    private int x,y, startX, startY;                     // Coordonnees x et y du PacMAn dans le labyrinthe
    
    private Labyrinth lab;
    private static boolean superPacMan;
    
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
            Case c = lab.get(nextX, nextY);
            if(c == null)
                moveInLab(nextX, nextY);
            else
                switch(c.getType())
                {
                    case PHANTOM:
                        if(getSuper()) {
                            ((Phantom) c).kill();
                            moveInLab(nextX, nextY);
                            GameState.addScore(20);
                        }
                        else {
                            kill();
                        }
                        break;
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
    
    public void moveToStart() {
        moveInLab(startX, startY);
    }
    
    public void moveInLab(int nextX, int nextY) {
        
        lab.set(x, y, null);
        x = nextX;
        y = nextY;
        lab.set(x, y, this);
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
    public void kill() {
        moveToStart();
        GameState.looseLife();
    }
    
}
