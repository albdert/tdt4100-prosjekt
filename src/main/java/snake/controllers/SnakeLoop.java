package snake.controllers;

import java.util.function.Consumer;

import javafx.animation.AnimationTimer;
import snake.model.Food;
import snake.model.Snake;
import snake.view.SnakeRenderer;

public class SnakeLoop extends AnimationTimer{
    private final SnakeRenderer renderer;
    private final Snake snake;
    private final Food food;

    private long updateTime;
    private long updateInterval;
    private int frameCount;
    private int sui;

    private Consumer<Integer> onScoreChanged = score -> {};
    private Consumer<Integer> onLevelChanged = level -> {};
    private Runnable onGameOver = () -> {};

    private int level = 1;
    private int score = 0;
    private int eaten = 0; 

    public SnakeLoop(SnakeRenderer r, Snake s, Food f) {
        updateTime = 0;
        updateInterval = 20_000_000;
        sui = 10;

        renderer = r;
        snake = s;
        food = f;
    }

    @Override
    public void handle(long now) {
        if (now-updateTime >= updateInterval) {
            update();
            updateTime = now;
        }
    }

    // callback functions
    public void setOnScoreChanged(Consumer<Integer> callback) {
        this.onScoreChanged = callback;
    }
    public void setOnLevelChanged(Consumer<Integer> callback) {
        this.onLevelChanged = callback;
    }
    public void setOnGameOver(Runnable callback) {
        this.onGameOver = callback;
    }

    private void spawnNewFood() {
        do {
            food.nextPos();
        } while (snake.posInSnake(food.getPos(), 0));
    }

    private void updateState() {
        score += 10 * level; 
        onScoreChanged.accept(score);
        eaten++;

        if (!(eaten%10==0)) { return; }

        if (sui>3) { sui--; }
        frameCount = 0;

        level++; 
        onLevelChanged.accept(level); 
    }
    
    private void render() {
        renderer.clear();
        renderer.renderGrid();
        renderer.renderSnake(snake);
        renderer.renderFood(food);
    }
    
    public void update() {
        if (snake.isDead()) { onGameOver.run(); }

        if (frameCount%sui==0) { snake.move(); }

        if (snake.isOnFood(food)) {
            snake.extend();
            updateState();
            spawnNewFood();
        }

        render();
        frameCount++;
    }
}
