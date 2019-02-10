package GameObjects.Enemies;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public abstract class Enemy {

    private Picture enemyImage;
    private int velocity;

    public Enemy(int x, int y, String enemyImage, int velocity) {
        this.enemyImage = new Picture(x, y, enemyImage);
        this.velocity = velocity;
    }

    public void tick() {
        enemyImage.setY(enemyImage.getY() + velocity);
    }

    public abstract void render();


    public Picture getEnemyImage() {
        return enemyImage;
    }
}
