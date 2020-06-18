
/**
 * This is the main class that just runs the code.
 *
 * @author NocaToca
 * @version 6/18/20
 */
public class main
{
    public static void main(String [] args){
    
        //Creating the new grid the AI will have to pathfind through
        Grid maze = new Grid(75, 25);
        
        //Setting the Start and end points of the grid
        maze.setStart(2, 2);
        maze.setEnd(70,22);
        
        //Building the grid (so it prints to the screen)
        maze.buildGrid();
        
        //Creating the pathfinder AI and then running it through the maze
        PathFinder Otacoon = new PathFinder('o');
        Otacoon.pathFind(maze);
    
    }
}
