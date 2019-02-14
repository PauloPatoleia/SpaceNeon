package GameObjects;

import GameObjects.SpaceShip;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.lang.reflect.Array;
import java.util.LinkedList;

public class Lifes {

    private LinkedList<Picture> lifeIcon = new LinkedList<>();
    private SpaceShip ship;
    private int inicialXposition;
    private String hpImage;


    public Lifes(SpaceShip player, String hpImage, int inicialXposition) {

        this.ship = player;
        this.inicialXposition = inicialXposition;
        this.hpImage = hpImage;

        lifeIcon.add(new Picture(inicialXposition, 787, hpImage));
        lifeIcon.add(new Picture(inicialXposition + 20, 787, hpImage));
        lifeIcon.add(new Picture(inicialXposition + 40, 787, hpImage));
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
                lifeIcon.add(new Picture(inicialXposition + 20, 787, hpImage));
                break;
            case 2:
                lifeIcon.add(new Picture(inicialXposition + 40, 787, hpImage));
                break;

        }
    }

    public void hit() {

        lifeIcon.get(lifeIcon.size() - 1).delete();
        lifeIcon.remove(lifeIcon.get(lifeIcon.size() - 1));
    }
}

