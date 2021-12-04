package timer;

import java.util.Timer;
import java.util.TimerTask;
import GameFrame.Game;
import GameMain.BombermanGame;

public class Timers {
  private boolean isPlaying = false;
  Timer timer = new Timer();
  private int interval;
  private static int delay = 1000; // 1s
  private static final int PERIOD = 1000; // 1s

  private int check;

  /**
   * Display time.
   */
  public void setTime() {
    isPlaying = true;
    timer.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        if (interval <= 1) {
          timer.cancel();
          if (check == BombermanGame.timeLiving) {
            Game.bomberman.setAlive(false);
          }
          // BombermanGame.setLives(0);
          isPlaying = false;
        }
        if (isPlaying) {
          --interval;
        }
      }
    }, delay, PERIOD);
  }

  public boolean isPlaying() {
    return isPlaying;
  }

  public void setPlay(boolean play) {
    isPlaying = play;
  }

  public int getInterval() {
    return interval;
  }

  public void setInterval(int interval) {
    this.interval = interval;
    check = interval;
  }

  public static int getDelay() {
    return delay;
  }

  public static void setDelay(int newDelay) {
    delay = newDelay;
  }
}
