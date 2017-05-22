/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Control.ControllerFX;
import Control.Dir;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 *
 * @author Hugo
 */
public class PacMan extends Character {

    // Coordonnees x et y du PacMAn dans le labyrinthe
    private boolean superPacMan;
    private static final int SUPER_START_TIME = 5000;
    private Timeline superTimeline;

    private static PacMan INSTANCE = null;

    public static PacMan getInstance() 
    {
        return INSTANCE;
    }

    PacMan(int x, int y) 
    {
        super(x,y, true);
        if (INSTANCE == null) 
            INSTANCE = this;
    
        startSuper();
    }
    
    PacMan(PacMan pacman) 
    {
        super(pacman.x,pacman.y, false);
        startX = pacman.startX;
        startY = pacman.startY;
        superPacMan = pacman.superPacMan;
        
        setSuper(SUPER_START_TIME);
    }
    
    @Override
    PacMan deepCopy() {
        return new PacMan(this);
    }

    @Override
    void activate() {
        INSTANCE = this;
        if(isSuper())
            playSuper();
    }
    
    // to stop the timeline if the garbage collector takes time
    void deactivate()
    {
        stopSuper();
    }
    
    
    void setSuper(double milis)
    {
        superTimeline = new Timeline(new KeyFrame(
                        Duration.millis(milis),
                        ae -> endSuper())
                    );
    }
    
    void resetSuper()
    {
        stopSuper();
        startSuper();
    }
    
    void startSuper()
    {
        setSuper(SUPER_START_TIME);
        playSuper();
    }
    
    void playSuper()
    {
        superTimeline.play();  
    }
    
    void pauseSuper()
    {
        superTimeline.pause();  
    }
    
    void stopSuper()
    {
        superTimeline.stop();  
    }
    
    

    public void makeSuper() 
    {
        superPacMan = true;
        resetSuper();
    }
    
    public void endSuper()
    {
        superPacMan = false;
    }


    public boolean isSuper() 
    {
        return superPacMan;
    }
    

    @Override
    boolean move(Dir d) 
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
                    if(!PacManPhantomCollision(this, phantom)) // if pacman looses
                        return false;

                // move only if not killed by phantom
                moveInLab(nextX, nextY);
                List<Consumable> li = c.getConsumables();
                while(li.size() > 0)
                    li.get(0).Consume();
            }
        }
        
        return true;
    }

    public void kill(int livesLost) 
    {
        GameState.looseLifes(livesLost);
        //kill();
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
        if(!superPacMan)
            return Type.PACMAN;
        else
            return Type.SUPERPACMAN;
    }
}
