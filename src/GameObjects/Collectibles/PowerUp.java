package GameObjects.Collectibles;

import GameObjects.Bullets;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import java.awt.*;

public class PowerUp {

    public Picture powerUpImage;
    private int speed;
    private Rectangle hitbox;
    private PowerUpType powerUpType;

    public PowerUp(int initialXPosition, int initialYPosition, PowerUpType powerUpType, String powerUpImage) {

        this.powerUpImage = new Picture(initialXPosition, initialYPosition, powerUpImage);
        this.hitbox = new Rectangle(initialXPosition, initialYPosition, this.powerUpImage.getWidth(), this.powerUpImage.getHeight());
        this.speed = 3;
        this.powerUpType = powerUpType;
    }

    public int getImgX() {

        return powerUpImage.getX();
    }

    public int getImgY() {

        return powerUpImage.getY();
    }

    public void tick() {

        powerUpImage.setY(powerUpImage.getY() + speed);
        hitbox.setLocation(powerUpImage.getX(), powerUpImage.getY());
    }

    public void render() {

        powerUpImage.draw();
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void hit() {

        powerUpImage.delete();
        hitbox = null;
    }

    public PowerUpType getPowerUpType() {
        return powerUpType;
    }

    public enum PowerUpType {

        BULLETFAST,
        BULLETDOUBLE,
        LIFEUP,
        SPEEDUP;

    }

}
