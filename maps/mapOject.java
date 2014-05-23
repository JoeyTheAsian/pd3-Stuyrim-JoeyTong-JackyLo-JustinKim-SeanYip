public abstract class mapObject{
    private boolean canWalkOn;
    private boolean canUse;
    private texture texture;
    
    public boolean canWalk(){
	return canWalkOn;
    }
    public boolean canUse(){
	return canUse;
    }
    //may have to revise this  vvv
    public String getTexture(){
	return texture.dir;
    }

    
}
