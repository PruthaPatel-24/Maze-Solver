package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


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
    public int [][][] increments = // []->straight, turn etc. [] // what way you're facing  [] what numbers to add 
    {
        {{-1, 0}, {0, +1}, {+1, 0}, {0, -1}}, //move straight 
        {{-1, +1}, {1, 1}, {1, -1}, {-1, -1}}, // right turn 
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

}

enum Direction {North, East, South, West};

