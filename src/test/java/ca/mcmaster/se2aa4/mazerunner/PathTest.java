package ca.mcmaster.se2aa4.mazerunner;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PathTest {
    private Path p; 
    private Maze m;

    @BeforeEach
     void initalizeForTesting(){
        p = new Path();
        m = new Maze("./examples/small.maz.txt");
    }

    @Test
    public void testAddStep() {
        p.addStep("R");
        assertEquals("R", p.getUserPath());
        p.addStep("F");
        assertEquals("RF", p.getUserPath());
        p.addStep("L");
        assertEquals("RFL", p.getUserPath());
    }
    
    @Test
    public void testFactorizedForm (){
        assertEquals("2R 2L 2F 1R 1L ",p.factorizedForm("RRLLFFRL"));
    }

    @Test
    public void testCanonicalForm(){
        assertEquals("RRLLFFRL",p.canonicalForm("2R 2L 2F 1R 1L"));
    }

    @Test
    public void testIsCorrectPath(){
        assertFalse(p.isCorrectPath("RLR", m));
        assertTrue(p.isCorrectPath("1F 1R 1F 2L 2F 1R 2F 1R 2F 2L 4F 1R 2F 1R 4F 2L 2F 1R 4F 1R 2F 1R 2F 2L 2F 1L 2F 1L 4F 1R 2F 1R 2F 2L 4F 1R 2F 1R 2F 2L 2F 1R 2F 1R 4F 1R 2F 1L 2F 1R 2F 1L 1F", m));

    }
    
}