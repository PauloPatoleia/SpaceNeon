import java.applet.AudioClip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class Main {

    public static void main(String[] args) {
/*
        new Thread(new Runnable() {

            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                            Main.class.getResourceAsStream("sound.wav"));
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();



*/

        try {
            Game game = new Game();
            game.start();
        } catch (Exception e) {
            System.out.println("O SIMPLE GRAPHICS É UM TUMOR DO MUNDO TECNOLOGICO");
        }



    }
}
