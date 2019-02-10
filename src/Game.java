import GameObjects.Bullets;
import GameObjects.Enemies.Enemy;
import GameObjects.Enemies.EnemyFactory;
import GameObjects.Enemies.EnemyShooter;
import GameObjects.SpaceShip;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.LinkedList;

public class Game {


    private Player playerOne;
    private Player playerTwo;
    private boolean playing = true;
    private boolean paused = false;
    private LinkedList<Bullets> friendlyBullets = new LinkedList<>();
    private LinkedList<Bullets> enemyBullets = new LinkedList<>();
    private SpaceShip ship1 = new SpaceShip(400, 380, friendlyBullets, "./Resources/spaceshipblue.png", "./Resources/bulletblue.png");
    private SpaceShip ship2 = new SpaceShip(200, 380, friendlyBullets, "./Resources/spaceshipgreen.png", "./Resources/bulletgreen.png");
    private LinkedList<Enemy> enemies = new LinkedList<>();
    private Picture topBar = new Picture(10, 10, "./Resources/upbar.png");
    private Picture bottomBar = new Picture(10, 770, "./Resources/bottombar.png");


    public Game() {


        playerOne = new Player(KeyboardEvent.KEY_UP, KeyboardEvent.KEY_DOWN, KeyboardEvent.KEY_LEFT, KeyboardEvent.KEY_RIGHT, KeyboardEvent.KEY_SPACE, ship1);
        playerTwo = new Player(KeyboardEvent.KEY_W, KeyboardEvent.KEY_S, KeyboardEvent.KEY_A, KeyboardEvent.KEY_D, KeyboardEvent.KEY_T, ship2);


    }

    /**
     * Initializes window
     */
    public void init() {

        Rectangle rect = new Rectangle(10, 10, 800, 800);
        rect.setColor(Color.BLACK);
        rect.fill();

        ship1.render();
        ship2.render();

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

        // Check FPS
        int updates = 0;
        //int frames = 0;
        long timer = System.currentTimeMillis();

        while (playing) {

            if (!paused) {

                long now = System.nanoTime();
                delta += (now - initialTime) / numberOfSeconds;
                initialTime = now;

                if (delta >= 1) {

                    int chance = (int) Math.floor(Math.random() * 100);

                    if (chance == 1) {
                        EnemyFactory.getNewEnemy(enemyBullets, "./Resources/bulletred.png", enemies);
                    }


                    tick();
                    render();
                    updates++;
                    delta--;
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


    /**
     * Responsable for calling every GameObjects.GameObject to action
     */
    private void tick() {

        ship1.tick();
        ship2.tick();


        for (int i = 0; i < enemies.size(); i++) {

            enemies.get(i).tick();

            // Out of bounds
            if (enemies.get(i).getEnemyImage().getY() >= 780) {
                enemies.get(i).getEnemyImage().delete();
                enemies.remove(enemies.get(i));
                i--;
                continue;
            }

            // Collision with ships
            if (ship1.getHitbox().intersects(enemies.get(i).getHitbox())) {

                ship1.hit();
                if (enemies.get(i) instanceof EnemyShooter) {

                    enemies.get(i).getEnemyImage().delete();
                    enemies.remove(enemies.get(i));
                    i = enemies.size();
                    continue;
                }
            }

            if (ship2.getHitbox().intersects(enemies.get(i).getHitbox())) {

                ship2.hit();
                if (enemies.get(i) instanceof EnemyShooter) {

                    enemies.get(i).getEnemyImage().delete();
                    enemies.remove(enemies.get(i));
                    i = enemies.size();
                    continue;
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

            if (enemyBullets.get(i).getHitbox().intersects(ship1.getHitbox())) {

                enemyBullets.get(i).bulletImage.delete();
                enemyBullets.remove(enemyBullets.get(i));
                i = enemyBullets.size();
                ship1.hit();
                continue;
            }

            if (enemyBullets.get(i).getHitbox().intersects(ship2.getHitbox())) {

                enemyBullets.get(i).bulletImage.delete();
                enemyBullets.remove(enemyBullets.get(i));
                i = enemyBullets.size();
                ship2.hit();
            }
        }
    }


    /**
     * Responsable for rendering everything to the screen
     */
    private void render() {

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
        topBar.delete();
        topBar.draw();
        bottomBar.delete();
        bottomBar.draw();
    }
}
