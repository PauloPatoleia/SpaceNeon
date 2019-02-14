package GameObjects;

import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.awt.*;


public class Bullets {

    public Picture bulletImage;
    private int speed;
    private Rectangle hitbox;

    public Bullets(int initialXPosition, int initialYPosition, BulletType bulletType, String bulletImage) {

        this.bulletImage = new Picture(initialXPosition, initialYPosition, bulletImage);
        this.hitbox = new Rectangle(initialXPosition, initialYPosition, this.bulletImage.getWidth(), this.bulletImage.getHeight());
        this.speed = bulletType.speed;
    }

    public int getImgX() {

        return bulletImage.getX();
    }

    public int getImgY() {

        return bulletImage.getY();
    }

    public void tick() {

        bulletImage.setY(bulletImage.getY() - speed);
        hitbox.setLocation(bulletImage.getX(), bulletImage.getY());
    }

    public void render() {

        bulletImage.draw();
    }

    public void hit () {

        bulletImage.delete();
        hitbox = null;

    }

    public enum BulletType {

        NORMAL(15, 6),
        FAST(9, 8),
        DOUBLE(15, 6),
        ENEMYBULLET(120, -3),
        VSBOTTOM(15, 15),
        VSTOP(15, -15),
        BOSSBULLETS(100, 6);

        private int cooldown;
        private int speed;

        BulletType(int cooldown, int speed) {
           this.speed = speed;
           this.cooldown = cooldown;
        }

        public int getCooldown() {
            return cooldown;
        }

        public static void increaseSpeed(int speed) {
            ENEMYBULLET.speed -= speed;
        }

        public static void resetSpeed() {
            ENEMYBULLET.speed = -3;
        }

        public int getSpeed() {
            return speed;
        }

        public void setSpeed(int speed) {
            this.speed = speed;
        }
    }

    public Rectangle getHitbox() {
        return hitbox;
    }
}
