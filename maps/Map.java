public class Map{
    private mapObject[][]map;
    public Map(mapObjectp[][] map){
	map = map;
    }
    public mapObject[][] getMap(){
	return map;
    }
    public void removeObject(, int x, int y){
	map[y][x] = null;
    }
    public void addObject(mapObject o, int x, int y){
	map[y][x] = o;
    }
}
