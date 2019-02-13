package GameObjects.Enemies;

public class Obstacle extends Enemy {

    private int hp;

    public Obstacle(int x, int y, ObstacleType obstacleType) {

        super(x, y, obstacleType.getImage(), obstacleType.getSpeed(), obstacleType.getHp());
    }

    @Override
    public void tick() {
       super.tick();
    }

    @Override
    public void render() {
        getEnemyImage().draw();
    }

    public enum ObstacleType {

        OBSTACLE_ONE(4, 10, "enemy_obstacle_80x20.png"),
        OBSTACLE_TWO(4, 10, "enemy_obstacle_100x20.png");

        private int speed;
        private String image;
        private int hp;

        ObstacleType(int speed, int hp, String image){
            this.speed = speed;
            this.hp = hp;
            this.image = image;
        }

        public static void increaseSpeed(int speed) {
            OBSTACLE_ONE.speed += speed;
            OBSTACLE_TWO.speed += speed;
        }

        public static void resetSpeed() {
            OBSTACLE_ONE.speed = 2;
            OBSTACLE_TWO.speed = 3;
        }

        public int getSpeed(){
            return speed;
        }

        public String getImage() {
            return image;
        }

        public int getHp() {
            return hp;
        }
    }

}
