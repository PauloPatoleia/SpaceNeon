public class EnemyFactory {

    public static Enemy getNewEnemy() {

        int randomEnemy = (int) (Math.random() * 2);

        if (randomEnemy == 0) {

            int randomEnemyNumber = (int) (Math.random() * (EnemyType.values()).length);

            switch (EnemyType.values()[randomEnemyNumber]) {
                case ENEMY_ONE:
                    int randomOne = (int) (Math.random() * 701 + 50);
                    return new EnemyShooter(randomOne, 0, EnemyType.ENEMY_ONE);
                case ENEMY_TWO:
                    int randomTwo = (int) (Math.random() * 651 + 70);
                    return new EnemyShooter(randomTwo, 0, EnemyType.ENEMY_TWO);

            }
        }

        if (randomEnemy == 1) {

            int randomObstacleNumber = (int) (Math.random() * (ObstacleType.values().length));

            switch (ObstacleType.values()[randomObstacleNumber]) {
                case OBSTACLE_ONE:
                    int randomOne = (int) (Math.random() * 601 + 100);
                    return new Obstacle(randomOne, 0, ObstacleType.OBSTACLE_ONE);
                case OBSTACLE_TWO:
                    int randomTwo = (int) (Math.random() * 581 + 120);
                    return new Obstacle(randomTwo, 0, ObstacleType.OBSTACLE_TWO);

            }
        }

        return null;
    }
}
