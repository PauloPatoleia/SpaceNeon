package GameObjects.Enemies;

import GameObjects.Bullets;
import GameObjects.Enemies.Enemy;
import GameObjects.Enemies.EnemyFactory;

import java.util.LinkedList;

public class EnemyGenerator {

    private LinkedList<Enemy> enemyList;
    private LinkedList<Bullets> enemyBullets;
    private int chanceMultiplier;

    public EnemyGenerator(LinkedList<Enemy> enemyList, LinkedList<Bullets> enemyBullets) {
        this.enemyList = enemyList;
        this.enemyBullets = enemyBullets;
        chanceMultiplier = 40;
    }

    public void tick() {

        int chance = (int) Math.floor(Math.random() * chanceMultiplier);

        if (chance == 1) {
            EnemyFactory.getNewEnemy(enemyBullets, "bullet_red_20x30.png", enemyList);
        }
    }

    public void increaseProbability (int increaseProbability) {

        chanceMultiplier -= increaseProbability;
    }

    public void resetProbability() {

        chanceMultiplier = 40;
    }

    public int getChanceMultiplier() {
        return chanceMultiplier;
    }
}
