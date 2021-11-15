package entities.monsters;

import entities.Entity;
import javafx.scene.image.Image;

public class Balloon extends Entity {

  public static final int WIDTH = 31;
  public static final int HEIGHT = 15;

  public Balloon(int xUnit, int yUnit, Image img) {
    super(xUnit, yUnit, img);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void update() {

    if (x < WIDTH) {
      x--;
    }
    x += 1;
    y += 1;
    // TODO Auto-generated method stub

  }

}
