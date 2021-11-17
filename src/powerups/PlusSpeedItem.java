package powerups;

import graphics.Sprite;

public class PlusSpeedItem extends Powerup {

  public PlusSpeedItem(int x, int y) {
    super(x, y, Sprite.powerupSpeed.getFxImage());
  }
}
