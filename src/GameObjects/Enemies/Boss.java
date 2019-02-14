package GameObjects.Enemies;

import GameObjects.Bullets;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import java.util.LinkedList;

public class Boss extends Enemy {

    private LinkedList<Bullets> enemyBullets;
    private String bulletImage;
    private Bullets.BulletType bulletType = Bullets.BulletType.MANIAC;
    private int cooldown;
    private boolean touchedRight;
    private boolean touchedLeft;
    private BossType bossType;

    public Boss (int x, int y, BossType bossType, LinkedList<Bullets> enemyBullets, String bulletImage) {
        super(x, y, bossType.getImage(), bossType.getSpeed(), bossType.getHp());
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

        System.out.println(super.getEnemyImage().getX());

        if (touchedLeft)
            super.getEnemyImage().setX(super.getEnemyImage().getX() + super.getSpeed());

        if (touchedRight)
            super.getEnemyImage().setX(super.getEnemyImage().getX() - super.getSpeed());

        super.getHitbox().setLocation(super.getEnemyImage().getX(), super.getEnemyImage().getY());


        if (super.getEnemyImage().getX() <= 10) {
            super.getEnemyImage().setX(10);
            touchedLeft = true;
            touchedRight = false;
        }

        if (super.getEnemyImage().getX() >= 390) {
            super.getEnemyImage().setX(390);
            touchedLeft = false;
            touchedRight = true;
        }

        //rainAttack();
        if (cooldown == 0) {


            maniacAttack();
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

    public void rainAttack() {
        if (super.getEnemyImage().getX() <= 10 || super.getEnemyImage().getX() >= 390) {


            enemyBullets.add(new Bullets(getBossImage().getX() + 01, getBossImage().getY() + 80,
                    Bullets.BulletType.ENEMYBULLET, bulletImage));
            enemyBullets.add(new Bullets(getBossImage().getX() + 50, getBossImage().getY() + 80,
                    Bullets.BulletType.ENEMYBULLET, bulletImage));
            enemyBullets.add(new Bullets(getBossImage().getX() + 100, getBossImage().getY() + 80,
                    Bullets.BulletType.ENEMYBULLET, bulletImage));
            enemyBullets.add(new Bullets(getBossImage().getX() + 150, getBossImage().getY() + 80,
                    Bullets.BulletType.ENEMYBULLET, bulletImage));
            enemyBullets.add(new Bullets(getBossImage().getX() + 200, getBossImage().getY() + 80,
                    Bullets.BulletType.ENEMYBULLET, bulletImage));
            enemyBullets.add(new Bullets(getBossImage().getX() + 250, getBossImage().getY() + 80,
                    Bullets.BulletType.ENEMYBULLET, bulletImage));
            enemyBullets.add(new Bullets(getBossImage().getX() + 300, getBossImage().getY() + 80,
                    Bullets.BulletType.ENEMYBULLET, bulletImage));
            enemyBullets.add(new Bullets(getBossImage().getX() + 350, getBossImage().getY() + 80,
                    Bullets.BulletType.ENEMYBULLET, bulletImage));
            enemyBullets.add(new Bullets(getBossImage().getX() + 400, getBossImage().getY() + 80,
                    Bullets.BulletType.ENEMYBULLET, bulletImage));
        }

    }
    public void maniacAttack() {

        enemyBullets.add(new Bullets(getBossImage().getX() + 10, getBossImage().getY() + 40,
                Bullets.BulletType.ENEMYBULLET, bulletImage));
        enemyBullets.add(new Bullets(getBossImage().getX() + 50, getBossImage().getY() + 60,
                Bullets.BulletType.ENEMYBULLET, bulletImage));
        enemyBullets.add(new Bullets(getBossImage().getX() + 100, getBossImage().getY() + 80,
                Bullets.BulletType.ENEMYBULLET, bulletImage));
        enemyBullets.add(new Bullets(getBossImage().getX() + 250, getBossImage().getY() + 140,
                Bullets.BulletType.ENEMYBULLET, bulletImage));
        enemyBullets.add(new Bullets(getBossImage().getX() + 300, getBossImage().getY() + 140,
                Bullets.BulletType.ENEMYBULLET, bulletImage));
        enemyBullets.add(new Bullets(getBossImage().getX() + 350, getBossImage().getY() + 160,
                Bullets.BulletType.ENEMYBULLET, bulletImage));
        enemyBullets.add(new Bullets(getBossImage().getX() + 400, getBossImage().getY() + 180,
                Bullets.BulletType.ENEMYBULLET, bulletImage));
    }

    public Picture getBossImage() {
        return getEnemyImage();
    }
    public BossType getBossType(){
        return bossType;

    }
    public enum BossType {

        BOSS_ONE(2, 24, "rsz_boss.png");

        private int speed;
        private String image;
        private int hp;

        BossType(int speed, int hp, String image) {
            this.speed = speed;
            this.hp = hp;
            this.image = image;
        }

        //increase speed when the life in the last quarter of life
        public static void increaseSpead(int speed) {
            BOSS_ONE.speed += speed;
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
