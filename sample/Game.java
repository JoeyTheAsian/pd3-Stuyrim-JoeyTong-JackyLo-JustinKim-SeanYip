import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.Canvas;
import java.io.File;
import static java.lang.System.out;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.SwingConstants;

public class Game extends JFrame {
    BufferedImage image;
    JPanel MenuPanel;
    Canvas Screen;
    Container pane;
    //screen dimensions
    int height =Toolkit.getDefaultToolkit().getScreenSize().height-37;
    int width = Toolkit.getDefaultToolkit().getScreenSize().width;
   

    public Game() {
	pane = getContentPane();
        MenuPanel = new MenuPanel();
	//MenuPanel mp = new Menu().new MenuPanel();
        Screen = new Screen();
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setTitle("The Senior Scrolls I: Stuyrim");
	setLayout(null);
	pane.add(MenuPanel);
	pack();
	setResizable(false);
	setSize(width, height);
	setLocationRelativeTo(null);
	setVisible(true);
    }
    public void paint(Graphics g) {
	super.paint(g);
    }
}
