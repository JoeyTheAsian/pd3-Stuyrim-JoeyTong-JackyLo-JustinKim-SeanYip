import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;

public class Swordsman extends Player{
    private BufferedImage down, up, left, right;
    protected Item shield = null;
    public Swordsman(){super();}
    public Swordsman(String imageLocation, int x, int y){
	super(imageLocation,x,y);
	try{
	left = ImageIO.read(new File("swordsman left.png"));
	right = ImageIO.read(new File("swordsman right.png"));
	up = ImageIO.read(new File("swordsman up.png"));
	down = ImageIO.read(new File("swordsman down.png"));
	}catch(Exception e){
	    Utilities.showErrorMessage(null,e);
	}
    }
    public final void setDown() {setImage(down);}
    public final void setUp(){setImage (up);}
    public final void setLeft() {setImage (left);}
    public final void setRight() {setImage( right);}
}
