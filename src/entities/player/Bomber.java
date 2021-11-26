package entities.player;

import java.util.ArrayList;
import java.util.List;
import Bomb.Bomb;
import Bomb.Flame;
import GameFrame.KeyboardInput;
import GameMain.BombermanGame;
import entities.Entity;
import entities.monsters.Monster;
import entities.stillobjects.Brick;
import entities.stillobjects.Grass;
import entities.stillobjects.Portal;
import entities.stillobjects.Wall;
import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import powerups.Powerup;
import sounds.Sound;

public class Bomber extends BomberCharacter {
  private KeyboardInput input;
  private int maxBom = 1;
  private int frameLength = 1;
  public boolean canPassBom = false;
  public boolean canPassFlame = false;
  private boolean canPassBrick = false;
  private List<Bomb> bombList = new ArrayList<>();
  private boolean isCollideWithAPortal = false;
  private boolean killAllEnemies = false;
  public int timeToStopFlame = 0;
  public int timeToStopBomb = 0;

  private Sound soundMoving = new Sound(Sound.MOVING_SOUND);

  private final int[] addToXToCheckCollision =
      {2, Sprite.SCALED_SIZE - 10, Sprite.SCALED_SIZE - 10, 2};
  private final int[] addToYToCheckCollision =
      {3, 3, Sprite.SCALED_SIZE - 6, Sprite.SCALED_SIZE - 6};
  private final int[] addToXToSetPrecision =
      {-5, Sprite.SCALED_SIZE + 5, Sprite.SCALED_SIZE - 20, 10};
  private final int[] addToYToCSetPrecision =
      {15, 15, Sprite.SCALED_SIZE + 1, Sprite.SCALED_SIZE + 1};


  /**
   * Create a bomber that react to user input.
   */
  public Bomber(int x, int y, KeyboardInput keyboardInput) {
    super(x, y, Sprite.playerDown.getFxImage());
    direction = 1;
    velocity = 2;
    input = keyboardInput;
  }

  public void restoreBomber(Bomber newBomber) {
    reset();

    this.input = newBomber.input;
    this.velocity = newBomber.velocity;
    this.maxBom = newBomber.maxBom;
    this.frameLength = newBomber.frameLength;
    this.canPassBom = newBomber.canPassBom;
    this.canPassBrick = newBomber.canPassBrick;
    this.canPassFlame = newBomber.canPassFlame;
  }

  public void reset() {
    this.setImg(Sprite.playerDown.getFxImage());
    this.direction = 1;
    this.bombList = new ArrayList<>();
    this.killAllEnemies = false;
    this.isCollideWithAPortal = false;
  }

  @Override
  public void update() {
    animate();
    bombUpdate();
    ifCollisionWithFlameOrEnemyOrItem();

    input = BombermanGame.getCanvasGame().getInput();

    if (isStartDie()) {
      // BombermanGame.setLives(BombermanGame.getLives() - 1);
      if (timeShowDeath-- > 0) {
        this.setImg(Sprite
            .movingSprite(Sprite.playerDead1, Sprite.playerDead2, Sprite.playerDead3, animation, 30)
            .getFxImage());
      } else {
        setAlive(false);
        setStartDie(false);
        setTimeShowDeath(100);
      }
    } else {

      if (input.space && bombList.size() < maxBom) {
        Entity e = BombermanGame.getCanvasGame().getEntityInCoodinate(getXUnit(), getYUnit());
        if (e == null) {
          bombList.add(new Bomb(getXUnit(), getYUnit(), frameLength, this));
        }
      }
      if (input.up || input.right || input.left || input.down) {
        setMoving(true);
      } else {
        setMoving(false);
        switch (direction) {
          case 0:
            this.setImg(Sprite.playerUp.getFxImage());
            break;
          case 1:
            this.setImg(Sprite.playerDown.getFxImage());
            break;
          case 2:
            this.setImg(Sprite.playerLeft.getFxImage());
            break;
          case 3:
            this.setImg(Sprite.playerRight.getFxImage());
            break;
          default:
            break;
        }
      }
      if (isMoving()) {
        // setPrecision();
        if (!soundMoving.isRunning()) {
          soundMoving.play();
        }
        calculateMove();
      } else {
        soundMoving.stop();
      }
    }
  }

  @Override
  public void render() {}

  @Override
  public void calculateMove() {
    input = BombermanGame.getCanvasGame().getInput();
    // setPrecision();
    if (input.up) {
      y -= velocity;
      // setPrecision(input);
      if (!canMove()) {
        y += velocity;
      }
      setDirection(0);
      this.setImg(Sprite.movingSprite(Sprite.playerUp, Sprite.playerUp1, Sprite.playerUp2,
          animation, timeTransfer).getFxImage());
    } else if (input.down) {
      y += velocity;

      if (!canMove()) {
        setPrecision(input);
        y -= velocity;
      }
      setDirection(1);
      this.setImg(Sprite.movingSprite(Sprite.playerDown, Sprite.playerDown1, Sprite.playerDown2,
          animation, timeTransfer).getFxImage());
    } else if (input.left) {
      x -= velocity;
      if (!canMove()) {
        setPrecision(input);
        x += velocity;
      }
      setDirection(2);
      this.setImg(Sprite.movingSprite(Sprite.playerLeft, Sprite.playerLeft1, Sprite.playerLeft2,
          animation, timeTransfer).getFxImage());
    } else {
      x += velocity;
      if (!canMove()) {
        setPrecision(input);
        x -= velocity;

      }
      setDirection(3);
      this.setImg(Sprite.movingSprite(Sprite.playerRight, Sprite.playerRight1, Sprite.playerRight2,
          animation, timeTransfer).getFxImage());
    }
  }

  @Override
  public boolean canMove() {
    for (int i = 0; i < 4; i++) { // check collision for 4 corners
      int newX = (getX() + addToXToCheckCollision[i]) / Sprite.SCALED_SIZE;
      int newY = (getY() + addToYToCheckCollision[i]) / Sprite.SCALED_SIZE;
      Entity e = BombermanGame.getCanvasGame().getEntityInCoodinate(newX, newY);

      if (e instanceof Wall || (e instanceof Brick && !canPassBrick)) {
        return false;
      }
      if (e instanceof Portal) {
        // if (killAllEnemies) {
        isCollideWithAPortal = true;
        soundMoving.stop();
        return true;
      }
      if (e instanceof Bomb bomb) {
        if (canPassBom) {
          bomb.setAllowPass(true);
        } else {
          if (!bomb.isAllowPass())
            return false;
        }
      }
    }
    return true;
  }

  @Override
  public void setPrecision(KeyboardInput input) {
    for (int i = 0; i < 4; i++) {
      int newX = (getX() + addToXToCheckCollision[i]) / Sprite.SCALED_SIZE;
      int newY = (getY() + addToYToCheckCollision[i]) / Sprite.SCALED_SIZE;
      Entity collision = BombermanGame.getCanvasGame().getEntityInCoodinate(newX, newY);
      int preX = (getX() + addToXToSetPrecision[i]) / Sprite.SCALED_SIZE;
      int preY = (getY() + addToYToCSetPrecision[i]) / Sprite.SCALED_SIZE;
      Entity check = BombermanGame.getCanvasGame().getGrass(preX, preY);
      boolean isCollided = collision instanceof Wall || collision instanceof Brick;

      if (check instanceof Grass && isCollided && i == 0) {
        y = preY * Sprite.SCALED_SIZE;
      }

      if (check instanceof Grass && isCollided && i == 1) {
        y = preY * Sprite.SCALED_SIZE;
      }

      if (check instanceof Grass && isCollided && i == 2) {
        x = preX * Sprite.SCALED_SIZE + 4;
      }

      if (check instanceof Grass && isCollided && i == 3) {
        x = preX * Sprite.SCALED_SIZE;
      }
    }
  }

  public List<Bomb> getBombList() {
    return bombList;
  }
  //
  // public void setCanPassBom(boolean canPassBom) {
  // this.canPassBom = canPassBom;
  // }
  //
  // public boolean isCanPassBom() {
  // return canPassBom;
  // }

  /**
   * Render bomb.
   */
  public void bombRender(GraphicsContext gc) {
    for (Bomb b : bombList) {
      if (b.isExplored()) {
        b.frameRender(gc);
      } else {
        b.render(gc);
      }
    }
  }

  public void ifCollisionWithFlameOrEnemyOrItem() {
    int x = getXUnit();
    int y = getYUnit();
    for (Bomb b : bombList) {
      List<Flame> fl = b.getFlameList();
      for (Flame f : fl) {
        if (f.getXUnit() == x && f.getYUnit() == y) {
          if (!canPassFlame && !startDie) {
            setStartDie(true);
            new Sound(Sound.DEAD_SOUND).play();
          }
          break;
        }
      }
    }
    Entity e = BombermanGame.getCanvasGame().getEntityInCoodinate(x, y);
    if (e instanceof Monster && !startDie) {
      setStartDie(true);
      new Sound(Sound.DEAD_SOUND).play();
    }

    if (e instanceof Powerup powerup) {
      new Sound(Sound.EAT_POWERUP_SOUND).play();
      switch (powerup.getId()) {
        case "psi":
          if (velocity < 3) { // velocity max = 3
            velocity++;
          }
          break;
        case "pbi":
          if (maxBom < 5) { // maxbom highest = 5
            maxBom++;
          }
          break;
        case "pfi":
          if (frameLength < 4) { // lenght max = 4
            frameLength++;
          }
          break;
        case "bpi":
          canPassBom = true;
          timeToStopBomb += 50 * 37;

          break;
        case "fpi":
          canPassFlame = true;
          timeToStopFlame += 50 * 37;
          break;
        case "wpi":
          canPassBrick = true; // hiem
          break;
        case "pli":
          BombermanGame.setLives(BombermanGame.getLives() + 1);
          break;
        default:
          break;
      }
      e.setImg(null);
    }
  }

  /**
   * Update bomb.
   */
  public void bombUpdate() {
    bombList.forEach(Bomb::update);
    for (Bomb b : bombList) {
      if (b.getImg() == null) {
        bombList.remove(b);
        break;
      } else {
        for (Flame fl : b.getFlameList()) {
          fl.update();
        }
      }
    }
  }

  public void setKillAllEnemies(boolean killAllEnemies) {
    this.killAllEnemies = killAllEnemies;
  }

  public boolean isCollideWithAPortal() {
    return isCollideWithAPortal;
  }
}
