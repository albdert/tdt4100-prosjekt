package snake.controllers;

import app.common.Vector2di;
import javafx.animation.AnimationTimer;
import snake.model.Food;
import snake.model.Grid;
import snake.model.Snake;
import snake.view.SnakeRenderer;

public class SnakeLoop extends AnimationTimer{
    private final SnakeRenderer renderer;
    private final Grid grid;
    private final Snake snake;
    private final Food food;

    private long updateTime;
    private long updateInterval;

    public SnakeLoop(SnakeRenderer r, Grid g, Snake s, Food f) {
        updateTime = 0;
        updateInterval = 250_000_000;

        renderer = r;
        snake = s;
        food = f;
        grid = g;
    }

    @Override
    public void handle(long now) {
        if (now-updateTime >= updateInterval) {
            update();
            updateTime = now;
        }
    }

    private void checkCollision() {

    }

    private void checkFood() {
        if (snake.getHeadPos().equals(food.getPos())) {
            snake.extend();
            do {
                food.nextPos();
            } while (snake.posInSnake(food.getPos()));
        }
    }

    private void render() {
        renderer.clear();
        renderer.renderGrid(grid);
        renderer.renderSnake(snake);
        renderer.renderFood(food);
    }
    
    public void update() {
        snake.move();
        checkCollision();
        checkFood();
        render();
    }
}
