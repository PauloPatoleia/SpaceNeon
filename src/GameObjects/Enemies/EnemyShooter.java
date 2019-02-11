package GameObjects.Enemies;

import GameObjects.Bullets;

import java.util.LinkedList;

public class EnemyShooter extends Enemy {

    private int hp;
    private LinkedList<Bullets> enemyBullets;
    private String bulletImage;
    private Bullets.BulletType bulletType = Bullets.BulletType.ENEMYBULLET;
    private int cooldown;



    public EnemyShooter(int x, int y, EnemyType enemyType, LinkedList<Bullets> enemyBullets, String bulletImage) {

        super(x, y, enemyType.getImage(), enemyType.getVelocityY());
        this.hp = enemyType.getHp();
        this.enemyBullets = enemyBullets;
        this.bulletImage = bulletImage;
    }

    @Override
    public void tick() {

        super.tick();

        if (cooldown == 0) {

            enemyBullets.add(new Bullets(getEnemyImage().getX() + 3, getEnemyImage().getY() + 8, Bullets.BulletType.ENEMYBULLET,bulletImage));
             cooldown = bulletType.getCooldown();
        }

        if (cooldown > 0) {
            cooldown--;
        }

    }

    @Override
    public void render() {
        getEnemyImage().draw();
    }


    public enum EnemyType {

        ENEMY_ONE( 1,4, "enemy25.png"),
        ENEMY_TWO( 2,6, "enemy25.png");

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
