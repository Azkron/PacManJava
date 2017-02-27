/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Hugo
 */
 public abstract class GameObject {
    int x, y;
    abstract Type getType();
    
    GameObject(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    final int getX()
    {
        return x;
    }
    
    final int getY()
    {
        return y;
    }
    
    void setXY(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
}
