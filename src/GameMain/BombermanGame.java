package GameMain;

import GameFrame.CanvasGame;
import GameFrame.KeyboardInput;
import entities.Entity;
import entities.player.Bomber;
import graphics.Sprite;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class BombermanGame extends Application {
  public static final int WIDTH = 31;
  public static final int HEIGHT = 14;

  private GraphicsContext gc;
  public static CanvasGame canvas;
  private List<Entity> entities = new ArrayList<>();
  private List<Entity> stillObjects = new ArrayList<>();

  private static int score = 0;
  private static int lives = 3;

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
    stage.setTitle(CanvasGame.TITLE);
    // Thêm scene vào stage
    stage.setScene(scene);
    stage.show();

    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(long l) {
        render();
        update();
      }
    };
    timer.start();

    createMap();

    Entity bomberman = new Bomber(1, 1, new KeyboardInput());
    entities.add(bomberman);
  }

  /**
   * Create the map.
   */
  public void createMap() {
    canvas.getGame().createMap();
    stillObjects.addAll(canvas.getGame().getGrassList());
    stillObjects.addAll(canvas.getGame().getCollidableEntities());
  }

  public void update() {
    entities.forEach(Entity::update);
  }

  /**
   * Render the game itself.
   */
  public void render() {
    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    stillObjects.forEach(g -> g.render(gc));
    entities.forEach(g -> g.render(gc));
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
}
