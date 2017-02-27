/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Control.Dir;
/**
 *
 * @author Hugo
 */
public abstract class Character extends GameObject {
    
    private static Labyrinth lab = null;
    int startX, startY;

    public Character(int x, int y) {
        super(x, y);
        startX = x;
        startY = y;
    }
    
    abstract public void move(Dir d);
    
    Labyrinth lab()
    {
        if(lab == null)
            lab = Labyrinth.getInstance();
        
        return lab;
    }
    
    abstract void moveInLab(int nextX, int nextY);
    abstract void kill();
    
    
    void moveToStart() {
        moveInLab(startX, startY);
    }
    
    
    boolean PacManPhantomCollision(PacMan pacman, Phantom phantom) // true if pacman wins and false if phantom wins
    {
        if(pacman.getSuper()) {
            phantom.kill();
            GameState.getInstance().addScore(20*phantom.getPower());
            return true;
        }
        else {
            pacman.kill(phantom.getPower());
            return false;
        }
    }
    
    int getNextY(Dir d) {
        int nextY = getY();
        switch(d){
            case UP:
                return --nextY;
            case DOWN:
                return ++nextY;
            default: 
                return nextY;
        }
    }
    
    int getNextX(Dir d) {
        int nextX = getX();
        switch(d){
            case LEFT:
                return --nextX;
            case RIGHT:
                return ++nextX;
            default: 
                return nextX;
        }
    }
    
    
    
}
