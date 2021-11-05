package graphics;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

/**
 * Lưu trữ thông tin các pixel của 1 sprite (hình ảnh game)
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
  public static Sprite grass = new Sprite(DEFAULT_SIZE, 6, 0, SpriteSheet.tiles, 16, 16);
  public static Sprite brick = new Sprite(DEFAULT_SIZE, 7, 0, SpriteSheet.tiles, 16, 16);
  public static Sprite wall = new Sprite(DEFAULT_SIZE, 5, 0, SpriteSheet.tiles, 16, 16);
  public static Sprite portal = new Sprite(DEFAULT_SIZE, 4, 0, SpriteSheet.tiles, 14, 14);

  /*
   * |-------------------------------------------------------------------------- | Bomber Sprites
   * |--------------------------------------------------------------------------
   */
  public static Sprite playerUp = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles, 12, 16);
  public static Sprite playerDown = new Sprite(DEFAULT_SIZE, 2, 0, SpriteSheet.tiles, 12, 15);
  public static Sprite playerLeft = new Sprite(DEFAULT_SIZE, 3, 0, SpriteSheet.tiles, 10, 15);
  public static Sprite playerRight = new Sprite(DEFAULT_SIZE, 1, 0, SpriteSheet.tiles, 10, 16);

  public static Sprite playerUp1 = new Sprite(DEFAULT_SIZE, 0, 1, SpriteSheet.tiles, 12, 16);
  public static Sprite playerUp2 = new Sprite(DEFAULT_SIZE, 0, 2, SpriteSheet.tiles, 12, 15);

  public static Sprite playerDown1 = new Sprite(DEFAULT_SIZE, 2, 1, SpriteSheet.tiles, 12, 15);
  public static Sprite playerDown2 = new Sprite(DEFAULT_SIZE, 2, 2, SpriteSheet.tiles, 12, 16);

  public static Sprite playerLeft1 = new Sprite(DEFAULT_SIZE, 3, 1, SpriteSheet.tiles, 11, 16);
  public static Sprite playerLeft2 = new Sprite(DEFAULT_SIZE, 3, 2, SpriteSheet.tiles, 12, 16);

  public static Sprite playerRight1 = new Sprite(DEFAULT_SIZE, 1, 1, SpriteSheet.tiles, 11, 16);
  public static Sprite playerRight2 = new Sprite(DEFAULT_SIZE, 1, 2, SpriteSheet.tiles, 12, 16);

  public static Sprite playerDead1 = new Sprite(DEFAULT_SIZE, 4, 2, SpriteSheet.tiles, 14, 16);
  public static Sprite playerDead2 = new Sprite(DEFAULT_SIZE, 5, 2, SpriteSheet.tiles, 13, 15);
  public static Sprite playerDead3 = new Sprite(DEFAULT_SIZE, 6, 2, SpriteSheet.tiles, 16, 16);

  /*
   * |-------------------------------------------------------------------------- | Character
   * |--------------------------------------------------------------------------
   */
  // BALLOM
  public static Sprite balloomLeft1 = new Sprite(DEFAULT_SIZE, 9, 0, SpriteSheet.tiles, 16, 16);
  public static Sprite balloomLeft2 = new Sprite(DEFAULT_SIZE, 9, 1, SpriteSheet.tiles, 16, 16);
  public static Sprite balloomLeft3 = new Sprite(DEFAULT_SIZE, 9, 2, SpriteSheet.tiles, 16, 16);

  public static Sprite balloomRight1 = new Sprite(DEFAULT_SIZE, 10, 0, SpriteSheet.tiles, 16, 16);
  public static Sprite balloomRight2 = new Sprite(DEFAULT_SIZE, 10, 1, SpriteSheet.tiles, 16, 16);
  public static Sprite balloomRight3 = new Sprite(DEFAULT_SIZE, 10, 2, SpriteSheet.tiles, 16, 16);

  public static Sprite balloomDead = new Sprite(DEFAULT_SIZE, 9, 3, SpriteSheet.tiles, 16, 16);

  // ONEAL
  public static Sprite onealLeft1 = new Sprite(DEFAULT_SIZE, 11, 0, SpriteSheet.tiles, 16, 16);
  public static Sprite onealLeft2 = new Sprite(DEFAULT_SIZE, 11, 1, SpriteSheet.tiles, 16, 16);
  public static Sprite onealLeft3 = new Sprite(DEFAULT_SIZE, 11, 2, SpriteSheet.tiles, 16, 16);

  public static Sprite onealRight1 = new Sprite(DEFAULT_SIZE, 12, 0, SpriteSheet.tiles, 16, 16);
  public static Sprite onealRight2 = new Sprite(DEFAULT_SIZE, 12, 1, SpriteSheet.tiles, 16, 16);
  public static Sprite onealRight3 = new Sprite(DEFAULT_SIZE, 12, 2, SpriteSheet.tiles, 16, 16);

  public static Sprite onealDead = new Sprite(DEFAULT_SIZE, 11, 3, SpriteSheet.tiles, 16, 16);

  // Doll
  public static Sprite dollLeft1 = new Sprite(DEFAULT_SIZE, 13, 0, SpriteSheet.tiles, 16, 16);
  public static Sprite dollLeft2 = new Sprite(DEFAULT_SIZE, 13, 1, SpriteSheet.tiles, 16, 16);
  public static Sprite dollLeft3 = new Sprite(DEFAULT_SIZE, 13, 2, SpriteSheet.tiles, 16, 16);

  public static Sprite dollRight1 = new Sprite(DEFAULT_SIZE, 14, 0, SpriteSheet.tiles, 16, 16);
  public static Sprite dollRight2 = new Sprite(DEFAULT_SIZE, 14, 1, SpriteSheet.tiles, 16, 16);
  public static Sprite dollRight3 = new Sprite(DEFAULT_SIZE, 14, 2, SpriteSheet.tiles, 16, 16);

  public static Sprite dolllDead = new Sprite(DEFAULT_SIZE, 13, 3, SpriteSheet.tiles, 16, 16);

  // Minvo
  public static Sprite minvoLeft1 = new Sprite(DEFAULT_SIZE, 8, 5, SpriteSheet.tiles, 16, 16);
  public static Sprite minvoLeft2 = new Sprite(DEFAULT_SIZE, 8, 6, SpriteSheet.tiles, 16, 16);
  public static Sprite minvoLeft3 = new Sprite(DEFAULT_SIZE, 8, 7, SpriteSheet.tiles, 16, 16);

  public static Sprite minvoRight1 = new Sprite(DEFAULT_SIZE, 9, 5, SpriteSheet.tiles, 16, 16);
  public static Sprite minvoRight2 = new Sprite(DEFAULT_SIZE, 9, 6, SpriteSheet.tiles, 16, 16);
  public static Sprite minvoRight3 = new Sprite(DEFAULT_SIZE, 9, 7, SpriteSheet.tiles, 16, 16);

  public static Sprite minvoDead = new Sprite(DEFAULT_SIZE, 8, 8, SpriteSheet.tiles, 16, 16);

  // Kondoria
  public static Sprite kondoriaLeft1 = new Sprite(DEFAULT_SIZE, 10, 5, SpriteSheet.tiles, 16, 16);
  public static Sprite kondoriaLeft2 = new Sprite(DEFAULT_SIZE, 10, 6, SpriteSheet.tiles, 16, 16);
  public static Sprite kondoriaLeft3 = new Sprite(DEFAULT_SIZE, 10, 7, SpriteSheet.tiles, 16, 16);

  public static Sprite kondoriaRight1 = new Sprite(DEFAULT_SIZE, 11, 5, SpriteSheet.tiles, 16, 16);
  public static Sprite kondoriaRight2 = new Sprite(DEFAULT_SIZE, 11, 6, SpriteSheet.tiles, 16, 16);
  public static Sprite kondoriaRight3 = new Sprite(DEFAULT_SIZE, 11, 7, SpriteSheet.tiles, 16, 16);

  public static Sprite kondoriaDead = new Sprite(DEFAULT_SIZE, 10, 8, SpriteSheet.tiles, 16, 16);

  // ALL
  public static Sprite mobDead11 = new Sprite(DEFAULT_SIZE, 15, 0, SpriteSheet.tiles, 16, 16);
  public static Sprite mobDead12 = new Sprite(DEFAULT_SIZE, 15, 1, SpriteSheet.tiles, 16, 16);
  public static Sprite mobDead13 = new Sprite(DEFAULT_SIZE, 15, 2, SpriteSheet.tiles, 16, 16);

  /*
   * |-------------------------------------------------------------------------- | Bomb Sprites
   * |--------------------------------------------------------------------------
   */
  public static Sprite bomb = new Sprite(DEFAULT_SIZE, 0, 3, SpriteSheet.tiles, 15, 15);
  public static Sprite bomb1 = new Sprite(DEFAULT_SIZE, 1, 3, SpriteSheet.tiles, 13, 15);
  public static Sprite bomb2 = new Sprite(DEFAULT_SIZE, 2, 3, SpriteSheet.tiles, 12, 14);

  /*
   * |-------------------------------------------------------------------------- | FlameSegment
   * Sprites |--------------------------------------------------------------------------
   */
  public static Sprite bombExploded = new Sprite(DEFAULT_SIZE, 0, 4, SpriteSheet.tiles, 16, 16);
  public static Sprite bombExploded1 = new Sprite(DEFAULT_SIZE, 0, 5, SpriteSheet.tiles, 16, 16);
  public static Sprite bombExploded2 = new Sprite(DEFAULT_SIZE, 0, 6, SpriteSheet.tiles, 16, 16);

  public static Sprite explosionVertical =
      new Sprite(DEFAULT_SIZE, 1, 5, SpriteSheet.tiles, 16, 16);
  public static Sprite explosionVertical1 =
      new Sprite(DEFAULT_SIZE, 2, 5, SpriteSheet.tiles, 16, 16);
  public static Sprite explosionVertical2 =
      new Sprite(DEFAULT_SIZE, 3, 5, SpriteSheet.tiles, 16, 16);

  public static Sprite explosionHorizontal =
      new Sprite(DEFAULT_SIZE, 1, 7, SpriteSheet.tiles, 16, 16);
  public static Sprite explosionHorizontal1 =
      new Sprite(DEFAULT_SIZE, 1, 8, SpriteSheet.tiles, 16, 16);
  public static Sprite explosionHorizontal2 =
      new Sprite(DEFAULT_SIZE, 1, 9, SpriteSheet.tiles, 16, 16);

  public static Sprite explosionHorizontalLeftLast =
      new Sprite(DEFAULT_SIZE, 0, 7, SpriteSheet.tiles, 16, 16);
  public static Sprite explosionHorizontalLeftLast1 =
      new Sprite(DEFAULT_SIZE, 0, 8, SpriteSheet.tiles, 16, 16);
  public static Sprite explosionHorizontalLeftLast2 =
      new Sprite(DEFAULT_SIZE, 0, 9, SpriteSheet.tiles, 16, 16);

  public static Sprite explosionHorizontalRightLast =
      new Sprite(DEFAULT_SIZE, 2, 7, SpriteSheet.tiles, 16, 16);
  public static Sprite explosionHorizontalRightLast1 =
      new Sprite(DEFAULT_SIZE, 2, 8, SpriteSheet.tiles, 16, 16);
  public static Sprite explosionHorizontalRightLast2 =
      new Sprite(DEFAULT_SIZE, 2, 9, SpriteSheet.tiles, 16, 16);

  public static Sprite explosionHorizontalTopLast =
      new Sprite(DEFAULT_SIZE, 1, 4, SpriteSheet.tiles, 16, 16);
  public static Sprite explosionHorizontalTopLast1 =
      new Sprite(DEFAULT_SIZE, 2, 4, SpriteSheet.tiles, 16, 16);
  public static Sprite explosionHorizontalTopLast2 =
      new Sprite(DEFAULT_SIZE, 3, 4, SpriteSheet.tiles, 16, 16);

  public static Sprite explosionHorizontalDownLast =
      new Sprite(DEFAULT_SIZE, 1, 6, SpriteSheet.tiles, 16, 16);
  public static Sprite explosionHorizontalDownLast1 =
      new Sprite(DEFAULT_SIZE, 2, 6, SpriteSheet.tiles, 16, 16);
  public static Sprite explosionHorizontalDownLast2 =
      new Sprite(DEFAULT_SIZE, 3, 6, SpriteSheet.tiles, 16, 16);

  /*
   * |-------------------------------------------------------------------------- | Brick
   * FlameSegment |--------------------------------------------------------------------------
   */
  public static Sprite brickExploded = new Sprite(DEFAULT_SIZE, 7, 1, SpriteSheet.tiles, 16, 16);
  public static Sprite brickExploded1 = new Sprite(DEFAULT_SIZE, 7, 2, SpriteSheet.tiles, 16, 16);
  public static Sprite brickExploded2 = new Sprite(DEFAULT_SIZE, 7, 3, SpriteSheet.tiles, 16, 16);

  /*
   * |-------------------------------------------------------------------------- | Powerups
   * |--------------------------------------------------------------------------
   */
  public static Sprite powerupBombs = new Sprite(DEFAULT_SIZE, 0, 10, SpriteSheet.tiles, 16, 16);
  public static Sprite powerupFlames = new Sprite(DEFAULT_SIZE, 1, 10, SpriteSheet.tiles, 16, 16);
  public static Sprite powerupSpeed = new Sprite(DEFAULT_SIZE, 2, 10, SpriteSheet.tiles, 16, 16);
  public static Sprite powerupWallpass = new Sprite(DEFAULT_SIZE, 3, 10, SpriteSheet.tiles, 16, 16);
  public static Sprite powerupDetonator =
      new Sprite(DEFAULT_SIZE, 4, 10, SpriteSheet.tiles, 16, 16);
  public static Sprite powerupBombpass = new Sprite(DEFAULT_SIZE, 5, 10, SpriteSheet.tiles, 16, 16);
  public static Sprite powerupFlamepass =
      new Sprite(DEFAULT_SIZE, 6, 10, SpriteSheet.tiles, 16, 16);

  public Sprite(int size, int x, int y, SpriteSheet sheet, int rw, int rh) {
    this.size = size;
    this.pixels = new int[this.size * this.size];
    this.x = x * this.size;
    this.y = y * this.size;
    this.sheet = sheet;
    this.realWidth = rw;
    this.realHeight = rh;
    load();
  }

  public Sprite(int size, int color) {
    this.size = size;
    this.pixels = new int[this.size * this.size];
    setColor(color);
  }

  private void setColor(int color) {
    for (int i = 0; i < this.pixels.length; i++) {
      this.pixels[i] = color;
    }
  }

  private void load() {
    for (int i = 0; i < this.size; i++) {
      for (int j = 0; j < this.size; j++) {
        pixels[j + i * this.size] = sheet.pixels[(j + x) + (i + y) * sheet.size];
      }
    }
  }

  public static Sprite movingSprite(Sprite normal, Sprite x1, Sprite x2, int animate, int time) {
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

  public static Sprite movingSprite(Sprite x1, Sprite x2, int animate, int time) {
    int diff = time / 2;
    return (animate % time > diff) ? x1 : x2;
  }

  public int getSize() {
    return this.size;
  }

  public int getPixel(int i) {
    return this.pixels[i];
  }

  public Image getFxImage() {
    WritableImage wr = new WritableImage(this.size, this.size);
    PixelWriter pw = wr.getPixelWriter();
    for (int i = 0; i < this.size; i++) {
      for (int j = 0; j < this.size; j++) {
        if (this.pixels[i + j * this.size] == TRANSPARENT_COLOR) {
          pw.setArgb(i, j, 0);
        } else {
          pw.setArgb(i, j, this.pixels[i + j * this.size]);
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

    for (int j = 0; j < H; j++) {
      for (int i = 0; i < W; i++) {
        final int argb = reader.getArgb(i, j);
        for (int dy = 0; dy < S; dy++) {
          for (int dx = 0; dx < S; dx++) {
            writer.setArgb(i * S + dx, j * S + dy, argb);
          }
        }
      }
    }

    return output;
  }
}
