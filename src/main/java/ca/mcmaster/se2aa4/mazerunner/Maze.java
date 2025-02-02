package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Maze {    
    private static final Logger logger = LogManager.getLogger();
    private String mazeFile;
    private positionType maze [][];
    private int entryRow;
    private int exitRow;
    private int rows; 
    private int cols;
    private boolean solved = false;
    private String solution = "";

    public Maze (String mazeFile){
        this.mazeFile = mazeFile;
        loadMaze();
    }

    public void loadMaze(){
        List<String> lines = new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(mazeFile));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();
        }catch(Exception e) {
            logger.error("Unable to Load Maze");
        }

        rows = lines.size();
        cols = lines.get(0).length();
        maze = new positionType[rows][cols]; 
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++) {
                if (lines.get(i).length() <= j){
                    while (j < cols){
                        maze[i][j] = positionType.empty; 
                        j++;
                    }
                    break;
                }
                if (lines.get(i).charAt(j) == '#') {
                    maze[i][j] = positionType.wall; 
                } else if (lines.get(i).charAt(j) == ' ') {
                    maze[i][j] = positionType.empty; 
                }
            }

        }

        findEntryAndExit();
    }

    public void findEntryAndExit (){
        for (int i = 0; i < rows; i++){
            if (maze[i][0] == positionType.empty){
                entryRow = i;
            }
            if (maze[i][cols - 1 ] == positionType.empty){
                exitRow = i;
            }
        }
    }
    public void printMaze (){
        for (int i = 0; i < rows; i++){
            String line = "";
            if (i == entryRow){
                line += "->";
            }
            else{
                line += "  ";
            }
            for (int j = 0; j <cols; j++) {
                if (maze[i][j] == positionType.empty){
                    line += " ";
                }
                else{
                    line += "#";
                }
            }
            if (i == exitRow){
                line += "<-";
            }
            logger.info(line);
        }
    }

    public void solveMaze(Character c){
        while (!solved){
            if (c.canTurnLeft(maze)){
                c.movePlayer(MovementType.turnLeft);
                solution+="L";
            }   
            else if (c.canGoStraight(maze)){
                c.movePlayer(MovementType.straight);
                solution+="F";
            }
            else{
                c.movePlayer(MovementType.turnRight);
                solution+="FRF";
            }
            
            if (c.getXPos() == exitRow && c.getYPos() == cols -1){
                solved = true;
            }
        }
        logger.info(solution);
    }

    public int getEntryRow(){
        return entryRow;
    }

}

enum positionType {wall, empty};
enum move {F, L, R};
enum MovementType {straight, turnRight, uTurn, turnLeft};