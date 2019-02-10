package GameObjects.Enemies;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public abstract class Enemy {

    private Picture enemyImage;
    private int speed;

    public Enemy(int x, int y, String enemyImage, int speed) {
        this.enemyImage = new Picture(x, y, enemyImage);
        this.speed = speed;
    }

    public void tick() {
        enemyImage.setY(enemyImage.getY() + speed);
    }

    public abstract void render();


    public Picture getEnemyImage() {
        return enemyImage;
    }
}
