import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.util.HashMap;
import javax.imageio.ImageIO;


public class Driver extends JFrame{
    static Image floor;
    static Image player;
    static Image map;

    public Driver(){
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	JPanel panel = new JPanel();

	setSize(dim.width, dim.height-37);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setTitle("Game");
	setLayout(null);
	setResizable(false);
	setLocationRelativeTo(null);
	setVisible(true);
	mapObject a = new Obstacle(new ImageIcon("map/Player.png"));
	player = a.texture.getImage();
    }

    public static void main(String[]args){
	new Driver();
    }
}
