package GameFrame;

import java.util.List;
import entities.Entity;
import entities.monsters.Monster;
import entities.player.Bomber;
import entities.stillobjects.Grass;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Game {
    public static String[] paths = {"res/levels/Level1.txt", "res/levels/Level2.txt",
            "res/levels/Level3.txt", "res/levels/Level4.txt", "res/levels/Level5.txt",
            "res/levels/Level6.txt", "res/levels/Level7.txt"};
    public int WIDTH, HEIGHT;
    public boolean pause = false;


    // list to render in canvas
    private List<Grass> grassList;
    private List<Entity> entityList; // list to check collision
    private List<Monster> enemyList;

    // bomber
    public static Bomber bomberman = new Bomber(1, 1, new KeyboardInput());
    public Bomber bomberInPreLevel = new Bomber(1, 1, new KeyboardInput());
    private Bomber originBomber;

    // level
    public Level level = new Level();
    private int currentLevel = 1;
    private int timeShowTransferLevel = 150;
    private boolean TransferLevel = false;

    private boolean gameOver = false;
    private boolean returnMainMenu = false;


    public Game() {}

    public void createNewGame() {
        gameOver = false;
        currentLevel = 1;
        bomberman = new Bomber(1, 1, new KeyboardInput());
        createMap();
        updateEnemy(bomberman);
    }

    private void updateEnemy(Bomber bomberman) {}

    public void createMap() {}


    public void update() {

    }

    public void updateAllEntities() {}

    public void render(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());


    }


    public void setGrassList(List<Grass> grassList) {
        this.grassList = grassList;
    }

    public void addEntity(Entity e) {
        entityList.add(e);
    }

    public void setTransferLevel(boolean transferLevel) {
        TransferLevel = transferLevel;
    }


}
