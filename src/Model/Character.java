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
public interface Character extends GameObject {
    
    public void move(Dir d);
    
    public Labyrinth getLab();
    
    public int getX();
    public int getY();
    public void setXY(int x, int y);
    public int getStartX();
    public int getStartY();
    
    public void kill();
    
    default int getNextY(Dir d) {
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
    
    default int getNextX(Dir d) {
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
    
    
    
    default public void moveToStart() {
        moveInLab(getStartX(), getStartY());
    }
    
    default public void moveInLab(int nextX, int nextY) {
        
        getLab().set(getX(), getY(), null);
        setXY(nextX, nextY);
        getLab().set(getX(), getY(), this);
    }
    
}
