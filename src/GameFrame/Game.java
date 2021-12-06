package GameFrame;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import sounds.Sound;
import sounds.Sound.SoundsStatus;
import timer.Timers;

public class Game {
  private static String[] paths = {"src/resources/levels/Level1.txt",
      "src/resources/levels/Level2.txt", "src/resources/levels/Level3.txt",
      "src/resources/levels/Level4.txt", "src/resources/levels/Level5.txt",
      "src/resources/levels/Level6.txt", "src/resources/levels/Level7.txt"};
  private static String[] levelName = {"SCARY MEADOW", "NORTH POLE", "STEEL PRISON", "WHOLE CAKE",
      "MARINE FORCE", "DEADLY DESERT", "CRAZY HYDRA"};
  private boolean pause = false;

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
  private Level level = new Level();
  private int currentLevel = 1;
  private int timeShowTransferLevel = 140;
  private boolean transferLevel = false;

  private boolean gameOver = false;
  private int timeShowGameOver = 250;
  private boolean returnMainMenu = false;
  private Timers timers = new Timers();

  // sounds
  private Sound soundGame = new Sound(Sound.GAME_SOUND);
  private Sound soundLoseGame = new Sound(Sound.LOSE_GAME_SOUND);
  private Sound soundWinGame = new Sound(Sound.WIN_GAME_SOUND);
  private Sound soundLevelup = new Sound(Sound.TRANSFER_LEVEL_SOUND);

  /**
   * Create new game.
   */
  public void createNewGame() {
    gameOver = false;
    currentLevel = 1;
    BombermanGame.setLives(100);
    BombermanGame.setScore(0);
    Timers.setDelay(3000);
    bomberman = new Bomber(1, 1, new KeyboardInput());
    createMap();
    updateEnemy(bomberman);
  }

  /**
   * Create map for each level.
   */
  public void createMap() {
    if (currentLevel <= paths.length) {
      timers = new Timers();

      Timers.setDelay(3000);

      level.createMapLevel(paths[currentLevel - 1], currentLevel - 1);

      originBomber = level.getBomber();
      if (currentLevel > 1) {
        // TODO: Thêm các trạng thái cũ vào bomber ở level mới
        bomberman.restoreBomber(bomberInPreLevel);
        // timers.stop();
      }
      bomberman.setX(originBomber.getX());
      bomberman.setY(originBomber.getY());
      bomberman.setAlive(true);

      grasses = level.getGrassList();
      entities = level.getCollidableEntities();
      monsters = level.getEnemyList();

      updateEnemy(bomberman);

      timers.setInterval(BombermanGame.timeLiving);
      timers.setTime();
    }
  }

  public void update() {
    if (!transferLevel) {
      updateAllEntities();
      Timers.setDelay(Timers.getDelay());
      if (timeShowTransferLevel == 140) {
        soundGame.play();
      }
      soundLevelup.stop();
      if (!bomberman.isAlive()) {
        bomberman.setX(originBomber.getX());
        bomberman.setY(originBomber.getY());
        bomberman.setAlive(true);
      }
    } else {
      soundGame.stop();
      soundLevelup.play();
    }
  }

  /**
   * Draw all entities.
   */
  public void updateAllEntities() {
    bomberman.update();

    for (Entity e : monsters) {
      if (e.getImg() == null) {
        monsters.remove(e);
        break;
      } else {
        e.update();
      }
    }
    for (Entity e : entities) {
      if (e.getImg() == null) { // if img == null, thi xoa entity do
        if (e instanceof Brick brick) {
          if (brick.hasPortal()) {
            this.addEntity(new Portal(e.getXUnit(), e.getYUnit()));
          }
          if (brick.hasPowerup()) {
            this.addEntity(brick.getPowerup());
          }
        }
        entities.remove(e);
        break;
      } else {
        e.update();
      }
    }

    if (monsters.isEmpty()) {
      bomberman.setKillAllEnemies((true));
    }

    if (bomberman.isCollideWithAPortal()) {
      bomberInPreLevel.restoreBomber(bomberman);
      currentLevel++;
      // timers.stop();
      timers.setPlay(false);
      transferLevel = true;

      if (currentLevel > paths.length) {
        transferLevel = false;
        gameOver = true;
        return;
      }
      this.createMap();
    }
    if (BombermanGame.getLives() == 0) {
      gameOver = true;
      // System.out.println("get live");
    }
  }

  private void updateEnemy(Bomber bomberman) {
    for (Monster e : monsters) {
      e.setBomber(bomberman);
    }
  }

  /**
   * Get enitites coordinate for collsion check.
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

    for (Monster e : monsters) {
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

  /**
   * Get grass list.
   */
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
    if (!transferLevel) {
      renderInfoOfCurrentLevel(gc);
      grasses.forEach(e -> e.render(gc));
      entities.forEach(e -> e.render(gc));
      monsters.forEach(e -> e.render(gc));
      bomberman.bombRender(gc);
      bomberman.render(gc);
    } else {
      if (timeShowTransferLevel-- > 0) {
        renderTransferLevelScreen(gc);
      } else {
        transferLevel = false;
        timeShowTransferLevel = 140;
      }
    }
    if (gameOver) {
      soundGame.stop();
      if (timeShowGameOver-- > 0) {
        if (BombermanGame.getLives() > 0) {
          soundWinGame.play();
        } else {
          soundLoseGame.play();
        }
        renderGameOver(gc);
      }
    }
  }

  public void renderTransferLevelScreen(GraphicsContext gc) {
    FileInputStream file;
    try {
      file = new FileInputStream("src/resources/textures/levelbackground.png");
      final Image backgroundLevel = new Image(file);
      gc.setFill(Color.WHITE);
      gc.clearRect(0, 0, 992, 448);
      gc.drawImage(backgroundLevel, 0, 0);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      System.out.println("Game.renderTransferLevelScreen()");
    }
    gc.setFont(Font.font("Impact", 60));
    gc.fillText("Level: " + currentLevel, 250, 200);
    gc.setFill(Color.RED);
    gc.fillText(levelName[currentLevel - 1], 250, 300);
  }

  /**
   * Render living time, score and buffs of player.
   */
  public void renderInfoOfCurrentLevel(GraphicsContext gc) {
    gc.setFill(Color.BLACK);
    gc.fillRect(0, 416, 992, 448);
    gc.setFill(Color.WHITE);
    gc.setFont(new Font("", 15));
    gc.fillText("Time left: " + formatTime(timers.getInterval()), 10, 440);
    gc.fillText("Level: " + currentLevel, 200, 440);
    gc.fillText("Lives: " + BombermanGame.getLives(), 300, 440);
    gc.fillText("Scores: " + BombermanGame.getScore(), 400, 440);
    if (bomberman.canPassFlame) {

      if (bomberman.timeToStopFlame-- > 0 && bomberman.timeToStopFlame / 37 > 0) {
        gc.fillText("Pass Flame in: " + formatTime(bomberman.timeToStopFlame / 37), 700, 440);
      } else {
        bomberman.canPassFlame = false;
      }
    }
    if (bomberman.canPassBom) {
      if (bomberman.timeToStopBomb-- > 0 && bomberman.timeToStopBomb / 37 > 0) {
        gc.fillText("Pass Bomb in: " + formatTime(bomberman.timeToStopBomb / 37), 500, 440);
      } else {
        bomberman.canPassBom = false;
      }

    }
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

  public void renderGameOver(GraphicsContext gc) {
    FileInputStream file;
    if (BombermanGame.getLives() <= 0) {
      try {
        file = new FileInputStream("src/resources/textures/gameover.png");
        final Image backgroundLevel = new Image(file);
        gc.setFill(Color.WHITE);
        gc.clearRect(0, 0, 992, 448);
        gc.drawImage(backgroundLevel, 0, 0);
      } catch (FileNotFoundException e) {
        e.printStackTrace();
        System.out.println("Game.renderGameOver()");
      }
      gc.setFont(Font.font("Impact", 60));
      int score = BombermanGame.getScore();
      gc.fillText("Your score: " + score, 200, 300);
      HighScore.setNewHighScore(score);
      gc.setFill(Color.RED);
    }

    if (BombermanGame.getLives() > 0) {
      try {
        file = new FileInputStream("src/resources/textures/victory.png");
        final Image backgroundLevel = new Image(file);
        gc.setFill(Color.WHITE);
        gc.clearRect(0, 0, 992, 448);
        gc.drawImage(backgroundLevel, 0, 0);
      } catch (FileNotFoundException e) {
        e.printStackTrace();
        System.out.println("Game.renderGameOver()");
      }
      gc.setFont(Font.font("Impact", 60));
      gc.setFill(Color.RED);
      int score = BombermanGame.getScore();
      HighScore.setNewHighScore(score);
      gc.fillText("New record: " + score, 350, 440);
    }
    // gc.setFont(Font.font("Impact", 60));
    // String result = (BombermanGame.getLives() > 0) ? "You win" : "You lose";
    // gc.fillText("Game Over!\n" + result, 250, 200);
    // gc.setFill(Color.RED);
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


  public boolean isReturnMainMenu() {
    return returnMainMenu;
  }

  public void setReturnMainMenu(boolean returnMainMenu) {
    this.returnMainMenu = returnMainMenu;
  }

  public void setPause(boolean pause) {
    this.pause = pause;
  }

  public boolean isPause() {
    return pause;
  }

  public void pauseSound() {
    if (soundGame.isRunning()) {
      soundGame.pause();
    }
    if (soundLevelup.isRunning()) {
      soundLevelup.pause();
    }
    if (soundWinGame.isRunning()) {
      soundWinGame.pause();
    }
    if (soundLoseGame.isRunning()) {
      soundLoseGame.pause();
    }
  }

  public void stopSound() {
    if (soundGame.isRunning()) {
      soundGame.stop();
    }
    if (soundLevelup.isRunning()) {
      soundLevelup.stop();
    }
    if (soundWinGame.isRunning()) {
      soundWinGame.stop();
    }
    if (soundLoseGame.isRunning()) {
      soundLoseGame.stop();
    }
  }

  public void resumeSound() {
    if (soundWinGame.getStatus() == SoundsStatus.PAUSE) {
      if (!soundGame.isRunning()) {
        soundGame.resume();
      }
      if (!soundLevelup.isRunning()) {
        soundLevelup.resume();
      }
      if (!soundWinGame.isRunning()) {
        soundWinGame.resume();
      }
      if (!soundLoseGame.isRunning()) {
        soundLoseGame.resume();
      }
    }
  }

  public Timers getTimers() {
    return timers;
  }

  public boolean isOver() {
    return gameOver;
  }

  public int getTimeShowTransferLevel() {
    return timeShowTransferLevel;
  }

  public int getTimeShowGameOver() {
    return timeShowGameOver;
  }

  public void setTimeShowGameOver() {
    timeShowGameOver = 250;
  }
}
