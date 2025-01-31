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

    public void moveForward(){
        yPos++;
    }

    public void turnCorner(){

        if (direction == Direction.North){
            xPos--;
            yPos++;
        }
        else if (direction == Direction.East){
            xPos++;
            yPos++;
        }
        else if (direction == Direction.South){
            xPos++;
            yPos--;
        }
        else{
            xPos--;
            yPos--;
        }

        direction = Direction.values()[(direction.ordinal() + 1 ) % Direction.values().length];
        
    }
    
}

enum Direction {North, East, South, West};

