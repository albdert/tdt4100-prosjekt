package block.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class blockModelBlockTest {
    private Block block;

    @BeforeEach
    public void setUp() {
        block = new Block(3);  // Opprett blokk på kolonne 3
    }

    @Test
    public void testBlockInitialRow() {
        assertEquals(0, block.getRow());  // Skal starte på rad 0
    }

    @Test
    public void testBlockInitialCol() {
        assertEquals(3, block.getCol());  // Skal være på kolonne 3
    }

    @Test
    public void testBlockMoveDown() {
        block.moveDown();
        assertEquals(1, block.getRow());  // Skal bevege seg ned til rad 1
    }

    @Test
    public void testBlockMoveDownMultipleTimes() {
        block.moveDown();
        block.moveDown();
        block.moveDown();
        assertEquals(3, block.getRow());  // Skal være på rad 3 etter 3 moveDown()
    }
}