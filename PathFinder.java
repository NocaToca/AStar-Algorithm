import java.util.*;
/**
 * PathFinding part of the AI
 * 
 * char pathChar    this is the char the path is represented by
 *
 * @author NocaToca
 * @version 6/18/20
 */
public class PathFinder
{
    char pathChar;

    //Constuctor just needs the path char to update
    public PathFinder(char character){

        pathChar = character;

    }

    /**
     *This uses the A* algorithm to find the best possible location,
     *having an openCells array and a closedCells array
     */
    public void pathFind(Grid grid){

        //Making the closed, open and path arrays
        ArrayList<Cell> openCells = new ArrayList<Cell>();
        ArrayList<Cell> closedCells = new ArrayList<Cell>();
        ArrayList<Cell> path = new ArrayList<Cell>();

        //Adding the starting node to the open cells
        openCells.add(grid.starting);

        //As long as there are open cells, the algorithm will try to find a
        //solution
        while(openCells.size() != 0){

            //Drawing the closed/open cells
            for(int i = 0; i < closedCells.size(); i++){
                closedCells.get(i).cellType = '.';
            }

            for(int i = 0; i < openCells.size(); i++){
                //openCells.get(i).setCellType('o');
            }
            for(int i = 0; i < path.size(); i++){

                path.get(i).cellType = pathChar;

            }

            //Making space between each board
            //System.out.println("");
            //System.out.println("");
            //System.out.println("");
            //grid.buildGrid();

            //finding the lowest index openCell to assess 
            int lowestIndex = 0;
            for(int i = 0; i < openCells.size(); i++){

                if(openCells.get(i).f < openCells.get(lowestIndex).f){

                    lowestIndex = i;

                }

            }

            //Setting the current cell to that cell with the lowest index
            Cell current = openCells.get(lowestIndex);
            ArrayList<Cell> currentNeighbors = current.neighbors;

            //If the current cell is the ending cell
            if(current == grid.ending){
                //Draw the path
                //for(int i = 0; i < path.size(); i++){

                //   path.get(i).cellType = pathChar;

                //}
                //Say we finished and end the algorithm
                System.out.println("Finished!");
                break;

            } else if(currentNeighbors.contains(grid.ending)){
                
                //If it finds the neighboring node is actually the ending node, we run all of the path functions to reset the path and create it
                int indexOf = 0;
                for(int i = 0; i < currentNeighbors.size(); i++){

                    if(currentNeighbors.get(i) == grid.ending){

                        indexOf = i;

                    }

                }
                for(int i = 0; i < path.size(); i++){

                    path.get(i).cellType = ' ';

                }

                for(int i = path.size()-1; i >= 0; i--){

                    path.remove(i);

                }
                currentNeighbors.get(indexOf).previous = current;
                currentNeighbors.get(indexOf).hasPrevious = true;
                Cell temp = currentNeighbors.get(indexOf);
                path.add(temp);
                while(temp.hasPrevious){

                    path.add(temp.previous);
                    //temp.previous.hasPrevious = true;
                    temp = temp.previous;

                }
                for(int i = 0; i < path.size(); i++){

                    path.get(i).cellType = pathChar;

                }

                System.out.println("Finished!");
                break;

            }

            //otherwise we get rid of this cell and add it to the closed cell
            openCells.remove(current);
            closedCells.add(current);

            //We reset the path array so it doesnt have the wrong path
            //(comment this out to see what it does, hard to explain short)
            for(int i = 0; i < path.size(); i++){

                path.get(i).cellType = ' ';

            }

            for(int i = path.size()-1; i >= 0; i--){

                path.remove(i);

            }

            //Getting the neighbors of the cell we just assessed 

            for(int i = 0; i < currentNeighbors.size(); i++){

                Cell neighbor = currentNeighbors.get(i);

                //Seeing if the neighbor cells are closed or walls
                //If they are we dont do anything

                if(!closedCells.contains(neighbor) && !neighbor.isWall){

                    //We dont want to immediately declare the new g
                    int tempG = current.g + 1;

                    //If the cell is already in the openCells array, we assess
                    //the g to see if its smaller
                    //if it is we assign it
                    if(openCells.contains(neighbor)){
                        if(tempG < neighbor.g){

                            neighbor.g = tempG;

                        }

                    } else {
                        //or if its not in the openCells array we assign its
                        //g and then add it to the open cells array
                        neighbor.g = tempG;
                        openCells.add(neighbor);

                    }

                    //assigning the values to the neighboring cells
                    neighbor.h = distance(neighbor, grid.ending);
                    neighbor.f = neighbor.g + neighbor.h;

                    //Assinging the previous Cell to the neighbor
                    neighbor.previous = current;
                    neighbor.hasPrevious = true;

                    //And then adding the cell and all of its previous' to the
                    //path
                    Cell temp = current;
                    path.add(temp);
                    while(temp.hasPrevious){

                        path.add(temp.previous);
                        //temp.previous.hasPrevious = true;
                        temp = temp.previous;

                    }

                 
                }
            
            }

        }
        //Building the board one last time
        System.out.println("");
        System.out.println("");
        System.out.println("");
        grid.buildGrid();

    }

    //A function I used to calculate distance using the traingle thing
    public double distance(Cell cellOne, Cell cellTwo){
        return Math.sqrt((Math.pow(cellOne.x - cellTwo.x, 2) + Math.pow(cellOne.y - cellTwo.y, 2)));
    }
}
