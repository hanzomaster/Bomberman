package powerups;

import graphics.Sprite;

public class PlusLiveItem extends Powerup {

  public PlusLiveItem(int x, int y) {
    super(x, y, Sprite.powerupDetonator.getFxImage());
  }
}
