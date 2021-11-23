package entities.monsters.moveMethod;

import java.util.Random;

public abstract class MonsterMovement {

  protected Random randomdirect = new Random();

  public abstract int setDirection(int d);
}
