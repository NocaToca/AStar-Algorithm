

import java.util.*;
/**
 * The grid class' main function is the create and store a grid of Cells in
 * a 2D array
 * 
 * Cell [][] Grid  is the 2D Cell array that stores all the cells
 * int w           is the width of the cell array
 * int h           is the height of the cell array
 * Cell starting   is the Cell that is deemed the starting point in the maze
 * Cell ending     is the Cell that is deemed the ending point in the maze
 *
 * @author NocaToca
 * @version 6/18/20
 */
public class Grid
{
    Cell[][] Grid;
    int w;
    int h;
    Cell starting;
    Cell ending;
    
    //The constructor needs what you want the width and the height of the grid
    //to be
    Grid(int width, int height){
        //Setting the cell to that height and width, and then the width and
        //the height to that too (I did this so it's easier for me to read)
        Grid = new Cell[height][width];
        w = width;
        h = height;
        
        //Creating the 2D Cell array and randomly determining if the Cell
        //is a wall or not
        for(int i = 0; i < height; i++){
            for(int k = 0; k < width; k++){
            
                Grid[i][k] = new Cell(k, i);
                double rng = Math.random();
                if(rng < .2){
                
                    Grid[i][k].changeCellType();
                
                }
            
            }        
        }
        
        //Giving all of the Cells their neighbors, which is used for pathfinding
        //purposes
        for(int i = 0; i < height; i++){
            for(int k = 0; k < width; k++){
                Grid[i][k].updateNeighbors(this);
            }
        }
    
    }
    
    /**
    *Quite simply sets the start of the Maze, letting the Cell know it's
    *now the starting position
    */
    public void setStart(int x, int y){
    
        Grid[y][x].isStart();
        starting = Grid[y][x];
    
    }
    
    /**
    *Quite simply sets the end of the Maze, letting the Cell know it's
    *now the ending position
    */
    public void setEnd(int x, int y){
    
        Grid[y][x].isEnd();
        ending = Grid[y][x];
    
    }
    
    /**
    *Prints the board to the screen
    */
    public void buildGrid(){
    
        //System.out.println(w + "" + h);
        
        for(int i = 0; i < h; i++){
            for(int k = 0; k < w; k++){
            
                System.out.print(Grid[i][k].cellType);
            
            }
            System.out.println("");
        }
    
    }
    
    
    //Returns the Cell at that certain position
    public Cell getCell(int x,int y){
    
        return Grid[y][x];
    
    }
    
    
    
    
}
