import java.util.*;
import java.io.*;

public class Golem extends Monster{

    public Golem(){
	name = "Golem";
	descript = "a rock";
	lvl = 1;
	HP = 200 * lvl;
	ATK = ((int)(Math.random() * 50) + 25) * lvl;
	DEF = ((int)(Math.random() * 100) + 100) * lvl;
	luck = 100 * lvl;
	speed = 1;
    }

    public void setList(){
	
    }

    public void sAttack(){
	//boulders that stun you
    }
