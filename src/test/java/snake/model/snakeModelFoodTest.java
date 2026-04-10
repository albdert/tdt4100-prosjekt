package snake.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.common.Vector2di;

public class snakeModelFoodTest {
    private Food food;
    private int rows = 20;
    private int cols = 20;

    @BeforeEach
    public void setUp() {
        food = new Food(rows, cols);
    }

    @Test
    public void testFoodPositionIsWithinBounds() {
        for (int i = 0; i < 5; i++) {
            food.nextPos();
            Vector2di pos = food.getPos();
            assertTrue(pos.x >= 0 && pos.x < rows);
            assertTrue(pos.y >= 0 && pos.y < cols);
        }
    }

    @Test
    public void testIfFoodStarts() {
        assertNotNull(food);
    }

    @Test
    public void testIfFoodPostitionNotNUll() {
        assertNotNull(food.getPos());
    }
}