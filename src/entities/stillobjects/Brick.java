package entities.stillobjects;

import entities.AnimationEntity;
import graphics.Sprite;
import javafx.scene.image.Image;
import powerups.Powerup;

public class Brick extends AnimationEntity {

  private boolean isDestroyed = false;
  private int timeAnimate = 30;

  private boolean hasPower = false;
  private Powerup powerup;

  private boolean hasPortal = false;

  private Sprite[] sprites = {Sprite.brick, Sprite.brick2, Sprite.brick3, Sprite.brick4,
      Sprite.brick5, Sprite.brick6, Sprite.brick7};

  public Brick(int x, int y, Image image) {
    super(x, y, image);
  }

  public Brick(int x, int y, int level) {
    super(x, y, null);
    img = sprites[level].getFxImage();
  }

  public boolean hasPowerup() {
    return hasPower;
  }

  public boolean hasPortal() {
    return hasPortal;
  }

  public void setPowerUp(boolean hasPower, Powerup powerup) {
    this.hasPower = hasPower;
    this.powerup = powerup;
  }

  public void setPortal(boolean hasPortal) {
    this.hasPortal = hasPortal;
  }

  public boolean isDestroyed() {
    return isDestroyed;
  }

  public void setDestroyed(boolean destroy) {
    isDestroyed = destroy;
  }

  public Powerup getPowerup() {
    return powerup;
  }

  @Override
  public void update() {
    if (isDestroyed) {
      animate();

      if (timeAnimate-- > 0) {
        img = Sprite.movingSprite(Sprite.brickExploded, Sprite.brickExploded1,
            Sprite.brickExploded2, animation, timeTransfer).getFxImage();
      } else {
        img = null;
      }
    }
  }
}
