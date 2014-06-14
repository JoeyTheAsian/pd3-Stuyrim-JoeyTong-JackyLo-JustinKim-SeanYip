import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

/* Represents an object on the map that exists at specific tile coordinates.
 * Contains a sprite to draw and a location to draw it at.
 */
public class MapObject implements Drawable {

    private int x, y;
    private String texturePath;
    private Image texture;

    public MapObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setImage(String imageLocation) {
        texturePath = imageLocation;
        try {
            texture = ImageIO.read(new File(texturePath));
        } catch (Exception e) {
            Utilities.showErrorMessage(null, e);
        }
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    public Image getImage() {
        return texture;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}