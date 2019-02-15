package GameObjects.Enemies;

import GameObjects.Bullets;
import GameObjects.Enemies.Enemy;
import GameObjects.Enemies.EnemyFactory;

import java.util.LinkedList;

public class EnemyGenerator {

    private LinkedList<Enemy> enemyList;
    private LinkedList<Bullets> enemyBullets;
    private int chanceMultiplier;
    private int timeGoneBy = 0;
    private int difficulty = 60;

    public EnemyGenerator(LinkedList<Enemy> enemyList, LinkedList<Bullets> enemyBullets) {
        this.enemyList = enemyList;
        this.enemyBullets = enemyBullets;
        chanceMultiplier = 40;
    }

    public void tick() {

        timeGoneBy += 1;

        int chance = (int) Math.floor(Math.random() * difficulty - (timeGoneBy / 900));

        if (chance < 5 && enemyList.size() < 10 + (timeGoneBy / 900)) {
            EnemyFactory.getNewEnemy(enemyBullets, "bullet_red_20x30.png", enemyList);
        }
    }

    public void reset() {
        timeGoneBy = 0;
    }

    public int getChanceMultiplier() {
        return chanceMultiplier;
    }
}
