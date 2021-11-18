package GameMain;

import java.util.ArrayList;
import java.util.List;
import GameFrame.CanvasGame;
import GameFrame.KeyboardInput;
import entities.Entity;
import entities.monsters.Balloon;
import entities.monsters.Doll;
import entities.monsters.Kondoria;
import entities.monsters.Minvo;
import entities.monsters.Oneal;
import entities.player.Bomber;
import entities.stillobjects.Grass;
import entities.stillobjects.Wall;
import graphics.Sprite;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class BombermanGame extends Application {
  public static final int WIDTH = 31;
  public static final int HEIGHT = 15;

  private GraphicsContext gc;
  public static CanvasGame canvas;
  private List<Entity> entities = new ArrayList<>();
  private List<Entity> stillObjects = new ArrayList<>();

  private int score = 0;
  private int lives = 3;

  @Override
  public void start(Stage stage) {
    // Tạo Canvas
    canvas = new CanvasGame(Sprite.SCALED_SIZE * WIDTH * 1.0, Sprite.SCALED_SIZE * HEIGHT * 1.0);
    gc = canvas.getGraphicsContext2D();

    // Tạo root container
    Group root = new Group();
    root.getChildren().add(canvas);

    // Tại scene
    Scene scene = new Scene(root);

    stage.setResizable(false);

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
    Entity balloonmonster = new Balloon(3, 1);
    Entity dollEntity = new Doll(1, 3);
    Entity kondoriaEntity = new Kondoria(1, 5);
    Entity minvoEntity = new Minvo(1, 7);
    Entity onealEntity = new Oneal(1, 9);
    entities.add(bomberman);
    entities.add(balloonmonster);
    entities.add(dollEntity);
    entities.add(kondoriaEntity);
    entities.add(minvoEntity);
    entities.add(onealEntity);
  }

  /**
   * Create the map.
   */
  public void createMap() {
    for (int i = 0; i < WIDTH; i++) {
      for (int j = 0; j < HEIGHT; j++) {
        Entity object;
        if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
          object = new Wall(i, j, Sprite.wall.getFxImage());
        } else {
          object = new Grass(i, j, Sprite.grass.getFxImage());
        }
        stillObjects.add(object);
      }
    }
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

  public int getScore() {
    return score;
  }

  public void setScore(final int score) {
    this.score = score;
  }

  public int getLives() {
    return lives;
  }

  public void setLives(final int lives) {
    this.lives = lives;
  }
}
