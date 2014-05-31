import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Character {
	private BufferedImage image;
	private int x, y;
	private String imageLocation;
	
	public Character() {}
	public Character(String imageLocation, int x, int y) {
		setImage(imageLocation);
		setX(x);
		setY(y);
	}
	
	public final BufferedImage getImage() {return image;}
	public final String getImageLocation() {return imageLocation;}
	public final int getHeight() {return image.getHeight();}
	public final int getWidth() {return image.getWidth();}
	public final int getX() {return x;}
	public final int getY() {return y;}
	
	public final void setImage(String imageLocation) {
		try {image = ImageIO.read(new File(imageLocation));}
		catch (Exception e) {Utilities.showErrorMessage(null, e);}
	}
	public final void setX(int x) {this.x = x;}
	public final void setY(int y) {this.y = y;}
}