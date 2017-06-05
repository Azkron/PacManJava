/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Hugo
 */

public class Labyrinth {
    private Case[][] lab;
    private int xSize, ySize;
    
    private static Labyrinth INSTANCE = new Labyrinth();
    
    static Labyrinth getInstance()
    {
        return INSTANCE;
    }
    
    private Labyrinth()
    {
        initLab();
    }
    
    
    void deactivate()
    {
        PacMan.getInstance().deactivate();
        
        for(Phantom p : Phantom.getPhantoms())
            p.deactivate();
        
        Phantom.reset();
        PacGum.reset();
        Fruit.reset();
    }
    
    Labyrinth(Labyrinth l)
    {
        xSize = l.xSize;
        ySize = l.ySize;
        lab = new Case[ySize][xSize];
        
        for(int y = 0; y < ySize; ++y)
            for(int x = 0; x < xSize; ++x)             
                lab[y][x] = l.lab[y][x].deepCopy();
    }
    
    Labyrinth deepCopy()
    {
        return new Labyrinth(this);
    }
    
    void activate()
    {
        INSTANCE = this;
        for(int y = 0; y < ySize; ++y)
            for(int x = 0; x < xSize; ++x)             
                lab[y][x].activate();
    }
    
    Case get(int x, int y)
    {
        return lab[y][x];
    }
    
    void set(int x, int y, Case c)
    {
        lab[y][x] = c;
    }
    
    void add(int x, int y, PacMan pacman)
    {
        get(x,y).add(pacman);
    }
    
    void add(int x, int y, Phantom phantom)
    {
        get(x,y).add(phantom);
    }
    
    void remove(int x, int y, PacMan pacman)
    {
        get(x,y).remove(pacman);
    }
    
    void remove(int x, int y, Phantom phantom)
    {
        get(x,y).remove(phantom);
    }
    
    void remove(int x, int y, Consumable consumable)
    {
        get(x,y).remove(consumable);
    }
    
    public ArrayList<Type>[][] getLabView()
    {
        ArrayList<Type>[][] labView = new ArrayList[ySize][xSize];
        
        for(int y = 0; y < ySize; ++y)
            for(int x = 0; x < xSize; ++x)
                    labView[y][x] = get(x,y).getTypeList();
        
        return labView;
    }
    
    private void initLab()
    {
        int[][] tab = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1},
            {1, 5, 1, 1, 1, 4, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 4, 1, 1, 1, 5, 1},
            {1, 4, 1, 1, 1, 4, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 4, 1, 1, 1, 4, 1},
            {1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1},
            {1, 4, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 4, 1},
            {1, 4, 4, 4, 4, 4, 1, 4, 1, 1, 1, 1, 1, 1, 1, 4, 1, 4, 4, 4, 4, 4, 1},
            {1, 1, 1, 1, 1, 4, 1, 4, 4, 4, 4, 1, 4, 4, 4, 4, 1, 4, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 4, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 4, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 4, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 4, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 4, 1, 4, 1, 0, 0, 0, 0, 0, 1, 4, 1, 4, 1, 1, 1, 1, 1},
            {1, 4, 4, 4, 4, 4, 6, 4, 1, 3, 3, 0, 3, 3, 1, 4, 6, 4, 4, 4, 4, 4, 1},
            {1, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 4, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 4, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 1},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {1, 4, 1, 1, 1, 4, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 4, 1, 1, 1, 4, 1},
            {1, 5, 4, 4, 1, 4, 4, 4, 4, 4, 4, 2, 4, 4, 4, 4, 4, 4, 1, 4, 4, 5, 1},
            {1, 1, 1, 4, 1, 4, 1, 4, 1, 1, 1, 1, 1, 1, 1, 4, 1, 4, 1, 4, 1, 1, 1},
            {1, 4, 4, 4, 4, 4, 1, 4, 4, 4, 4, 1, 4, 4, 4, 4, 1, 4, 4, 4, 4, 4, 1},
            {1, 4, 1, 1, 1, 1, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 1, 1, 1, 1, 4, 1},
            {1, 4, 1, 1, 1, 1, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 1, 1, 1, 1, 4, 1},
            {1, 6, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 6, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };
        
        tab = generateLab();
        xSize = tab[0].length;
        ySize = tab.length;
        
        lab = new Case[ySize][xSize];
        for(int y = 0; y < ySize; ++y)
            for(int x = 0; x < xSize; ++x)             
                lab[y][x] = caseFromTab(tab[y][x], x, y);
         
        
        //randomizePhantoms();
    }
    
    private int[][] generateLab()
    {
        return generateLab(23,23);
    }
    
    private int[][] generateLab(int width, int height)
    {
        if(width % 2 == 1)
            width += 1;
        if(height % 2 == 1)
            height += 1;
        // create half map, we will unfold it after so its simetrical
        int[][] arrLab = new int[height/2][width/2];
        ArrayList<int[]> corners = new ArrayList<>();
        
        
        // set all to walls
        for(int y = 0; y < arrLab.length; ++y)
            for(int x = 0; x < arrLab[0].length; ++x)
                arrLab[y][x] = 1;
        
        // create phantoms square
        labSquare(arrLab, corners, arrLab[0].length-1, arrLab.length-1, 3, 3, -1, -1);
        for(int[] i : corners)
            System.out.println(i[0]+","+i[1]+" "+i[2] +"," +i[3]);
        
        
        // craete phantoms
        arrLab[arrLab.length-3 +1][arrLab[0].length-3] = 3;
        
        ArrayList<int[]> freePlaces = labFreePlaces(arrLab, true);
        Random r = new Random();
        while(freePlaces.size() < arrLab.length*arrLab[0].length / 2)
        {
            //int[] pos = freePlaces.get(r.nextInt(freePlaces.size()));
            int cornersIdx = r.nextInt(corners.size());
            int[] pos = corners.get(cornersIdx);
            
            labSquare(arrLab, corners, pos[0], pos[1], r.nextInt(arrLab[0].length/2)+4,  r.nextInt(arrLab.length/2)+4, pos[2], pos[3]);
            
            corners.remove(cornersIdx);
            freePlaces = labFreePlaces(arrLab, true);
        }
        
        // add mushroom
        int cornersIdx = r.nextInt(corners.size());
        int[] pos = corners.get(cornersIdx);
        arrLab[pos[1]][pos[0]] = 6;
        corners.remove(cornersIdx);
        
        // add fuit
        cornersIdx = r.nextInt(corners.size());
        pos = corners.get(cornersIdx);
        arrLab[pos[1]][pos[0]] = 5;
        corners.remove(cornersIdx);
        
       
        
        // unfold the array
        int[][] finalArrLab = new int[height][width];
        for(int y = 0; y < arrLab.length; ++y)
            for(int x = 0; x < arrLab[0].length; ++x)
            {
                finalArrLab[y][x] = arrLab[y][x];
                finalArrLab[y][finalArrLab[0].length-x-1] = arrLab[y][x];
                finalArrLab[finalArrLab.length-y-1][x] = arrLab[y][x];
                finalArrLab[finalArrLab.length-y-1][finalArrLab[0].length-x-1] = arrLab[y][x];
            }
        
        
        // add pacman
        //cornersIdx = r.nextInt(corners.size());
        //pos = corners.get(cornersIdx);
        
        int dist = 0;
        for(int[] i : corners)
        {
            int tDist = Math.abs(i[0] - (arrLab[0].length-3)) + Math.abs(i[1] - (arrLab.length-3));
            if(tDist > dist)
                pos = i;
        }
        
        int xPac = pos[0];
        int yPac = pos[1];
        if(r.nextFloat() < 0.5)
            xPac = finalArrLab[0].length-xPac-1;
        if(r.nextFloat() < 0.5)
            yPac = finalArrLab[0].length-yPac-1;
        finalArrLab[xPac][yPac] = 2;
        
        return finalArrLab;
    }
    
    private ArrayList<int[]> labFreePlaces(int[][] arrLab, boolean onlyEmpty)
    {
        return labFreePlaces(arrLab, onlyEmpty, 0, 0, arrLab[0].length, arrLab.length);
    }
    
    private ArrayList<int[]> labFreePlaces(int[][] arrLab, boolean onlyEmpty, int startX, int startY, int width, int height)
    {
        ArrayList<int[]> free = new ArrayList<>();
        
        for(int y = startY; y < height; ++y)
            for(int x = startX; x < width; ++x)
                if(arrLab[y][x] == 4 || (!onlyEmpty && arrLab[y][x] != 1))
                {
                    int[] pos = {x, y};
                    free.add(pos);
                }
        
       return free;
    }
    
    
    private void labSquare(int[][] arrLab, ArrayList<int[]>corners, int x, int y, int width, int height, int xMod, int yMod)
    {
        for(int i = 0; i < width; ++i)
        {
            int xPos = x + xMod * i;
            if(xPos >= 0 && xPos < arrLab[0].length)
            {
                if(arrLab[y][xPos] == 1)
                    arrLab[y][xPos] = 4;
                
                int yPos = y+(height-1)*yMod;
                if(yPos >= 0 && yPos < arrLab.length)
                    if(arrLab[yPos][xPos] == 1)
                        arrLab[yPos][xPos] = 4;
            }
            
        }
        
        for(int i = 0; i < height; ++i)
        {
            int yPos = y + yMod * i;
            if(yPos >= 0 && yPos < arrLab.length)
            {
                if(arrLab[yPos][x] == 1)
                    arrLab[yPos][x] = 4;
                
                int xPos = x+(width-1)*xMod;
                if(xPos >= 0 && xPos < arrLab[0].length)
                    if(arrLab[yPos][xPos] == 1)
                        arrLab[yPos][xPos] = 4;
            }
        }
        
        // add corners
        int[] pos = new int[4];
        pos[0] = x+(width-1)*xMod;
        pos[1] = y;
        pos[2] = xMod;
        pos[3] = -yMod;
        if(pos[0] >= 0 && pos[0] < arrLab[0].length)
            corners.add(pos);
        
        int[] pos2 = new int[4];
        pos2[0] = x;
        pos2[1] = y+(height-1)*xMod;
        pos2[2] = -xMod;
        pos2[3] = yMod;
        if(pos2[1] >= 0 && pos2[1] < arrLab.length)
            corners.add(pos2);
        
        int[] pos3 = new int[4];
        pos3[0] = x+(width-1)*xMod;
        pos3[1] = y+(height-1)*xMod;
        pos3[2] = xMod;
        pos3[3] = yMod;
        if(pos3[1] >= 0 && pos3[1] < arrLab.length &&
                pos3[0] >= 0 && pos3[0] < arrLab[0].length)
            corners.add(pos3);
    }
  
    
//    private void randomizePhantoms()
//    {
//        for(Phantom p : Phantom.getPhantoms())
//        {
//            boolean placed = false;
//            Random r = new Random();
//            
//            while(!placed)
//            {
//                int x = r.nextInt(xSize), y = r.nextInt(ySize);
//                if(get(x,y) == null)
//                    placed = true;
//                else
//                {
//                    Type t = get(x,y).getType();
//                    
//                    if(t == Type.PACGUM || t == Type.FRUIT || t == Type.MUSHROOM)
//                        placed = true;
//                }
//                
//                if(placed)
//                    p.moveInLab(x,y);
//            }
//        }
//        
//    }
    
    private Case caseFromTab(int i, int x, int y)
    {
        //0 : rien
        //1 : mur
        //2 : pacman
        //3 : fantôme
        //4 : gomme
        //5 : fruit
        //6 : champignon
        Case c = new Case();
        switch(i)
        {
            case 0 : break;
            case 1 : c.isWall(true);
                break;
            case 2 : c.add(new PacMan(x,y));
                break;
            case 3 : c.add(new Phantom(x, y));
                break;
            case 4 : c.add(new PacGum(x, y));
                break;
            case 5 : c.add(new Fruit(x, y));
                break;
            case 6 : c.add(new Mushroom(x, y));
                break;
            default : throw new RuntimeException("Initial array value not recognized");
        }
        
        return c;
    }
    
    public int getXsize() {
        return xSize;
    }
    
    public int getYsize() {
        return ySize;
    }
}
