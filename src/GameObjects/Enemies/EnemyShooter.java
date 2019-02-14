package GameObjects.Enemies;

import GameObjects.Bullets;

import java.util.LinkedList;

public class EnemyShooter extends Enemy {

    private LinkedList<Bullets> enemyBullets;
    private String bulletImage;
    private Bullets.BulletType bulletType = Bullets.BulletType.ENEMYBULLET;
    private int cooldown;



    public EnemyShooter(int x, int y, EnemyType enemyType, LinkedList<Bullets> enemyBullets, String bulletImage) {

        super(x, y, enemyType.getImage(), enemyType.getSpeed(), enemyType.getHp());
        this.enemyBullets = enemyBullets;
        this.bulletImage = bulletImage;
    }

    @Override
    public void tick() {

        super.tick();

        if (cooldown == 0) {

            enemyBullets.add(new Bullets(getEnemyImage().getX() + 3, getEnemyImage().getY() + 10, Bullets.BulletType.ENEMYBULLET,bulletImage));
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

        ENEMY_ONE(1, 2, "shooter_enemy_25x25.png"),
        ENEMY_TWO(2, 1, "shooter_enemy_25x25.png");

        private int speed;
        private String image;
        private int hp;

        EnemyType(int speed, int hp, String image){
            this.speed = speed;
            this.hp = hp;
            this.image = image;
        }

        public static void increaseSpeed(int speed) {
            ENEMY_ONE.speed += speed;
            ENEMY_TWO.speed += speed;
        }

        public static void resetSpeed() {
            ENEMY_ONE.speed = 1;
            ENEMY_TWO.speed = 2;
        }

        public int getSpeed(){
            return speed;
        }

        public String getImage() {
            return image;
        }

        public int getHp(){
            return hp;
        }
    }
}
