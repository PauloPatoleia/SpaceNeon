public enum ObstacleType {

    OBSTACLE_ONE(1,2, "./Resources/obstacle100.jpg"),
    OBSTACLE_TWO(2,2, "./Resources/obstacle120.jpg");

    private int velocity;
    private String image;
    private int hp;

    ObstacleType(int velocity, int hp, String image){
        this.velocity = velocity;
        this.hp = hp;
        this.image = image;
    }

    public int getVelocityY(){
        return velocity;
    }

    public String getImage() {
        return image;
    }

    public int getHp() {
        return hp;
    }
}
