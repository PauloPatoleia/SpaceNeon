package GameObjects.Enemies;

import GameObjects.Bullets;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.awt.*;
import java.util.LinkedList;

public class Boss extends Enemy {



    private LinkedList<Bullets> enemyBullets;
    private String bulletImage;
    private Bullets.BulletType bulletType = Bullets.BulletType.BOSSBULLETS;
    private int cooldown;
    private boolean touchedRight;
    private boolean touchedLeft;

    public Boss(int x, int y, BossType bossType, LinkedList<Bullets> enemyBullets, String bulletImage) {
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

/*
        int randomBossDirection = (int) (Math.random() * 2);
        switch (randomBossDirection) {
            case 0:
                touchedLeftEdge = true;
                break;
            case 1:
                touchedRightEdge = true;
                break;
        }
        */
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

        if (super.getEnemyImage().getX() >= 380) {
            super.getEnemyImage().setX(380);
            touchedLeft = false;
            touchedRight = true;
        }


        if (cooldown == 0) {

            rainAttack();
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

        enemyBullets.add(new Bullets(getBossImage().getX() + 3, getBossImage().getY() + 80, Bullets.BulletType.ENEMYBULLET, bulletImage));
        enemyBullets.add(new Bullets(getBossImage().getX() + 50, getBossImage().getY() + 80, Bullets.BulletType.ENEMYBULLET, bulletImage));
        enemyBullets.add(new Bullets(getBossImage().getX() + 100, getBossImage().getY() + 80, Bullets.BulletType.ENEMYBULLET, bulletImage));
        enemyBullets.add(new Bullets(getBossImage().getX() + 150, getBossImage().getY() + 80, Bullets.BulletType.ENEMYBULLET, bulletImage));
        enemyBullets.add(new Bullets(getBossImage().getX() + 200, getBossImage().getY() + 80, Bullets.BulletType.ENEMYBULLET, bulletImage));
        enemyBullets.add(new Bullets(getBossImage().getX() + 250, getBossImage().getY() + 80, Bullets.BulletType.ENEMYBULLET, bulletImage));
        enemyBullets.add(new Bullets(getBossImage().getX() + 300, getBossImage().getY() + 80, Bullets.BulletType.ENEMYBULLET, bulletImage));
        enemyBullets.add(new Bullets(getBossImage().getX() + 350, getBossImage().getY() + 80, Bullets.BulletType.ENEMYBULLET, bulletImage));
        enemyBullets.add(new Bullets(getBossImage().getX() + 400, getBossImage().getY() + 80, Bullets.BulletType.ENEMYBULLET, bulletImage));

    }
    /*
    @Override
    public void hit(int hit) {
        super.setHp(super.getHp() - hit);

        if (super.getHp() <= 0)
            getEnemyImage().delete();
        hitbox = null;
        return;
    }
*/
    public Picture getBossImage() {
        return getEnemyImage();
    }

    public enum BossType {

        BOSS_ONE(5, 24, "rsz_boss.png");

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
