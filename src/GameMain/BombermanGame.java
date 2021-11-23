package GameMain;

import GameFrame.CanvasGame;
import entities.Entity;
import graphics.Sprite;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class BombermanGame extends Application {
  public static final int WIDTH = 31;
  public static final int HEIGHT = 14;

  private GraphicsContext gc;
  private static CanvasGame canvas;
  private List<Entity> entities = new ArrayList<>();
  private List<Entity> stillObjects = new ArrayList<>();

  // Player status
  public static int timeLiving = 300;
  private static int score = 0;
  private static int lives = 3;

  private static boolean mute = false;

  @Override
  public void start(Stage stage) {
    // Tạo Canvas
    canvas = new CanvasGame(Sprite.SCALED_SIZE * WIDTH * 1.0, Sprite.SCALED_SIZE * HEIGHT * 1.0);
    gc = canvas.getGraphicsContext2D();

    // Tạo root container
    Group root = new Group();
    root.getChildren().add(canvas);

    // Tạo scene
    Scene scene = new Scene(root);

    stage.setResizable(false);
    Image icon = new Image("./resources/textures/icon.png");
    stage.getIcons().add(icon);
    stage.setTitle("Bomberman");
    stage.setOnCloseRequest(e -> {
      Platform.exit();
      System.exit(0);
    });
    // Thêm scene vào stage
    stage.setScene(scene);
    stage.show();

    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(long l) {
        canvas.update();
        canvas.render();
      }
    };
    timer.start();

    createMap();
  }

  /**
   * Create the map.
   */
  public void createMap() {
    canvas.getGame().createMap();
    stillObjects.addAll(canvas.getGame().getGrassList());
    stillObjects.addAll(canvas.getGame().getCollidableEntities());
  }

  public static int getScore() {
    return score;
  }

  public static void setScore(final int score) {
    BombermanGame.score = score;
  }

  public static int getLives() {
    return lives;
  }

  public static void setLives(final int lives) {
    BombermanGame.lives = lives;
  }

  public static CanvasGame getCanvasGame() {
    return canvas;
  }

  public static boolean getMuted() {
    return mute;
  }
}
