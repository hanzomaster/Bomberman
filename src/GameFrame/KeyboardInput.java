package GameFrame;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyboardInput {

  public boolean up = false;
  public boolean down = false;
  public boolean left = false;
  public boolean right = false;
  public boolean space = false;
  public boolean pause = false;
  public boolean enter = false;
  public boolean backspace = false;
  public boolean release = false;// su dung de dieu huong lua chon trong menu game

  /**
   * Kiểm tra khi ấn key xuống.
   */
  public void updateKeyPressed(KeyEvent e) {
    if (e.getCode() == KeyCode.UP || e.getCode() == KeyCode.W) {
      up = true;
    }
    if (e.getCode() == KeyCode.DOWN || e.getCode() == KeyCode.S) {
      down = true;
    }
    if (e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.A) {
      left = true;
    }
    if (e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.D) {
      right = true;
    }
    if (e.getCode() == KeyCode.SPACE) {
      space = true;
    }
    if (e.getCode() == KeyCode.P) {
      pause = true;
    }
    if (e.getCode() == KeyCode.ENTER) {
      enter = true;
    }
    if (e.getCode() == KeyCode.BACK_SPACE) {
      backspace = true;
    }
    release = false;
  }

  /**
   * Kiểm tra khi thả key ra.
   */
  public void updateKeyReleased(KeyEvent e) {
    if (e.getCode() == KeyCode.UP || e.getCode() == KeyCode.W) {
      up = false;
    }
    if (e.getCode() == KeyCode.DOWN || e.getCode() == KeyCode.S) {
      down = false;
    }
    if (e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.A) {
      left = false;
    }
    if (e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.D) {
      right = false;
    }
    if (e.getCode() == KeyCode.SPACE) {
      space = false;
    }
    if (e.getCode() == KeyCode.ENTER) {
      enter = false;
    }
    if (e.getCode() == KeyCode.BACK_SPACE) {
      backspace = false;
    }
    release = true;
  }

  public void setRelease(boolean release) {
    this.release = release;
  }

}
