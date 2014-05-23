public abstract class Item{
    private String name;
    private String description;
    private int value;
    private int rarity;
    
    public int getVal(){
	return value;
    }
    public String getName(){
	return name;
    }
    public int getRarity(){
	return rarity;
    }	
    public void setName(String s){
	name = s;
    }
    public void setRarity(int r){
	rarity = r;
    }
    public void setDesc(String d){
	description = d;
    }
    public void setValue(int v){
	value = v;
    }

}
