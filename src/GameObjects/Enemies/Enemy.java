package GameObjects.Enemies;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public abstract class Enemy {

    private Picture EnemyImage;

    public Enemy(int x, int y, String enemyImage) {
        this.EnemyImage = new Picture(x, y, enemyImage);
    }

    public abstract void tick();

    public abstract void render();

    public int getY() {
        return EnemyImage.getY();
    }

    public int getX() {
        return EnemyImage.getX();
    }

    public Picture getEnemyImage() {
        return EnemyImage;
    }
}
