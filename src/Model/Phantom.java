/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Control.Type;
import Control.Dir;
import java.util.ArrayList;

/**
 *
 * @author Hugo
 */
public class Phantom implements Character{
    
    private int x, y, startX, startY; 
    private static ArrayList<Phantom> phantoms = new ArrayList<>();
    private Labyrinth lab;
    
    Phantom(int x, int y, Labyrinth lab)
    {
        this.lab = lab;
        startX = x;
        startY = y;
        this.x = startX;
        this.y = startY;
        phantoms.add(this);
    }
    
    public void kill()
    {
        phantoms.remove(this);
    }
    
    public static ArrayList<Phantom> getPhantoms()
    {
        return phantoms;
    }
    
    public static int getTotal()
    {
        return phantoms.size();
    }
    
    @Override
    public void move(Dir d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Type getType() {
        return Type.PHANTOM;
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
    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Labyrinth getLab() {
        return lab;
    }
}
