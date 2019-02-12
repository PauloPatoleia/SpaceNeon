import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;

public class FramesPerSecond {

    private int fps;
    private Text display = new Text(25, 25, "fps");

    public FramesPerSecond() {
        display.setColor(Color.WHITE);
    }

    public void tick() {

    }

    public void render() {
        display.setText(fps + " fps");
        //display.delete();
        display.draw();
    }

    public void setFps(int fps) {
        this.fps = fps;
    }
}
