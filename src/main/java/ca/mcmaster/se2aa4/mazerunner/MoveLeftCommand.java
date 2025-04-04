package ca.mcmaster.se2aa4.mazerunner;
import static ca.mcmaster.se2aa4.mazerunner.MovementType.L;


public class MoveLeftCommand extends Command {

    private int moves = 3; 
    private int movesForUndo = 1; 

    public MoveLeftCommand(Character c){
        super(c, L);
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
