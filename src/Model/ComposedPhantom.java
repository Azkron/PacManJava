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
        setType();
    }
    
    void add(Phantom p)
    {
        power += p.getPower();
        pList.add(p);
        phantoms.remove(p);
        lab().remove(p.getX(), p.getY(), p);
    }
    
    @Override
    void move(Dir d) 
    {
        decomposeCount -= ControllerFX.getFrameTime() * (MAX_MOVE_COUNT+2); // multiplied by the phantom move speed modifier so as to stay in real time
        if(decomposeCount <= 0)
            decompose();
        else
            super.move(getDir());
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
                    ((ComposedPhantom)p).decomposeCount = DECOMPOSEMAX;

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
