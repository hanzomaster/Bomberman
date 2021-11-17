package entities.stillobjects;

import entities.Entity;
import graphics.Sprite;
import javafx.scene.image.Image;

public class Grass extends Entity {
  private Sprite[] sprites = {Sprite.grass1, Sprite.grass2, Sprite.grass3, Sprite.grass4,
      Sprite.grass5, Sprite.grass6, Sprite.grass7};

  public Grass(int x, int y, int level) {
    super(x, y, null);
    this.setImg(sprites[level].getFxImage());
  }

  public Grass(int x, int y, Image image) {
    super(x, y, image);
  }

  @Override
  public void update() {}
}
