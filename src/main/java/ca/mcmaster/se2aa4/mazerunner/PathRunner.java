package ca.mcmaster.se2aa4.mazerunner;

public class PathRunner {

    Character c; 
    Path p = new Path();
    public PathRunner(int startRow){
        c = new Character(startRow);
    }

    public boolean isCorrectPath(String inputString, Maze m){

        String inputCanonical = p.canonicalForm(inputString);

        //try both the west to east and east to west paths
        if (runThroughMaze(inputCanonical, m, Direction.East) || runThroughMaze(inputCanonical, m, Direction.West)){
            return true;
        }
        return false;
    }

    public boolean runThroughMaze (String s, Maze m, Direction startDirection){
        
        s = s.trim();
        c.setDirection(startDirection);

        //switch starting position to west end
        if (startDirection == Direction.West){
            c.setXPos(m.getExitRow());
            c.setYPos(m.getCols()-1);
        }
        
        for (int i = 0; i< s.length(); i++){
            char currMove = s.charAt(i);
            if (currMove == 'L'){
                c.movePlayer(MovementType.L);
            }
            else if (currMove == 'R'){
                c.movePlayer(MovementType.R);
            }
            else if (currMove == 'F'){
                c.movePlayer(MovementType.F);
            }
            else if (currMove == ' '){
                continue;
            }
            else{
                //invalid character in path solution entered by user 
                return false; 
            }
            if (c.getXPos() < 0 || c.getXPos() >= m.getRows() || c.getYPos() < 0 || c.getYPos() >= m.getCols()){
                return false; //gone out of bounds of the maze 
            }
            if (m.getMaze()[c.getXPos()][c.getYPos()] == positionType.wall ){
                //user has entered into a wall
                return false;
            }
        }
        if (startDirection == Direction.East && c.getXPos() == m.getExitRow() && c.getYPos() == m.getCols()-1){
            return true; 
        }
        else if (startDirection == Direction.West && c.getXPos() == m.getEntryRow() && c.getYPos() == 0){
            return true; 
        }
        return false;

    }
}
