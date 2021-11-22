package entities.monsters;

import graphics.Sprite;

public class Oneal extends Monster {

  public Oneal(int xUnit, int yUnit) {
    super(xUnit, yUnit, Sprite.onealLeft1.getFxImage());
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
      this.setImg(Sprite.movingSprite(Sprite.onealLeft1, Sprite.onealLeft2, Sprite.onealLeft3,
          animation, timeTransfer).getFxImage());
    } else if (direction == 1) {
      this.setImg(Sprite.movingSprite(Sprite.onealRight1, Sprite.onealRight2, Sprite.onealRight3,
          animation, timeTransfer).getFxImage());
    } else if (direction == 2) {
      this.setImg(Sprite.movingSprite(Sprite.onealLeft1, Sprite.onealRight1, Sprite.onealLeft3,
          animation, timeTransfer).getFxImage());
    } else if (direction == 3) {
      this.setImg(Sprite.movingSprite(Sprite.onealRight1, Sprite.onealLeft2, Sprite.onealRight2,
          animation, timeTransfer).getFxImage());
    }
  }

  @Override
  public void deadAnimation() {
    // TODO Auto-generated method stub

  }
}
