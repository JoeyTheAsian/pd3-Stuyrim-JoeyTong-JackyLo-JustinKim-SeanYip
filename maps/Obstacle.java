import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.util.HashMap;
public class Obstacle extends mapObject{
    public Obstacle(ImageIcon image){
	canUse= false;
	canWalkOn=false;
	canBreak = false;
	texture = image;
    }
}
