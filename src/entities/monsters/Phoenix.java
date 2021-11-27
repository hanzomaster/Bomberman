package entities.monsters;

import entities.monsters.moveMethod.moveType2;
import graphics.Sprite;

public class Phoenix extends Monster {
  private final moveType2 movetype = new moveType2();
  // private int timeShowFire = 100;
  // private int gapTime = 250;
  // private DragonBreathe breathe;

  public Phoenix(int xUnit, int yUnit) {
    super(xUnit, yUnit, Sprite.dragonLeft1.getFxImage());
    direction = 2;
    velocity = 3;
  }

  /**
   * Move logic.
   */
  public void move() {
    int tempX = x;
    int tempY = y;
    switch (direction) {
      case 0:
        tempY = y - velocity;
        break;
      case 1:
        tempY = y + velocity;
        break;
      case 2:
        tempX = x - velocity;
        break;
      case 3:
        tempX = x + velocity;
        break;
      default:
        break;
    }

    for (int i = 0; i < 4; i++) {
      int xx = tempX + AddToXToCheckCollision[i];
      int yy = tempY + AddToYToCheckCollision[i];
      if (!canMove(xx, yy)) {
        direction = movetype.setDirection(direction);
        return;
      }
    }

    this.setX(tempX);
    this.setY(tempY);
  }

  @Override
  public void update() {

    if (!isAlive) {
      deadAnimation();
    } else {
      move();
      animate();
      ifCollideWithPowerupOrFlame();
      if (direction == 0 && flameHit == 0) {
        this.setImg(Sprite.movingSprite(Sprite.phoenixUp1, Sprite.phoenixUp2, Sprite.phoenixUp3,
            animation, timeTransfer).getFxImage());
      } else if (direction == 1 && flameHit == 0) {
        this.setImg(Sprite.movingSprite(Sprite.phoenixDown1, Sprite.phoenixDown2,
            Sprite.phoenixDown3, animation, timeTransfer).getFxImage());
      } else if (direction == 2 && flameHit == 0) {
        this.setImg(Sprite.movingSprite(Sprite.phoenixLeft1, Sprite.phoenixLeft2,
            Sprite.phoenixLeft3, animation, timeTransfer).getFxImage());
      } else if (direction == 3 && flameHit == 0) {
        this.setImg(Sprite.movingSprite(Sprite.phoenixRight1, Sprite.phoenixRight2,
            Sprite.phoenixRight3, animation, timeTransfer).getFxImage());
      } else if (flameHit == 1) {
        this.setImg(
            Sprite.movingSprite(Sprite.fire1, Sprite.fire2, Sprite.fire3, animation, timeTransfer)
                .getFxImage());
      }
    }
  }

  @Override
  public void deadAnimation() {
    if (timeDead-- > 0) {
      this.setImg(Sprite.movingSprite(Sprite.phoenixDown1, Sprite.mobDead11, Sprite.mobDead12,
          animation, timeTransfer).getFxImage());
    } else {
      this.removeFromGame();
    }
  }
}
