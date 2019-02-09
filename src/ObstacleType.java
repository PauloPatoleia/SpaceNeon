public enum ObstacleType {

    OBSTACLE_ONE(2, "./Resources/obstacle100.jpg"),
    OBSTACLE_TWO(1, "./Resources/obstacle120.jpg");

    private int velocity;
    private String image;

    ObstacleType(int velocity, String image){
        this.velocity = velocity;
        this.image = image;
    }

    public int getVelocityY(){
        return velocity;
    }

    public String getImage() {
        return image;
    }


}
