
public enum Tile {
    GRASS   (0, "sprites/grass.png");

    public int id;
    public String texture;

    Tile(int id, String texture) {
        this.id = id;
        this.texture = texture;
    }
}
