public class Breakable extends MapObject{
    private ArrayList <Item> DropTable;
    canWalkOn = false;
    canUse = false;
    canBreak = true;
    
    public Breakable(ImageIcon image){
	texture = image;
    }
}
