package GameObjects;

import org.academiadecodigo.simplegraphics.pictures.Picture;


public class Bullets {

    private Picture imgBullet;
    private int speed;

    public Bullets(int x, int y, BulletType bulletType) {

        imgBullet = new Picture(x, y, bulletType.pic);
        this.speed = bulletType.speed;
    }

    public int getImgX() {

        return imgBullet.getX();
    }

    public int getImgY() {

        return imgBullet.getY();
    }

    public void tick() {

        imgBullet.setY(imgBullet.getY() - speed);
    }

    public void render() {

        imgBullet.draw();
    }

    public enum BulletType {

        NORMAL(15, 5, "./Resources/rsz_bullet.jpg"),
        FAST(10, 8, "./Resources/rsz_bullet.jpg"),
        DOUBLE(15, 5, "./Resources/rsz_bullet.jpg");


        BulletType(int cooldown, int speed, String pic) {
           this.speed = speed;
           this.pic = pic;
           this.cooldown = cooldown;
        }

        private int cooldown;
        private int speed;
        private String pic;

        public int getCooldown() {
            return cooldown;
        }
    }
}
