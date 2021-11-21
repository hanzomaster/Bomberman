package entities.stillobjects;

import entities.Entity;
import graphics.Sprite;

public class Portal extends Entity {

  public Portal(int x, int y) {
    super(x, y, Sprite.portal.getFxImage());
  }

  @Override
  public void update() {}
}
