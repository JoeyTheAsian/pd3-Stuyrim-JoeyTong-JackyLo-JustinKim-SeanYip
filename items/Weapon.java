public class Weapon extends Item{
    private int maxDmg;
    private int minDmg;

    public Weapon(int value, int rarity, String name, int maxDmg, int minDmg, String desc){
        this.value=value;
	this.rarity=rarity;
	this.name=name;
	this.Desc=desc;
	this.maxDmg = maxDmg;
	this.minDmg = minDmg;
    }
    
    public int getMaxDmg(){
	return maxDmg;
    }
    public int getMinDmg(){
	return minDmg;
    }
    
}
