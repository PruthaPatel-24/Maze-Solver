package ca.mcmaster.se2aa4.mazerunner;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static ca.mcmaster.se2aa4.mazerunner.Direction.*;

public class CharacterTest {
    private Character c;
    private Maze m;

    @BeforeEach
    public void setUp(){
        this.c = new Character(8);   
        this.m  = new Maze("./examples/small.maz.txt");     
    }
    @Test
    public void testExecuteCommand() {
        System.out.println("before test move player");
        c.executeCommand(new MoveLeftCommand(c));
        assertEquals(North, c.getDirection());
        c.executeCommand(new MoveLeftCommand(c));
        assertEquals(West, c.getDirection());
        c.executeCommand(new MoveRightCommand(c));
        assertEquals(North, c.getDirection());
        c.executeCommand(new MoveForwardCommand(c));
        assertEquals(North, c.getDirection());
        c.executeCommand(new MoveLeftCommand(c));
        assertEquals(West, c.getDirection());

    }
    
    @Test
    public void testCanTurnLeft() {
        assertEquals(false, c.canTurnLeft(m.getMaze()));
    }

    @Test
    public void testCanGoStraight() {
        assertEquals(false, c.canGoStraight(m.getMaze()));
    }

}
