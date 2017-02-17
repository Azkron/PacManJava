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

/**
 *
 * @author Hugo
 */
public class ComposedPhantom extends Phantom{
    
    private List<Phantom> pList;
    private static final int DECOMPOSEMAX = 5000; // time in milliseconds
    private int decomposeCount;
    
    public ComposedPhantom(Phantom p1, Phantom p2) {
        super(p1.getX(), p1.getY(), Labyrinth.getInstance());
        decomposeCount = DECOMPOSEMAX;
        power = p1.getPower() + p2.getPower();
        pList = new ArrayList<>();
        pList.add(p1);
        pList.add(p2);
        phantoms.remove(p1);
        phantoms.remove(p2);
    }
    
    @Override
    public void move(Dir d) 
    {
        decomposeCount -= ControllerFX.getFrameTime() * (MAX_MOVE_COUNT+1); // multiplied by the phantom move speed modifier so as to stay in real time
        if(decomposeCount <= 0)
            decompose();
        else
            super.move(getDir());
    }
    
    public void decompose()
    {
        
        for(Phantom p : pList)
        {
            p.x = this.x;
            p.y = this.y;
            
            if(p instanceof ComposedPhantom)
                ((ComposedPhantom)p).decomposeCount = DECOMPOSEMAX;
            
            phantoms.add(p);
            p.move(p.changeDirection(false));// change direction with ignorePhantoms set to false so that it tries to avoid them
        }
        
        phantoms.remove(this);
    }
}
