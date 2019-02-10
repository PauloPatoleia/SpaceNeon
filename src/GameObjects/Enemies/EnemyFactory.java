package GameObjects.Enemies;

import GameObjects.Bullets;
import java.util.LinkedList;

public class EnemyFactory {


    public static Enemy getNewEnemy(LinkedList<Bullets> enemyBullets, String bulletImage, LinkedList<Enemy> enemyLinkedList) {

        int randomEnemy = (int) (Math.random() * 2);


        if (randomEnemy == 0) {

            int upperBoundary = 776;
            int randomEnemyNumber = (int) (Math.random() * (EnemyShooter.EnemyType.values()).length);

            switch (EnemyShooter.EnemyType.values()[randomEnemyNumber]) {
                case ENEMY_ONE:
                    int randomOne = (int) (Math.random() * upperBoundary);
                    enemyLinkedList.add(new EnemyShooter(randomOne, 0, EnemyShooter.EnemyType.ENEMY_ONE, enemyBullets, bulletImage));
                    break;
                case ENEMY_TWO:
                    int randomTwo = (int) (Math.random() * upperBoundary);
                    enemyLinkedList.add(new EnemyShooter(randomTwo, 0, EnemyShooter.EnemyType.ENEMY_TWO, enemyBullets, bulletImage));
                    break;

            }
        }

        if (randomEnemy == 1) {

            int randomObstacleNumber = (int) (Math.random() * (Obstacle.ObstacleType.values().length));

            switch (Obstacle.ObstacleType.values()[randomObstacleNumber]) {
                case OBSTACLE_ONE:
                    int upperBoundaryOne = 681;
                    int randomOne = (int) (Math.random() * upperBoundaryOne);
                    enemyLinkedList.add(new Obstacle(randomOne, 0, Obstacle.ObstacleType.OBSTACLE_ONE));
                    break;
                case OBSTACLE_TWO:
                    int upperBoundaryTwo = 661;
                    int randomTwo = (int) (Math.random() * upperBoundaryTwo);
                    enemyLinkedList.add(new Obstacle(randomTwo, 0, Obstacle.ObstacleType.OBSTACLE_TWO));
                    break;

            }
        }

        return null;
    }
}
