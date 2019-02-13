package GameObjects.Collectibles;

import java.util.LinkedList;

public class PowerUpGenerator {

    private LinkedList<PowerUp> powerUpList;

    public PowerUpGenerator(LinkedList<PowerUp> powerUpList) {
        this.powerUpList = powerUpList;
    }

    public void tick() {

        int chance = (int) Math.floor(Math.random() * 300);

        if (chance == 1) {

            PowerUpFactory.getNewPowerUp(powerUpList);
        }
    }
}
