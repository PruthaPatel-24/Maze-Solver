package ca.mcmaster.se2aa4.mazerunner;



public class RightHandSolver implements MazeSolver {        
    private boolean solved = false;
    private Path p = new Path();

    public String solveMaze(Character c, Maze m){
        while (!solved){
            if (c.canTurnLeft(m.getMaze())){ //if wall in front, must turn left
                c.movePlayer(MovementType.turnLeft);
                p.addStep(move.L.toString());
            }   
            else if (c.canGoStraight(m.getMaze())){ //if no wall in front & going straight would mean theres a wall on your right
                c.movePlayer(MovementType.straight);
                p.addStep(move.F.toString());
            }
            else{ //turns the right corner 
                c.movePlayer(MovementType.straight);
                c.movePlayer(MovementType.turnRight);
                c.movePlayer(MovementType.straight);
                p.addStep(move.F.toString() + move.R.toString() + move.F.toString());
            }
            
            if (c.getXPos() == m.getExitRow() && c.getYPos() == m.getCols() - 1){ //if at exit position
                solved = true;
            }
        }
        return p.factorizedForm(p.getUserPath());
    }


}
