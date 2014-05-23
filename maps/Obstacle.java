<<<<<<< HEAD
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.util.HashMap;
=======
import javax.swing.Icon;
import javax.swing.ImageIcon;
>>>>>>> b5a7804b66dbe93cb9d31ca4d2a660c8ad3f2a9e
public class Obstacle extends mapObject{
    public Obstacle(ImageIcon image){
	canUse= false;
	canWalkOn=false;
	canBreak = false;
	texture = image;
    }
}
