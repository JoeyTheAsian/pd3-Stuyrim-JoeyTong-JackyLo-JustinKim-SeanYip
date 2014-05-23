public class Weapon extends Item{
    private int maxDmg;
    private int minDmg;

    public Weapon(int value, int rarity, String name, int maxDmg, int minDmg, String desc){
        this.value=value;
	this.rarity=rarity;
	this.name=name;
<<<<<<< HEAD
	this.Desc=desc;
=======
	this.desc=desc;
>>>>>>> 1baf47017c86a30b29a401f196523471ecad4121
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
