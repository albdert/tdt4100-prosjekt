package block.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class blockModelBlockTest {
    private Block block;

    @BeforeEach
    public void setUp() {
        block = new Block(3);
    }


    @Test
    public void testBlockInitialRow() {
        assertEquals(0, block.getRow());
    }
    //sjekker at en ny blokk starter øverst

    @Test
    public void testBlockInitialCol() {
        assertEquals(3, block.getCol());
    }
    //sjekker om Block(3) faktisk starter på kolonne 3

    @Test
    public void testBlockMoveDown() {
        block.moveDown();
        assertEquals(1, block.getRow());
    }
    //sjekker om etter at vi kaller moveDown at den faktisk flyttes en rad ned,

    @Test
    public void testBlockMoveDownMultipleTimes() {
        block.moveDown();
        block.moveDown();
        block.moveDown();
        assertEquals(3, block.getRow()); 
    }
    //sjekker samme som over bare 3 ganger/ flere ganger
}