package GameObjects.Enemies;

import GameObjects.Bullets;

import java.util.LinkedList;

public class EnemyDiamond extends Enemy {

    private LinkedList<Bullets> enemyBullets;
    private String bulletImage;
    private Bullets.BulletType bulletType = Bullets.BulletType.ENEMYBULLET;
    private int cooldown;
    private boolean touchedRight;
    private boolean touchedLeft;


    public EnemyDiamond(int x, int y, EnemyType enemyType, LinkedList<Bullets> enemyBullets, String bulletImage) {

        super(x, y, enemyType.getImage(), enemyType.getSpeed(), enemyType.getHp());
        this.enemyBullets = enemyBullets;
        this.bulletImage = bulletImage;
        int randomStartDirection = (int) (Math.random() * 2);
        switch (randomStartDirection) {
            case 0:
                touchedLeft = true;
                break;
            case 1:
                touchedRight = true;
                break;
        }
    }

    @Override
    public void tick() {

        if (touchedLeft)
            super.getEnemyImage().setX(super.getEnemyImage().getX() + Math.floor(super.getSpeed())/2);

        if (touchedRight)
            super.getEnemyImage().setX(super.getEnemyImage().getX() - Math.floor(super.getSpeed())/2);

        super.getEnemyImage().setY(super.getEnemyImage().getY() + super.getSpeed());
        super.getHitbox().setLocation(super.getEnemyImage().getX(), super.getEnemyImage().getY());

        if (super.getEnemyImage().getX() <= 10) {
            super.getEnemyImage().setX(10);
            touchedLeft = true;
            touchedRight = false;
        }

        if (super.getEnemyImage().getX() >= 775) {
            super.getEnemyImage().setX(775);
            touchedLeft = false;
            touchedRight = true;
        }

        if (cooldown == 0) {

            enemyBullets.add(new Bullets(getEnemyImage().getX() + 3, getEnemyImage().getY() + 10, Bullets.BulletType.ENEMYBULLET, bulletImage));
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

        ENEMY_ONE(3, 2, "enemy_diamond_25x25.png"),
        ENEMY_TWO(4, 2, "enemy_diamond_25x25.png");

        private int speed;
        private String image;
        private int hp;

        EnemyType(int speed, int hp, String image) {
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

        public int getSpeed() {
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
