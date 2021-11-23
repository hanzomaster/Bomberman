package entities.monsters;

import entities.monsters.moveMethod.moveType2;
import graphics.Sprite;

public class Kondoria extends Monster {
  private moveType2 moveType = new moveType2();

  public Kondoria(int xUnit, int yUnit) {
    super(xUnit, yUnit, Sprite.kondoriaLeft1.getFxImage());
    velocity = 2;
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
        setDirection(moveType.setDirection(direction));
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

  @Override
  public void deadAnimation() {
    // TODO Auto-generated method stub
    if (timeDead-- > 0) {
      this.setImg(Sprite.movingSprite(Sprite.kondoriaDead, Sprite.mobDead11, Sprite.mobDead12,
          animation, timeTransfer).getFxImage());
    } else {
      this.removeFromGame();
    }
  }

}
