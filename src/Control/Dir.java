/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Hugo
 */
public enum Dir {
    UP, DOWN, LEFT, RIGHT, NONE;
    
    private static final Random rand = new Random();
    
    public static Set<Dir> getSet()
    {
        Set<Dir> set = new HashSet<>();
        set.add(UP);
        set.add(DOWN);
        set.add(LEFT);
        set.add(RIGHT);
        
        return set;
    }
    
    
    public static Dir randomDirection() {
        return randomDirection(true);
    }
    
    public static Dir randomDirection(boolean ignoreNone) {
        int mod = ignoreNone ? 1 : 0;
        return Dir.values()[rand.nextInt(Dir.values().length - mod)];
    }
    
}
