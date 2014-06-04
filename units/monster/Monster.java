import java.util.*;
import java.io.*;

public abstract class Monster extends Unit{

    public Monster(int x, int y){
	super(x,y);
    }

    public abstract void setList();

}
