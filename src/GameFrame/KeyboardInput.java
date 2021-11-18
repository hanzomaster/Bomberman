package GameFrame;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyboardInput {

  public boolean up;
  public boolean down;
  public boolean left;
  public boolean right;
  public boolean space;
  public boolean pause;
  public boolean enter;
  public boolean backspace;
  public boolean release = false;// su dung de dieu huong lua chon trong menu game

  /**
   * Initialize all keyboard input as false.
   */
  public KeyboardInput() {
    up = false;
    down = false;
    left = false;
    right = false;
    space = false;
    pause = false;
    enter = false;
    backspace = false;
  }

  /**
   * Kiểm tra khi ấn key xuống.
   */
  public void updateKeyPressed(KeyEvent e) {
    if (e.getCode() == KeyCode.UP) {
      up = true;
    }
    if (e.getCode() == KeyCode.DOWN) {
      down = true;
    }
    if (e.getCode() == KeyCode.LEFT) {
      left = true;
    }
    if (e.getCode() == KeyCode.RIGHT) {
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
