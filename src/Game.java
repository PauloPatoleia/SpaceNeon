import GameObjects.Arrow;
import GameObjects.Bullets;
import GameObjects.Collectibles.PowerUp;
import GameObjects.Collectibles.PowerUpGenerator;
import GameObjects.Enemies.Enemy;
import GameObjects.Enemies.EnemyGenerator;
import GameObjects.Score;
import GameObjects.SpaceShip;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.awt.*;
import java.util.LinkedList;

public class Game implements KeyboardHandler {


    private Player playerOne;
    private Player playerTwo;
    private boolean playing = true;
    private boolean paused = false;
    private boolean versus = false;
    private LinkedList<Bullets> friendlyBullets = new LinkedList<>();
    private LinkedList<Bullets> enemyBullets = new LinkedList<>();
    private LinkedList<PowerUp> powerUps = new LinkedList<>();

    private LinkedList<SpaceShip> spaceShips = new LinkedList<>();

    private Picture background;
    private Picture menu = new Picture(10, 10, "menu800.png");
    private Picture instructions = new Picture(10, 10, "instructions_800x800.png");
    private Picture pauseScreen = new Picture(10, 50, "pause_screen_800x720.png");
    private Arrow arrow = new Arrow();


    // REFACTOR
    private Score score = new Score();
    private STATE state = STATE.MENU;
    private LinkedList<Enemy> enemies = new LinkedList<>();
    private TopBar topBar;
    private BottomBar bottomBar;
    private EnemyGenerator enemyGenerator = new EnemyGenerator(enemies, enemyBullets);
    private PowerUpGenerator powerUpGenerator = new PowerUpGenerator(powerUps);
    private FramesPerSecond fps = new FramesPerSecond();
    private Difficulty difficulty = new Difficulty(score, enemyGenerator, powerUpGenerator, enemies);


    enum STATE {
        MENU,
        GAME,
        INSTRUCTIONS,
        PAUSE
    }


    public Game() {

        // Keyboard
        Keyboard k = new Keyboard(this);

        // Keyboard events
        KeyboardEvent PAUSE = new KeyboardEvent();
        PAUSE.setKey(KeyboardEvent.KEY_P);
        PAUSE.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(PAUSE);

        KeyboardEvent UP = new KeyboardEvent();
        UP.setKey(KeyboardEvent.KEY_UP);
        UP.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(UP);

        KeyboardEvent DOWN = new KeyboardEvent();
        DOWN.setKey(KeyboardEvent.KEY_DOWN);
        DOWN.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(DOWN);

        KeyboardEvent ENTER = new KeyboardEvent();
        ENTER.setKey(KeyboardEvent.KEY_ENTER);
        ENTER.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(ENTER);

        KeyboardEvent ESC = new KeyboardEvent();
        ESC.setKey(KeyboardEvent.KEY_ESC);
        ESC.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(ESC);

        KeyboardEvent MENU = new KeyboardEvent();
        MENU.setKey(KeyboardEvent.KEY_M);
        MENU.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(MENU);




    }

    /**
     * Initializes window
     */
    public void init() {

        background = new Picture(10, 10, "background_800x800.png");
        background.draw();
        topBar = new TopBar("top_bar_800x40.png");
        bottomBar = new BottomBar("bottom_bar_800x40.png");
        fps = new FramesPerSecond();
        score = new Score();
        menu.delete();
        arrow.delete();
        state = STATE.GAME;
    }


    /**
     * Starts the game
     */
    public void start() {

        long initialTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double numberOfSeconds = 1000000000 / amountOfTicks;
        double delta = 0;

        long timer = System.currentTimeMillis();
        int frames = 0;

        while (playing) {


            long now = System.nanoTime();
            delta += (now - initialTime) / numberOfSeconds;
            initialTime = now;

            if (delta >= 1) {

                if (state != STATE.PAUSE) {

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

        if (state == STATE.PAUSE) {
            return;
        }

        if (state == STATE.MENU || state == STATE.INSTRUCTIONS) {
            return;
        }

        if (spaceShips.size() == 0) {
            reset();
            state = STATE.MENU;
            return;
        }

        if (versus && spaceShips.size() == 1) {
            reset();
            state = STATE.MENU;
            versus = false;
            return;
        }

        if (!versus) {
            //enemyGenerator.tick();
            powerUpGenerator.tick();
            score.tick();
            difficulty.tick();


            //////////////////////////////////////////////////////////////////////////////////////////////

            // Friendly bullets out of bounds and collision with enemies
            for (int i = 0; i < friendlyBullets.size(); i++) {

                friendlyBullets.get(i).tick();

                if (friendlyBullets.get(i).getImgY() <= 40) {
                    friendlyBullets.get(i).bulletImage.delete();
                    friendlyBullets.remove(friendlyBullets.get(i));
                    i--;
                    continue;
                }

                for (int j = 0; j < enemies.size(); j++) {

                    if (friendlyBullets.get(i).getHitbox().intersects(enemies.get(j).getHitbox())) {

                        friendlyBullets.get(i).hit();
                        friendlyBullets.remove(friendlyBullets.get(i));
                        i--;

                        enemies.get(j).hit();

                        if (enemies.get(j).getHp() <= 0) {
                            enemies.remove(enemies.get(j));
                            score.setScore(50);
                            j--;
                        }
                        //end this loop
                        j = enemies.size();
                    }
                }

            }

            ///////////////////////////////////////////////////////////////////////////////////////


            // Enemy out of bounds
            for (int i = 0; i < enemies.size(); i++) {

                enemies.get(i).tick();

                // Out of bounds
                if (enemies.get(i).getEnemyImage().getY() >= 750) {
                    enemies.get(i).getEnemyImage().delete();
                    enemies.remove(enemies.get(i));

                    if (enemies.size() > 1) {
                        i--;
                    }
                }
            }


            //////////////////////////////////////////////////////////////////////////

            //powerUps out of bounds and collision with spaceships
            for (int i = 0; i < powerUps.size(); i++) {

                powerUps.get(i).tick();

                if (powerUps.get(i).getImgY() >= 745) {

                    powerUps.get(i).powerUpImage.delete();
                    powerUps.remove(powerUps.get(i));

                    i--;
                    continue;
                }

                for (int j = 0; j < spaceShips.size(); j++) {

                    if (spaceShips.get(j).getHitbox().intersects(powerUps.get(i).getHitbox())) {


                        spaceShips.get(j).powerUp(powerUps.get(i).getPowerUpType());
                        powerUps.get(i).hit();
                        powerUps.remove(powerUps.get(i));

                        //if it collides with one leave the for loop
                        i--;
                        j = spaceShips.size();
                    }
                }

            }
        }
        //////////////////////////////////////////////////////////////////////////


        //enemyBullets out of bounds and collision with spaceships
        for (int i = 0; i < enemyBullets.size(); i++) {

            enemyBullets.get(i).tick();

            if (enemyBullets.get(i).getImgY() >= 750 || enemyBullets.get(i).getImgY() <= 40) {

                enemyBullets.get(i).bulletImage.delete();
                enemyBullets.remove(enemyBullets.get(i));
                i--;
                continue;
            }

            for (int j = 0; j < spaceShips.size(); j++) {

                if (spaceShips.get(j).getHitbox().intersects(enemyBullets.get(i).getHitbox())) {

                    spaceShips.get(j).hit();
                    enemyBullets.get(i).hit();
                    enemyBullets.remove(enemyBullets.get(i));

                    if (spaceShips.get(j).getHp() <= 0) {
                        spaceShips.remove(spaceShips.get(j));
                    }

                    //if it collides with one leave the for loop
                    i--;
                    j = spaceShips.size();
                }
            }
        }

        //////////////////////////////////////////////////////////////////////////////////////////////

        // SPACESHIPS WITH ENEMIES AND ENEMY BULLETS
        for (int i = 0; i < spaceShips.size(); i++) {
            spaceShips.get(i).tick();

            // WITH ENEMIES
            for (int j = 0; j < enemies.size(); j++) {
                if (spaceShips.get(i).getHitbox().intersects(enemies.get(j).getHitbox())) {

                    spaceShips.get(i).hit();
                    enemies.get(j).hit(20);
                    enemies.remove(enemies.get(j));

                    if (spaceShips.get(i).getHp() <= 0) {
                        spaceShips.remove(spaceShips.get(i));
                    }

                    //if it collides with one leave the for loop
                    j = enemies.size();
                }
            }
        }
    }


    /**
     * Responsable for rendering everything to the screen
     */
    private void render() {

        if (state == STATE.PAUSE) {

            return;
        }



        if (state == STATE.MENU) {

            menu.draw();
            arrow.render();
            return;

        }

        if (state == STATE.INSTRUCTIONS) {
            instructions.draw();
            return;
        }

        for (int i = 0; i < spaceShips.size(); i++) {
            spaceShips.get(i).render();
        }


        for (Enemy enemy : enemies) {
            enemy.render();
        }

        for (int i = 0; i < powerUps.size(); i++) {

            powerUps.get(i).render();
        }

        for (int i = 0; i < enemyBullets.size(); i++) {

            enemyBullets.get(i).render();
        }

        for (int i = 0; i < friendlyBullets.size(); i++) {

            friendlyBullets.get(i).render();
        }

        topBar.render();
        bottomBar.render();
        fps.render();
        score.render();


    }

    private void reset() {

        // TODO: 13/02/2019 iterate all linkedlist and .delete each item, then clear()


        spaceShips.clear();
        playerOne = null;
        playerTwo = null;
        friendlyBullets.clear();
        enemyBullets.clear();
        powerUps.clear();
        difficulty.reset();

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).getEnemyImage().delete();
        }
        enemies.clear();
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        // TODO: 11/02/2019 specify in what game state this controls exist

        if (state == STATE.GAME) {

            if (keyboardEvent.getKey() == KeyboardEvent.KEY_ESC) {
                pauseScreen.draw();
                state = STATE.PAUSE;
                return;
            }

        }

        if (state == STATE.MENU) {
            if (keyboardEvent.getKey() == 38) {
                arrow.moveUp();
                return;
            }
            if (keyboardEvent.getKey() == 40) {
                arrow.moveDown();
                return;
            }
            if (keyboardEvent.getKey() == KeyboardEvent.KEY_ENTER) {
                if (arrow.getY() == arrow.getSinglePlayerPosition()) {
                    initiateOnePlayer();
                    return;
                }
                if (arrow.getY() == arrow.getMultiPlayerPosition()) {
                    initiateTwoPlayers();
                    return;
                }
                if (arrow.getY() == arrow.getVersusPlayerPosition()) {
                    initiateVersusPlayer();
                    return;
                }
                if (arrow.getY() == arrow.getInstructionsPosition()) {
                    state = STATE.INSTRUCTIONS;
                    return;
                }
            }

        }

        if (state == STATE.INSTRUCTIONS) {
            if (keyboardEvent.getKey() == KeyboardEvent.KEY_ESC){
                instructions.delete();
                state = STATE.MENU;
                return;
            }
        }

        if (state == STATE.PAUSE) {

            if (keyboardEvent.getKey() == KeyboardEvent.KEY_M) {
                reset();
                pauseScreen.delete();
                state = STATE.MENU;
                return;

            }

            if (keyboardEvent.getKey() == KeyboardEvent.KEY_ESC) {
                pauseScreen.delete();
                state = STATE.GAME;
            }

        }
    }

    public void initiateOnePlayer() {

        spaceShips.add(new SpaceShip(370, 700, friendlyBullets, "spaceship_blue_30x30.png", "bullet_blue_20x30.png", "heart_blue_13x13.png", 50, Bullets.BulletType.NORMAL, 0));
        playerOne = new Player(KeyboardEvent.KEY_UP, KeyboardEvent.KEY_DOWN, KeyboardEvent.KEY_LEFT, KeyboardEvent.KEY_RIGHT, KeyboardEvent.KEY_SPACE, spaceShips.get(0));

        versus = false;
        init(); // TODO: 13/02/2019 same
    }

    public void initiateTwoPlayers() {

        // Start with 2 players
        spaceShips.add(new SpaceShip(250, 700, friendlyBullets, "spaceship_blue_30x30.png", "bullet_blue_20x30.png", "heart_blue_13x13.png", 50, Bullets.BulletType.NORMAL, 0));
        spaceShips.add(new SpaceShip(500, 700, friendlyBullets, "green_spaceship_30x30.png", "bullet_green_20x30.png", "heart_green_13x13.png", 730, Bullets.BulletType.NORMAL, 0));

        playerOne = new Player(KeyboardEvent.KEY_UP, KeyboardEvent.KEY_DOWN, KeyboardEvent.KEY_LEFT, KeyboardEvent.KEY_RIGHT, KeyboardEvent.KEY_SPACE, spaceShips.get(0));
        playerTwo = new Player(KeyboardEvent.KEY_W, KeyboardEvent.KEY_S, KeyboardEvent.KEY_A, KeyboardEvent.KEY_D, KeyboardEvent.KEY_T, spaceShips.get(1));

        versus = false;
        init(); // TODO: 13/02/2019 passar nr de player

    }


    public void initiateVersusPlayer() {

        //initiate versus mode
        spaceShips.add(new SpaceShip(300, 100, enemyBullets, "spaceship_blue_upside_30x30.png", "bullet_blue_20x30.png", "heart_blue_13x13.png", 50, Bullets.BulletType.VSTOP, 1));
        spaceShips.add(new SpaceShip(500, 700, enemyBullets, "green_spaceship_30x30.png", "bullet_green_20x30.png","heart_green_13x13.png", 730, Bullets.BulletType.VSBOTTOM, 2));

        playerOne = new Player(KeyboardEvent.KEY_UP, KeyboardEvent.KEY_DOWN, KeyboardEvent.KEY_LEFT, KeyboardEvent.KEY_RIGHT, KeyboardEvent.KEY_SPACE, spaceShips.get(0));
        playerTwo = new Player(KeyboardEvent.KEY_W, KeyboardEvent.KEY_S, KeyboardEvent.KEY_A, KeyboardEvent.KEY_D, KeyboardEvent.KEY_T, spaceShips.get(1));

        versus = true;
        init(); // TODO: 13/02/2019 passar nr de player
    }


    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }


}
