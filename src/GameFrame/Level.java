package GameFrame;

import entities.Bomber;
import entities.Entity;
import entities.monsters.Balloon;
import entities.monsters.Doll;
import entities.monsters.Kondoria;
import entities.monsters.Minvo;
import entities.monsters.Monster;
import entities.monsters.Oneal;
import entities.stillobjects.Brick;
import entities.stillobjects.Grass;
import entities.stillobjects.Wall;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.java.Log;
import powerups.BombPassItem;
import powerups.BrickPassItem;
import powerups.FlamePassItem;
import powerups.PlusBombItem;
import powerups.PlusFlameItem;
import powerups.PlusLiveItem;
import powerups.PlusSpeedItem;

@Log
public class Level {
  private int width;
  private int height;
  private List<Entity> collidableEntities = new ArrayList<>();
  private List<Grass> grasses = new ArrayList<>();
  private List<Monster> monsters = new ArrayList<>();
  private Bomber bomber;

  /**
   * Create map for each level.
   * 
   * @param path Path to each level
   * @param level Level
   */
  public void createMapLevel(String path, int level) {
    try (FileReader fileReader = new FileReader(path);
        BufferedReader br = new BufferedReader(fileReader)) {
      String mapInfo = br.readLine();
      height = Integer.parseInt(mapInfo.substring(2, 4));
      width = Integer.parseInt(mapInfo.substring(5));

      for (int i = 0; i < height; i++) {
        String temp = br.readLine();
        Entity object;
        for (int j = 0; j < temp.length(); j++) {
          object = new Grass(j, i, level);
          grasses.add((Grass) object);

          switch (temp.charAt(j)) {
            case '#':
              object = new Wall(j, i, level);
              collidableEntities.add(object);
              break;
            case '*':
              object = new Brick(j, i, level);
              collidableEntities.add(object);
              break;
            case 'x':
              Brick object2 = new Brick(j, i, level);
              object2.setBrickHasPortal(true);
              collidableEntities.add(object2);
              break;
            case 'p':
              bomber = new Bomber(j, i, new KeyboardInput());
              break;
            case '1':
              object = new Balloon(j, i);
              monsters.add((Balloon) object);
              break;
            case '2':
              object = new Oneal(j, i);
              monsters.add((Oneal) object);
              break;
            case '3':
              object = new Doll(j, i);
              monsters.add((Doll) object);
              break;
            case '4':
              object = new Minvo(j, i);
              monsters.add((Minvo) object);
              break;
            case '5':
              object = new Kondoria(j, i);
              monsters.add((Kondoria) object);
              break;
            case '6':
              object = new Dragon(j, i);
              monsters.add((Dragon) object);
              break;
            case 'b':
              object = new Brick(j, i, level);
              Item pbi = new PlusBombItem(j, i);

              ((Brick) object).setBrickHasItem(true, pbi);
              collidableEntities.add(object);

              pbi.setId("pbi");
              break;
            case 'f':
              object = new Brick(j, i, level);
              Item pfi = new PlusFlameItem(j, i);

              ((Brick) object).setBrickHasItem(true, pfi);
              collidableEntities.add(object);

              pfi.setId("pfi");
              break;
            case 's':
              object = new Brick(j, i, level);
              Item psi = new PlusSpeedItem(j, i);

              ((Brick) object).setBrickHasItem(true, psi);
              collidableEntities.add(object);

              psi.setId("psi");
              break;
            case 'B':
              object = new Brick(j, i, level);
              Item bpi = new BombPassItem(j, i);

              ((Brick) object).setBrickHasItem(true, bpi);
              collidableEntities.add(object);

              bpi.setId("bpi");
              break;
            case 'F':
              object = new Brick(j, i, level);
              Item fpi = new FlamePassItem(j, i);

              ((Brick) object).setBrickHasItem(true, fpi);
              collidableEntities.add(object);

              fpi.setId("fpi");
              break;
            case 'W':
              object = new Brick(j, i, level);
              Item wpi = new BrickPassItem(j, i);

              ((Brick) object).setBrickHasItem(true, wpi);
              collidableEntities.add(object);

              wpi.setId("wpi");
              break;
            case 'l':
              object = new Brick(j, i, level);
              Item pli = new PlusLiveItem(j, i);

              ((Brick) object).setBrickHasItem(true, pli);
              collidableEntities.add(object);

              pli.setId("pli");
              break;
            default:
              break;
          }
        }
      }
    } catch (Exception e) {
      log.severe("Can't generate map");
      e.printStackTrace();
    }
  }
}
