public abstract class Item{
    protected String name;
    protected String description;
    protected int value;
    protected int rarity;
    public int getValue(){
	return value;
    }
    public String getName(){
	return name;
    }
    public String getDesc(){
	return description;
    }
    public int getRarity(){
	return rarity;
    }

}
