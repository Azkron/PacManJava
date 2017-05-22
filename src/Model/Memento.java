/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Hugo
 */
public class Memento {
    private final Labyrinth lab;
    private final int score;
    private final long seed;
    
    Memento(Labyrinth lab, int score, long seed)
    {
        this.lab = lab;
        this.score = score;
        this.seed = seed;
    }
    
    Labyrinth getLab()
    {
        return lab;
    }
    
    int getScore()
    {
        return score;
    }
    
    long getSeed()
    {
        return seed;
    }
}
