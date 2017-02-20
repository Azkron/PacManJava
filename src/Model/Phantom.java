/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Control.Type;
import Control.Dir;
import Model.PacMan;
import static Model.PacMan.getSuper;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Hugo
 */
public class Phantom implements Character{
    
    int x, y, startX, startY; 
    static ArrayList<Phantom> phantoms = new ArrayList<>();
    static ArrayList<Phantom> phantomsToMove;
    private Labyrinth lab;
    Dir dir;
    private static final Random rand = new Random();
    final static int MAX_MOVE_COUNT = 1;
    private static int moveCount = MAX_MOVE_COUNT;
    int power;
    
    private static void compose(Phantom p, Phantom p2)
    {
        new ComposedPhantom(p, p2);
        
        phantomsToMove.remove(p);
        phantomsToMove.remove(p2);
    }
    
    Phantom(int x, int y, Labyrinth lab)
    {
        power = 1;
        this.lab = lab;
        startX = x;
        startY = y;
        this.x = startX;
        this.y = startY;
        phantoms.add(this);
        dir = Dir.UP;
    }
    
    public int getPower()
    {
        return power;
    }
    
    public static void movePhantoms() 
    {
        if(moveCount-- == 0)
        {
            moveCount  = MAX_MOVE_COUNT;
            phantomsToMove = new ArrayList<>(phantoms);
            
            while(phantomsToMove.size() > 0)
            {
                Phantom p = phantomsToMove.get(0);
                p.move(); 
                phantomsToMove.remove(p);
            }
        }
    }
    
    public static Phantom phantomInPos(int x, int y)
    {
        for(Phantom p: phantoms) 
            if(p.getX() == x && p.getY() == y)
                return p;
        
        return null;
    }
    
    public Dir changeDirection(boolean ignorePhantoms) 
    {
        ArrayList<Dir> ld = new ArrayList<>();
        for(Dir d : Dir.getSet())
        {
            int nextX = getNextX(d), nextY = getNextY(d);
            if(nextX >= 0 && nextX < lab.getXsize() && nextY >=0 && nextY < lab.getYsize())
            {
                GameObject c = lab.get(nextX, nextY);
                if( c == null || (c.getType() != Type.WALL  && (ignorePhantoms || c.getType() != Type.PHANTOM)))
                        ld.add(d);
            }
        }
        
        if(ld.size() > 0)
            dir = ld.get(rand.nextInt(ld.size()));
        else
            dir = Dir.NONE;
        
        return dir;
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
    
    void move() {
        move(getDir());
    }
    
    public Dir getDir()
    {
        return dir;
    }
    
    
    @Override
    public void move(Dir d) {
        if(d != Dir.NONE)
        {
            int nextY = getNextY(d);
            int nextX = getNextX(d);
            
            if(nextX >= 0 && nextX < lab.getXsize() && nextY >=0 && nextY < lab.getYsize()) 
            {
                Phantom p = Phantom.phantomInPos(nextX, nextY);

                // eheck phantom collision
                if(p != null)
                {
                    compose(this, p);
                }
                else
                {
                    GameObject c = lab.get(nextX, nextY);
                    if(c == null)
                        moveInLab(nextX, nextY);
                    else
                        switch(c.getType())
                        {
                            case PACMAN:
                                if(PacMan.getSuper()) {
                                    this.kill();
                                    GameState.addScore(20*this.getPower());
                                }
                                else {
                                    ((PacMan) c).kill(this.getPower());
                                }
                                break;
                            case WALL:
                                changeDirection(true);
                                break;
                            default:
                                moveInLab(nextX, nextY);
                                break;
                        }
                }
                
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
