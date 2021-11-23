package entities.player;

import GameFrame.KeyboardInput;
import entities.AnimationEntity;
import javafx.scene.image.Image;

public abstract class BomberCharacter extends AnimationEntity {
  protected int direction;// 0 Up, 1 Down, 2 Left, 3 Right
  protected boolean alive = true;
  protected boolean moving = false;
  protected int velocity;
  protected int timeShowDeath = 100;
  protected boolean startDie = false;

  public BomberCharacter(int x, int y, Image img) {
    super(x, y, img);
  }

  public void setDirection(int direction) {
    this.direction = direction;
  }

  public void setAlive(boolean alive) {
    this.alive = alive;
  }

  public void setMoving(boolean moving) {
    this.moving = moving;
  }

  public void setVelocity(int velocity) {
    this.velocity = velocity;
  }

  public boolean isAlive() {
    return alive;
  }

  public boolean isMoving() {
    return moving;
  }

  public int getTimeTransfer() {
    return timeTransfer;
  }

  public abstract void update();

  public abstract void render();

  public abstract void calculateMove();

  public abstract boolean canMove();

  public abstract void setPrecision(KeyboardInput input);

  public boolean isStartDie() {
    return startDie;
  }

  public void setTimeShowDeath(int timeShowDeath) {
    this.timeShowDeath = timeShowDeath;
  }

  public void setStartDie(boolean startDie) {
    this.startDie = startDie;
  }
}
