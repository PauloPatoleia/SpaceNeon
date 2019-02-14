import GameObjects.Bullets;
import GameObjects.Collectibles.PowerUpGenerator;
import GameObjects.Enemies.*;
import GameObjects.Score;

import java.util.LinkedList;

public class Difficulty {

    private EnemyGenerator enemyGenerator;
    private PowerUpGenerator powerUpGenerator;
    private Score score;
    private double levelIncrease = 500;
    private double currentDifficulty = 0;

    public Difficulty(Score score, EnemyGenerator enemyGenerator, PowerUpGenerator powerUpGenerator, LinkedList<Enemy> enemyLinkedList) {

        this.score = score;
        this.enemyGenerator = enemyGenerator;
        this.powerUpGenerator = powerUpGenerator;
    }


    public void tick() {

        /*
        // TODO: 13/02/2019 Make score non static and accessible by getScore()
        int level = (int) Math.floor(Score.score / levelIncrease);
        if (currentDifficulty != level) {

            currentDifficulty = level;
            powerUpGenerator.decreaseProbability(5);
            if (enemyGenerator.getChanceMultiplier() > 5) {
                enemyGenerator.increaseProbability(5);
            }
            Bullets.BulletType.increaseSpeed(1);
            EnemyShooter.EnemyType.increaseSpeed(1);
            Obstacle.ObstacleType.increaseSpeed(1);
        }

    }

    public void reset() {

        currentDifficulty = 0;
        powerUpGenerator.resetProbability();
        enemyGenerator.resetProbability();
        Bullets.BulletType.resetSpeed();
        EnemyShooter.EnemyType.resetSpeed();
        Obstacle.ObstacleType.resetSpeed();
    }
    */

    }
}
