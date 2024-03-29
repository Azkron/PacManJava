/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Control.ControllerFX;
import Control.Dir;
import Model.PacMan;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Hugo
 */
public class Phantom extends Character{
    
    static ArrayList<Phantom> phantoms = new ArrayList<>();
    static ArrayList<Phantom> phantomsToMove;
    private static long seed = System.currentTimeMillis();
    private static Random rand = new Random();
    //long rgenseed = System.currentTimeMillis();
    //Random rgen = new Random(rgenseed);
    Dir dir;
    Type type;
    int power;
    
    static void setRandomSeed(long seed)
    {
        Phantom.seed = seed;
        rand = new Random(seed);
    }
    
    static long getRandomSeed()
    {
        return seed;
    }
    
    static long newRandomSeed()
    {
        seed = System.currentTimeMillis();
        rand = new Random(seed);
        return seed;
    }
    
    static void reset()
    {
        phantoms = new ArrayList<>();
    }
    
    private static void compose(Phantom p, Phantom p2)
    {
        new ComposedPhantom(p, p2);
        
        phantomsToMove.remove(p);
        phantomsToMove.remove(p2);
    }
    
    Phantom(int x, int y)
    {
        super(x,y, true);
        type = Type.PHANTOM;
        power = 1;
        phantoms.add(this);
        dir = Dir.randomDirection();
    }
    
    // Constructeur de copie pour le memento
    Phantom(Phantom p) {
        super(p.x, p.y, false);
        startX = p.startX;
        startY = p.startY;
        type = p.type;
        power = p.power;
        dir = Dir.randomDirection();
    }
    
    @Override
    Phantom deepCopy() {
        return new Phantom(this);
    }
    
    @Override
    void activate() {
        phantoms.add(this);
    }
    
    void afterDecompose(){}
    
    
    int getPower()
    {
        return power;
    }
    
    static boolean movePhantoms() // returns false if pacman gets killed and we have to load the memento
    {
        phantomsToMove = new ArrayList<>(phantoms);
        while(phantomsToMove.size() > 0)
        {
            Phantom p = phantomsToMove.get(0);
            if(!p.move()) // if pacman gets killed an we have to load the memento
                return false;
            phantomsToMove.remove(p);
        }
        
        return true;
    }
    
//    static Phantom phantomInPos(int x, int y)
//    {
//        for(Phantom p: phantoms) 
//            if(p.getX() == x && p.getY() == y)
//                return p;
//        
//        return null;
//    }
    
    Dir changeDirection(boolean ignorePhantoms) 
    {
        ArrayList<Dir> ld = new ArrayList<>();
        for(Dir d : Dir.getSet())
        {
            int nextX = getNextX(d), nextY = getNextY(d);
            if(nextX >= 0 && nextX < lab().getXsize() && nextY >=0 && nextY < lab().getYsize())
            {
                Case c = lab().get(nextX, nextY);
                if(!c.isWall()  && (!ignorePhantoms || c.getPhantom() == null))
                        ld.add(d);
            }
        }
        
        if(ld.size() > 0)
            dir = ld.get(rand.nextInt(ld.size()));
        else
            dir = Dir.NONE;
        
        return dir;
    }
    
    void kill()
    {
        moveToStart();
        //phantoms.remove(this);
    }
    
    static ArrayList<Phantom> getPhantoms()
    {
        return phantoms;
    }
    
    static int getTotal()
    {
        return phantoms.size();
    }
    
    boolean move() {
        CheckIntersection();
        
        return move(getDir());
    }
    
    private void CheckIntersection()
    {
        // check if intersection
        int freeDirections = 0;
        for(Dir d : Dir.values())
        {
            Case c = lab().get(getNextX(d), getNextY(d));
            if(!c.isWall())
                ++freeDirections;
        }
        
        if(freeDirections > 2 && rand.nextFloat() > 0.5)
            dir = changeDirection(false);
    }
    
    Dir getDir()
    {
        return dir;
    }
    
    
    @Override
    boolean move(Dir d) {
        if(d != Dir.NONE)
        {
            
            int nextY = getNextY(d);
            int nextX = getNextX(d);
            
            if(nextX >= 0 && nextX < lab().getXsize() && nextY >=0 && nextY < lab().getYsize()) 
            {
                Case c = lab().get(nextX, nextY);

                if(c.isWall())
                {
                    changeDirection(true);
                }
                else 
                {
                    Phantom p = c.getPhantom(); 
                    if(p != null)
                        compose(this, p);
                    else
                    {
                        PacMan pacman = c.getPacMan();
                        if(pacman != null)
                        {
                            if(!PacManPhantomCollision(pacman, this)) // pacman gets killed and we have to load the next memento
                                return false;
                        }
                        else
                            moveInLab(nextX, nextY);
                    }
                        
                }
                
            }
        }
        
        return true;
    }
    
    
    @Override
    void moveInLab(int nextX, int nextY) {
        
        lab().remove(getX(), getY(), this);
        setXY(nextX, nextY);
        lab().add(getX(), getY(),this);
    }
    
    
    
    @Override
    public Type getType() {
        return type;
    }

}