/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;

/**
 *
 * @author Hugo
 */
public class ViewCase {
    private List<ViewType> ViewTypes;
    
    public ViewCase(List<ViewType> ViewTypes)
    {
        this.ViewTypes = ViewTypes;
    }
    
    public List<ViewType> getViewTypes()
    {
        return ViewTypes;
    }
}
