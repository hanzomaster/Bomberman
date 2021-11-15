package entities.monsters;

import entities.Entity;
import javafx.scene.image.Image;

public class Doll extends Entity {
  public Doll(int xUnit, int yUnit, Image img) {
    super(xUnit, yUnit, img);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void update() {


    x -= 1;
    y += 1;
    // TODO Auto-generated method stub

  }


}
