public enum Tile {
    GRASS (0, "sprites/grass.png"),
    DIRT (1, "sprites/dirt.png");

    public int id;
    public String texture;

    Tile(int id, String texture) {
        this.id = id;
        this.texture = texture;
    }
}
