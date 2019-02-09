import GameObjects.SpaceShip;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;


public class Player implements KeyboardHandler {

    private boolean isUpKeyPressed = false;
    private boolean isDownKeyPressed = false;
    private boolean isLeftKeyPressed = false;
    private boolean isRightKeyPressed = false;
    private boolean isShootKeyPressed = false;
    private final int KEY_UP;
    private final int KEY_DOWN;
    private final int KEY_LEFT;
    private final int KEY_RIGHT;
    private final int KEY_SHOOT;
    private SpaceShip ship;


    public Player(final int KEY_UP, final int KEY_DOWN, final int KEY_LEFT, final int KEY_RIGHT, final int KEY_SHOOT, SpaceShip ship) {

        this.ship = ship;

        this.KEY_UP = KEY_UP;
        this.KEY_DOWN = KEY_DOWN;
        this.KEY_LEFT = KEY_LEFT;
        this.KEY_RIGHT = KEY_RIGHT;
        this.KEY_SHOOT = KEY_SHOOT;


        // Keyboard
        Keyboard k = new Keyboard(this);

        // Keyboard events
        KeyboardEvent UP = new KeyboardEvent();
        UP.setKey(this.KEY_UP);
        UP.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent DOWN = new KeyboardEvent();
        DOWN.setKey(this.KEY_DOWN);
        DOWN.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent LEFT = new KeyboardEvent();
        LEFT.setKey(this.KEY_LEFT);
        LEFT.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent RIGHT = new KeyboardEvent();
        RIGHT.setKey(this.KEY_RIGHT);
        RIGHT.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);


        KeyboardEvent SHOOT = new KeyboardEvent();
        SHOOT.setKey(this.KEY_SHOOT);
        SHOOT.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent RIGHT2 = new KeyboardEvent();
        RIGHT2.setKey(this.KEY_RIGHT);
        RIGHT2.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent LEFT2 = new KeyboardEvent();
        LEFT2.setKey(this.KEY_LEFT);
        LEFT2.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent UP2 = new KeyboardEvent();
        UP2.setKey(this.KEY_UP);
        UP2.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent DOWN2 = new KeyboardEvent();
        DOWN2.setKey(this.KEY_DOWN);
        DOWN2.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent SHOOT2 = new KeyboardEvent();
        SHOOT2.setKey(this.KEY_SHOOT);
        SHOOT2.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);


        // Add events to KEYBOARD
        k.addEventListener(UP);
        k.addEventListener(DOWN);
        k.addEventListener(LEFT);
        k.addEventListener(RIGHT);
        k.addEventListener(SHOOT);
        k.addEventListener(LEFT2);
        k.addEventListener(UP2);
        k.addEventListener(DOWN2);
        k.addEventListener(RIGHT2);
        k.addEventListener(SHOOT2);

    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        if (keyboardEvent.getKey() == this.KEY_UP) {

            this.ship.setVelocityY(-ship.getSpeed());
            isUpKeyPressed = true;
        }

        if (keyboardEvent.getKey() == this.KEY_DOWN) {

            this.ship.setVelocityY(ship.getSpeed());
            isDownKeyPressed = true;
        }

        if (keyboardEvent.getKey() == this.KEY_LEFT) {

            this.ship.setVelocityX(-ship.getSpeed());
            isLeftKeyPressed = true;
        }

        if (keyboardEvent.getKey() == this.KEY_RIGHT) {

            this.ship.setVelocityX(ship.getSpeed());
            isRightKeyPressed = true;
        }

        if (keyboardEvent.getKey() == this.KEY_SHOOT) {

            this.ship.setShooting(true);
            isShootKeyPressed = true;
        }


    }



    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

        if(keyboardEvent.getKey() == this.KEY_UP) {
            isUpKeyPressed = false;
            if (isDownKeyPressed) {
                ship.setVelocityY(ship.getSpeed());
                return;
            }
            ship.setVelocityY(0);
        }

        if(keyboardEvent.getKey() == this.KEY_DOWN) {
            isDownKeyPressed = false;
            if (isUpKeyPressed) {
                ship.setVelocityY(-ship.getSpeed());
                return;
            }
            ship.setVelocityY(0);
        }

        if(keyboardEvent.getKey() == this.KEY_LEFT) {
            isLeftKeyPressed = false;
            if (isRightKeyPressed) {
                ship.setVelocityX(ship.getSpeed());
                return;
            }
            ship.setVelocityX(0);
        }

        if(keyboardEvent.getKey() == this.KEY_RIGHT) {
            isRightKeyPressed = false;
            if (isLeftKeyPressed) {
                ship.setVelocityX(-ship.getSpeed());
                return;
            }
            ship.setVelocityX(0);
        }

        if(keyboardEvent.getKey() == this.KEY_SHOOT) {
            this.ship.setShooting(false);
            isShootKeyPressed = false;
        }


    }

    public SpaceShip getShip() {
        return ship;
    }
}
