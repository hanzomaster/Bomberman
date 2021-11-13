package graphics;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

/**
 * Lưu trữ thông tin các pixel của 1 sprite (hình ảnh game).
 */
public class Sprite {

  public static final int DEFAULT_SIZE = 16;
  public static final int SCALED_SIZE = DEFAULT_SIZE * 2;
  private static final int TRANSPARENT_COLOR = 0xffff00ff;
  public final int size;
  private int x;
  private int y;
  public int[] pixels;
  protected int realWidth;
  protected int realHeight;
  private SpriteSheet sheet;

  /*
   * |-------------------------------------------------------------------------- | Board sprites
   * |--------------------------------------------------------------------------
   */
  public static final Sprite grass = new Sprite(DEFAULT_SIZE, 6, 0, SpriteSheet.tiles, 16, 16);
  public static final Sprite brick = new Sprite(DEFAULT_SIZE, 7, 0, SpriteSheet.tiles, 16, 16);
  public static final Sprite wall = new Sprite(DEFAULT_SIZE, 5, 0, SpriteSheet.tiles, 16, 16);
  public static final Sprite portal = new Sprite(DEFAULT_SIZE, 4, 0, SpriteSheet.tiles, 14, 14);

  /*
   * |-------------------------------------------------------------------------- | Bomber Sprites
   * |--------------------------------------------------------------------------
   */
  public static final Sprite playerUp = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles, 12, 16);
  public static final Sprite playerDown = new Sprite(DEFAULT_SIZE, 2, 0, SpriteSheet.tiles, 12, 15);
  public static final Sprite playerLeft = new Sprite(DEFAULT_SIZE, 3, 0, SpriteSheet.tiles, 10, 15);
  public static final Sprite playerRight =
      new Sprite(DEFAULT_SIZE, 1, 0, SpriteSheet.tiles, 10, 16);

  public static final Sprite playerUp1 = new Sprite(DEFAULT_SIZE, 0, 1, SpriteSheet.tiles, 12, 16);
  public static final Sprite playerUp2 = new Sprite(DEFAULT_SIZE, 0, 2, SpriteSheet.tiles, 12, 15);

  public static final Sprite playerDown1 =
      new Sprite(DEFAULT_SIZE, 2, 1, SpriteSheet.tiles, 12, 15);
  public static final Sprite playerDown2 =
      new Sprite(DEFAULT_SIZE, 2, 2, SpriteSheet.tiles, 12, 16);

  public static final Sprite playerLeft1 =
      new Sprite(DEFAULT_SIZE, 3, 1, SpriteSheet.tiles, 11, 16);
  public static final Sprite playerLeft2 =
      new Sprite(DEFAULT_SIZE, 3, 2, SpriteSheet.tiles, 12, 16);

  public static final Sprite playerRight1 =
      new Sprite(DEFAULT_SIZE, 1, 1, SpriteSheet.tiles, 11, 16);
  public static final Sprite playerRight2 =
      new Sprite(DEFAULT_SIZE, 1, 2, SpriteSheet.tiles, 12, 16);

  public static final Sprite playerDead1 =
      new Sprite(DEFAULT_SIZE, 4, 2, SpriteSheet.tiles, 14, 16);
  public static final Sprite playerDead2 =
      new Sprite(DEFAULT_SIZE, 5, 2, SpriteSheet.tiles, 13, 15);
  public static final Sprite playerDead3 =
      new Sprite(DEFAULT_SIZE, 6, 2, SpriteSheet.tiles, 16, 16);

  /*
   * |-------------------------------------------------------------------------- | Character
   * |--------------------------------------------------------------------------
   */
  // BALLOM
  public static final Sprite balloomLeft1 =
      new Sprite(DEFAULT_SIZE, 9, 0, SpriteSheet.tiles, 16, 16);
  public static final Sprite balloomLeft2 =
      new Sprite(DEFAULT_SIZE, 9, 1, SpriteSheet.tiles, 16, 16);
  public static final Sprite balloomLeft3 =
      new Sprite(DEFAULT_SIZE, 9, 2, SpriteSheet.tiles, 16, 16);

  public static final Sprite balloomRight1 =
      new Sprite(DEFAULT_SIZE, 10, 0, SpriteSheet.tiles, 16, 16);
  public static final Sprite balloomRight2 =
      new Sprite(DEFAULT_SIZE, 10, 1, SpriteSheet.tiles, 16, 16);
  public static final Sprite balloomRight3 =
      new Sprite(DEFAULT_SIZE, 10, 2, SpriteSheet.tiles, 16, 16);

  public static final Sprite balloomDead =
      new Sprite(DEFAULT_SIZE, 9, 3, SpriteSheet.tiles, 16, 16);

  // ONEAL
  public static final Sprite onealLeft1 =
      new Sprite(DEFAULT_SIZE, 11, 0, SpriteSheet.tiles, 16, 16);
  public static final Sprite onealLeft2 =
      new Sprite(DEFAULT_SIZE, 11, 1, SpriteSheet.tiles, 16, 16);
  public static final Sprite onealLeft3 =
      new Sprite(DEFAULT_SIZE, 11, 2, SpriteSheet.tiles, 16, 16);

  public static final Sprite onealRight1 =
      new Sprite(DEFAULT_SIZE, 12, 0, SpriteSheet.tiles, 16, 16);
  public static final Sprite onealRight2 =
      new Sprite(DEFAULT_SIZE, 12, 1, SpriteSheet.tiles, 16, 16);
  public static final Sprite onealRight3 =
      new Sprite(DEFAULT_SIZE, 12, 2, SpriteSheet.tiles, 16, 16);

  public static final Sprite onealDead = new Sprite(DEFAULT_SIZE, 11, 3, SpriteSheet.tiles, 16, 16);

  // Doll
  public static final Sprite dollLeft1 = new Sprite(DEFAULT_SIZE, 13, 0, SpriteSheet.tiles, 16, 16);
  public static final Sprite dollLeft2 = new Sprite(DEFAULT_SIZE, 13, 1, SpriteSheet.tiles, 16, 16);
  public static final Sprite dollLeft3 = new Sprite(DEFAULT_SIZE, 13, 2, SpriteSheet.tiles, 16, 16);

  public static final Sprite dollRight1 =
      new Sprite(DEFAULT_SIZE, 14, 0, SpriteSheet.tiles, 16, 16);
  public static final Sprite dollRight2 =
      new Sprite(DEFAULT_SIZE, 14, 1, SpriteSheet.tiles, 16, 16);
  public static final Sprite dollRight3 =
      new Sprite(DEFAULT_SIZE, 14, 2, SpriteSheet.tiles, 16, 16);

  public static final Sprite dolllDead = new Sprite(DEFAULT_SIZE, 13, 3, SpriteSheet.tiles, 16, 16);

  // Minvo
  public static final Sprite minvoLeft1 = new Sprite(DEFAULT_SIZE, 8, 5, SpriteSheet.tiles, 16, 16);
  public static final Sprite minvoLeft2 = new Sprite(DEFAULT_SIZE, 8, 6, SpriteSheet.tiles, 16, 16);
  public static final Sprite minvoLeft3 = new Sprite(DEFAULT_SIZE, 8, 7, SpriteSheet.tiles, 16, 16);

  public static final Sprite minvoRight1 =
      new Sprite(DEFAULT_SIZE, 9, 5, SpriteSheet.tiles, 16, 16);
  public static final Sprite minvoRight2 =
      new Sprite(DEFAULT_SIZE, 9, 6, SpriteSheet.tiles, 16, 16);
  public static final Sprite minvoRight3 =
      new Sprite(DEFAULT_SIZE, 9, 7, SpriteSheet.tiles, 16, 16);

  public static final Sprite minvoDead = new Sprite(DEFAULT_SIZE, 8, 8, SpriteSheet.tiles, 16, 16);

  // Kondoria
  public static final Sprite kondoriaLeft1 =
      new Sprite(DEFAULT_SIZE, 10, 5, SpriteSheet.tiles, 16, 16);
  public static final Sprite kondoriaLeft2 =
      new Sprite(DEFAULT_SIZE, 10, 6, SpriteSheet.tiles, 16, 16);
  public static final Sprite kondoriaLeft3 =
      new Sprite(DEFAULT_SIZE, 10, 7, SpriteSheet.tiles, 16, 16);

  public static final Sprite kondoriaRight1 =
      new Sprite(DEFAULT_SIZE, 11, 5, SpriteSheet.tiles, 16, 16);
  public static final Sprite kondoriaRight2 =
      new Sprite(DEFAULT_SIZE, 11, 6, SpriteSheet.tiles, 16, 16);
  public static final Sprite kondoriaRight3 =
      new Sprite(DEFAULT_SIZE, 11, 7, SpriteSheet.tiles, 16, 16);

  public static final Sprite kondoriaDead =
      new Sprite(DEFAULT_SIZE, 10, 8, SpriteSheet.tiles, 16, 16);

  // ALL
  public static final Sprite mobDead11 = new Sprite(DEFAULT_SIZE, 15, 0, SpriteSheet.tiles, 16, 16);
  public static final Sprite mobDead12 = new Sprite(DEFAULT_SIZE, 15, 1, SpriteSheet.tiles, 16, 16);
  public static final Sprite mobDead13 = new Sprite(DEFAULT_SIZE, 15, 2, SpriteSheet.tiles, 16, 16);

  /*
   * |-------------------------------------------------------------------------- | Bomb Sprites
   * |--------------------------------------------------------------------------
   */
  public static final Sprite bomb = new Sprite(DEFAULT_SIZE, 0, 3, SpriteSheet.tiles, 15, 15);
  public static final Sprite bomb1 = new Sprite(DEFAULT_SIZE, 1, 3, SpriteSheet.tiles, 13, 15);
  public static final Sprite bomb2 = new Sprite(DEFAULT_SIZE, 2, 3, SpriteSheet.tiles, 12, 14);

  /*
   * |-------------------------------------------------------------------------- | FlameSegment
   * Sprites |--------------------------------------------------------------------------
   */
  public static final Sprite bombExploded =
      new Sprite(DEFAULT_SIZE, 0, 4, SpriteSheet.tiles, 16, 16);
  public static final Sprite bombExploded1 =
      new Sprite(DEFAULT_SIZE, 0, 5, SpriteSheet.tiles, 16, 16);
  public static final Sprite bombExploded2 =
      new Sprite(DEFAULT_SIZE, 0, 6, SpriteSheet.tiles, 16, 16);

  public static final Sprite explosionVertical =
      new Sprite(DEFAULT_SIZE, 1, 5, SpriteSheet.tiles, 16, 16);
  public static final Sprite explosionVertical1 =
      new Sprite(DEFAULT_SIZE, 2, 5, SpriteSheet.tiles, 16, 16);
  public static final Sprite explosionVertical2 =
      new Sprite(DEFAULT_SIZE, 3, 5, SpriteSheet.tiles, 16, 16);

  public static final Sprite explosionHorizontal =
      new Sprite(DEFAULT_SIZE, 1, 7, SpriteSheet.tiles, 16, 16);
  public static final Sprite explosionHorizontal1 =
      new Sprite(DEFAULT_SIZE, 1, 8, SpriteSheet.tiles, 16, 16);
  public static final Sprite explosionHorizontal2 =
      new Sprite(DEFAULT_SIZE, 1, 9, SpriteSheet.tiles, 16, 16);

  public static final Sprite explosionHorizontalLeftLast =
      new Sprite(DEFAULT_SIZE, 0, 7, SpriteSheet.tiles, 16, 16);
  public static final Sprite explosionHorizontalLeftLast1 =
      new Sprite(DEFAULT_SIZE, 0, 8, SpriteSheet.tiles, 16, 16);
  public static final Sprite explosionHorizontalLeftLast2 =
      new Sprite(DEFAULT_SIZE, 0, 9, SpriteSheet.tiles, 16, 16);

  public static final Sprite explosionHorizontalRightLast =
      new Sprite(DEFAULT_SIZE, 2, 7, SpriteSheet.tiles, 16, 16);
  public static final Sprite explosionHorizontalRightLast1 =
      new Sprite(DEFAULT_SIZE, 2, 8, SpriteSheet.tiles, 16, 16);
  public static final Sprite explosionHorizontalRightLast2 =
      new Sprite(DEFAULT_SIZE, 2, 9, SpriteSheet.tiles, 16, 16);

  public static final Sprite explosionHorizontalTopLast =
      new Sprite(DEFAULT_SIZE, 1, 4, SpriteSheet.tiles, 16, 16);
  public static final Sprite explosionHorizontalTopLast1 =
      new Sprite(DEFAULT_SIZE, 2, 4, SpriteSheet.tiles, 16, 16);
  public static final Sprite explosionHorizontalTopLast2 =
      new Sprite(DEFAULT_SIZE, 3, 4, SpriteSheet.tiles, 16, 16);

  public static final Sprite explosionHorizontalDownLast =
      new Sprite(DEFAULT_SIZE, 1, 6, SpriteSheet.tiles, 16, 16);
  public static final Sprite explosionHorizontalDownLast1 =
      new Sprite(DEFAULT_SIZE, 2, 6, SpriteSheet.tiles, 16, 16);
  public static final Sprite explosionHorizontalDownLast2 =
      new Sprite(DEFAULT_SIZE, 3, 6, SpriteSheet.tiles, 16, 16);

  /*
   * |-------------------------------------------------------------------------- | Brick
   * FlameSegment |--------------------------------------------------------------------------
   */
  public static final Sprite brickExploded =
      new Sprite(DEFAULT_SIZE, 7, 1, SpriteSheet.tiles, 16, 16);
  public static final Sprite brickExploded1 =
      new Sprite(DEFAULT_SIZE, 7, 2, SpriteSheet.tiles, 16, 16);
  public static final Sprite brickExploded2 =
      new Sprite(DEFAULT_SIZE, 7, 3, SpriteSheet.tiles, 16, 16);

  /*
   * |-------------------------------------------------------------------------- | Powerups
   * |--------------------------------------------------------------------------
   */
  public static final Sprite powerupBombs =
      new Sprite(DEFAULT_SIZE, 0, 10, SpriteSheet.tiles, 16, 16);
  public static final Sprite powerupFlames =
      new Sprite(DEFAULT_SIZE, 1, 10, SpriteSheet.tiles, 16, 16);
  public static final Sprite powerupSpeed =
      new Sprite(DEFAULT_SIZE, 2, 10, SpriteSheet.tiles, 16, 16);
  public static final Sprite powerupWallpass =
      new Sprite(DEFAULT_SIZE, 3, 10, SpriteSheet.tiles, 16, 16);
  public static final Sprite powerupDetonator =
      new Sprite(DEFAULT_SIZE, 4, 10, SpriteSheet.tiles, 16, 16);
  public static final Sprite powerupBombpass =
      new Sprite(DEFAULT_SIZE, 5, 10, SpriteSheet.tiles, 16, 16);
  public static final Sprite powerupFlamepass =
      new Sprite(DEFAULT_SIZE, 6, 10, SpriteSheet.tiles, 16, 16);

  /**
   * Generate sprite from image.
   * 
   * @param size Size of image
   * @param x x postion of sprite in image
   * @param y y position of sprite in image
   * @param sheet Location of that image
   * @param rw Width of the cut
   * @param rh Height of the cut
   */
  public Sprite(int size, int x, int y, SpriteSheet sheet, int rw, int rh) {
    this.size = size;
    pixels = new int[this.size * this.size];
    this.x = x * this.size;
    this.y = y * this.size;
    this.sheet = sheet;
    realWidth = rw;
    realHeight = rh;
    load();
  }

  /**
   * Generate sprite with color.
   */
  public Sprite(int size, int color) {
    this.size = size;
    pixels = new int[this.size * this.size];
    setColor(color);
  }

  private void setColor(int color) {
    for (int i = 0; i < pixels.length; i++) {
      pixels[i] = color;
    }
  }

  private void load() {
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        pixels[j + i * size] = sheet.pixels[(j + x) + (i + y) * sheet.size];
      }
    }
  }

  /**
   * Animate the sprite.
   * 
   * @param time Time of the animation
   * @return Final sprite at new position
   */
  public static final Sprite movingSprite(Sprite normal, Sprite x1, Sprite x2, int animate,
      int time) {
    int calc = animate % time;
    int diff = time / 3;

    if (calc < diff) {
      return normal;
    }

    if (calc < diff * 2) {
      return x1;
    }

    return x2;
  }

  public static final Sprite movingSprite(Sprite x1, Sprite x2, int animate, int time) {
    int diff = time / 2;
    return (animate % time > diff) ? x1 : x2;
  }

  public int getSize() {
    return size;
  }

  public int getPixel(int i) {
    return pixels[i];
  }

  /**
   * Get FX image.
   */
  public Image getFxImage() {
    WritableImage wr = new WritableImage(size, size);
    PixelWriter pw = wr.getPixelWriter();
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if (pixels[i + j * size] == TRANSPARENT_COLOR) {
          pw.setArgb(i, j, 0);
        } else {
          pw.setArgb(i, j, pixels[i + j * size]);
        }
      }
    }
    Image input = new ImageView(wr).getImage();
    return resample(input, SCALED_SIZE / DEFAULT_SIZE);
  }

  private Image resample(Image input, int scaleFactor) {
    final int W = (int) input.getWidth();
    final int H = (int) input.getHeight();
    final int S = scaleFactor;

    WritableImage output = new WritableImage(W * S, H * S);

    PixelReader reader = input.getPixelReader();
    PixelWriter writer = output.getPixelWriter();

    for (int i = 0; i < H; i++) {
      for (int j = 0; j < W; j++) {
        final int argb = reader.getArgb(j, i);
        for (int dy = 0; dy < S; dy++) {
          for (int dx = 0; dx < S; dx++) {
            writer.setArgb(j * S + dx, i * S + dy, argb);
          }
        }
      }
    }
    return output;
  }
}
