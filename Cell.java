import java.util.*;
/**
 * The Cell class is the makeup of the grid, storing important variables for
 * pathfinding
 * 
 * char cellType             is the char that is printed when the cell is accessed 
 * boolean isWall            returns if the Cell is a wall
 * boolean isStart           returns if the Cell is the start
 * boolean isEnd             returns if the Cell is the end
 * ArrayList<Cell> neighbors returns the neighbors of the cell
 * int g                     the g value associated with the cell (path distance)
 * int h                     the h value associated with the cell (raw distance)
 * int f                     the f value associated with the cell (raw + path distance)
 * int x                     the x location of the cell
 * int y                     the y location of the cell
 * Cell previous             the previous Cell before this cell
 * boolean hasPrevious       returns if the Cell has a previous cell
 * 
 * @author NocaToca
 * @version 6/18/20
 */
public class Cell
{
    char cellType;
    boolean isWall;
    boolean isStart;
    boolean isEnd;
    ArrayList<Cell> neighbors = new ArrayList<Cell>();
    int g;
    double h;
    double f;
    int x;
    int y;
    Cell previous;
    boolean hasPrevious;
    //The constuctor takes in the Cell's x and y location and sets the cell's
    //defaults
    public Cell(int xk, int yk){
    
        cellType =' ';
        isWall = false;
        isStart = false;
        isEnd = false;
        x = xk;
        y = yk;
        g = 0;
        h = 0;
        f = 0;
        hasPrevious = false;
    }
    
    //Changes the cellType to a wall
    public void changeCellType(){
    
        cellType ='X';
        isWall = true;
    
    }
    
    //Sets the Cell as the starting location
    public void isStart(){
    
        cellType = 'O';
        isWall = false;
        isStart = true;
    
    }
    
    //Sets the Cell as the ending location
    public void isEnd(){
    
        cellType = '!';
        isWall = false;
        isEnd = true;
    
    }
    
    /**
    *Updates the neighbors of the cell, taking in the grid that the cell is on
    *
    *
    */
    public void updateNeighbors(Grid grid){
        //If the cell is on the bottom, it has no neighbor cell under it
        if(y < grid.h-1){
        
            //System.out.println(y + " " + (grid.getHeight()-1));
            //System.out.println(x);
            neighbors.add(grid.getCell(x, y+1));
        
        }
        //If the cell is on the top, it has no neighbor cell above it
        if(y > 0){
        
            neighbors.add(grid.getCell(x, y-1));
        
        }
        //If the cell is on the right, it has no neighbor cell right of it
        if(x < grid.w-1){
        
            neighbors.add(grid.getCell(x+1, y));
        
        }
        //If the cell is on the left, it has no neighbor cell left of it
        if(x > 0){
        
            neighbors.add(grid.getCell(x-1, y));
        
        }
        
        
    
    }

}
