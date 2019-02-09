import org.academiadecodigo.simplegraphics.pictures.Picture;

public class EnemyShooter extends Enemy {

    private Picture enemy;
    private int velocity;
    private int hp;

    public EnemyShooter(int x, int y, EnemyType enemyType) {
        this.enemy = new Picture(x, y, enemyType.getImage());
        this.hp = enemyType.getHp();
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
