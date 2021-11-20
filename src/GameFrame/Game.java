package GameFrame;

import java.util.List;
import GameMain.BombermanGame;
import entities.Entity;
import entities.monsters.Monster;
import entities.player.Bomber;
import entities.stillobjects.Grass;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Game {
  public static String[] paths = {"src/resources/levels/Level1.txt",
      "src/resources/levels/Level2.txt", "src/resources/levels/Level3.txt",
      "src/resources/levels/Level4.txt", "src/resources/levels/Level5.txt",
      "src/resources/levels/Level6.txt", "src/resources/levels/Level7.txt"};
  public int WIDTH;
  public int HEIGHT;
  public boolean pause = false;


  // list to render in canvas
  private List<Grass> grasses;
  private List<Entity> entities; // list to check collision
  private List<Monster> monsters;

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
      WIDTH = level.getWidth();
      HEIGHT = level.getHeight();

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
    }
  }

  public Entity getEntityInCoodinate(int x, int y) {
    for (Entity e : entities) {
      if (e.getXUnit() == x && e.getYUnit() == y) {
        return e;
      }
    }
    return null;
  }

  public void update() {

  }

  public void updateAllEntities() {}

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
