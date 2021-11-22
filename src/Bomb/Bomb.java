package Bomb;

import java.util.ArrayList;
import java.util.List;
import GameMain.BombermanGame;
import entities.AnimationEntity;
import entities.Entity;
import entities.player.Bomber;
import entities.stillobjects.Brick;
import entities.stillobjects.Portal;
import entities.stillobjects.Wall;
import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;

public class Bomb extends AnimationEntity {
  private int timeBeforeExplore = 100;
  private int timeFlame = 14;

  private static final int timeTransfer = 40;
  private boolean explored;
  private boolean allowPass = true;
  private Bomber bomber;

  private int flameLength = 1;
  private List<Flame> flameList = new ArrayList<>();


  /**
   * Create a bomb.
   */
  // public Sound soundExplode = new Sound(Sound.soundExplosion);

  public Bomb(int x, int y, int flameLen, Bomber bomber) {
    super(x, y, Sprite.bomb.getFxImage());
    this.flameLength = flameLen;
    explored = false;
    this.bomber = bomber;
  }

  @Override
  public void update() {
    animate();
    if (!explored) {
      if (allowPass) {
        int subX = bomber.getX() - getX();
        int subY = bomber.getY() - getY();
        if (subX < -20 || subX > 31 || subY > 25 || subY < -31) {
          allowPass = false;
        }
      }
      if (timeBeforeExplore-- > 0) {
        setImg(Sprite.movingSprite(Sprite.bomb, Sprite.bomb1, Sprite.bomb2, animation, timeTransfer)
            .getFxImage());
      } else {
        explored = true;
        explosion();
      }
    } else {

      if (timeFlame-- == 0) {
        setImg(null);
      }
    }
  }

  private void explosion() {
    // init FlameList
    int x = getXUnit();
    int y = getYUnit();

    flameList.add(new Flame(x, y, 4, false));// add center
    // truong hop bomber o tren qua bom
    Entity e = BombermanGame.getCanvasGame().getEntityInCoodinate(x, y);
    canPassThrough(e);

    // check left
    int il = 1;
    for (; il <= flameLength; il++) { // check tu 1 den FrameLen neu gap vat can break
      int xLeft = x - il;
      e = BombermanGame.getCanvasGame().getEntityInCoodinate(xLeft, y);
      if (!canPassThrough(e)) {
        break;
      }
    }
    for (int i = 1; i < il; i++) { // them flame tu 1 den do dai frame lon nhat co the
      if (i == il - 1) {
        flameList.add(new Flame(x - i, y, 2, true));
      } else {
        flameList.add(new Flame(x - i, y, 2, false));
      }
    }

    // check right
    int ir = 1;
    for (; ir <= flameLength; ir++) {
      int xRight = x + ir;
      e = BombermanGame.getCanvasGame().getEntityInCoodinate(xRight, y);
      if (!canPassThrough(e)) {
        break;
      }
    }
    for (int i = 1; i < ir; i++) {
      if (i == ir - 1) {
        flameList.add(new Flame(x + i, y, 3, true));
      } else {
        flameList.add(new Flame(x + i, y, 3, false));
      }
    }

    // check up
    int iu = 1;
    for (; iu <= flameLength; iu++) {
      int yUp = y - iu;
      e = BombermanGame.getCanvasGame().getEntityInCoodinate(x, yUp);
      if (!canPassThrough(e)) {
        break;
      }
    }
    for (int i = 1; i < iu; i++) {
      if (i == iu - 1) {
        flameList.add(new Flame(x, y - i, 0, true));
      } else {
        flameList.add(new Flame(x, y - i, 0, false));
      }
    }

    // check down
    int id = 1;
    for (; id <= flameLength; id++) {
      int yDown = y + id;
      e = BombermanGame.getCanvasGame().getEntityInCoodinate(x, yDown);
      if (!canPassThrough(e)) {
        break;
      }
    }
    for (int i = 1; i < id; i++) {
      if (i == id - 1) {
        flameList.add(new Flame(x, y + i, 1, true));
      } else {
        flameList.add(new Flame(x, y + i, 1, false));
      }
    }
  }

  public void frameRender(GraphicsContext gc) {
    flameList.forEach(g -> g.render(gc));
  }

  public boolean isExplored() {
    return explored;
  }

  public boolean canPassThrough(Entity e) { // return false if ko truyen qua dc e, true if truyen
                                            // qua dc

    int gotScore = 0;
    if (e instanceof Brick) {
      ((Brick) e).setDestroyed(true);
      gotScore = 5;
      BombermanGame.setScore(BombermanGame.getScore() + gotScore);
      return false;
    }
    if (e instanceof Wall || e instanceof Portal) {
      return false;
    }

    if (e instanceof Bomb) {
      ((Bomb) e).setTimeBeforeExplore(5);
    }
    return true;
  }

  public List<Flame> getFlameList() {
    return flameList;
  }

  public boolean isAllowPass() {
    return allowPass;
  }

  public void setAllowPass(boolean allowPass) {
    this.allowPass = allowPass;
  }

  public void setTimeBeforeExplore(int timeBeforeExplore) {
    this.timeBeforeExplore = timeBeforeExplore;
  }

}
