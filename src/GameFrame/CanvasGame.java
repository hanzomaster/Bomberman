package GameFrame;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;

public class CanvasGame extends Canvas {

    private KeyboardInput input = new KeyboardInput();
    public static final String TITTLE = "Bomberman";

    public CanvasGame(double d, double e) {
        super(d, e);

        // key Event
        this.requestFocus();
        this.setFocusTraversable(true);
        this.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                input.updateKeyPressed(keyEvent);
            }
        });
        this.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                input.updateKeyReleased(keyEvent);
            }
        });
    }


    public void update() {}

    public void render() {}

    public KeyboardInput getInput() {
        return input;
    }

}
