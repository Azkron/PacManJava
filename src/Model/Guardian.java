/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Stack;

/**
 *
 * @author Hugo
 */
public class Guardian {
    private Stack<Memento> mementos;
    
    void addMemento(Memento m)
    {
        mementos.add(m);
    }
    
    Memento getMemento(int pos)
    {
        for(int i = 0; i < pos-1; ++i)
            mementos.pop();
        
        return mementos.pop();
    }
    
    int getSize()
    {
        return mementos.size();
    }
}
