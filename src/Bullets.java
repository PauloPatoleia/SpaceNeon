import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;


public class Bullets {

    private Picture imgBullet;
    private int speed;


    public Bullets(int x, int y, int speed) {

        imgBullet = new Picture(x, y, "./Resources/rsz_bullet.jpg");
        this.speed = speed;
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

        NORMAL,
        FAST,
    }
}
