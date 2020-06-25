package game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Main extends Application {

    public static int speed = 5;
    public static int width = 20;
    public static int height = 20;

    public void start(Stage primaryStage) {
        try {
            Food.newFood();
            VBox root = new VBox();
            Canvas c = new Canvas(width * Snake.cornerSize, height * Snake.cornerSize);
            GraphicsContext gc = c.getGraphicsContext2D();
            root.getChildren().add(c);

            new AnimationTimer() {
                long lastTick = 0;

                public void handle(long now) {
                    if (lastTick == 0) {
                        lastTick = now;
                        tick(gc);
                        return;
                    }

                    if (now - lastTick > 1000000000 / speed) {
                        lastTick = now;
                        tick(gc);
                    }
                }

            }.start();

            Scene scene = new Scene(root, width * Snake.cornerSize, height * Snake.cornerSize);

            scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
                if (key.getCode() == KeyCode.W) {
                    Snake.direction = Dir.up;
                }
                if (key.getCode() == KeyCode.A) {
                    Snake.direction = Dir.left;
                }
                if (key.getCode() == KeyCode.S) {
                    Snake.direction = Dir.down;
                }
                if (key.getCode() == KeyCode.D) {
                    Snake.direction = Dir.right;
                }

            });

            Snake.addStartingCorners();
            primaryStage.setScene(scene);
            primaryStage.setTitle("SNAKE GAME");
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void tick(GraphicsContext gc) {
        if (Snake.gameOver) {
            gc.setFill(Color.RED);
            gc.setFont(new Font("", 50));
            gc.fillText("GAME OVER", 100, 250);
            return;
        }

        Snake.tick();

        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width * Snake.cornerSize, height * Snake.cornerSize);

        gc.setFill(Color.WHITE);
        gc.setFont(new Font("", 30));
        gc.fillText("Score: " + (speed - 6), 10, 30);

        Food.randomColor();

        gc.setFill(Food.cc);
        gc.fillOval(Food.foodX * Snake.cornerSize, Food.foodY * Snake.cornerSize, Snake.cornerSize, Snake.cornerSize);

        for (Corner c : Snake.snake) {
            gc.setFill(Color.LIGHTGREEN);
            gc.fillRect(c.x * Snake.cornerSize, c.y * Snake.cornerSize, Snake.cornerSize - 1, Snake.cornerSize - 1);
            gc.setFill(Color.GREEN);
            gc.fillRect(c.x * Snake.cornerSize, c.y * Snake.cornerSize, Snake.cornerSize - 2, Snake.cornerSize - 2);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}