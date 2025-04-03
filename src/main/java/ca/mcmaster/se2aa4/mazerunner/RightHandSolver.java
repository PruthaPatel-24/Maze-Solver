package ca.mcmaster.se2aa4.mazerunner;

public class RightHandSolver implements MazeSolver {        
    private boolean solved = false;
    private Character c;
    private Path p;

    public RightHandSolver (int startRow){
        c = new Character(startRow);
        p = new Path(c);
    }

    public String solveMaze(Maze m){
        while (!solved){
            if (c.canTurnLeft(m.getMaze())){ //if wall in front, must turn left
                c.movePlayer(MovementType.L);
            }   
            else if (c.canGoStraight(m.getMaze())){ //if no wall in front & going straight would mean theres a wall on your right
                c.movePlayer(MovementType.F);
            }
            else{ //turns the right corner 
                c.movePlayer(MovementType.F);
                c.movePlayer(MovementType.R);
                c.movePlayer(MovementType.F);
            }
            
            if (c.getXPos() == m.getExitRow() && c.getYPos() == m.getCols() - 1){ //if at exit position
                solved = true;
            }
        }
        return p.factorizedForm(p.getUserPath());
    }


}
