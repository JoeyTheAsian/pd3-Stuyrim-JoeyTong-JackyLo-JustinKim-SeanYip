import java.awt.Image;

public interface Drawable{
    void setImage(String imageLocation);
    void setX(int x);
    void setY(int y);
    
    Image getImage();
    int getX();
    int getY();
}
