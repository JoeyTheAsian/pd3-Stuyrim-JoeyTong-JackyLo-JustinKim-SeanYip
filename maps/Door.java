public class Door extends MapObject{
    IconImage opened;
    public Door(ImageIcon closed, ImageIcon opened){
	canBreak = false;
	canWalkOn = false;
	canUse = true;
	this.opened = opened;
	texture = image;
    }
    public setWalkTrue(){
	canWalkOn = true;
    }
    public setWalkFalse(){
	canWalkOn = false;
    }
    public setWalk(boolean b){
	canWalkOn = b;
    }
}
