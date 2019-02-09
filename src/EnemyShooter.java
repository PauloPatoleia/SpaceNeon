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


    public enum EnemyType {

        ENEMY_ONE( 3,4, "./Resources/enemy50.jpg"),
        ENEMY_TWO( 3,6, "./Resources/enemyTwo70.jpg");

        private int velocity;
        private String image;
        private int hp;

        EnemyType(int velocity, int hp, String image){
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

        public int getHp(){
            return hp;
        }
    }
}
