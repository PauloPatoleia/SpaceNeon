import java.applet.AudioClip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class Main {

    public static void main(String[] args) {

        Sound sound = new Sound("music.wav");
        sound.play(true);
        sound.setLoop(5);

        try {
            Game game = new Game();
            game.start();
        } catch (Exception e) {
            System.out.println("O SIMPLE GRAPHICS É UM TUMOR DO MUNDO TECNOLOGICO");
        }



    }
}
