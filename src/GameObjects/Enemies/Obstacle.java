package GameObjects.Enemies;

public class Obstacle extends Enemy {

    private int hp;

    public Obstacle(int x, int y, ObstacleType obstacleType) {

        super(x, y, obstacleType.getImage(), obstacleType.getVelocityY(), obstacleType.getHp());
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

        OBSTACLE_ONE(1,20, "enemy_obstacle_80x20.png"),
        OBSTACLE_TWO(1,20, "enemy_obstacle_100x20.png");

        private int velocity;
        private String image;
        private int hp;

        ObstacleType(int velocity, int hp, String image){
            this.velocity = velocity;
            this.hp = hp;
            this.image = image;
        }

        public int getVelocityY(){
            return velocity;
        }

        public String getImage() {
            return image;
        }

        public int getHp() {
            return hp;
        }
    }

}
