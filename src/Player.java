public class Player {


    public SpaceShip ship;
    private PlayerControls controls;


    public Player(PlayerControls controls, SpaceShip ship) {

        this.ship = ship;
        this.controls = controls;

    }
}
