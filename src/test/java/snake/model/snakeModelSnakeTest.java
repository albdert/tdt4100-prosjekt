package snake.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.common.Vector2di;

public class snakeModelSnakeTest {
    private Snake snake;


    @BeforeEach
    public void setUp() {
        snake = new Snake();
    }

    @Test
    public void testSnakeInitialSize() {
        assertEquals(5, snake.size());
    }

    @Test
    public void testSnakeMoveUp() {
        int initialY = snake.getSegments()[0].y;
        snake.move();
        assertEquals(initialY - 1, snake.getSegments()[0].y);
    }
    @Test
    public void testSnakeCannotGoReverse() {
        snake.changeDir(Snake.Dir.DOWN);
        snake.move();
        int yAfterReverse = snake.getSegments()[0].y;
        
        assertTrue(yAfterReverse < 8);
    }
    @Test
    public void testSnakeExtend() {
        int initialSize = snake.size();
        snake.extend();
        assertEquals(initialSize + 1, snake.size());
    }

    @Test
    public void testSnakeChangeDirectionRight() {
        snake.changeDir(Snake.Dir.RIGHT);
        snake.move();
        snake.move();
        int currentX = snake.getSegments()[0].x;
        snake.move();
        assertEquals(currentX + 1, snake.getSegments()[0].x);
    }
}
