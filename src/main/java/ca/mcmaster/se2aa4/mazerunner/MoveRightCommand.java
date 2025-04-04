package ca.mcmaster.se2aa4.mazerunner;
import static ca.mcmaster.se2aa4.mazerunner.MovementType.R; 

public class MoveRightCommand extends Command {

    private int moves = 1; 
    private int movesForUndo = 3; 

    public MoveRightCommand(Character c){
        super(c, R);
    }

    @Override
    public void execute() {
        c.setDirection(Direction.values()[(c.getDirection().ordinal() + moves) % Direction.values().length]);
    }

    @Override
    public void undo(){
        c.setDirection(Direction.values()[(c.getDirection().ordinal() + movesForUndo) % Direction.values().length]);
    }

}
