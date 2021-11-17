package powerups;

import entities.Entity;
import javafx.scene.image.Image;

public abstract class Powerup extends Entity {
  public Powerup(int x, int y, Image img) {
    super(x, y, img);
  }

  protected String id;
  protected int timer = 500;

  @Override
  public void update() {
    timer--;
    if (timer == 0) {
      img = null;
    }
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
