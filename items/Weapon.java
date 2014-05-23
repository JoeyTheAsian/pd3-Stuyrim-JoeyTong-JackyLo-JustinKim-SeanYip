public class Weapon extends Item{
    private int maxDmg;
    private int minDmg;
    private String Description;

    public Weapon(int value, int rarity, String name, int maxDmg, int minDmg, String desc){
        setValue(value);
	setRarity(rarity);
	setName(name);
	setDesc(desc);
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
