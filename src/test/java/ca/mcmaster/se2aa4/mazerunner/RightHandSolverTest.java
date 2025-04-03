package ca.mcmaster.se2aa4.mazerunner;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RightHandSolverTest {
    private Character c; 
    private Maze m;
    private RightHandSolver rHS; 

    @BeforeEach
    public void initalizeForTesting(){
        c = new Character(2);   
        m  = new Maze("./examples/straight.maz.txt");
        rHS = new RightHandSolver();
    }

    @Test
    public void testSolveMaze() {
        rHS.solveMaze(c, m);
        assertEquals("4F ", rHS.solveMaze(c, m));
    }
}