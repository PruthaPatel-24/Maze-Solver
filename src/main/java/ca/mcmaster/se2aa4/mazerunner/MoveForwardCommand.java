package ca.mcmaster.se2aa4.mazerunner;
import static ca.mcmaster.se2aa4.mazerunner.MovementType.F;

public class MoveForwardCommand extends Command {

    private int [][] forwardIncrements = {{-1, 0}, {0, +1}, {+1, 0}, {0, -1}};
    private int [][] backwardIncrements = {{+1, 0}, {0, -1}, {-1, 0}, {0, +1}};

    public MoveForwardCommand(Character c){
        super(c, F);
    }

    @Override
    public void execute() {
        c.setXPos(c.getXPos() + forwardIncrements[c.getDirection().ordinal()][0]);
        c.setYPos(c.getYPos() + forwardIncrements[c.getDirection().ordinal()][1]);
        
    }

    @Override
    public void undo(){
        c.setXPos(c.getXPos() + backwardIncrements[c.getDirection().ordinal()][0]);
        c.setYPos(c.getYPos() + backwardIncrements[c.getDirection().ordinal()][1]);
    }

}
