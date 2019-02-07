import org.academiadecodigo.simplegraphics.pictures.Picture;

public class SpaceShip {

    private Picture img;
    private int speed;


    private int velocityX = 0;
    private int velocityY = 0;

    /**
     * constructor
     * Initializes a new Player SpaceShip
     * @param x - Initial X position
     * @param y - Initial Y position
     * @param imageSource - SpaceShip image source
     */
    public SpaceShip(int x, int y, String imageSource) {
        this.img = new Picture(x, y, imageSource);
        this.speed = 5;

    }

    /**
     *
     * @return Picture - The SpaceShip image
     */
    public Picture getImg() {
        return img;
    }

    /**
     *
     * @return int - The SpaceShip current speed status
     */
    public int getSpeed() {
        return speed;
    }



    public void tick() {
        img.setX(img.getX() + velocityX);
        img.setY(img.getY() + velocityY);

        if(img.getX() <= 10)
            img.setX(10);
        if(img.getX() >= 760)
            img.setX(760);
        if(img.getY() <= 10)
            img.setY(10);
        if(img.getY() >= 760)
            img.setY(760);

    }

    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }


}

