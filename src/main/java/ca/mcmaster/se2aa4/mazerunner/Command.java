package ca.mcmaster.se2aa4.mazerunner;

public abstract class Command {
    public Character c; 
    private MovementType mType; 

    public Command(Character c, MovementType mType){
        this.c = c;
        this.mType = mType;
    }

    public abstract void execute();
    public abstract void undo();

    public MovementType getMType(){
        return mType; 
    }

    
}
