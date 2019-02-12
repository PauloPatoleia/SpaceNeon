package GameObjects;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;

public class Score {

    private int score = 0;
    private Text display = new Text(380, 20, "score");
    private int cooldown = 60;

    public Score() {
        display.setColor(Color.WHITE);
        display.grow(20,10);
    }

    public void tick() {
        if(cooldown == 0) {
            score += 10;
            cooldown = 60;
        }

        cooldown--;
    }

    public void render() {
        display.setText("Score: "+ score);
        //display.delete();
        display.draw();
    }

    public void setScore(int score) {
        this.score += score;
    }
}
