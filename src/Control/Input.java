/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.util.Observable;
import java.util.Scanner;
import java.util.TreeMap;


/**
 *
 * @author Hugo
 */
public class Input extends Observable{
    
    
    private Keyboard keyboard = null;
    
    private Dir dir = null;
    
    Scanner sc = new Scanner(System.in);
    public Input()
    {
        selectKeyboard();
    }
    
    public void getInput()
    {
        while(true)
        {
            System.out.println("Please input a direction (zqsd, wqsd) or \"x\" to exit");
            String c = sc.nextLine().toLowerCase();
            dir = keyboard.getDir(c);
            if(c.equals("x") || dir != Dir.NONE)
            {
                setChanged();
                notifyObservers();
            }
        }
    }
    
    public Dir getDir()
    {
        return dir;
    }
    
    private void selectKeyboard()
    {
        while(keyboard == null)
        {
            System.out.println("Please select a keyboard: 'a' for azert or 'q' for qwert");
            String c = sc.nextLine().toLowerCase();
            if(c.equals("a"))
                keyboard = Keyboard.AZERT;
            else if(c.equals("q"))
                keyboard = Keyboard.QWERTY;
            else
                System.out.println("Unexistent selection");
        }
    }
    
    
    public enum Keyboard {

        QWERTY("w", "s", "a", "d"), AZERT("z", "s", "q", "d");

        private TreeMap<String, Dir> keyMap = new TreeMap<>();

        Keyboard(String up, String down, String left, String right)
        {
            keyMap.put(up,Dir.UP);
            keyMap.put(down,Dir.DOWN);
            keyMap.put(left,Dir.LEFT);
            keyMap.put(right,Dir.RIGHT);
        }

        public Dir getDir(String s)
        {
            return keyMap.getOrDefault(s, Dir.NONE);
        }
    }
    
}
