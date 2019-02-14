package GameObjects.Enemies;

import GameObjects.Bullets;
import java.util.LinkedList;

public class EnemyFactory {


    public static Enemy getNewEnemy(LinkedList<Bullets> enemyBullets, String bulletImage, LinkedList<Enemy> enemyLinkedList) {

        int randomEnemy = (int) (Math.random() * 3);


        if (randomEnemy <= 0) {

            int upperBoundary = 766;
            int randomEnemyShooterNumber = (int) (Math.random() * (EnemyShooter.EnemyType.values()).length);

            switch (EnemyShooter.EnemyType.values()[randomEnemyShooterNumber]) {
                case ENEMY_ONE:
                    int randomOne = (int) (Math.random() * upperBoundary + 10);
                    enemyLinkedList.add(new EnemyShooter(randomOne, 35, EnemyShooter.EnemyType.ENEMY_ONE, enemyBullets, bulletImage));
                    break;
                case ENEMY_TWO:
                    int randomTwo = (int) (Math.random() * upperBoundary + 10);
                    enemyLinkedList.add(new EnemyShooter(randomTwo, 35, EnemyShooter.EnemyType.ENEMY_TWO, enemyBullets, bulletImage));
                    break;

            }
        }

        if (randomEnemy == 1) {

            int randomObstacleNumber = (int) (Math.random() * (Obstacle.ObstacleType.values().length));

            switch (Obstacle.ObstacleType.values()[randomObstacleNumber]) {
                case OBSTACLE_ONE:
                    int upperBoundaryOne = 671;
                    int randomOne = (int) (Math.random() * upperBoundaryOne + 10);
                    enemyLinkedList.add(new Obstacle(randomOne, 35, Obstacle.ObstacleType.OBSTACLE_ONE));
                    break;
                case OBSTACLE_TWO:
                    int upperBoundaryTwo = 651;
                    int randomTwo = (int) (Math.random() * upperBoundaryTwo + 10);
                    enemyLinkedList.add(new Obstacle(randomTwo, 35, Obstacle.ObstacleType.OBSTACLE_TWO));
                    break;

            }
        }

        if (randomEnemy >= 2) {

            int randomEnemyDiamondNumber = (int) (Math.random() * (EnemyDiamond.EnemyType.values().length));

            int upperBoundary = 766;
            switch (EnemyDiamond.EnemyType.values()[randomEnemyDiamondNumber]) {
                case ENEMY_ONE:
                    int randomOne = (int) (Math.random() * upperBoundary + 10);
                    enemyLinkedList.add(new EnemyDiamond(randomOne, 35, EnemyDiamond.EnemyType.ENEMY_ONE, enemyBullets, bulletImage));
                    break;
                case ENEMY_TWO:
                    int randomTwo = (int) (Math.random() * upperBoundary + 10);
                    enemyLinkedList.add(new EnemyDiamond(randomTwo, 35, EnemyDiamond.EnemyType.ENEMY_TWO, enemyBullets, bulletImage));
                    break;

            }
        }

        return null;
    }
}
