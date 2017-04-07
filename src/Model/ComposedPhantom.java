/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Control.ControllerFX;
import Control.Dir;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 *
 * @author Hugo
 */
public class ComposedPhantom extends Phantom{
    
    private List<Phantom> pList;
    private static final int DECOMPOSE_START_TIME = 5000; // time in milliseconds
    private Timeline decomposeTimeLine;
    
    public ComposedPhantom(Phantom p1, Phantom p2) 
    {
        super(p1.getX(), p1.getY());
        power = 0;
        pList = new ArrayList<>();
        add(p1);
        add(p2);
        lab().add(x, y, this);
        setType();
        
        if(p1 instanceof ComposedPhantom)
            ((ComposedPhantom)p1).stopDecompose();
        
        if(p2 instanceof ComposedPhantom)
            ((ComposedPhantom)p2).stopDecompose();
        
        decomposeTimeLine = new Timeline(new KeyFrame(
            Duration.millis(DECOMPOSE_START_TIME),
            ae -> decompose())
        );
        
        startDecompose();
    }
    
    ComposedPhantom(ComposedPhantom cp) 
    {
        super(cp);
        pList = new ArrayList<>();
        for(Phantom p : cp.pList)
            pList.add(p.deepCopy());
        
        decomposeTimeLine = new Timeline(new KeyFrame(
            Duration.millis(cp.decomposeTimeLine.getCurrentTime().toMillis()),
            ae -> decompose())
        );
        
    }
    
    @Override
    ComposedPhantom deepCopy() {
        return new ComposedPhantom(this);
    }
    
    @Override
    void activate()
    {
        startDecompose();
        super.activate();
    }
    
    void startDecompose()
    {
        decomposeTimeLine.play();  
    }
    
    void pauseDecompose()
    {
        decomposeTimeLine.pause();  
    }
    
    void stopDecompose()
    {
        decomposeTimeLine.stop();  
    }
    
    void add(Phantom p)
    {
        power += p.getPower();
        pList.add(p);
        phantoms.remove(p);
        lab().remove(p.getX(), p.getY(), p);
    }
    
    private void decompose()
    {
        if(changeDirection(false) != Dir.NONE)
        {
            for(Phantom p : pList)
            {
                p.x = this.x;
                p.y = this.y;
                lab().add(p.x, p.y, p);
                phantoms.add(p);

                if(p instanceof ComposedPhantom)
                    ((ComposedPhantom)p).startDecompose();

                p.move(p.changeDirection(true));// change direction with ignorePhantoms set to false so that it tries to avoid them
            }

            phantoms.remove(this);
        }
    }
    
    private void setType()// Sets the type of phantom based on its power
    {
        switch(power)
        {
            case 2:
                type = Type.PHANTOM2;
                break;
            case 3:
                type = Type.PHANTOM3;
                break;
            case 4:
                type = Type.PHANTOM4;
                break;
            default:
                throw new RuntimeException("Composed phantom power must be 2-4");
        }
    }
}
