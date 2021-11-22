package entities.monsters;

import graphics.Sprite;

public class Minvo extends Monster {

  public Minvo(int xUnit, int yUnit) {
    super(xUnit, yUnit, Sprite.minvoLeft1.getFxImage());
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

    this.setX(tempX);
    this.setY(tempY);
  }

  @Override
  public void update() {
    move();
    animate();
    if (direction == 0) {
      this.setImg(Sprite.movingSprite(Sprite.minvoLeft1, Sprite.minvoLeft2, Sprite.minvoLeft3,
          animation, timeTransfer).getFxImage());
    } else if (direction == 1) {
      this.setImg(Sprite.movingSprite(Sprite.minvoRight1, Sprite.minvoRight2, Sprite.minvoRight3,
          animation, timeTransfer).getFxImage());
    } else if (direction == 2) {
      this.setImg(Sprite.movingSprite(Sprite.minvoLeft1, Sprite.minvoRight1, Sprite.minvoLeft3,
          animation, timeTransfer).getFxImage());
    } else if (direction == 3) {
      this.setImg(Sprite.movingSprite(Sprite.minvoRight1, Sprite.minvoLeft2, Sprite.minvoRight2,
          animation, timeTransfer).getFxImage());
    }
  }

  @Override
  public void deadAnimation() {
    // TODO Auto-generated method stub

  }
}
