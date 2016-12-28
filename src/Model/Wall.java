/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Control.Type;

/**
 *
 * @author Hugo
 */
public class Wall implements Case{

    @Override
    public Type getType() {
        return Type.WALL;
    }
    
}
