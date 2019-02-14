package GameObjects.Collectibles;

import java.util.LinkedList;

public class PowerUpFactory {

    public static PowerUp getNewPowerUp(LinkedList<PowerUp> powerUpList) {

        int randomEnemy = (int) (Math.random() * 2);


        if (randomEnemy == 0) {

            int upperBoundary = 766;
            int randomPowerUpNumber = (int) (Math.random() * (PowerUp.PowerUpType.values()).length);

            switch (PowerUp.PowerUpType.values()[randomPowerUpNumber]) {
                case LIFEUP:
                    int randomOne = (int) (Math.random() * upperBoundary + 10);
                    powerUpList.add(new PowerUp(randomOne, 30, PowerUp.PowerUpType.LIFEUP, "powerup_heart.png"));
                    break;
                case BULLETFAST:
                    int randomTwo = (int) (Math.random() * upperBoundary + 10);
                    powerUpList.add(new PowerUp(randomTwo, 30, PowerUp.PowerUpType.BULLETFAST, "powerup.png"));
                    break;
                case BULLETDOUBLE:
                    int randomThree = (int) (Math.random() * upperBoundary + 10);
                    powerUpList.add(new PowerUp(randomThree, 30, PowerUp.PowerUpType.BULLETDOUBLE, "powerup.png"));
                    break;
                case SPEEDUP:
                    int randomFour = (int) (Math.random() * upperBoundary + 10);
                    powerUpList.add(new PowerUp(randomFour, 30, PowerUp.PowerUpType.SPEEDUP, "powerup.png")); //change pic
                    break;

            }
        }
        return null;
    }
}
