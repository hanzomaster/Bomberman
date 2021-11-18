package entities.player;

import GameFrame.KeyboardInput;
import GameMain.BombermanGame;
import graphics.Sprite;


public class Bomber extends BomberCharacter {

  public static final int WIDTH = 31;
  public static final int HEIGHT = 15;
  private KeyboardInput input;

  public Bomber(int x, int y, KeyboardInput keyboardInput) {
    super(x, y, Sprite.playerDown.getFxImage());
    this.input = keyboardInput;
    direction = 1;
    velocity = 2;
    input = keyboardInput;
  }

  @Override
  public void update() {
    animate();

    input = BombermanGame.canvas.getInput();
    if (input.up || input.right || input.left || input.down) {
      setMoving(true);
    } else {
      setMoving(false);
      switch (direction) {
        case 0:
          this.setImg(Sprite.playerUp.getFxImage());
          break;
        case 1:
          this.setImg(Sprite.playerDown.getFxImage());
          break;
        case 2:
          this.setImg(Sprite.playerLeft.getFxImage());
          break;
        case 3:
          this.setImg(Sprite.playerRight.getFxImage());
          break;
      }
    }
    if (isMoving()) {
      calculateMove();
    }
  }

  @Override
  public void render() {
    // TODO Auto-generated method stub

  }

  @Override
  public void calculateMove() {
    // TODO Auto-generated method stub
    input = BombermanGame.canvas.getInput();
    if (input.up) {
      y -= velocity;
      setDirection(0);
      this.setImg(Sprite.movingSprite(Sprite.playerUp, Sprite.playerUp1, Sprite.playerUp2,
          animation, timeTransfer).getFxImage());
    } else if (input.down) {
      y += velocity;
      setDirection(1);
      this.setImg(Sprite.movingSprite(Sprite.playerDown, Sprite.playerDown1, Sprite.playerDown2,
          animation, timeTransfer).getFxImage());
    } else if (input.left) {
      x -= velocity;
      setDirection(2);
      this.setImg(Sprite.movingSprite(Sprite.playerLeft, Sprite.playerLeft1, Sprite.playerLeft2,
          animation, timeTransfer).getFxImage());
    } else {
      x += velocity;
      setDirection(3);
      this.setImg(Sprite.movingSprite(Sprite.playerRight, Sprite.playerRight1, Sprite.playerRight2,
          animation, timeTransfer).getFxImage());
    }

  }

  @Override
  public boolean canMove() {
    // TODO Auto-generated method stub
    return false;
  }
}