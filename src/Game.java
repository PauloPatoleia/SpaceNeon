import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;

public class Game {

    Player playerOne;
    Player playerTwo;
    private boolean playing = true;
    private boolean paused = false;

    SpaceShip ship1 = new SpaceShip(400, 380,"./Resources/rsz_arrow.png");
    SpaceShip ship2 = new SpaceShip(200, 380,"./Resources/rsz_arrow.png");

    public Game() {

        Player playerOne = new Player(KeyboardEvent.KEY_UP, KeyboardEvent.KEY_DOWN, KeyboardEvent.KEY_LEFT, KeyboardEvent.KEY_RIGHT, KeyboardEvent.KEY_SPACE, ship1);
        Player playerTwo = new Player(KeyboardEvent.KEY_W, KeyboardEvent.KEY_S, KeyboardEvent.KEY_A, KeyboardEvent.KEY_D, KeyboardEvent.KEY_C, ship2);

    }

    /**
     * Initializes the game
     */
    public void init() {


        Rectangle rect = new Rectangle(10, 10, 800, 800);
        rect.setColor(Color.BLACK);
        rect.fill();

        ship1.getImg().draw();
        ship2.getImg().draw();

    }

    public void start() {

        init();

        long initialTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double numberOfSeconds = 1000000000 / amountOfTicks;
        double delta = 0;

        // Check FPS
        int updates = 0;
        //int frames = 0;
        long timer = System.currentTimeMillis();

        while(playing) {

            if(!paused) {

                long now = System.nanoTime();
                delta += (now - initialTime) / numberOfSeconds;
                initialTime = now;

                if(delta >= 1) {
                    tick();
                    render();
                    updates++;
                    delta --;
                }



                //frames++;

                if (System.currentTimeMillis() - timer > 1000) {
                    timer += 1000;
                    System.out.println(updates + " FPS");
                    updates = 0;
                   // frames = 0;
                }
            }
        }
    }

    private void tick() {
        ship1.tick();
        ship2.tick();
    }

    private void render() {
        ship1.getImg().draw();
        ship2.getImg().draw();

    }
}