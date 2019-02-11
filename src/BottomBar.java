import org.academiadecodigo.simplegraphics.pictures.Picture;

public class BottomBar {

    private Picture bottomBarImage;

    public BottomBar(String bottomBarImageSource) {
        // TODO: 11/02/2019 make this values dinamic
        this.bottomBarImage = new Picture(10, 770, bottomBarImageSource);
    }

    public void tick() {

    }

    public void render() {
        //bottomBarImage.delete();
        bottomBarImage.draw();
    }
}
