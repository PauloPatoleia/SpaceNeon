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
                    powerUpList.add(new PowerUp(randomOne, 30, PowerUp.PowerUpType.LIFEUP, "powerup_heart_25x25.png"));
                    break;
                case BULLETFAST:
                    int randomTwo = (int) (Math.random() * upperBoundary + 10);
                    powerUpList.add(new PowerUp(randomTwo, 30, PowerUp.PowerUpType.BULLETFAST, "powerup_fast_25x25.png"));
                    break;
                case BULLETDOUBLE:
                    int randomThree = (int) (Math.random() * upperBoundary + 10);
                    powerUpList.add(new PowerUp(randomThree, 30, PowerUp.PowerUpType.BULLETDOUBLE, "powerup_double_25x25.png"));
                    break;
                case SPEEDUP:
                    int randomFour = (int) (Math.random() * upperBoundary + 10);
                    powerUpList.add(new PowerUp(randomFour, 30, PowerUp.PowerUpType.SPEEDUP, "powerup_speed_25x25.png"));
                    break;
                case BULLETSPEED:
                    int randomFive = (int) (Math.random() * upperBoundary + 10);
                    powerUpList.add(new PowerUp(randomFive, 30, PowerUp.PowerUpType.BULLETSPEED, "powerup_bullet_speed_25x25.png"));
                    break;

            }
        }
        return null;
    }
}
