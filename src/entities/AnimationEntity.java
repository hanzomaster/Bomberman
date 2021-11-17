package entities;

import javafx.scene.image.Image;

public abstract class AnimationEntity extends Entity {

    protected int animate = 0;
    protected final int MAX_ANIMATE = 5000;

    protected int timeTransfer = 26;

    public AnimationEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        // TODO Auto-generated constructor stub
    }

    protected void animate() {
        if (animate > MAX_ANIMATE) {
            animate = 0;
        } else {
            animate += 1;
        }
    }
}


