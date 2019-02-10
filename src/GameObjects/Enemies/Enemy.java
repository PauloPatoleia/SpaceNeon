package GameObjects.Enemies;

import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.awt.*;

public abstract class Enemy {

    private Picture enemyImage;
    private int speed;
    private Rectangle hitbox;

    public Enemy(int initialXPosition, int initialYPosition, String enemyImage, int speed) {

        this.enemyImage = new Picture(initialXPosition, initialYPosition, enemyImage);
        this.hitbox = new Rectangle(initialXPosition, initialYPosition, this.enemyImage.getWidth(), this.enemyImage.getHeight());
        this.speed = speed;
    }

    public void tick() {
        enemyImage.setY(enemyImage.getY() + speed);
        hitbox.setLocation(enemyImage.getX(), enemyImage.getY());
    }

    public abstract void render();


    public Picture getEnemyImage() {
        return enemyImage;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

}
