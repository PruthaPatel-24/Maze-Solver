package ca.mcmaster.se2aa4.mazerunner;

public class Character {
    private int xPos; 
    private int yPos = 0;
    private Direction direction = Direction.East;

    public Character (int startRow){
        xPos = startRow;
    }

    public int getXPos (){
        return xPos;
    }

    public int getYPos (){
        return yPos;
    }

    public Direction getDirection (){
        return direction; 
    }

    private int [][][] increments = // []->straight, turn etc. [] //-> direction  [] ->what numbers to add to coordinate to move
    {
        {{-1, 0}, {0, +1}, {+1, 0}, {0, -1}}, //move straight 
        {{0, 0}, {0, 0}, {0, 0}, {0, 0}}, // right turn 
        {{0, +2}, {+2, 0}, {0, -2}, {-2, 0}}, // u turn 
        {{0, 0}, {0, 0}, {0, 0}, {0, 0}}  // left turn
    };

    public void movePlayer(MovementType m){
        xPos = xPos + increments[m.ordinal()][direction.ordinal()][0];
        yPos = yPos + increments[m.ordinal()][direction.ordinal()][1];

        direction = Direction.values()[(direction.ordinal() + m.ordinal()) % Direction.values().length];
    }

    public boolean canTurnLeft(positionType [][] maze){
        return (maze[xPos + increments[MovementType.straight.ordinal()][direction.ordinal()][0]]
        [yPos + increments[MovementType.straight.ordinal()][direction.ordinal()][1]]
        == positionType.wall);
    }

    public boolean canGoStraight(positionType [][] maze){
        return (!canTurnLeft(maze) && 
        maze[xPos+ increments[MovementType.straight.ordinal()][direction.ordinal()][0] + increments[MovementType.straight.ordinal()][(direction.ordinal()+1)%Direction.values().length][0]]
        [yPos+ increments[MovementType.straight.ordinal()][direction.ordinal()][1] + increments[MovementType.straight.ordinal()][(direction.ordinal()+1)%Direction.values().length][1]]
        == positionType.wall);
    }

    //takes path from inputted string s, and moves player accordingly
    public boolean runThroughMaze (String s, Maze m, Direction startDirection){
        s = s.trim();
        direction = startDirection;

        //switch starting position to west end
        if (startDirection == Direction.West){
            xPos = m.getExitRow();
            yPos = m.getCols()-1;
        }
        
        for (int i = 0; i< s.length(); i++){
            char currMove = s.charAt(i);
            if (currMove == 'L'){
                movePlayer(MovementType.turnLeft);
            }
            else if (currMove == 'R'){
                movePlayer(MovementType.turnRight);
            }
            else if (currMove == 'F'){
                movePlayer(MovementType.straight);
            }
            else if (currMove == ' '){
                continue;
            }
            else{
                //invalid character in path solution entered by user 
                return false; 
            }
            if (xPos < 0 || xPos >= m.getRows() || yPos < 0 || yPos >= m.getCols()){
                return false; //gone out of bounds of the maze 
            }
            if (m.getMaze()[xPos][yPos] == positionType.wall ){
                //user has entered into a wall
                return false;
            }
        }
        if (startDirection == Direction.East && xPos == m.getExitRow() && yPos == m.getCols()-1){
            return true; 
        }
        else if (startDirection == Direction.West && xPos == m.getEntryRow() && yPos == 0){
            return true; 
        }
        return false;

    }
}

enum Direction {North, East, South, West};

enum MovementType {straight, turnRight, uTurn, turnLeft};