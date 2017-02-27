/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Control.ControllerFX;
import Control.Dir;
import java.util.List;

/**
 *
 * @author Hugo
 */
public class PacMan extends Character {

    // Coordonnees x et y du PacMAn dans le labyrinthe
    private static boolean superPacMan;
    private static int superCount;

    private static PacMan INSTANCE = null;

    public static PacMan getInstance() 
    {
        return INSTANCE;
    }

    public PacMan(int x, int y) 
    {
        super(x,y);
        if (INSTANCE == null) 
        {
            INSTANCE = this;
        } 
        else 
        {
            throw new RuntimeException("There can only be one PacMan");
        }
    }

    public static void makeSuper() 
    {
        superPacMan = true;
        superCount = 5000;
    }

    public static void updateSuper() 
    {
        if (superPacMan) 
        {
            superCount -= ControllerFX.getFrameTime();
            if (superCount <= 0) 
            {
                superCount = 0;
                superPacMan = false;
            }
        }
    }

    public static boolean getSuper() 
    {
        return superPacMan;
    }

    @Override
    public void move(Dir d) 
    {
        int nextY = getNextY(d);
        int nextX = getNextX(d);

        if (nextX >= 0 && nextX < lab().getXsize() && nextY >= 0 && nextY < lab().getYsize()) 
        {
            Case c = lab().get(nextX, nextY);
            if (!c.isWall()) 
            {
                Phantom phantom = c.getPhantom();
                boolean killed = false;

                // eheck phantom collision
                if (phantom != null) 
                {
                    if(!PacManPhantomCollision(this, phantom)) // if pacman looses
                        killed = true;
                }

                // move only if not killed by phantom
                if (!killed) 
                {
                    moveInLab(nextX, nextY);
                    List<Consumable> li = c.getConsumables();
                    while(li.size() > 0)
                        li.get(0).Consume();
                }
            }

        }

    }

    public void kill(int livesLost) 
    {
        GameState.looseLifes(livesLost);
        kill();
    }

    @Override
    public void kill() 
    {
        moveToStart();
    }
    
    @Override
    void moveInLab(int nextX, int nextY) {
        
        lab().get(getX(), getY()).remove(this);
        setXY(nextX, nextY);
        lab().get(getX(), getY()).add(this);
    }
    
    @Override
    public Type getType() {
        return Type.PACMAN;
    }
}
