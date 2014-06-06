import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferStrategy;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;


public class GamePanel extends JPanel {
    /*
      BUTTON FRAMEWORK
      Button.setOpaque(false);
      Button.setBorderPainted(false);
      Button.setContentAreaFilled(false);
      Button.setSize(width/10,height/20);
      Button.setVerticalTextPosition(SwingConstants.CENTER);
      Button.setHorizontalTextPosition(SwingConstants.CENTER);
      Button.setFont(new Font("TimesRoman", Font.PLAIN, 20));
      Button.setLocation((width/3)-(width/20), (height/7*6)-(height/40));
      Image i1 = new ImageIcon("").getImage().getScaledInstance
      (Button.getWidth(),Button.getHeight(),java.awt.Image.SCALE_SMOOTH);
      Button.setIcon(new ImageIcon(img));
      Button.setForeground(Color.white);
      Button.addActionListener(e -> {
      });
    */
    private int windowHeight = Toolkit.getDefaultToolkit().getScreenSize().height-37;
    private int windowWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int height = windowHeight / 6;
    private int width = windowWidth;
    private Image bg;
    public GamePanel() {
	setBounds(0, windowHeight / 5 * 4, windowWidth, windowHeight);
	try {bg = ImageIO.read(new File(""));}
	catch (Exception e) {Utilities.showErrorMessage(this, e);}
	setVisible(true);
	repaint();			 
	//add buttons
	JButton InventButton = new JButton("Inventory");
	InventButton.setOpaque(false);
	InventButton.setBorderPainted(false);
	InventButton.setContentAreaFilled(false);
	InventButton.setSize(width/10,height/20);
	InventButton.setVerticalTextPosition(SwingConstants.CENTER);
	InventButton.setHorizontalTextPosition(SwingConstants.CENTER);
	InventButton.setFont(new Font("TimesRoman", Font.PLAIN, 20));
	InventButton.setLocation(width-(width/20), (height)-(height/3));
	Image i1 = new ImageIcon("GUI Images/Button.png").getImage().getScaledInstance
	    (InventButton.getWidth(),InventButton.getHeight(),java.awt.Image.SCALE_SMOOTH);
	InventButton.setIcon(new ImageIcon(i1));
	InventButton.setForeground(Color.white);
	InventButton.addActionListener(e -> {
		

	    });

        JButton MenuButton = new JButton("Inventory");
	MenuButton.setOpaque(false);
	MenuButton.setBorderPainted(false);
	MenuButton.setContentAreaFilled(false);
	MenuButton.setSize(width/10,height/20);
        MenuButton.setVerticalTextPosition(SwingConstants.CENTER);
	MenuButton.setHorizontalTextPosition(SwingConstants.CENTER);
	MenuButton.setFont(new Font("TimesRoman", Font.PLAIN, 20));
	MenuButton.setLocation(width-(width/20), (height)-(height/3*2));
	MenuButton.setIcon(new ImageIcon(i1));
	MenuButton.setForeground(Color.white);
	MenuButton.addActionListener(e -> {
			

	    });
	add(MenuButton);
	add(InventoryButton);
	addKeyListener(new KeyListener() {

		public void keyPressed(KeyEvent e) {
		    if (e.getKeyChar() == 'w') {
		    }
		    if (e.getKeyChar() == 's') {
		    }
		    if (e.getKeyChar() == 'a') {
		    }
		    if (e.getKeyChar() == 'd') {
		    }
		}
		public void keyReleased(KeyEvent e) {
		    if (e.getKeyChar() == 'w') {
		    }
		    if (e.getKeyChar() == 's') {
		    }
		    if (e.getKeyChar() == 'a') {
		    }
		    if (e.getKeyChar() == 'd') {
		    }
		}
	
		public void keyTyped(KeyEvent e) {}
	    });
	revalidate();
    }
			    
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	g.drawImage(bg,0,0,width,height,null);
	g.dispose();
    }
}
