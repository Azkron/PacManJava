/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.util.Random;

/**
 *
 * @author Hugo
 */
public enum Dir {
    UP, DOWN, LEFT, RIGHT, NONE;
    
    private static final Random rand = new Random();
    
    public static Dir randomDirection() {
        return Dir.values()[rand.nextInt(Dir.values().length)];
    }
}
