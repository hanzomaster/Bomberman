package entities;

import javafx.scene.image.Image;


public class Bomber extends Entity {

  public static final int WIDTH = 31;
  public static final int HEIGHT = 15;

  public Bomber(int x, int y, Image img) {
    super(x, y, img);
  }

  @Override
  public void update() {
    if (x <= WIDTH) {
      x--;
    }
    x += 1;
    System.out.println(x);
  }
}
