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
    
    Memento(Labyrinth lab, int score)
    {
        this.lab = lab;
        this.score = score;
    }
    
    Labyrinth getLab()
    {
        return lab;
    }
    
    int getScore()
    {
        return score;
    }
}
