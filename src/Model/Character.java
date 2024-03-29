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
    
    int startX, startY;

    public Character(int x, int y, boolean setStart) {
        super(x, y);
        if(setStart)
        {
            startX = x;
            startY = y;
        }
    }
    
     // returns false if pacman gets killed as then we have to load the memento
    abstract boolean move(Dir d);
    
    Labyrinth lab()
    {
        return Labyrinth.getInstance();
    }
    
    abstract void moveInLab(int nextX, int nextY);
    abstract void kill();
    
    
    void moveToStart() {
        moveInLab(startX, startY);
    }
    
    
    boolean PacManPhantomCollision(PacMan pacman, Phantom phantom) // true if pacman wins and false if phantom wins
    {
        if(pacman.isSuper()) {
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
                --nextY;
                break;
            case DOWN:
                ++nextY;
                break;
        }
        
        if (nextY < 0)
            nextY = lab().getYsize()-1;
        else if( nextY >= lab().getYsize()) 
            nextY = 0;
        
        return nextY;
    }
    
    int getNextX(Dir d) {
        int nextX = getX();
        switch(d){
            case LEFT:
                --nextX;
                break;
            case RIGHT:
                ++nextX;
                break;
        }
        if (nextX < 0)
            nextX = lab().getXsize()-1;
        else if( nextX >= lab().getXsize()) 
            nextX = 0;
        
        return nextX;
    }
    
    
    
}
