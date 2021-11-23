package entities.monsters.moveMethod;

public class moveType1 extends MonsterMovement {

  @Override
  public int setDirection(int d) {
    // TODO Auto-generated method stub
    if (d == 2) {
      return 3;
    } else {
      return 2;
    }
  }


}
