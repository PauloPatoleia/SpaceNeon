public enum EnemyType {

    ENEMY_ONE(2, "./Resources/enemy50.jpg"),
    ENEMY_TWO(2, "./Resources/enemyTwo70.jpg");

    private int velocity;
    private String image;

    EnemyType(int velocity, String image){
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
