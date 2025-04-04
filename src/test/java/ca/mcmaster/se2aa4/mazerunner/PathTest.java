package ca.mcmaster.se2aa4.mazerunner;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static ca.mcmaster.se2aa4.mazerunner.MovementType.*;
import static ca.mcmaster.se2aa4.mazerunner.CommandType.*;

public class PathTest {
    private Path p; 
    private Character c; 

    @BeforeEach
     void initalizeForTesting(){
        c = new Character(8);
        p = new Path(c);
    }

    @Test
    public void testUpdate() { //used to be addStep function but refactored with observer design pattern
        p.update(new MoveRightCommand(c), execute);
        assertEquals("R", p.getUserPath());
        p.update(new MoveForwardCommand(c), execute);
        assertEquals("RF", p.getUserPath());
        p.update(new MoveLeftCommand(c), execute);
        assertEquals("RFL", p.getUserPath());
        p.update(new MoveLeftCommand(c), undo);
        assertEquals("RF", p.getUserPath());
        p.update(new MoveForwardCommand(c), execute);
        assertEquals("RFF", p.getUserPath());
        
    }

    @Test
    public void testFactorizedForm (){
        assertEquals("2R 2L 2F 1R 1L ",p.factorizedForm("RRLLFFRL"));
    }

    @Test
    public void testCanonicalForm(){
        assertEquals("RRLLFFRL",p.canonicalForm("2R 2L 2F 1R 1L"));
    }
    
}