package ca.mcmaster.se2aa4.mazerunner;



public class RightHandSolver implements MazeSolver {        
    private boolean solved = false;
    private Path p = new Path();

    public String solveMaze(Character c, Maze m){
        while (!solved){
            if (c.canTurnLeft(m.getMaze())){
                c.movePlayer(MovementType.turnLeft);
                p.addStep("L");
            }   
            else if (c.canGoStraight(m.getMaze())){
                c.movePlayer(MovementType.straight);
                p.addStep("F");
            }
            else{
                c.movePlayer(MovementType.turnRight);
                p.addStep("FRF");
            }
            
            if (c.getXPos() == m.getExitRow() && c.getYPos() == m.getCols() -1){
                solved = true;
            }
        }
        return p.factoriedForm();
    }


}
