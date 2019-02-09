package GameObjects.Enemies;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Obstacle extends Enemy {

    private Picture obstacle;
    private int velocity;
    private int hp;

    public Obstacle(int x, int y, ObstacleType obstacleType) {
        this.obstacle = new Picture(x, y, obstacleType.getImage());
        this.hp = obstacleType.getHp();
        this.velocity = obstacleType.getVelocityY();
    }

    @Override
    public void tick() {
        obstacle.setY(obstacle.getY() + velocity);
    }

    @Override
    public void render() {
        obstacle.draw();
    }

    public enum ObstacleType {

        OBSTACLE_ONE(1,2, "./Resources/obstacle100.jpg"),
        OBSTACLE_TWO(2,2, "./Resources/obstacle120.jpg");

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
