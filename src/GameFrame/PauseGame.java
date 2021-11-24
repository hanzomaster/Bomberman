package GameFrame;

import GameMain.BombermanGame;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PauseGame {
  private KeyboardInput keyboardInput;
  private int selecting;
  private int selected;

  /**
   * Constructor.
   * 
   * @param keyboardInput Accept keyboard input
   */
  public PauseGame(KeyboardInput keyboardInput) {
    this.keyboardInput = keyboardInput;
    selecting = 0;
    selected = -1;
  }

  /**
   * Render pause game.
   */
  public void showPauseGame(GraphicsContext gc) {
    String sound = BombermanGame.getMuted() ? "off" : "on";

    gc.setFill(Color.BLACK);
    gc.fillRect(0, 0, 992, 448);
    gc.setFont(Font.font("Impact", 40)); // Font
    gc.setFill(Color.WHITE);
    switch (selecting) {
      case 0:
        gc.fillText("Sound: " + sound, 350, 200);
        gc.fillText("Main Menu", 350, 260);
        gc.setFill(Color.RED);
        gc.fillText("Resume", 350, 140);
        break;
      case 1:
        gc.fillText("Resume", 350, 140);
        gc.fillText("Main Menu", 350, 260);
        gc.setFill(Color.RED);
        gc.fillText("Sound: " + sound, 350, 200);
        break;
      case 2:
        gc.fillText("Resume", 350, 140);
        gc.fillText("Sound: " + sound, 350, 200);
        gc.setFill(Color.RED);
        gc.fillText("Main Menu", 350, 260);
        break;
      default:
        break;
    }
  }

  /**
   * Update scene base on user keyboard input.
   */
  public void update() {
    if (!keyboardInput.release && keyboardInput.down && selecting < 2) {
      selecting++;
      keyboardInput.setRelease(true);
    } else if (!keyboardInput.release && keyboardInput.up && selecting > 0) {
      selecting--;
      keyboardInput.setRelease(true);
    }
    if ((keyboardInput.enter || keyboardInput.space) && !keyboardInput.release) {
      switch (selecting) {
        case 0:
          selected = 1;
          break;
        case 1:
          BombermanGame.setMuted(!BombermanGame.getMuted());
          break;
        case 2:
          selected = 2;
          break;
        default:
          break;
      }
      if (selecting == 2) {
        selecting = 0;
      }
      keyboardInput.setRelease(true);
    }
  }

  public int getSelected() {
    return selected;
  }

  public void setSelected(int selected) {
    this.selected = selected;
  }
}
