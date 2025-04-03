package ca.mcmaster.se2aa4.mazerunner;

public class Character extends Subject {
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

    public void setXPos (int i){
        xPos = i;
    }

    public void setYPos (int i){
        yPos = i;
    }

    public Direction getDirection (){
        return direction; 
    }

    public void setDirection (Direction d){
        direction = d;
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

        notifyAllObservers(m);
        direction = Direction.values()[(direction.ordinal() + m.ordinal()) % Direction.values().length];
    }

    public boolean canTurnLeft(positionType [][] maze){
        return (maze[xPos + increments[MovementType.F.ordinal()][direction.ordinal()][0]]
        [yPos + increments[MovementType.F.ordinal()][direction.ordinal()][1]]
        == positionType.wall);
    }

    public boolean canGoStraight(positionType [][] maze){
        return (!canTurnLeft(maze) && 
        maze[xPos+ increments[MovementType.F.ordinal()][direction.ordinal()][0] + increments[MovementType.F.ordinal()][(direction.ordinal()+1)%Direction.values().length][0]]
        [yPos+ increments[MovementType.F.ordinal()][direction.ordinal()][1] + increments[MovementType.F.ordinal()][(direction.ordinal()+1)%Direction.values().length][1]]
        == positionType.wall);
    }

}

enum Direction {North, East, South, West};

enum MovementType {F, R, uTurn, L};