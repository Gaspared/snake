package game;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    public static Dir direction = Dir.left;
    public static boolean gameOver = false;

    public static int cornerSize = 25;
    public static List<Corner> snake = new ArrayList<>();

    public static void addStartingCorners() {
        snake.add(new Corner(Main.width / 2, Main.height / 2));
        snake.add(new Corner(Main.width / 2, Main.height / 2));
        snake.add(new Corner(Main.width / 2, Main.height / 2));
    }

    private static void moveSnake() {
        switch (direction) {
            case up:
                snake.get(0).y--;
                if (snake.get(0).y < 0) {
                    gameOver = true;
                }
                break;
            case down:
                snake.get(0).y++;
                if (snake.get(0).y > Main.height) {
                    gameOver = true;
                }
                break;
            case left:
                snake.get(0).x--;
                if (snake.get(0).x < 0) {
                    gameOver = true;
                }
                break;
            case right:
                snake.get(0).x++;
                if (snake.get(0).x > Main.width) {
                    gameOver = true;
                }
                break;

        }
    }

    public static void tick() {
        for (int i = snake.size() - 1; i >= 1; i--) {
            snake.get(i).x = snake.get(i - 1).x;
            snake.get(i).y = snake.get(i - 1).y;
        }

        moveSnake();

        if (Food.foodX == snake.get(0).x && Food.foodY == snake.get(0).y) {
            snake.add(new Corner(-1, -1));
            Food.newFood();
        }

        for (int i = 1; i < snake.size(); i++) {
            if (snake.get(0).x == snake.get(i).x && snake.get(0).y == snake.get(i).y) {
                gameOver = true;
            }
        }
    }
}