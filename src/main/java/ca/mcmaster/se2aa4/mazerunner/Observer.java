package ca.mcmaster.se2aa4.mazerunner;

public abstract class Observer {

    protected Subject subject; 
    
    public abstract void update(Command c, CommandType cType);

}
