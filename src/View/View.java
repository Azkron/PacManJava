/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Type;
import java.util.Observable;
import java.util.Observer;
import Model.GameState;
import java.util.ArrayList;

/**
 *
 * @author Hugo
 */
public class View implements Observer{
    private static final View instance = new View();
    
    public static View getInstance()
    {
        return instance;
    }
    
    private View()
    {
        
    }
    
    @Override
    public void update(Observable o, Object arg) {
        drawLabyrinth((GameState) o);
    }
    
    private void drawLabyrinth(GameState g) {
        for(ArrayList<Type>[] a: g.getLabView()) {
            System.out.println("");
            for(ArrayList<Type> t: a) {
                drawType(t.get(0));
            }
        }
        displayInfo();
    }
    
    public void drawType(Type t) {
        String s = "";
        switch(t) 
        {
            case PACMAN : s = "O";
                break;
            case PHANTOM : s = "A";
                break;
            case PACGUM : s = "°";
                break;
            case FRUIT : s = "F";
                break;
            case MUSHROOM : s = "M";
                break;
            case WALL : s = "#";
                break;
            case EMPTY: s = " ";
                break;
            default : s = " ";
                break;
        }
        System.out.print(s);
    }
    
    public void displayInfo() {
        System.out.println("");
        System.out.println("-------------------------------------------------------");
        System.out.print("Lives: " +GameState.getLives());
        System.out.print("   Score: " +GameState.getScore());
        System.out.print("   Phantoms: " +GameState.getPhantoms());
        System.out.print("   Pac-Gums: " +GameState.getPacGum());
        System.out.println("");
        System.out.println("-------------------------------------------------------");
        
    }
    
}
