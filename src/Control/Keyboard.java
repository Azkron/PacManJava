/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.util.TreeMap;

/**
 *
 * @author Hugo
 */
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
        return keyMap.getOrDefault(s, null);
    }
}
