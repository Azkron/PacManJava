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
public class Wall implements GameObject{

    @Override
    public ViewType getType() {
        return ViewType.WALL;
    }
    
}
