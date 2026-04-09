package block.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import block.model.Player;


public class blockModelPlayerTest {
    private Player player;

    @BeforeEach
    public void setUp() {
        player = new Player();
    }

    @Test
    public void testInitialPosition() { //skal teste at spilleren starter på kolonne 4:
        assertEquals(4, player.getCol());
    }
    
    @Test
    public void testMoveLeft() {
        player.moveLeft();
        assertEquals(3, player.getCol());
    }

    @Test
    public void testMoveRight() {
        player.moveRight();
        assertEquals(5,player.getCol());
    }

    @Test
    public void testCantMoveLeftAtBoundary() {
        for (int i = 0; i <5; i++) {
            player.moveLeft();
        }
        //player er nå på kolonne 0

        boolean result = player.moveLeft();
        assertFalse(result);
        assertEquals(0, player.getCol());
    }

    @Test public void testCantMoveRightAtBoundary() {
        for (int i=0; i<5; i++) {
            player.moveRight();
        }

        boolean result = player.moveRight();
        assertFalse(result);
        assertEquals(8, player.getCol());
    }
}
