package GameObjects.Enemies;

import GameObjects.Bullets;
import GameObjects.Enemies.Enemy;
import GameObjects.Enemies.EnemyFactory;

import java.util.LinkedList;

public class EnemyGenerator {

    private LinkedList<Enemy> enemyList;
    private LinkedList<Bullets> enemyBullets;

    public EnemyGenerator(LinkedList<Enemy> enemyList, LinkedList<Bullets> enemyBullets) {
        this.enemyList = enemyList;
        this.enemyBullets = enemyBullets;
    }

    public void tick() {

        int chance = (int) Math.floor(Math.random() * 50);

        if (chance == 1) {
            EnemyFactory.getNewEnemy(enemyBullets, "bullet_red_20x30.png", enemyList);
        }
    }

}
