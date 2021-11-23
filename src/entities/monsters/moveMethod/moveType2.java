package entities.monsters.moveMethod;

public class moveType2 extends MonsterMovement {

  // public static boolean changeDirect = false;

  @Override
  public int setDirection(int d) {
    // Auto-generated method stub
    return randomdirect.nextInt(4);
  }

}
