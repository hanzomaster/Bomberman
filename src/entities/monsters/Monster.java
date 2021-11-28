package entities.monsters;

import java.util.List;
import Bomb.Bomb;
import GameFrame.KeyboardInput;
import GameMain.BombermanGame;
import entities.AnimationEntity;
import entities.Entity;
import entities.player.Bomber;
import entities.stillobjects.Brick;
import entities.stillobjects.Portal;
import entities.stillobjects.Wall;
import graphics.Sprite;
import javafx.scene.image.Image;
import powerups.Powerup;

public abstract class Monster extends AnimationEntity {

  protected int velocity = 1;

  protected boolean isAlive = true;
  protected int direction = 0;
  // 0 up, 1 down, 2 left, 3 right
  protected Bomber bomber = new Bomber(0, 0, new KeyboardInput());
  protected int timeDead = 40;
  protected int flameHit = 0;
  public static final int[] AddToXToCheckCollision =
      {2, Sprite.SCALED_SIZE - 2, Sprite.SCALED_SIZE - 2, 2};
  public static final int[] AddToYToCheckCollision =
      {2, 2, Sprite.SCALED_SIZE - 2, Sprite.SCALED_SIZE - 2};

  public Monster(int xUnit, int yUnit, Image img) {
    super(xUnit, yUnit, img);
  }

  /**
   * Check if monster can move to coordinate [x,y].
   * 
   * @return True if monster can go to [x,y] and false other wise
   */
  public boolean canMove(int x, int y) {
    int xUnit = (int) x / Sprite.SCALED_SIZE;
    int yUnit = (int) y / Sprite.SCALED_SIZE;
    Entity e = BombermanGame.getCanvasGame().getEntityInCoodinate(xUnit, yUnit);
    if (this instanceof Kondoria && e instanceof Brick) {
      return true;
    }
    if (this instanceof Dragon && recognizeBomb(x, y)) {
      return false;
    }
    if (this instanceof BatMonster && recognizeBomb(x, y)) {
      return false;
    }
    if (this instanceof Phoenix && recognizeBomb(x, y)) {
      return false;
    }
    return !(e instanceof Wall || e instanceof Brick || e instanceof Bomb || e instanceof Portal);
  }

  public boolean recognizeBomb(int x, int y) {
    int xUnit = (int) x / Sprite.SCALED_SIZE;
    int yUnit = (int) y / Sprite.SCALED_SIZE;
    Entity e = BombermanGame.getCanvasGame().getEntityInCoodinate(xUnit + 1, yUnit);
    Entity e1 = BombermanGame.getCanvasGame().getEntityInCoodinate(xUnit - 1, yUnit);
    Entity e2 = BombermanGame.getCanvasGame().getEntityInCoodinate(xUnit, yUnit + 1);
    Entity e3 = BombermanGame.getCanvasGame().getEntityInCoodinate(xUnit, yUnit - 1);
    if (e instanceof Bomb) {
      return true;
    }
    if (e1 instanceof Bomb) {
      return true;
    }
    if (e2 instanceof Bomb) {
      return true;
    }
    if (e3 instanceof Bomb) {
      return true;
    }
    return false;

  }


  public abstract void deadAnimation();

  /**
   * If monster collide with powerup, powerup will disappear.
   */
  public void ifCollideWithPowerupOrFlame() {
    int x = getXUnit();
    int y = getYUnit();
    // if (this instanceof Dragon) {
    // flameHit++;
    // System.out.println(flameHit);
    // }
    // enemy gap bat ky item auto se tang speed
    Entity e = BombermanGame.getCanvasGame().getEntityInCoodinate(x, y);
    if (e instanceof Powerup) {
      // if (!(this instanceof Dragon)) {
      // setVelocity(velocity + 1);
      // }
      e.setImg(null);
    }
    // List<Bomb> bombList = bomber.getBombList();
    // for (Bomb b : bombList) {
    // List<Flame> fl = b.getFlameList();
    // for (Flame f : fl) {
    // if (f.getXUnit() == x && f.getYUnit() == y) {
    // setAlive(false);
    // break;
    // }
    // }
    // }
  }

  /**
   * If collide with bomb then turn around.
   */
  public void collideWithBomb() {
    List<Bomb> bombs = bomber.getBombList();
    for (Bomb b : bombs) {
      if (b.getXUnit() == this.getXUnit() && b.getYUnit() == this.getYUnit()) {
        setVelocity(0);
        break;
      }
    }
  }

  public int getAnimate() {
    return animation;
  }

  public void setBomber(Bomber bomber) {
    this.bomber = bomber;
  }

  public int getVelocity() {
    return velocity;
  }

  public int getDirection() {
    return direction;
  }

  public void setDirection(int direction) {
    this.direction = direction;
  }

  public void setAlive(boolean alive) {
    isAlive = alive;
  }

  public void setVelocity(int velocity) {
    this.velocity = velocity;
  }

  public void setHit(int h) {
    this.flameHit = h;
    // System.out.println(flameHit);
  }

  public int getHit() {
    return flameHit;
  }
}
