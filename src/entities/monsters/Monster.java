package entities.monsters;

import entities.AnimationEntity;
import javafx.scene.image.Image;

public abstract class Monster extends AnimationEntity {

  protected int velocity = 1;

  protected boolean isAlive = true;
  protected int direction = 3;

  public Monster(int xUnit, int yUnit, Image img) {
    super(xUnit, yUnit, img);
    // TODO Auto-generated constructor stub
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



}
