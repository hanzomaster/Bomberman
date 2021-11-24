package entities.monsters;

import entities.monsters.moveMethod.moveType1;
import graphics.Sprite;

public class BatMonster extends Monster {
  private final moveType1 movetype = new moveType1();

  public BatMonster(int xUnit, int yUnit) {
    super(xUnit, yUnit, Sprite.balloomLeft1.getFxImage());
    direction = 2;
  }

  /**
   * Move logic.
   */
  public void move() {
    int tempX = x;
    int tempY = y;
    switch (direction) {
      case 0:
        tempY = y + velocity;
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
      if (direction == 0) {
        this.setImg(Sprite.movingSprite(Sprite.batLeft1, Sprite.batLeft2, Sprite.batLeft3,
            animation, timeTransfer).getFxImage());
      } else if (direction == 1) {
        this.setImg(Sprite.movingSprite(Sprite.batRight1, Sprite.batRight2, Sprite.batRight3,
            animation, timeTransfer).getFxImage());
      } else if (direction == 2) {
        this.setImg(Sprite.movingSprite(Sprite.batLeft1, Sprite.batLeft1, Sprite.batLeft3,
            animation, timeTransfer).getFxImage());
      } else if (direction == 3) {
        this.setImg(Sprite.movingSprite(Sprite.batRight1, Sprite.batRight2, Sprite.batRight3,
            animation, timeTransfer).getFxImage());
      }
    }
  }

  @Override
  public void deadAnimation() {
    if (timeDead-- > 0) {
      this.setImg(Sprite.movingSprite(Sprite.batDown1, Sprite.mobDead12, Sprite.mobDead13,
          animation, timeTransfer).getFxImage());
    } else {
      this.removeFromGame();
    }
  }
}
