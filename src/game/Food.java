package game;

import javafx.scene.paint.Color;

import java.util.Random;

public class Food {

    public static int foodX = 0;
    public static int foodY = 0;
    public static int foodColor = 0;
    private static final Random rand = new Random();
    public static Color cc = Color.WHITE;

    public static void newFood() {
        start:
        while (true) {
            foodX = rand.nextInt(Main.width);
            foodY = rand.nextInt(Main.height);

            for (Corner c : Snake.snake) {
                if (c.x == foodX && c.y == foodY) {
                    continue start;
                }
            }
            foodColor = rand.nextInt(5);
            Main.speed++;
            break;
        }
    }

    public static void randomColor() {
        switch (foodColor) {
            case 0:
                cc = Color.PURPLE;
                break;
            case 1:
                cc = Color.LIGHTBLUE;
                break;
            case 2:
                cc = Color.YELLOW;
                break;
            case 3:
                cc = Color.PINK;
                break;
            case 4:
                cc = Color.ORANGE;
                break;
        }
    }
}