package ca.mcmaster.se2aa4.mazerunner;
import static ca.mcmaster.se2aa4.mazerunner.CommandType.*;

public class Character extends Subject {
    private int xPos; 
    private int yPos = 0;
    private Direction direction = Direction.East;   
    private CommandHistory history = new CommandHistory();

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

    public void executeCommand(Command command){
        command.execute();
        history.push(command);
        notifyAllObservers(command, execute);
    }

    public void undoCommand(Command command){

        if (history.isEmpty()) return;

        Command commandToUndo = history.pop();
        if (commandToUndo != null) {
            commandToUndo.undo();
        }
        notifyAllObservers(command, undo);
    }

    public boolean canTurnLeft(positionType [][] maze){
        //can turn left if there will be a wall in the forward position 
        MoveForwardCommand moveForward = new MoveForwardCommand(this);
        executeCommand(moveForward);
        if (maze[xPos][yPos] == positionType.wall){
            undoCommand(moveForward);
            return true; 
        } 
        else{
            undoCommand(moveForward);
            return false; 
        }

    }

    public boolean canGoStraight(positionType [][] maze){
        if (canTurnLeft(maze)){ 
            return false; 
        }
        MoveForwardCommand moveForward = new MoveForwardCommand(this);
        MoveRightCommand moveRight = new MoveRightCommand(this);
        MoveForwardCommand moveForwardAgain = new MoveForwardCommand(this);
        executeCommand(moveForward);
        executeCommand(moveRight);
        executeCommand(moveForwardAgain);
        if (maze[xPos][yPos] == positionType.wall){
            undoCommand(moveForward);
            undoCommand(moveRight);
            undoCommand(moveForwardAgain);
            return true; 
        } 
        else{
            undoCommand(moveForward);
            undoCommand(moveRight);
            undoCommand(moveForward);
            return false; 
        }
    }

    public boolean isValidMove(int rows, int cols, positionType p){
        if (xPos < 0 || xPos >= rows || yPos < 0 || yPos >= cols){
            return false; //gone out of bounds of the maze 
        }
        if (p == positionType.wall ){
            //user has entered into a wall
            return false;
        }
        return true;
    }

}

enum Direction {North, East, South, West};

enum MovementType {F, R, L};