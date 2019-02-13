import org.academiadecodigo.simplegraphics.pictures.Picture;

public class TopBar {

    private Picture topBarImage;

    public TopBar(String topBarImageSource) {
        this.topBarImage = new Picture(10, 10, topBarImageSource);
    }

    public void tick() {

    }

    public void render() {
        topBarImage.draw();
    }
}
