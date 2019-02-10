package GameObjects;

import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.LinkedList;

public class SpaceShip {

    private Picture img;
    private int speed;


    private int velocityX = 0;
    private int velocityY = 0;
    private boolean isShooting = false;
    private LinkedList<Bullets> friendlyBullets;
    private String bulletImage;
    private Bullets.BulletType bulletType = Bullets.BulletType.DOUBLE;
    private int cooldown = bulletType.getCooldown();

    /**
     * constructor
     * Initializes a new Player GameObjects.SpaceShip
     *
     * @param x           - Initial X position
     * @param y           - Initial Y position
     * @param imageSource - GameObjects.SpaceShip image source
     */
    public SpaceShip(int x, int y, LinkedList<Bullets> friendlyBullets, String imageSource, String bulletImage) {
        this.img = new Picture(x, y, imageSource);
        this.speed = 5;
        this.friendlyBullets = friendlyBullets;
        this.bulletImage = bulletImage;
    }

    /**
     * @return Picture - The GameObjects.SpaceShip image
     */
    public Picture getImg() {
        return img;
    }

    /**
     * @return int - The GameObjects.SpaceShip current speed status
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Ship action
     */
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
        if (img.getX() >= 770)
            img.setX(770);
        if (img.getY() <= 10)
            img.setY(10);
        if (img.getY() >= 780)
            img.setY(780);

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
                friendlyBullets.add(new Bullets(img.getX() + 5, img.getY() - 15, bulletType, bulletImage));
                break;
            case FAST:
                friendlyBullets.add(new Bullets(img.getX() + 5, img.getY() - 15, bulletType, bulletImage));
                break;
            case DOUBLE:
                friendlyBullets.add(new Bullets(img.getX() - 4, img.getY() - 15, bulletType, bulletImage));
                friendlyBullets.add(new Bullets(img.getX() + 15, img.getY() - 15, bulletType ,bulletImage));
                break;
        }
    }
}

