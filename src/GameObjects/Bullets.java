package GameObjects;

import org.academiadecodigo.simplegraphics.pictures.Picture;


public class Bullets {

    public Picture imgBullet;
    private int speed;

    public Bullets(int x, int y, BulletType bulletType, String bulletImage) {

        imgBullet = new Picture(x, y, bulletImage);
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

        NORMAL(15, 5),
        FAST(10, 8),
        DOUBLE(15, 5),
        ENEMYBULLET(40, -5);


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
}
