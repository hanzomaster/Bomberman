package entities.player;

import GameFrame.KeyboardInput;
import GameMain.BombermanGame;
import entities.Entity;
import entities.stillobjects.Brick;
import entities.stillobjects.Wall;
import graphics.Sprite;

public class Bomber extends BomberCharacter {

  public static final int WIDTH = 31;
  public static final int HEIGHT = 15;
  private KeyboardInput input;
  private final int[] AddToXToCheckCollision =
      {0, Sprite.SCALED_SIZE - 10, Sprite.SCALED_SIZE - 10, 0};
  private final int[] AddToYToCheckCollision =
      {6, 6, Sprite.SCALED_SIZE - 1, Sprite.SCALED_SIZE - 1};

  /**
   * Create a bomber that react to user input.
   */
  public Bomber(int x, int y, KeyboardInput keyboardInput) {
    super(x, y, Sprite.playerDown.getFxImage());
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
        default:
          break;
      }
    }
    if (isMoving()) {
      calculateMove();
    }
  }

  @Override
  public void render() {}

  @Override
  public void calculateMove() {
    input = BombermanGame.canvas.getInput();
    if (input.up) {
      y -= velocity;
      if (!canMove()) {
        y += velocity;
      }
      setDirection(0);
      this.setImg(Sprite.movingSprite(Sprite.playerUp, Sprite.playerUp1, Sprite.playerUp2,
          animation, timeTransfer).getFxImage());
    } else if (input.down) {
      y += velocity;
      if (!canMove()) {
        y -= velocity;
      }
      setDirection(1);
      this.setImg(Sprite.movingSprite(Sprite.playerDown, Sprite.playerDown1, Sprite.playerDown2,
          animation, timeTransfer).getFxImage());
    } else if (input.left) {
      x -= velocity;
      if (!canMove()) {
        x += velocity;
      }
      setDirection(2);
      this.setImg(Sprite.movingSprite(Sprite.playerLeft, Sprite.playerLeft1, Sprite.playerLeft2,
          animation, timeTransfer).getFxImage());
    } else {
      x += velocity;
      if (!canMove()) {
        x -= velocity;
      }
      setDirection(3);
      this.setImg(Sprite.movingSprite(Sprite.playerRight, Sprite.playerRight1, Sprite.playerRight2,
          animation, timeTransfer).getFxImage());
    }
  }

  @Override
  public boolean canMove() {
    for (int i = 0; i < 4; i++) { // check collision for 4 corners
      int newX = (getX() + AddToXToCheckCollision[i]) / Sprite.SCALED_SIZE;
      int newY = (getY() + AddToYToCheckCollision[i]) / Sprite.SCALED_SIZE;
      Entity e = BombermanGame.canvas.getEntityInCoodinate(newX, newY);

      if (e instanceof Wall || e instanceof Brick) {
        return false;
      }
    }
    return true;
  }
}
