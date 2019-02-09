import org.academiadecodigo.simplegraphics.pictures.Picture;

public class EnemyShooter extends Enemy {

    private Picture enemy;
    private int velocity;

    public EnemyShooter(int x, int y, EnemyType enemyType) {
        this.enemy = new Picture(x, y, enemyType.getImage());
        this.velocity = enemyType.getVelocityY();
    }

    @Override
    public void tick(){
        enemy.setY(enemy.getY() + velocity);
        //add shoot
    }

    @Override
    public void render() {
        enemy.draw();
    }
}
