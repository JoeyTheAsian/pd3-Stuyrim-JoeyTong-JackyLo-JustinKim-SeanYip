import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class GamePanel extends JPanel {
    private int windowHeight = Toolkit.getDefaultToolkit().getScreenSize().height-37;
    private int windowWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int height = windowHeight / 6;
    private int width = windowWidth;
	private boolean[] keysPressed = new boolean[256];
    private BufferedImage bg;
	
    public GamePanel() {
		setLayout(null);
		setBounds(0, windowHeight / 5 * 4, windowWidth, windowHeight);
		try {bg = ImageIO.read(new File("GUI Images/wood background.png"));}
		catch (Exception e) {Utilities.showErrorMessage(this, e);}
		setVisible(true);


		//add buttons
		JButton InventButton = new JButton("Inventory");
		InventButton.setOpaque(false);
		InventButton.setBorderPainted(false);
		InventButton.setContentAreaFilled(false);
		InventButton.setVerticalTextPosition(SwingConstants.CENTER);
		InventButton.setHorizontalTextPosition(SwingConstants.CENTER);
		InventButton.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		InventButton.setBounds(width/6*5,0,width/6,height/3);
		//get button texture
		Image i1 = new ImageIcon("GUI Images/Button.png").getImage().getScaledInstance
			(InventButton.getWidth(),InventButton.getHeight(),java.awt.Image.SCALE_SMOOTH);
		InventButton.setIcon(new ImageIcon(i1));
		InventButton.setForeground(Color.white);
		InventButton.addActionListener(e -> {


			});

			JButton PartyButton = new JButton("Party");
		PartyButton.setOpaque(false);
		PartyButton.setBorderPainted(false);
		PartyButton.setContentAreaFilled(false);
		PartyButton.setVerticalTextPosition(SwingConstants.CENTER);
		PartyButton.setHorizontalTextPosition(SwingConstants.CENTER);
		PartyButton.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		PartyButton.setBounds(width/6*5,height/3,width/6,height/3);
		PartyButton.setIcon(new ImageIcon(i1));
		PartyButton.setForeground(Color.white);
		PartyButton.addActionListener(e -> {

			});
			JButton MenuButton = new JButton("Menu");
		MenuButton.setOpaque(false);
		MenuButton.setBorderPainted(false);
		MenuButton.setContentAreaFilled(false);
		MenuButton.setVerticalTextPosition(SwingConstants.CENTER);
		MenuButton.setHorizontalTextPosition(SwingConstants.CENTER);
		MenuButton.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		MenuButton.setBounds(width/6*5,height/3*2,width/6,height/3);
		MenuButton.setIcon(new ImageIcon(i1));
		MenuButton.setForeground(Color.white);
		MenuButton.addActionListener(e -> {

			});
		add(MenuButton);
		add(InventButton);
		add(PartyButton);
		addKeyListener(new KeyListener() {
				public void keyPressed(KeyEvent e) {keysPressed[e.getKeyCode()] = true;}
				public void keyReleased(KeyEvent e) {keysPressed[e.getKeyCode()] = false;}
				public void keyTyped(KeyEvent e) {
					System.out.println(e);
					movePlayer();
				}
			});
		revalidate();
    }
	
	private void movePlayer() {
		Character player = Screen.getCharacters().get(0);
		if (keysPressed[KeyEvent.VK_W] && (player.getY() > 0)) {player.setY(player.getY() - 5);}
		if (keysPressed[KeyEvent.VK_S] && (player.getY() < ((Toolkit.getDefaultToolkit().getScreenSize().height-37)/5*4))) {player.setY(player.getY() + 5);}
		if (keysPressed[KeyEvent.VK_A] && (player.getY() > 0)) {player.setX(player.getX() - 5);}
		if (keysPressed[KeyEvent.VK_D] && (player.getY() > Toolkit.getDefaultToolkit().getScreenSize().width)) {player.setX(player.getX() + 5);}
	}
	
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bg,0,0,width,height, null);
    }
}
