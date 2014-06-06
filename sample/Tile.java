import java.awt.Color;

public enum Tile {
    // public int id;
    // public String texture;

    // Tile(int id, String texture) {
    //     this.id = id;
    //     this.texture = texture;
    // }
    // For testing

    FLOOR   (0, Color.WHITE),   WALL(1, Color.BLACK);
    public int id;
    public Color color;

    Tile(int id, Color color) {
        this.id = id;
        this.color = color;
    }
}
