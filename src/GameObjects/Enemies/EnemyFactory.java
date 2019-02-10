package GameObjects.Enemies;

public class EnemyFactory {

    public static Enemy getNewEnemy() {

        int randomEnemy = (int) (Math.random() * 2);


        if (randomEnemy == 0) {

            int upperBoundary = 776;
            int randomEnemyNumber = (int) (Math.random() * (EnemyShooter.EnemyType.values()).length);

            switch (EnemyShooter.EnemyType.values()[randomEnemyNumber]) {
                case ENEMY_ONE:
                    int randomOne = (int) (Math.random() * upperBoundary);
                    return new EnemyShooter(randomOne, 0, EnemyShooter.EnemyType.ENEMY_ONE);
                case ENEMY_TWO:
                    int randomTwo = (int) (Math.random() * upperBoundary);
                    return new EnemyShooter(randomTwo, 0, EnemyShooter.EnemyType.ENEMY_TWO);

            }
        }

        if (randomEnemy == 1) {

            int randomObstacleNumber = (int) (Math.random() * (Obstacle.ObstacleType.values().length));

            switch (Obstacle.ObstacleType.values()[randomObstacleNumber]) {
                case OBSTACLE_ONE:
                    int upperBoundaryOne = 681;
                    int randomOne = (int) (Math.random() * upperBoundaryOne);
                    return new Obstacle(randomOne, 0, Obstacle.ObstacleType.OBSTACLE_ONE);
                case OBSTACLE_TWO:
                    int upperBoundaryTwo = 661;
                    int randomTwo = (int) (Math.random() * upperBoundaryTwo);
                    return new Obstacle(randomTwo, 0, Obstacle.ObstacleType.OBSTACLE_TWO);

            }
        }

        return null;
    }
}
