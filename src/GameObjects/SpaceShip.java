package GameObjects;

import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.awt.*;
import java.util.LinkedList;

public class SpaceShip {

    private Picture img;
    private int speed;
    private Rectangle hitbox;
    private int hp = 3;



    private int velocityX = 0;
    private int velocityY = 0;
    private boolean isShooting = false;
    private LinkedList<Bullets> friendlyBullets;
    private String bulletImage;
    private Bullets.BulletType bulletType = Bullets.BulletType.DOUBLE;
    private int cooldown = bulletType.getCooldown();
    private boolean invincible = true;
    private int invincibilityCooldown = 0;

    /**
     * constructor
     * Initializes a new Player GameObjects.SpaceShip
     *
     * @param initialXPosition           - Initial X position
     * @param initialYPosition           - Initial Y position
     * @param imageSource - GameObjects.SpaceShip image source
     */
    public SpaceShip(int initialXPosition, int initialYPosition, LinkedList<Bullets> friendlyBullets, String imageSource, String bulletImage) {
        this.img = new Picture(initialXPosition, initialYPosition, imageSource);
        this.hitbox = new Rectangle(initialXPosition, initialYPosition, this.img.getWidth(), this.img.getHeight());
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

        if(hp <= 0) {
            img.delete();
            hitbox = null;
            return;
        }

        img.setX(img.getX() + velocityX);
        img.setY(img.getY() + velocityY);
        hitbox.setLocation(img.getX(), img.getY());

        if (img.getX() <= 10)
            img.setX(10);
        if (img.getX() >= 775)
            img.setX(775);
        if (img.getY() <= 10)
            img.setY(10);
        if (img.getY() >= 780)
            img.setY(780);

        if (isShooting && cooldown == 0) {

            shoot();
            cooldown = bulletType.getCooldown();
        }

        if (cooldown > 0) {
            cooldown--;
        }

        if(invincibilityCooldown > 0) {
            invincibilityCooldown--;
        }
    }
    public Rectangle getHitbox() {
        return hitbox;
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

    public void hit() {

        if(invincible)
            return;

        hp--;
        invincible = true;
        invincibilityCooldown = 120;
        //
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
                friendlyBullets.add(new Bullets(img.getX() + 15, img.getY() - 15, bulletType, bulletImage));
                break;
        }
    }

    public void render() {

        if(hp <= 0) {
            return;
        }

         if (invincible) {

             if(invincibilityCooldown >= 100) {
                 img.delete();
                 return;
             }

             if(invincibilityCooldown >= 80) {
                 img.draw();
                 return;
             }

             if(invincibilityCooldown >= 60) {
                 img.delete();
                 return;
             }

             if(invincibilityCooldown >= 40) {
                 img.draw();
                 return;
             }

             if(invincibilityCooldown >= 20) {
                 img.delete();
                 return;
             }

             if(invincibilityCooldown >= 0) {
                 img.draw();
                 invincible = false;
                 return;
             }
         }


        img.draw();

    }

    public int getHp() {
        return hp;
    }
}

