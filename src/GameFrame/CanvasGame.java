package GameFrame;

import entities.Entity;
import javafx.scene.canvas.Canvas;

public class CanvasGame extends Canvas {
  private Game game = new Game();
  private KeyboardInput input = new KeyboardInput();

  /**
   * Create canvas.
   */
  public CanvasGame(double width, double height) {
    super(width, height);

    // Key Event
    this.requestFocus();
    this.setFocusTraversable(true);
    this.setOnKeyPressed(keyEvent -> input.updateKeyPressed(keyEvent));
    this.setOnKeyReleased(keyEvent -> input.updateKeyReleased(keyEvent));
  }

  public void update() {
    game.update();
  }

  public void render() {
    game.render(this);
  }

  public KeyboardInput getInput() {
    return input;
  }

  public Entity getEntityInCoodinate(int x, int y) {
    return game.getEntityInCoodinate(x, y);
  }

  public Entity getGrass(int x, int y) {
    return game.getGrass(x, y);
  }

  public Game getGame() {
    return game;
  }
}
