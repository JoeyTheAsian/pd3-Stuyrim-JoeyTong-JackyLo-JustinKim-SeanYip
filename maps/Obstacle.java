import javax.swing.Icon;
import javax.swing.ImageIcon;
public class Obstacle extends mapObject{
    public Obstacle(ImageIcon image){
	canUse= false;
	canWalkOn=false;
	canBreak = false;
	texture = image;
    }
}
