package GameFrame;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import GameMain.BombermanGame;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MenuGame {
  private boolean startGame = false;
  private boolean quit = false;

  private KeyboardInput keyboardInput;
  private int selecting = 0;
  private final int[] pointerCoordinate = {230, 280, 330, 380};

  public MenuGame(KeyboardInput keyboardInput) {
    this.keyboardInput = keyboardInput;
  }

  public void showMenu(GraphicsContext gc) {
    FileInputStream file;
    try {
      file = new FileInputStream("src/resources/textures/menubackground.png");
      final Image backgroundImage = new Image(file);
      file = new FileInputStream("src/resources/textures/menupointer.png");
      final Image pointer = new Image(file);
      gc.setFill(Color.WHITE);
      gc.clearRect(0, 0, 992, 448);
      gc.drawImage(backgroundImage, 0, 0);
      gc.drawImage(pointer, 350, pointerCoordinate[selecting]);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      System.out.println("MenuGame.showMenu()");
    }
    gc.setFont(Font.font("Impact", 30)); // Font
    // gc.setFont(Font.font("Lobster Regular", 60));
    gc.fillText("New Game", 400, 280);

    String sound = BombermanGame.getMuted() ? "off" : "on";
    gc.fillText("Sound : " + sound, 400, 330);

    gc.fillText("Quit", 400, 380);
  }

  public void update() {
    if (!keyboardInput.release && keyboardInput.down && selecting < 2) {
      selecting++;
      keyboardInput.setRelease(true);
    } else if (!keyboardInput.release && keyboardInput.up && selecting > 0) {
      selecting--;
      keyboardInput.setRelease(true);
    }
    if ((keyboardInput.space || keyboardInput.enter) && !keyboardInput.release) {
      switch (selecting) {
        case 0:
          startGame = true;
          break;
        case 1:
          BombermanGame.setMuted(!BombermanGame.getMuted());
          break;
        case 2:
          quit = true;
          break;
        default:
          break;
      }
      keyboardInput.setRelease(true);
    }
  }

  public boolean isStartGame() {
    return startGame;
  }

  public void setStartGame(boolean start) {
    startGame = start;
  }

  public boolean isMuted() {
    return BombermanGame.getMuted();
  }

  public boolean isQuit() {
    return quit;
  }
}