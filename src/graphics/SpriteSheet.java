package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 * Tất cả sprite (hình ảnh game) được lưu trữ vào một ảnh duy nhất Class này giúp lấy ra các sprite
 * riêng từ 1 ảnh chung duy nhất đó.
 */
public class SpriteSheet {

  private String path;
  public final int size;
  protected int[] pixels;

  public static final SpriteSheet tiles = new SpriteSheet("/resources/textures/classic.png", 256);
  public static final SpriteSheet dragon = new SpriteSheet("/resources/textures/dragon.png", 64);
  public static final SpriteSheet fireMonster = new SpriteSheet("/resources/textures/fire.png", 48);
  public static final SpriteSheet batMonster =
      new SpriteSheet("/resources/textures/batmonster4.png", 64);
  public static final SpriteSheet newtiles =
      new SpriteSheet("/resources/textures/TilesMap.png", 96);

  /**
   * Load sprite sheet for cutting.
   * 
   * @param path Path to sprite sheet
   */
  public SpriteSheet(String path, int size) {
    this.path = path;
    this.size = size;
    pixels = new int[this.size * this.size];
    load();
  }

  private void load() {
    BufferedImage image;
    try {
      URL a = SpriteSheet.class.getResource(path);
      image = ImageIO.read(a);
      int w = image.getWidth();
      int h = image.getHeight();
      image.getRGB(0, 0, w, h, pixels, 0, w);
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(0);
    }
  }
}
