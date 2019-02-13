package GameObjects;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;

public class Score {

    // TODO: 13/02/2019 make score non-static and accessible by Difficulty
    public static int score;
    private Text display = new Text(380, 20, "score");
    private int cooldown = 60;

    public Score() {
        display.setColor(Color.WHITE);
        display.grow(20,10);
        score = 0;
    }

    public void tick() {
        if(cooldown == 0) {
            score += 10;
            cooldown = 60;
        }

        cooldown--;
    }

    public void render() {
        display.setText("Score: " + score);
        //display.delete();
        display.draw();
    }

    public void setScore(int score) {
        this.score += score;
    }

    public int getScore() {
        return score;
    }
}
