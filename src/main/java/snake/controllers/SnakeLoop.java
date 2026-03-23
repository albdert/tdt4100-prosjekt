package snake.controllers;

import java.util.function.Consumer;

import app.controllers.GameController;
import javafx.animation.AnimationTimer;
import snake.model.Food;
import snake.model.Snake;
import snake.view.SnakeRenderer;

public class SnakeLoop extends AnimationTimer{
    private final GameController controller;
    private final SnakeRenderer renderer;
    private final Snake snake;
    private final Food food;

    private long updateTime;
    private long updateInterval;

    private Consumer<Integer> onScoreChanged = score -> {};
    private Consumer<Integer> onLevelChanged = level -> {};
    private Runnable onGameOver = () -> {};

    private int level = 1;
    private int score = 0;
    private int eaten = 0; 

    public SnakeLoop(SnakeRenderer r, Snake s, Food f, GameController c) {
        updateTime = 0;
        updateInterval = 250_000_000;

        controller = c;
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
    
    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

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
        System.out.println("Score updated: " + score);
        onScoreChanged.accept(score);
        eaten++;
        if (eaten%5==0) { 
            updateInterval -= 10_000_000;
            level++; 
            onLevelChanged.accept(level); 
        }
    }
    
    private void render() {
        renderer.clear();
        renderer.renderGrid();
        renderer.renderSnake(snake);
        renderer.renderFood(food);
    }
    
    public void update() {
        if (snake.isDead()) { onGameOver.run(); }

        snake.move();

        if (snake.isOnFood(food)) {
            snake.extend();
            updateState();
            spawnNewFood();
        };

        render();
    }
}
