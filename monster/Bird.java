import java.util.*;
import java.io.*;

public class Bird extends Monster{

    public Bird(){
	name = "Bird";
	desc = "Flying Death";
	lvl = 1;
	HP = 750;
	ATK = (int)(Math.random() * 50) + 25;
	Def = (int)(Math.random() * 50) + 25;
	//luck = ;
	//speed = ;
	sprite = new ImageIcon("").getImage();
	image = new ImageIcon("").getImage();
	mana = 100;
    }

    public void setList(){

    }

    public void sAttack(){
	
    }

}