public abstract class Armour extends Item{
    protected int defBonus;
    protected int HPBonus;
    protected int speedBonus;
    protected int lukBonus;
    public int getDefBonus(){
	return def;
    }
    public int getHPBonus(){
	return HPBonus;
    }
    public int getSpeedBonus(){
	return speedBonus();
    }
    public int getLukBonus(){
	return luk;
    }
}
