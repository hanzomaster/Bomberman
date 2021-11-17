package entities.monsters;

import graphics.Sprite;

public class Doll extends Monster {
  public Doll(int xUnit, int yUnit) {
    super(xUnit, yUnit, Sprite.dollLeft1.getFxImage());
    // TODO Auto-generated constructor stub
  }

  public void move() {
    int tempX = x, tempY = y;
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
    }

    this.setX(tempX);
    this.setY(tempY);
  }

  @Override
  public void update() {
    move();
    animate();
    if (direction == 0)
      this.setImg(Sprite
          .movingSprite(Sprite.dollLeft1, Sprite.dollLeft2, Sprite.dollLeft3, animate, timeTransfer)
          .getFxImage());
    else if (direction == 1)
      this.setImg(Sprite.movingSprite(Sprite.dollRight1, Sprite.dollRight2, Sprite.dollRight3,
          animate, timeTransfer).getFxImage());
    else if (direction == 2)
      this.setImg(Sprite.movingSprite(Sprite.dollLeft1, Sprite.dollRight1, Sprite.dollLeft3,
          animate, timeTransfer).getFxImage());
    else if (direction == 3)
      this.setImg(Sprite.movingSprite(Sprite.dollRight1, Sprite.dollLeft2, Sprite.dollRight2,
          animate, timeTransfer).getFxImage());

  }

}
