package entities.monsters;

import graphics.Sprite;

public class Balloon extends Monster {

  public static final int WIDTH = 31;
  public static final int HEIGHT = 15;

  public Balloon(int xUnit, int yUnit) {
    super(xUnit, yUnit, Sprite.balloomLeft1.getFxImage());
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
      this.setImg(Sprite.movingSprite(Sprite.balloomLeft1, Sprite.balloomLeft2, Sprite.balloomLeft3,
          animate, timeTransfer).getFxImage());
    else if (direction == 1)
      this.setImg(Sprite.movingSprite(Sprite.balloomRight1, Sprite.balloomRight2,
          Sprite.balloomRight3, animate, timeTransfer).getFxImage());
    else if (direction == 2)
      this.setImg(Sprite.movingSprite(Sprite.balloomLeft1, Sprite.balloomRight1,
          Sprite.balloomLeft3, animate, timeTransfer).getFxImage());
    else if (direction == 3)
      this.setImg(Sprite.movingSprite(Sprite.balloomRight1, Sprite.balloomLeft2,
          Sprite.balloomRight2, animate, timeTransfer).getFxImage());

  }
  // TODO Auto-generated method stub

}


