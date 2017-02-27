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
        super(p1.getX(), p1.getY());
        decomposeCount = DECOMPOSEMAX;
        power = 0;
        pList = new ArrayList<>();
        add(p1);
        add(p2);
        lab().add(x, y, this);
    }
    
    void add(Phantom p)
    {
        power += p.getPower();
        pList.add(p);
        phantoms.remove(p);
        lab().remove(p.getX(), p.getY(), p);
    }
    
    @Override
    public void move(Dir d) 
    {
        decomposeCount -= ControllerFX.getFrameTime() * (MAX_MOVE_COUNT+2); // multiplied by the phantom move speed modifier so as to stay in real time
        if(decomposeCount <= 0)
            decompose();
        else
            super.move(getDir());
    }
    
    public void decompose()
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
                    ((ComposedPhantom)p).decomposeCount = DECOMPOSEMAX;

                p.move(p.changeDirection(true));// change direction with ignorePhantoms set to false so that it tries to avoid them
            }

            phantoms.remove(this);
        }
    }
}
