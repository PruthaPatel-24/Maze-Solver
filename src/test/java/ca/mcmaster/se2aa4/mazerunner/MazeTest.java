package ca.mcmaster.se2aa4.mazerunner;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MazeTest {
    
    private Maze m;

    @BeforeEach 
    public void initalizeForTesting(){
        m  = new Maze("./examples/small.maz.txt");     
    }

    @Test
    public void testLoadMaze(){
        m.loadMaze();
        assertEquals(11, m.getRows());
        assertEquals(11, m.getCols());
    }
    @Test
    public void testFindEntryandExit() {
        m.findEntryAndExit();
        assertEquals(8, m.getEntryRow());
        assertEquals(5, m.getExitRow());
    }



}
