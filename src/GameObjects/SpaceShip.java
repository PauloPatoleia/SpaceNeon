package GameObjects;

import GameObjects.Collectibles.PowerUp;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.awt.*;
import java.util.LinkedList;

public class SpaceShip {

    private Picture img;
    private int speed;
    private Rectangle hitbox;
    private int hp = 3;


    private Lifes hpDisplay;
    private int velocityX = 0;
    private int velocityY = 0;
    private boolean isShooting = false;
    private LinkedList<Bullets> friendlyBullets;
    private String bulletImage;
    private Bullets.BulletType bulletType;
    private int cooldown;
    private boolean invincible = true;
    private int invincibilityCooldown = 0;
    private int versus;

    /**
     * constructor
     * Initializes a new Player GameObjects.SpaceShip
     *
     * @param initialXPosition - Initial X position
     * @param initialYPosition - Initial Y position
     * @param imageSource      - GameObjects.SpaceShip image source
     */
    public SpaceShip(int initialXPosition, int initialYPosition, LinkedList<Bullets> friendlyBullets, String imageSource,
                     String bulletImage, int hpDisplayXposition, Bullets.BulletType bulletType, int versus) {
        this.img = new Picture(initialXPosition, initialYPosition, imageSource);
        this.hitbox = new Rectangle(initialXPosition, initialYPosition, this.img.getWidth(), this.img.getHeight());
        this.speed = 5;
        this.friendlyBullets = friendlyBullets;
        this.bulletImage = bulletImage;
        this.hpDisplay = new Lifes(this, hpDisplayXposition);
        this.bulletType = bulletType;
        this.cooldown = bulletType.getCooldown();
        this.versus = versus;
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
        hitbox.setLocation(img.getX(), img.getY());

        if (img.getX() <= 10)
            img.setX(10);
        if (img.getX() >= 775)
            img.setX(775);
        if (img.getY() <= 50)
            img.setY(50);
        if (img.getY() >= 740)
            img.setY(740);

        if (versus == 1) {

            if (img.getY() >= 200)
                img.setY(200);
        }

        if (versus == 2) {
            if (img.getY() <= 600)
                img.setY(600);
        }

        if (isShooting && cooldown == 0) {

            shoot();
            cooldown = bulletType.getCooldown();
        }

        if (cooldown > 0) {
            cooldown--;
        }

        if (invincibilityCooldown > 0) {
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

        if (invincible)
            return;

        hp--;
        hpDisplay.hit();

        if (hp <= 0) {
            img.delete();
            hitbox = null;
            return;
        }


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
            case VSBOTTOM:
                friendlyBullets.add(new Bullets(img.getX() + 5, img.getY() - 20, bulletType, bulletImage));
                break;
            case VSTOP:
                friendlyBullets.add(new Bullets(img.getX() + 5, img.getY() + 20, bulletType, bulletImage));
        }
    }

    public void render() {

        if (hp <= 0) {
            return;
        }

        if (invincible) {

            if (invincibilityCooldown >= 100) {
                img.delete();
                return;
            }

            if (invincibilityCooldown >= 80) {
                img.draw();
                return;
            }

            if (invincibilityCooldown >= 60) {
                img.delete();
                return;
            }

            if (invincibilityCooldown >= 40) {
                img.draw();
                return;
            }

            if (invincibilityCooldown >= 20) {
                img.delete();
                return;
            }

            if (invincibilityCooldown >= 0) {
                img.draw();
                invincible = false;
                return;
            }
        }


        img.draw();
        hpDisplay.render();

    }

    public int getHp() {
        return hp;
    }

    public void powerUp(PowerUp.PowerUpType powerUpType) {

        switch (powerUpType) {

            case LIFEUP:
                if (hp < 3) {
                    hp++;
                    hpDisplay.lifeUp();
                }

                break;
            case BULLETFAST:
                bulletType = Bullets.BulletType.FAST;
                break;
            case BULLETDOUBLE:
                bulletType = Bullets.BulletType.DOUBLE;
                break;
        }
    }
}

