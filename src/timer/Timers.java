package timer;

import GameFrame.Game;
import GameMain.BombermanGame;
import java.util.Timer;
import java.util.TimerTask;

public class Timers {
  private boolean isPlaying = false;
  Timer timer = new Timer();
  private int interval;
  private static int delay = 1000; // 1s
  public static final int period = 1000; // 1s

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
          isPlaying = false;
        }
        if (isPlaying) {
          --interval;
        }
      }
    }, delay, period);
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