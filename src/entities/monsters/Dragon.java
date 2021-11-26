package entities.monsters;

import entities.monsters.moveMethod.moveType2;
import graphics.Sprite;

public class Dragon extends Monster {
  private final moveType2 movetype = new moveType2();
  private int timeShowFire = 100;
  private int gapTime = 250;
  // private DragonBreathe breathe;

  public Dragon(int xUnit, int yUnit) {
    super(xUnit, yUnit, Sprite.balloomLeft1.getFxImage());
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
        tempY = y + velocity;
        break;
      case 1:
        tempY = y - velocity;
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
        this.setImg(Sprite.movingSprite(Sprite.dragonUp1, Sprite.dragonUp2, Sprite.dragonUp3,
            animation, timeTransfer).getFxImage());
      } else if (direction == 1) {
        this.setImg(Sprite.movingSprite(Sprite.dragonRight1, Sprite.dragonRight2,
            Sprite.dragonRight3, animation, timeTransfer).getFxImage());
      } else if (direction == 2) {
        this.setImg(Sprite.movingSprite(Sprite.dragonLeft1, Sprite.dragonLeft2, Sprite.dragonLeft3,
            animation, timeTransfer).getFxImage());
      } else if (direction == 3) {
        this.setImg(Sprite.movingSprite(Sprite.dragonRight1, Sprite.dragonRight2,
            Sprite.dragonRight3, animation, timeTransfer).getFxImage());
      }
    }
  }

  @Override
  public void deadAnimation() {
    if (timeDead-- > 0) {
      this.setImg(Sprite.movingSprite(Sprite.dragonDown1, Sprite.mobDead11, Sprite.mobDead12,
          animation, timeTransfer).getFxImage());
    } else {
      this.removeFromGame();
    }
  }
}
