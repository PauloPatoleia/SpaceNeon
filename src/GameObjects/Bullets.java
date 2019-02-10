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

    public enum BulletType {

        NORMAL(15, 6),
        FAST(10, 8),
        DOUBLE(15, 6),
        ENEMYBULLET(80, -3);


        BulletType(int cooldown, int speed) {
           this.speed = speed;
           this.cooldown = cooldown;
        }

        private int cooldown;
        private int speed;

        public int getCooldown() {
            return cooldown;
        }
    }

    public Rectangle getHitbox() {
        return hitbox;
    }
}
