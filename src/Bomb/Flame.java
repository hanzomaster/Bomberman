package Bomb;

import entities.AnimationEntity;
import graphics.Sprite;

public class Flame extends AnimationEntity {
    private int direct; // 0 UP, 1 DOWN, 2 LEFT, 3 RIGHT, 4 CENTER
    private boolean last = false;

    public Flame(int xUnit, int yUnit, int direct, boolean last) {
        super(xUnit, yUnit, null);
        this.direct = direct;
        this.last = last;
    }

    @Override
    public void update() {
        animate();
        switch (direct) {
            case 0:
                if (last) {
                    setImg(Sprite
                            .movingSprite(Sprite.explosionVerticalTopLast,
                                    Sprite.explosionVerticalTopLast1,
                                    Sprite.explosionVerticalTopLast2, animation, timeTransfer)
                            .getFxImage());
                } else {
                    setImg(Sprite
                            .movingSprite(Sprite.explosionVertical, Sprite.explosionVertical1,
                                    Sprite.explosionVertical2, animation, timeTransfer)
                            .getFxImage());
                }
                break;
            case 1:
                if (last) {
                    setImg(Sprite
                            .movingSprite(Sprite.explosionVerticalDownLast,
                                    Sprite.explosionVerticalDownLast1,
                                    Sprite.explosionVerticalDownLast2, animation, timeTransfer)
                            .getFxImage());
                } else {
                    setImg(Sprite
                            .movingSprite(Sprite.explosionVertical, Sprite.explosionVertical1,
                                    Sprite.explosionVertical2, animation, timeTransfer)
                            .getFxImage());
                }
                break;
            case 2:
                if (last) {
                    setImg(Sprite
                            .movingSprite(Sprite.explosionHorizontalLeftLast,
                                    Sprite.explosionHorizontalLeftLast1,
                                    Sprite.explosionHorizontalLeftLast2, animation, timeTransfer)
                            .getFxImage());
                } else {
                    setImg(Sprite
                            .movingSprite(Sprite.explosionHorizontal, Sprite.explosionHorizontal1,
                                    Sprite.explosionHorizontal2, animation, timeTransfer)
                            .getFxImage());
                }
                break;
            case 3:
                if (last) {
                    setImg(Sprite
                            .movingSprite(Sprite.explosionHorizontalRightLast,
                                    Sprite.explosionHorizontalRightLast1,
                                    Sprite.explosionHorizontalRightLast2, animation, timeTransfer)
                            .getFxImage());
                } else {
                    setImg(Sprite
                            .movingSprite(Sprite.explosionHorizontal, Sprite.explosionHorizontal1,
                                    Sprite.explosionHorizontal2, animation, timeTransfer)
                            .getFxImage());
                }
                break;
            case 4:
                setImg(Sprite.movingSprite(Sprite.bombExploded, Sprite.bombExploded1,
                        Sprite.bombExploded2, animation, timeTransfer).getFxImage());
                break;
        }
    }
}
