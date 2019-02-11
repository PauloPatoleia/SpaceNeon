import GameObjects.Bullets;
import GameObjects.Enemies.Enemy;
import GameObjects.Enemies.EnemyFactory;
import GameObjects.Enemies.EnemyGenerator;
import GameObjects.Enemies.EnemyShooter;
import GameObjects.SpaceShip;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.LinkedList;

public class Game implements KeyboardHandler {


    private Player playerOne;
    private Player playerTwo;
    private boolean playing = true;
    private boolean paused = false;
    private LinkedList<Bullets> friendlyBullets = new LinkedList<>();
    private LinkedList<Bullets> enemyBullets = new LinkedList<>();

    // TODO: 11/02/2019 MAKE A SHIP ARRAY
    private SpaceShip ship1 = new SpaceShip(400, 380, friendlyBullets, "spaceship_blue_30x30.png", "bullet_blue_20x30.png");
    private SpaceShip ship2 = new SpaceShip(200, 380, friendlyBullets, "green_spaceship_30x30.png", "bullet_green_20x30.png");


    private STATE state = STATE.MENU;
    private Rectangle rect = new Rectangle(10, 10, 800, 800);
    private Picture menu = new Picture(10,10, "menu_spaceneon_400x400.png");
    // TODO: 11/02/2019 change simple graphics to take more fonts and possibly more input keys

    // REFACTOR
    private LinkedList<Enemy> enemies = new LinkedList<>();
    private TopBar topBar = new TopBar("top_bar_800x40.png");
    private BottomBar bottomBar = new BottomBar("bottom_bar_800x40.png");
    private EnemyGenerator enemyGenerator = new EnemyGenerator(enemies, enemyBullets);
    private FramesPerSecond fps = new FramesPerSecond();

    enum STATE {
        MENU,
        GAME
    }


    public Game() {


        // TODO: 11/02/2019 MOVE THIS - dont create players before the ships
        playerOne = new Player(KeyboardEvent.KEY_UP, KeyboardEvent.KEY_DOWN, KeyboardEvent.KEY_LEFT, KeyboardEvent.KEY_RIGHT, KeyboardEvent.KEY_SPACE, ship1);
        playerTwo = new Player(KeyboardEvent.KEY_W, KeyboardEvent.KEY_S, KeyboardEvent.KEY_A, KeyboardEvent.KEY_D, KeyboardEvent.KEY_T, ship2);


    }

    /**
     * Initializes window
     */
    public void init() {

        // TODO: 11/02/2019 Add mouse events and other game essentials for menu etc

        // Keyboard
        Keyboard k = new Keyboard(this);

        // Keyboard events
        KeyboardEvent PAUSE = new KeyboardEvent();
        PAUSE.setKey(KeyboardEvent.KEY_P);
        PAUSE.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(PAUSE);

        KeyboardEvent START = new KeyboardEvent();
        START.setKey(KeyboardEvent.KEY_L);
        START.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(START);
    }


    /**
     * Starts the game
     */
    public void start() {

        init();

        long initialTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double numberOfSeconds = 1000000000 / amountOfTicks;
        double delta = 0;

        long timer = System.currentTimeMillis();
        int frames= 0;

        while (playing) {


                long now = System.nanoTime();
                delta += (now - initialTime) / numberOfSeconds;
                initialTime = now;

                if (delta >= 1) {

                    if (!paused) {

                        tick();
                        render();
                    }
                    frames++;
                    delta--;
                }


                if (System.currentTimeMillis() - timer > 1000) {
                    timer += 1000;
                    //System.out.println(updates + " FPS");
                    fps.setFps(frames);
                    frames = 0;
                }
            }
        }



    /**
     * Responsable for calling every GameObject to action
     */
    private void tick() {

        if(state == STATE.MENU) {
            return;
        }

        enemyGenerator.tick();

        // TODO: 11/02/2019 Organize tic into different classes
        int chance = (int) Math.floor(Math.random() * 100);

        if (chance == 1) {
            EnemyFactory.getNewEnemy(enemyBullets, "bullet_red_20x30.png", enemies);
        }

        // TODO: 11/02/2019 This will be an array
        ship1.tick();
        ship2.tick();


        for (int i = 0; i < enemies.size(); i++) {

            enemies.get(i).tick();

            // Out of bounds
            if (enemies.get(i).getEnemyImage().getY() >= 750) {
                enemies.get(i).getEnemyImage().delete();
                enemies.remove(enemies.get(i));
                i--;
                continue;
            }


            // TODO: 11/02/2019 use the ship array for this
            // Collision with ships
            if(ship1.getHp() > 0) {
                if (ship1.getHitbox().intersects(enemies.get(i).getHitbox())) {

                    ship1.hit();
                    if (enemies.get(i) instanceof EnemyShooter) {

                        enemies.get(i).getEnemyImage().delete();
                        enemies.remove(enemies.get(i));
                        i = enemies.size();
                        continue;
                    }
                }
            }

            if(ship2.getHp() > 0) {
                if (ship2.getHitbox().intersects(enemies.get(i).getHitbox())) {

                    ship2.hit();
                    if (enemies.get(i) instanceof EnemyShooter) {

                        enemies.get(i).getEnemyImage().delete();
                        enemies.remove(enemies.get(i));
                        i = enemies.size();
                        continue;
                    }
                }
            }


            // Collision with friendlyBullets
            for (int j = 0; j < friendlyBullets.size(); j++) {

                if (enemies.get(i).getHitbox().intersects(friendlyBullets.get(j).getHitbox())) {

                    if (enemies.get(i) instanceof EnemyShooter) {
                        enemies.get(i).getEnemyImage().delete();
                        enemies.remove(enemies.get(i));
                        i--;
                    }

                    friendlyBullets.get(j).bulletImage.delete();
                    friendlyBullets.remove(friendlyBullets.get(j));
                    j = friendlyBullets.size();
                }
            }
        }

        //friendlyBullets out of bounds
        for (int i = 0; i < friendlyBullets.size(); i++) {

            if (friendlyBullets.get(i).getImgY() <= 40) {
                friendlyBullets.get(i).bulletImage.delete();
                friendlyBullets.remove(friendlyBullets.get(i));
                i--;
                continue;
            }
            friendlyBullets.get(i).tick();
        }

        //enemyBullets out of bounds and collision with spaceships
        for (int i = 0; i < enemyBullets.size(); i++) {

            enemyBullets.get(i).tick();

            if (enemyBullets.get(i).getImgY() >= 745) {

                enemyBullets.get(i).bulletImage.delete();
                enemyBullets.remove(enemyBullets.get(i));
                i = enemyBullets.size();
                i--;
                continue;
            }

            // TODO: 11/02/2019 use the ship array for this
            if (ship1.getHp() > 0) {
                if (enemyBullets.get(i).getHitbox().intersects(ship1.getHitbox())) {

                    enemyBullets.get(i).bulletImage.delete();
                    enemyBullets.remove(enemyBullets.get(i));
                    i = enemyBullets.size();
                    ship1.hit();
                    continue;
                }
            }

            if (ship2.getHp() > 0) {
                if (enemyBullets.get(i).getHitbox().intersects(ship2.getHitbox())) {

                    enemyBullets.get(i).bulletImage.delete();
                    enemyBullets.remove(enemyBullets.get(i));
                    i = enemyBullets.size();
                    ship2.hit();
                }
            }
        }
    }


    /**
     * Responsable for rendering everything to the screen
     */
    private void render() {

        if(state == STATE.MENU) {

            menu.draw();
            return;

        }

        // TODO: 11/02/2019 use the ship array / controller
        ship1.render();
        ship2.render();


        for (Enemy enemy : enemies) {
            enemy.render();
        }

        for (int i = 0; i < enemyBullets.size(); i++) {

            enemyBullets.get(i).render();
        }

        for (int i = 0; i < friendlyBullets.size(); i++) {

            friendlyBullets.get(i).render();
        }

        // TODO: 11/02/2019 fix this bug
        topBar.render();
        bottomBar.render();
        //fps.render();


    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        // TODO: 11/02/2019 specify in what game state this controls exist



        // IF STATE = GAME
        if(keyboardEvent.getKey() == 80) {
            System.out.println(paused);
            paused = !paused;
        }

        //IF STATE = MENU
        if(keyboardEvent.getKey() == 76)
            state = STATE.GAME;
            menu.delete();
            rect.setColor(Color.BLACK);
            rect.fill();
    }


    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
