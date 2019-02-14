package GameObjects;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Arrow {

    private Picture arrow;

    private int singlePlayerPosition = 400;
    private int multiPlayerPosition = 495;
    private int versusPlayerPosition = 585;
    private int instructionsPosition = 675;

    private int yToMove = 95;
    private String arrowPicSource = "arrow.png";

    public Arrow() {
        this.arrow = new Picture(680, 400, arrowPicSource);
    }

    public void moveUp() {

        arrow.setY(arrow.getY() - yToMove);

        if (arrow.getY() <= 400)
            arrow.setY(400);

    }

    public void moveDown() {

        arrow.setY(arrow.getY() + yToMove);

        if (arrow.getY() >= 675)
            arrow.setY(675);

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

    public int getSinglePlayerPosition(){
        return singlePlayerPosition;
    }

    public int getMultiPlayerPosition(){
        return multiPlayerPosition;
    }

    public int getVersusPlayerPosition() {
        return versusPlayerPosition;
    }

    public int getInstructionsPosition() {
        return instructionsPosition;
    }
}
