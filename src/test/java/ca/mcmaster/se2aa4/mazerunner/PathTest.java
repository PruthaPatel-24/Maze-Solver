package ca.mcmaster.se2aa4.mazerunner;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static ca.mcmaster.se2aa4.mazerunner.MovementType.*;
public class PathTest {
    private Path p; 

    @BeforeEach
     void initalizeForTesting(){
        p = new Path(new Character(8));
    }

    @Test
    public void testUpdate() { //used to be addStep function but refactored with observer design pattern
        p.update(R);
        assertEquals("R", p.getUserPath());
        p.update(F);
        assertEquals("RF", p.getUserPath());
        p.update(L);
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
    
}