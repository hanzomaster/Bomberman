package GameMain;

import GameFrame.CanvasGame;
import GameFrame.MenuGame;
import GameFrame.PauseGame;
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
import sounds.Sound;

public class BombermanGame extends Application {
  public static final int WIDTH = 31;
  public static final int HEIGHT = 14;

  private GraphicsContext gc;
  private static CanvasGame canvas;
  private MenuGame menuGame;
  private PauseGame pauseGame;
  private List<Entity> stillObjects = new ArrayList<>();

  // Player status
  public static final int timeLiving = 300;
  private static int score = 0;
  private static int lives = 3;

  public boolean showMenu = true;
  private static boolean mute = false;

  public Sound menuSound = new Sound(Sound.MENU_SOUND);

  @Override
  public void start(Stage stage) {
    // Tạo Canvas
    canvas = new CanvasGame(Sprite.SCALED_SIZE * WIDTH * 1.0, Sprite.SCALED_SIZE * HEIGHT * 1.0);
    gc = canvas.getGraphicsContext2D();

    // Tạo root container
    Group root = new Group();
    root.getChildren().add(canvas);

    stage.setResizable(false);
    stage.setTitle("Bomberman");
    Image icon = new Image("./resources/textures/icon.png");
    stage.getIcons().add(icon);

    // Tạo scene
    Scene scene = new Scene(root);

    stage.setOnCloseRequest(e -> {
      Platform.exit();
      System.exit(0);
    });

    // Thêm scene vào stage
    stage.setScene(scene);
    stage.show();

    menuGame = new MenuGame(canvas.getInput());
    pauseGame = new PauseGame(canvas.getInput());

    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(long l) {
        if (showMenu) {
          canvas.getGame().getTimers().setPlay(false);
          menuGame.showMenu(gc);
          menuGame.update();

          if (!menuGame.isMuted() && !menuSound.isRunning()) {
            menuSound.play();
          } else if (menuGame.isMuted()) {
            menuSound.stop();
          }

          if (menuGame.isQuit()) {
            Platform.exit();
            System.exit(0);
          } else if (menuGame.isStartGame()) {
            // create new map at level 1
            canvas.getGame().createNewGame();
            mute = menuGame.isMuted();
            menuGame.setStartGame(false);
            showMenu = false;
            canvas.getGame().setTransferLevel(true);
          }

        } else if (canvas.getInput().pause) {
          canvas.getGame().getTimers().setPlay(false);
          if (!canvas.getGame().isPause()) {
            canvas.getGame().pauseSound();
            canvas.getGame().setPause(true);
          }
          pauseGame.showPauseGame(gc);
          pauseGame.update();

          // handle selections while in game pause
          if (pauseGame.getSelected() == 2) {
            canvas.getInput().pause = false;
            canvas.getGame().getTimers().setPlay(false);
            canvas.getGame().pauseSound();
            showMenu = true;
          } else if (pauseGame.getSelected() == 1) {
            canvas.getInput().pause = false;
            canvas.getGame().resumeSound();
            canvas.getGame().getTimers().setPlay(true);
          }
          canvas.getGame().setPause(false);
          pauseGame.setSelected(-1);
        } else {
          menuSound.stop();
          canvas.update();
          canvas.render();
          if (canvas.getGame().isReturnMainMenu()) {
            showMenu = true;
            canvas.getGame().setReturnMainMenu(false);
          }
        }
      }
    };
    timer.start();
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

  public static void setMuted(boolean muted) {
    mute = muted;
  }
}
