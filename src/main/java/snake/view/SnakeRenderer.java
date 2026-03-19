package snake.view;

import snake.model.Grid;

public class SnakeRenderer {

    public void renderGrid(Grid g) {};

    public void renderSnake(Player p) {};
    // SnakeRenderer.java
    /*
    public void render(Grid grid) {
        for (int x = 0; x < grid.getWidth(); x++) {
            for (int y = 0; y < grid.getHeight(); y++) {
                switch (grid.getCell(x, y)) {
                    case SNAKE -> drawSnakeTile(x, y);
                    case FOOD  -> drawFoodTile(x, y);
                    case EMPTY -> drawEmptyTile(x, y);
                }
            }
        }
    }   
 */
}
