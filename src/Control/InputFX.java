/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import View.ViewFX;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

/**
 *
 * @author Hugo
 */
public class InputFX  extends Observable {
    
    private Dir dir = null;
    private KeyMap keyMap;
    private static InputFX instance = new InputFX();
    double time = 0;
    private boolean cooldown = false;
    
    public static InputFX getInstance()
    {
        return instance;
    }
    
    private InputFX()
    {
        keyMap = KeyMap.AZERT;
    }

    public Dir getDir()
    {
        return dir;
    }
    public void processInput(KeyCode c)
    {
        if(!cooldown)
        {
            putOnCooldown();
            dir = keyMap.getDir(c);

            if(dir != Dir.NONE)// || c == KeyCode.X)
            {
                setChanged();
                notifyObservers();
            }
        }
    }
    
    private void putOnCooldown()
    {
        cooldown = true;
        Timeline timeline = new Timeline(new KeyFrame(
        Duration.millis(100),
        ae -> resetCooldown()));
        timeline.play();
    }
    
    private void resetCooldown()
    {
        cooldown = false;
    }
    
    public enum KeyMap {

        QWERTY(KeyCode.W, KeyCode.S, KeyCode.A, KeyCode.D), 
        AZERT(KeyCode.Z, KeyCode.S, KeyCode.Q, KeyCode.D);

        private Map<KeyCode, Dir> keyMap = new HashMap<>();

        
        KeyMap(KeyCode up, KeyCode down, KeyCode left, KeyCode right)
        {
            keyMap.put(KeyCode.UP, Dir.UP);
            keyMap.put(KeyCode.DOWN, Dir.DOWN);
            keyMap.put(KeyCode.LEFT, Dir.LEFT);
            keyMap.put(KeyCode.RIGHT, Dir.RIGHT);
            keyMap.put(up,Dir.UP);
            keyMap.put(down,Dir.DOWN);
            keyMap.put(left,Dir.LEFT);
            keyMap.put(right,Dir.RIGHT);
        }

        public Dir getDir(KeyCode k)
        {
            return keyMap.getOrDefault(k, Dir.NONE);
        }
    }
}
