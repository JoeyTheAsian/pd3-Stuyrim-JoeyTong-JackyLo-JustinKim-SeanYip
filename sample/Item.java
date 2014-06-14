import java.awt.Image;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;

public class Item implements Comparable<Item>, Drawable {
	protected String name;
	protected String description;
	protected Image image;
	protected int level;
	protected int value;
	protected int rarity;
	protected int x, y;
	public Item(Image image, String name, String description, int level, int value, int rarity){
		this.image = image;
	    this.name = name;
	    this.description = description;
	    this.level = level;
	    this.rarity = rarity;
	}

	public int getValue(){
	return value;
	}
	public int getLevel() {
		return level;
	}
	public String getName(){
	return name;
	}
	public String getDesc(){
	return description;
	}
	public int getRarity(){
	return rarity;
	}
	public Image getImage() {return image;}
	public int getX() {return x;}
	public int getY() {return y;}
	
	public void setImage(String location) {
		try {image = ImageIO.read(new File(location));}
		catch (Exception e) {Utilities.showErrorMessage(null, e);}
	}
	public void setX(int x) {this.x = x;}
	public void setY(int y) {this.y = y;}
	/* Items are sorted based on a hierarchy of qualities:
	 *	Item type > Rarity > Level > Name
	 *
	 * Item type order: Weapon, shield, head, torso, back, hands, legs, feet,
	 * accessory, consumable, misc.
	 * Rarity goes from 5 to 1, with 5 being the rarest and coming first.
	 * Level is sorted highest first.
	 * Name is just lexicographic order.
	 */
	public int compareTo(Item other) {
		// Check item type here
		String[] types = {"Weapon", "Shield", "Head", "Torso", "Back", "Hands",
			"Legs", "Feet", "Accessory", "Consumable", "Misc"};
		List<String> typeOrder = Arrays.asList(types);

		int myRank = typeOrder.indexOf(getName());
		int otherRank = typeOrder.indexOf(other.getName());

		if (myRank > otherRank) {
			return 1;
		} else if (myRank < otherRank) {
			return -1;
		}


		// Compare by rarity
		if (rarity > other.getRarity()) {
			return 1;
		} else if (rarity < other.getRarity()) {
			return -1;
		}

		// Compare by level
		if (level > other.getLevel()) {
			return 1;
		} else if (level < other.getLevel()) {
			return -1;
		}

		// Compare by name
		//
		// Because this is the last sorted criteria, we assume that all other
		// criteria are equal in both items, so simply return the result of
		// this last comparison.
		return name.compareTo(other.getName());
	}

}
