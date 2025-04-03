package ca.mcmaster.se2aa4.mazerunner;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RightHandSolverTest {
    private Maze m;
    private RightHandSolver rHS; 

    @BeforeEach
    public void initalizeForTesting(){
        m  = new Maze("./examples/straight.maz.txt");
        rHS = new RightHandSolver(2);
    }

    @Test
    public void testSolveMaze() {
        assertEquals("4F ", rHS.solveMaze(m));
    }

}