package GameObjects;

import GameObjects.SpaceShip;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.lang.reflect.Array;
import java.util.LinkedList;

public class Lifes {

    private LinkedList<Picture> lifeIcon = new LinkedList<>();
    private SpaceShip ship;
    private int inicialXposition;


    public Lifes(SpaceShip player, int inicialXposition) {

        this.ship = player;
        this.inicialXposition = inicialXposition;

        lifeIcon.add(new Picture(inicialXposition, 785, "rsz_heart.png"));
        lifeIcon.add(new Picture(inicialXposition + 20, 785, "rsz_heart.png"));
        lifeIcon.add(new Picture(inicialXposition + 40, 785,"rsz_heart.png"));
    }

    public void tick() {

    }

    public void render() {
        for (int i = 0; i < ship.getHp(); i++) {
            lifeIcon.get(i).draw();
        }
    }

    public void lifeUp() {

        switch (lifeIcon.size()) {
            case 1:
                lifeIcon.add(new Picture(inicialXposition + 20, 785, "rsz_heart.png"));
                break;
            case 2:
                lifeIcon.add(new Picture(inicialXposition + 40, 785, "rsz_heart.png"));
                break;

        }
    }

    public void hit() {

        lifeIcon.get(lifeIcon.size() - 1).delete();
        lifeIcon.remove(lifeIcon.get(lifeIcon.size() - 1));
    }
}

