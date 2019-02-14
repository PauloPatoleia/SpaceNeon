package GameObjects.Enemies;

import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.awt.*;

public abstract class Enemy {

    private Picture enemyImage;
    private int speed;
    private Rectangle hitbox;
    private int hp;

    public Enemy(int initialXPosition, int initialYPosition, String enemyImage, int speed, int hp) {

        this.enemyImage = new Picture(initialXPosition, initialYPosition, enemyImage);
        this.hitbox = new Rectangle(initialXPosition, initialYPosition, this.enemyImage.getWidth(), this.enemyImage.getHeight());
        this.speed = speed;
        this.hp = hp;
    }

    public void tick() {
        enemyImage.setY(enemyImage.getY() + speed);
        hitbox.setLocation(enemyImage.getX(), enemyImage.getY());
    }

    public abstract void render();

    public void hit() {
        hp--;

        if(hp <= 0) {
            enemyImage.delete();
            hitbox = null;
            return;
        }
    }

    public void hit(int hit) {
        hp-=hit;

        if(hp <= 0) {
            enemyImage.delete();
            hitbox = null;
            return;
        }
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Picture getEnemyImage() {
        return enemyImage;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
