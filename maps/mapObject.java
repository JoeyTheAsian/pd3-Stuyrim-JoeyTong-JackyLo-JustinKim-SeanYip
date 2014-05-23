import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.util.HashMap;
public abstract class mapObject{
    protected boolean canWalkOn;
    protected boolean canUse;
    protected boolean canBreak;
    protected ImageIcon texture;
    public boolean canBreak(){
	return canBreak;
    }
    public boolean canWalk(){
	return canWalkOn;
    }
    public boolean canUse(){
	return canUse;
    }    
    public ImageIcon getTexture(){
	return texture;
    }
}
