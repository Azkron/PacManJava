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
    Dir dir;
    Type type;
    private static final Random rand = new Random();
    int power;
    
    private static void compose(Phantom p, Phantom p2)
    {
        new ComposedPhantom(p, p2);
        
        phantomsToMove.remove(p);
        phantomsToMove.remove(p2);
    }
    
    Phantom(int x, int y)
    {
        super(x,y);
        type = Type.PHANTOM;
        power = 1;
        phantoms.add(this);
        dir = Dir.UP;
    }
    
    int getPower()
    {
        return power;
    }
    
    static void movePhantoms() 
    {
        phantomsToMove = new ArrayList<>(phantoms);

        while(phantomsToMove.size() > 0)
        {
            Phantom p = phantomsToMove.get(0);
            p.move(); 
            phantomsToMove.remove(p);
        }
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
    
    void move() {
        move(getDir());
    }
    
    Dir getDir()
    {
        return dir;
    }
    
    
    @Override
    void move(Dir d) {
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
                            if(!PacManPhantomCollision(pacman, this)) // if phantom wins
                                moveInLab(nextX, nextY);
                        }
                        else
                                moveInLab(nextX, nextY);
                    }
                        
                }
                
            }
        }
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
