package ca.mcmaster.se2aa4.mazerunner;
import static ca.mcmaster.se2aa4.mazerunner.Direction.East;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class PathRunnerTest {
    private PathRunner pathRunner; 
    private Maze m;

    @BeforeEach
     void setUp(){
        pathRunner = new PathRunner(2);
        m = new Maze("./examples/straight.maz.txt");
    }

    @Test
    public void testIsCorrectPath(){
        //testing on straight maze 
        assertFalse(pathRunner.isCorrectPath("RLRFFF", m));

        pathRunner = new PathRunner(2);
        assertTrue(pathRunner.isCorrectPath("4F", m));

        //testing on tiny maze 
        pathRunner = new PathRunner(6);
        m = new Maze("./examples/tiny.maz.txt");
        assertFalse(pathRunner.isCorrectPath("5F 2L 2F", m));
        pathRunner = new PathRunner(6);
        assertTrue(pathRunner.isCorrectPath("5F 2L 2F 1R 2F 1R 2F 2L 2F 1R 2F 1R 3F", m));
        
        //testing on small maze 
        pathRunner = new PathRunner(8);
        m = new Maze("./examples/small.maz.txt");
        assertFalse(pathRunner.isCorrectPath("5R 2L 2F", m));
        pathRunner = new PathRunner(8);
        assertTrue(pathRunner.isCorrectPath("1F 1R 1F 2L 2F 1R 2F 1R 2F 2L 4F 1R 2F 1R 4F 2L 2F 1R 4F 1R 2F 1R 2F 2L 2F 1L 2F 1L 4F 1R 2F 1R 2F 2L 4F 1R 2F 1R 2F 2L 2F 1R 2F 1R 4F 1R 2F 1L 2F 1R 2F 1L 1F", m));

    }
    
    @Test
    public void testRunThroughMaze(){
        assertFalse(pathRunner.runThroughMaze("27F", m, East));
    }

}