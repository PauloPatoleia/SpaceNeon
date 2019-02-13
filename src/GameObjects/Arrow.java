package GameObjects;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Arrow {

    private Picture arrow;

    private int onePlayerPosition = 405;
    private int twoPlayerPosition = 500;
    private int instructionsPosition = 595;
    private int optionsPosition = 690;

    private int yToMove = 95;
    private String arrowPicSource = "arrow.png";


    public Arrow(){
        this.arrow = new Picture(680, 405, arrowPicSource);
    }

    public void moveUp() {

        arrow.setY(arrow.getY() - yToMove);

        if (arrow.getY() <= 405)
            arrow.setY(405);

    }

    public void moveDown() {

        arrow.setY(arrow.getY() + yToMove);

        if (arrow.getY() >= 690)
            arrow.setY(690);

    }

    public void render() {
        arrow.draw();
    }

    public void delete() {
        arrow.delete();
    }

    public int getY() {
        return arrow.getY();
    }
    public int getOnePlayerPosition(){
        return onePlayerPosition;
    }

    public int getTwoPlayerPosition(){
        return twoPlayerPosition;
    }

    public int getInstructionsPosition() {
        return instructionsPosition;
    }

    public int getOptionsPosition() {
        return optionsPosition;
    }
}
