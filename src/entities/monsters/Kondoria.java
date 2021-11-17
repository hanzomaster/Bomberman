package entities.monsters;

import graphics.Sprite;

public class Kondoria extends Monster {

  public Kondoria(int xUnit, int yUnit) {
    super(xUnit, yUnit, Sprite.kondoriaLeft1.getFxImage());
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
      this.setImg(Sprite.movingSprite(Sprite.kondoriaLeft1, Sprite.kondoriaLeft2,
          Sprite.kondoriaLeft3, animation, timeTransfer).getFxImage());
    } else if (direction == 1) {
      this.setImg(Sprite.movingSprite(Sprite.kondoriaRight1, Sprite.kondoriaRight2,
          Sprite.kondoriaRight3, animation, timeTransfer).getFxImage());
    } else if (direction == 2) {
      this.setImg(Sprite.movingSprite(Sprite.kondoriaLeft1, Sprite.kondoriaRight1,
          Sprite.kondoriaLeft3, animation, timeTransfer).getFxImage());
    } else if (direction == 3) {
      this.setImg(Sprite.movingSprite(Sprite.kondoriaRight1, Sprite.kondoriaLeft2,
          Sprite.kondoriaRight2, animation, timeTransfer).getFxImage());
    }
  }

}
