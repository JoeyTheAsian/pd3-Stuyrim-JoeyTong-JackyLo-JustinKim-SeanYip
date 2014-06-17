import java.awt.Image;
import java.io.File;
import java.util.HashMap;
import javax.imageio.ImageIO;

public class Map {

    public static final int NUM_ROWS = 20;
    public static final int NUM_COLS = 25;

    private Tile[][] tilemap;
    private HashMap<Integer, Image> textures;

    public Map() {
        tilemap = new Tile[NUM_COLS][NUM_ROWS];
        textures = new HashMap<>();
        setAll(Tile.GRASS);
	for (int i = 0; i < 50; i++){
	    int x = (int)(Math.random()*23)+1;
	    int y = (int)(Math.random()*18)+1;
	    setTile(x,y,Tile.DIRT);
	}
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < NUM_COLS; i++) {
            for (int j = 0; j < NUM_ROWS; j++) {
                sb.append(tilemap[i][j].id + " ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public int getWidth() {
        return tilemap[0].length;
    }

    public int getHeight() {
        return tilemap.length;
    }

    public Tile[][] getTileMap() {
        return tilemap;
    }

    public Tile getTile(int x, int y) {
        return tilemap[x][y];
    }

    public void setTile(int x, int y, Tile tile) {
        tilemap[x][y] = tile;
    }

    public void setAll(Tile tile) {
        for (int i = 0; i < NUM_COLS; i++) {
            for (int j = 0; j < NUM_ROWS; j++) {
                setTile(i, j, tile);
            }
        }
    }

    public void setColumn(int x, Tile tile) {
        for (int i = 0; i < tilemap[0].length; i++) {
            setTile(x, i, tile);
        }
    }

    public void setRow(int y, Tile tile) {
        for (int i = 0; i < tilemap.length; i++) {
            setTile(i, y, tile);
        }
    }

    public Image getTexture(Tile tile) {
        ensureTextureExistence(tile);
        return textures.get(tile.id);
    }

    private void ensureTextureExistence(Tile tile) {
        if (!textures.containsKey(tile.id)) {
            try {
                Image image = ImageIO.read(new File(tile.texture));
                textures.put(tile.id, image);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
