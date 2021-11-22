package entities.monsters;

import graphics.Sprite;

public class Doll extends Monster {
  public Doll(int xUnit, int yUnit) {
    super(xUnit, yUnit, Sprite.dollLeft1.getFxImage());
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
      this.setImg(Sprite.movingSprite(Sprite.balloomLeft1, Sprite.balloomLeft2, Sprite.balloomLeft3,
          animation, timeTransfer).getFxImage());
    } else if (direction == 1) {
      this.setImg(Sprite.movingSprite(Sprite.balloomRight1, Sprite.balloomRight2,
          Sprite.balloomRight3, animation, timeTransfer).getFxImage());
    } else if (direction == 2) {
      this.setImg(Sprite.movingSprite(Sprite.balloomLeft1, Sprite.balloomRight1,
          Sprite.balloomLeft3, animation, timeTransfer).getFxImage());
    } else if (direction == 3) {
      this.setImg(Sprite.movingSprite(Sprite.balloomRight1, Sprite.balloomLeft2,
          Sprite.balloomRight2, animation, timeTransfer).getFxImage());
    }
  }

  @Override
  public void deadAnimation() {
    // TODO Auto-generated method stub

  }
}
