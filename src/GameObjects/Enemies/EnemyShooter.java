package GameObjects.Enemies;

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

        ENEMY_ONE( 1,4, "./Resources/enemy25.png"),
        ENEMY_TWO( 2,6, "./Resources/enemy25.png");

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
