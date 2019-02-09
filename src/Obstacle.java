import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Obstacle extends Enemy {

    private Picture obstacle;
    private int velocity;

    public Obstacle(int x, int y, ObstacleType obstacleType) {
        this.obstacle = new Picture(x, y, obstacleType.getImage());
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
}
