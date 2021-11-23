package sounds;

import GameMain.BombermanGame;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
  public static final String GAME_SOUND = "src/resources/sounds/inGame.wav";
  public static final String TRANSFER_LEVEL_SOUND = "src/resources/sounds/levelComplete.wav";
  public static final String EAT_POWERUP_SOUND = "src/resources/sounds/eatingItem.wav";
  public static final String MENU_SOUND = "src/resources/sounds/Title.wav";
  public static final String EXPLOSION_SOUND = "src/resources/sounds/explosion.wav";
  public static final String DEAD_SOUND = "src/resources/sounds/LifeLost.wav";
  public static final String LOSE_GAME_SOUND = "src/resources/sounds/gameOver.wav";
  public static final String WIN_GAME_SOUND = "src/resources/sounds/Victory.wav";
  public static final String MOVING_SOUND = "src/resources/sounds/moving.wav";
  public static final String PLACE_BOMB_SOUND = "src/resources/sounds/placeBomb.wav";

  private String path;
  private boolean isRunning = false;
  Clip clip;
  private long currentFrame;

  public enum Status {
    PLAY, STOP, PAUSE
  }

  private Status status;

  /**
   * Constructor for creating sound from path.
   */
  public Sound(String path) {
    this.path = path;
    File file = new File(path);
    try {
      AudioInputStream ais = AudioSystem.getAudioInputStream(file);
      clip = AudioSystem.getClip();
      clip.open(ais);
    } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
      e.printStackTrace();
      System.out.println("Sound.Sound()");
    }
  }

  /**
   * Play sound.
   */
  public void play() {
    if (isRunning || BombermanGame.getMuted()) {
      return;
    }
    clip.setFramePosition(0);
    if (path.equals(GAME_SOUND)) {
      FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
      gainControl.setValue(-20.0f);
      clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    clip.start();
    isRunning = true;
    status = Status.PLAY;
  }

  /**
   * Stop sound.
   */
  public void stop() {
    clip.stop();
    isRunning = false;
    status = Status.STOP;
  }

  /**
   * Pause sound.
   */
  public void pause() {
    clip.stop();
    isRunning = false;
    currentFrame = clip.getMicrosecondPosition();
    status = Status.PAUSE;
  }

  /**
   * Resume sound from pause.
   */
  public void resume() {
    if (status == Status.PAUSE) {
      if (isRunning || BombermanGame.getMuted()) {
        return;
      }
      clip.close();
      File file = new File(path);
      try {
        AudioInputStream ais = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(ais);
      } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
        e.printStackTrace();
        System.out.println("Sound.Sound()");
      }
      if (path.equals(GAME_SOUND)) {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
      }
      clip.setMicrosecondPosition(currentFrame);
      clip.start();
      isRunning = true;
      status = Status.PLAY;
    }
  }

  public boolean isRunning() {
    return isRunning;
  }

  public void setRunning(boolean running) {
    isRunning = running;
  }

  public Status getStatus() {
    return status;
  }
}
