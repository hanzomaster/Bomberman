package GameFrame;

import java.util.ArrayList;
import java.util.List;
import Bomb.Bomb;
import GameMain.BombermanGame;
import entities.Entity;
import entities.monsters.Monster;
import entities.player.Bomber;
import entities.stillobjects.Brick;
import entities.stillobjects.Grass;
import entities.stillobjects.Portal;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import timer.Timers;

public class Game {
  public static String[] paths = {"src/resources/levels/Level1.txt",
      "src/resources/levels/Level2.txt", "src/resources/levels/Level3.txt",
      "src/resources/levels/Level4.txt", "src/resources/levels/Level5.txt",
      "src/resources/levels/Level6.txt", "src/resources/levels/Level7.txt"};
  public boolean pause = false;

  // list to render in canvas
  private List<Grass> grasses;
  private List<Entity> entities; // list to check collision
  private List<Monster> monsters;
  private List<Bomb> bombs = new ArrayList<>();

  // bomber
  public static Bomber bomberman = new Bomber(1, 1, new KeyboardInput());
  public Bomber bomberInPreLevel = new Bomber(1, 1, new KeyboardInput());
  private Bomber originBomber;

  // level
  public Level level = new Level();
  private int currentLevel = 1;
  private int timeShowTransferLevel = 150;
  private boolean transferLevel = false;

  private boolean gameOver = false;
  private boolean returnMainMenu = false;
  private Timers timers = new Timers();

  /**
   * Create new game.
   */
  public void createNewGame() {
    gameOver = false;
    currentLevel = 1;
    BombermanGame.setLives(3);
    BombermanGame.setScore(0);
    bomberman = new Bomber(1, 1, new KeyboardInput());
    createMap();
    updateEnemy(bomberman);
  }

  private void updateEnemy(Bomber bomberman) {}

  /**
   * Create map for each level.
   */
  public void createMap() {
    if (currentLevel <= paths.length) {
      level.createMapLevel(paths[currentLevel - 1], currentLevel - 1);

      originBomber = level.getBomber();
      if (currentLevel > 1) {
        // TODO: Thêm các trạng thái cũ vào bomber ở level mới
      }
      bomberman.setX(originBomber.getX());
      bomberman.setY(originBomber.getY());
      bomberman.setAlive(true);

      grasses = level.getGrassList();
      entities = level.getCollidableEntities();
      monsters = level.getEnemyList();
      bomberman = level.getBomber();

      updateEnemy(bomberman);

      timers.setInterval(BombermanGame.timeLiving);
      timers.setTime();
    }
  }

  public void update() {
    updateAllEntities();
    // this.createMap();
  }

  public void updateAllEntities() {
    bomberman.update();
    for (Entity e : entities) {

      if (e.getImg() == null) { // if img == null, thi xoa entity do
        if (e instanceof Brick) {
          if (((Brick) e).hasPortal()) {
            this.addEntity(new Portal(e.getXUnit(), e.getYUnit()));
          }
          if (((Brick) e).hasPowerup()) {
            this.addEntity(((Brick) e).getPowerup());
          }
        }
        entities.remove(e);
        break;
      } else {
        e.update();
      }
    }
  }

  /**
   * Get enitites coordinate for collsion check. TODO: Cần tối ưu để di chuyển dễ dàng hơn
   * 
   * @param x position x
   * @param y position x
   * @return
   */
  public Entity getEntityInCoodinate(int x, int y) {
    for (Entity e : entities) {
      if (e.getXUnit() == x && e.getYUnit() == y) {
        return e;
      }
    }

    bombs = bomberman.getBombList();
    for (Bomb b : bombs) {
      if (b.getXUnit() == x && b.getYUnit() == y) {
        return b;
      }
    }
    return null;
  }

  public Entity getGrass(int x, int y) {
    for (Entity e : grasses) {
      if (e.getXUnit() == x && e.getYUnit() == y) {
        return e;
      }
    }
    return null;
  }

  public List<Entity> getCollidableEntities() {
    return entities;
  }

  public List<Grass> getGrassList() {
    return grasses;
  }

  /**
   * Render map.
   */
  public void render(Canvas canvas) {
    GraphicsContext gc = canvas.getGraphicsContext2D();
    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    grasses.forEach(e -> e.render(gc));
    entities.forEach(e -> e.render(gc));
    renderInfoOfCurrentLevel(gc);
    // bomberman.bombRender(gc);
    bomberman.bombRender(gc);
    bomberman.render(gc);
  }

  /**
   * Render living time, score and buffs of player.
   */
  public void renderInfoOfCurrentLevel(GraphicsContext gc) {
    gc.setFill(Color.BLACK);
    gc.fillRect(0, 416, 992, 448);
    gc.setFill(Color.WHITE);
    gc.setFont(new Font("", 15));
    // gc.fillText("Time left: " + formatTime(timers.getInterval()), 10, 440);
    gc.fillText("Level: " + currentLevel, 200, 440);
    gc.fillText("Lives: " + BombermanGame.getLives(), 300, 440);
    gc.fillText("Scores: " + BombermanGame.getScore(), 400, 440);
  }

  /**
   * Format the displayed time.
   * 
   * @param time time argument
   * @return a String to display time in format
   */
  public String formatTime(int time) {
    String res = (time / 60) + ":";
    int second = time % 60;
    if (second < 10) {
      res += "0" + second;
    } else {
      res += second;
    }
    return res;
  }

  public void setGrassList(List<Grass> grassList) {
    this.grasses = grassList;
  }

  public void addEntity(Entity e) {
    entities.add(e);
  }

  public void setTransferLevel(boolean transferLevel) {
    this.transferLevel = transferLevel;
  }
}
