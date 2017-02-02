/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Control.Type;
import Control.Dir;
import Model.PacMan;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Hugo
 */
public class Phantom implements Character{
    
    private int x, y, startX, startY; 
    private static ArrayList<Phantom> phantoms = new ArrayList<>();
    private Labyrinth lab;
    private Dir dir;
    private static final Random rand = new Random();
    
    Phantom(int x, int y, Labyrinth lab)
    {
        this.lab = lab;
        startX = x;
        startY = y;
        this.x = startX;
        this.y = startY;
        phantoms.add(this);
        changeDirection();
    }
    
    public static void movePhantoms() {
        for(Phantom p: phantoms) 
            p.move();   
    }
    
    public void changeDirection() {
        System.out.println("ChangeDirection");
        ArrayList<Dir> ld = new ArrayList<>();
        for(Dir d : Dir.values())
        {
            int nextX = getNextX(d), nextY = getNextY(d);
            if(nextX >= 0 && nextX < lab.getXsize() && nextY >=0 && nextY < lab.getYsize())
                if(lab.get(nextX, nextY).getType() != Type.WALL)
                    ld.add(d);
        }
                    
        
        dir =  ld.get(rand.nextInt(ld.size()));
        dir = Dir.randomDirection();
    }
    
    public void kill()
    {
        moveToStart();
        //phantoms.remove(this);
    }
    
    public static ArrayList<Phantom> getPhantoms()
    {
        return phantoms;
    }
    
    public static int getTotal()
    {
        return phantoms.size();
    }
    
    private void move() {
        move(dir);
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
                    case PACMAN:
                        if(PacMan.getSuper()) {
                            this.kill();
                            GameState.addScore(20);
                        }
                        else {
                            ((PacMan) c).kill();
                        }
                        break;
                    case WALL:
                        changeDirection();
                        break;
                    default:
                        moveInLab(nextX, nextY);
                        break;
                }
        }
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
    
    @Override
    public void moveInLab(int nextX, int nextY) {       
        setXY(nextX, nextY);
    }
}
