package GameObjects.Collectibles;

import java.util.LinkedList;

public class PowerUpGenerator {

    private LinkedList<PowerUp> powerUpList;
    private int chanceMultiplier;

    public PowerUpGenerator(LinkedList<PowerUp> powerUpList) {

        this.powerUpList = powerUpList;
        chanceMultiplier = 300;
    }

    public void tick() {

        int chance = (int) Math.floor(Math.random() * chanceMultiplier);

        if (chance == 1) {

            PowerUpFactory.getNewPowerUp(powerUpList);
        }
    }

    public void decreaseProbability (int decreaseProbability)  {

        chanceMultiplier += decreaseProbability;
    }

    public void resetProbability () {

        chanceMultiplier = 300;
    }
}
