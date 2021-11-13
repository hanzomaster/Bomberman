package entities;

import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Entity {
  // Tọa độ X tính từ góc trái trên trong Canvas
  protected int x;

  // Tọa độ Y tính từ góc trái trên trong Canvas
  protected int y;

  protected Image img;

  /**
   * Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas.
   */
  public Entity(int xUnit, int yUnit, Image img) {
    x = xUnit * Sprite.SCALED_SIZE;
    y = yUnit * Sprite.SCALED_SIZE;
    this.img = img;
  }

  public void render(GraphicsContext gc) {
    gc.drawImage(img, x, y);
  }

  public abstract void update();

  public int getXUnit() {
    return (x + Sprite.DEFAULT_SIZE) / Sprite.SCALED_SIZE;
  }

  public int getYUnit() {
    return (y + Sprite.DEFAULT_SIZE) / Sprite.SCALED_SIZE;
  }
}
