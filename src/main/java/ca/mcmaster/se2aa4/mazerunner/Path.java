package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Path {

    private String userPath = ""; //tracks canonical form

    public String getUserPath(){
        return userPath;
    }

    public void addStep (String step){
        userPath += step;
    }

    public String factorizedForm (String s){
        if (s.equals("")){
            return s;
        }
        s = s.trim();
        String factorized = "";
    
        char currentChar = s.charAt(0); 
        int instances = 0; 
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == ' '){
                continue;
            }
            else if (s.charAt(i) == currentChar){
                instances +=1;
            }
            else{
                factorized += String.valueOf(instances) + currentChar + ' ';
                currentChar = s.charAt(i);
                instances = 1; 
            }
        }

        factorized += String.valueOf(instances) + currentChar + ' ';
        return factorized;

    }

    public String canonicalForm(String factorized){
        StringBuilder canonical = new StringBuilder();
        StringBuilder currentDigit = new StringBuilder("");

        for (int i = 0; i < factorized.length(); i++){
            if (factorized.charAt(i) == 'F' || factorized.charAt(i) == 'R' || factorized.charAt(i)== 'L'){
                int repetitions = 1; 
                if (!currentDigit.toString().equals("")){
                    repetitions = Integer.parseInt(currentDigit.toString());
                }
                for (int j = 0; j < repetitions; j++){
                        canonical.append(factorized.charAt(i));
                }
                currentDigit.replace(0, currentDigit.length(), ""); 
                
            }
            else if (factorized.charAt(i) >= '0' && factorized.charAt(i) <= '9'){
                currentDigit.append(factorized.charAt(i));
            }
            else if (factorized.charAt(i) != ' '){
                //"String inputted not a valid path";
                System.exit(1);

            }
        }
        return canonical.toString(); 
    }

    public boolean isCorrectPath(String inputString, Maze m){

        String inputCanonical = canonicalForm(inputString);
        if (runThroughMaze(inputCanonical, m, Direction.East) || runThroughMaze(inputCanonical, m, Direction.West)){
            return true;
        }
        return false;
    }

    public boolean runThroughMaze (String s, Maze m, Direction startDirection){
        s = s.trim();
        Character c = new Character(m.getEntryRow());
        c.setDirection(startDirection);

        if (startDirection == Direction.West){
            c.setXPos(m.getExitRow());
            c.setYPos(m.getCols()-1);
        }
        
        for (int i = 0; i< s.length(); i++){
            char currMove = s.charAt(i);
            if (currMove == 'L'){
                c.movePlayer(MovementType.turnLeft);
            }
            else if (currMove == 'R'){
                c.movePlayer(MovementType.turnRight);
            }
            else if (currMove == 'F'){
                c.movePlayer(MovementType.straight);
            }
            else if (currMove == ' '){
                continue;
            }
            else{
                //invalid character in path solution entered by user 
                return false; 
            }
            if (m.getMaze()[c.getXPos()][c.getYPos()] == positionType.wall){
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
