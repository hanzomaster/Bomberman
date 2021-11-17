package entities.stillobjects;

import entities.Entity;
import graphics.Sprite;
import javafx.scene.image.Image;

public class Wall extends Entity {

  private Sprite[] sprites = {Sprite.wall1, Sprite.wall2, Sprite.wall3, Sprite.wall4, Sprite.wall5,
      Sprite.wall6, Sprite.wall7};

  public Wall(int x, int y, int level) {
    super(x, y, null);
    img = sprites[level].getFxImage();
  }

  public Wall(int x, int y, Image image) {
    super(x, y, image);
  }

  @Override
  public void update() {}
}
