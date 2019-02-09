import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.LinkedList;

public class SpaceShip {

    private Picture img;
    private int speed;


    private int velocityX = 0;
    private int velocityY = 0;
    private boolean isShooting = false;
    private LinkedList<Bullets> friendlyBullets;
    private Bullets.BulletType bulletType = Bullets.BulletType.DOUBLE;
    private int cooldown = bulletType.getCooldown();

    /**
     * constructor
     * Initializes a new Player SpaceShip
     *
     * @param x           - Initial X position
     * @param y           - Initial Y position
     * @param imageSource - SpaceShip image source
     */
    public SpaceShip(int x, int y, LinkedList<Bullets> friendlyBullets, String imageSource) {
        this.img = new Picture(x, y, imageSource);
        this.speed = 5;
        this.friendlyBullets = friendlyBullets;

    }

    /**
     * @return Picture - The SpaceShip image
     */
    public Picture getImg() {
        return img;
    }

    /**
     * @return int - The SpaceShip current speed status
     */
    public int getSpeed() {
        return speed;
    }


    public void tick() {
        img.setX(img.getX() + velocityX);
        img.setY(img.getY() + velocityY);

        if (isShooting && cooldown == 0) {

            shoot();
            cooldown = bulletType.getCooldown();
        }

        if (cooldown > 0) {
            cooldown--;
        }


        if (img.getX() <= 10)
            img.setX(10);
        if (img.getX() >= 760)
            img.setX(760);
        if (img.getY() <= 10)
            img.setY(10);
        if (img.getY() >= 760)
            img.setY(760);

    }

    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }

    public void setShooting(boolean shooting) {
        isShooting = shooting;
    }

    public void shoot() {

        switch (bulletType) {
            case NORMAL:
                friendlyBullets.add(new Bullets(img.getX() + 20, img.getY(), bulletType));
                break;
            case FAST:
                friendlyBullets.add(new Bullets(img.getX() + 20, img.getY(), bulletType));
                break;
            case DOUBLE:
                friendlyBullets.add(new Bullets(img.getX(), img.getY(), bulletType));
                friendlyBullets.add(new Bullets(img.getX() + 40, img.getY(), bulletType));
                break;
        }
    }
}

