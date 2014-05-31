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
	
	public BufferedImage getImage() {return image;}
	public String getImageLocation() {return imageLocation;}
	public int getHeight() {return image.getHeight();}
	public int getWidth() {return image.getWidth();}
	public int getX() {return x;}
	public int getY() {return y;}
	
	public void setImage(String imageLocation) {
		try {image = ImageIO.read(new File(imageLocation));}
		catch (Exception e) {Utilities.showErrorMessage(null, e);}
	}
	public void setX(int x) {this.x = x;}
	public void setY(int y) {this.y = y;}
}
