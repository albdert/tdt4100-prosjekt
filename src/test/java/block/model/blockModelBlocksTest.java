package block.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class blockModelBlocksTest {
    private Blocks blocks;

    @BeforeEach
    public void setUp() {
        blocks = new Blocks();
    }

    @Test
    public void testInitialBlocksEmpty() {
        assertEquals(0, blocks.getBlocks().size());  // Skal være 0 blokker ved start
    }

    @Test
    public void testSpawnBlock() {
        blocks.spawnBlock();
        assertTrue(blocks.getBlocks().size() > 0);  // Skal ha minst 1 blokk etter spawn
    }

    @Test
    public void testSpawnMultipleBlocks() {
        blocks.spawnBlock();
        blocks.spawnBlock();
        assertTrue(blocks.getBlocks().size() >= 2);  // Skal ha minst 2 blokker
    }

    @Test
    public void testInitialScoreIsZero() {
        assertEquals(0, blocks.getScore());  // Score skal starte på 0
    }

    @Test
    public void testInitialLevelIsZero() {
        assertEquals(0, blocks.getLevel());  // Level skal starte på 0
    }

    @Test
    public void testIsNotHitInitially() {
        assertFalse(blocks.isHit());  // Skal ikke være hit ved start
    }
}