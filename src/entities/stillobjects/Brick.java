package entities.stillobjects;

import entities.AnimationEntity;
import javafx.scene.image.Image;

public class Brick extends AnimationEntity {
  public Brick(int x, int y, Image image) {
    super(x, y, image);
  }

  @Override
  public void update() {}
}
