package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 * Tất cả sprite (hình ảnh game) được lưu trữ vào một ảnh duy nhất Class này giúp lấy ra các sprite
 * riêng từ 1 ảnh chung duy nhất đó
 */
public class SpriteSheet {

  private String path;
  public final int size;
  public int[] pixels;
  public BufferedImage image;

  public static SpriteSheet tiles = new SpriteSheet("/resources/textures/classic.png", 256);

  public SpriteSheet(String path, int size) {
    this.path = path;
    this.size = size;
    this.pixels = new int[this.size * this.size];
    load();
  }

  private void load() {
    try {
      URL a = SpriteSheet.class.getResource(this.path);
      image = ImageIO.read(a);
      int w = image.getWidth();
      int h = image.getHeight();
      image.getRGB(0, 0, w, h, this.pixels, 0, w);
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(0);
    }
  }
}
