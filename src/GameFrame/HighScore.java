package GameFrame;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class HighScore {
  private static String url = "src/resources/highscore/highscore.txt";

  /**
   * Get high score.
   * 
   * @return high score.
   */
  public static String getHighScore() {
    try (FileInputStream fileInputStream = new FileInputStream(url);
        BufferedReader bufferedReader =
            new BufferedReader(new InputStreamReader(fileInputStream))) {
      String line = bufferedReader.readLine();
      // while (line != null) {
      // line = bufferedReader.readLine();
      return line;
      // }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "";
  }

  public static void setNewHighScore(int score) {
    int preHighscore = Integer.parseInt(HighScore.getHighScore());
    if (preHighscore < score) {
      try (FileWriter fileWriter = new FileWriter(url, false);
          BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
        bufferedWriter.write(String.valueOf(score));
        // bufferedWriter.newLine();
        bufferedWriter.flush();
      } catch (IOException e) {
        e.printStackTrace();
      }
      // return true;
    }
    // return false;
  }
}
