public enum EnemyType {

    ENEMY_ONE( 3,4, "./Resources/enemy50.jpg"),
    ENEMY_TWO( 3,6, "./Resources/enemyTwo70.jpg");

    private int velocity;
    private String image;
    private int hp;

    EnemyType(int velocity, int hp, String image){
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

    public int getHp(){
        return hp;
    }
}
