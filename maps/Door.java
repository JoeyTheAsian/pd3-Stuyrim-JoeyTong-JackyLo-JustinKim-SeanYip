public class Door extends MapObject{
    canBreak = false;
    canWalkOn = false;
    canUse = true;
    public Door(ImageIcon image){
	texture = image;
    }
    public setWalk(boolean b){
	canWalkOn = b;
    }
}
