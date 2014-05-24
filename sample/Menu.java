import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import static java.lang.System.out;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;


public class Menu extends JFrame {
    BufferedImage image;
    JButton optionsButton = new JButton("Options");
    JButton startButton = new JButton ("Start!");
    JComboBox<String> themeComboBox;
    JPanel Panel;

    //screen dimensions
    int height = Toolkit.getDefaultToolkit().getScreenSize().height-37;
    int width = Toolkit.getDefaultToolkit().getScreenSize().width;


    public Menu() {
	Container pane = getContentPane();
	Panel = new Panel();

	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setTitle("Game");
	setLayout(null);
	pane.add(Panel);
	pack();
	setResizable(false);
	setSize(width, height);
	setLocationRelativeTo(null);
    }
    
    public void paint(Graphics g) {
	super.paint(g);
    }
    
    public class Panel extends JPanel {
	public Panel() {
	    setLayout(null);
	    setSize(width, height);

	    try {image = ImageIO.read(new File("stuyrim.jpg"));}
	    catch (Exception e) {Utilities.showErrorMessage(this, e);}
	    
	    //themeComboBox.setSize(100, 25);
	    optionsButton.addActionListener(e -> {
	      });
	    optionsButton.setSize(width/10,height/20);
	    optionsButton.setLocation((width/3)-(width/20), (height/7*6)-(height/40));
	    startButton.setSize(width/10,height/20);
	    startButton.setLocation((width/3*2)-(width/20), (height/7*6)-(height/40));

	    add(optionsButton);
	    add(startButton);
	    pack();
	    setVisible(true);
	}
	
	public void paintComponent(Graphics g) {
	    //themeComboBox.setLocation(getWidth() / 2 + 115, 10);
	    g.drawImage(image,0,0,width,height, null);
	    
	}
	
	public class OptionsDialog extends JDialog {
	    public OptionsDialog() {
		
	    }
	}
    }
    
    public static void main(String[] args) {
	try {
	    //Use the following for the "Nimbus" look and feel (L&F):
	    /*for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
	      if ("Nimbus".equals(info.getName())) {
	      javax.swing.UIManager.setLookAndFeel(info.getClassName());
	      break;
	      }
	      }*/
	    //Use the following for the "System" (Windows/Mac/Linux/etc.) L&F:
	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (Exception e) {Utilities.showErrorMessage(null, e);}
	long t1 = System.nanoTime();
	SwingUtilities.invokeLater(() -> new Menu().setVisible(true));
	long t2 = System.nanoTime();
	out.println("Initialization time: " + (t2 - t1) + " ns / " + ((double) (t2 - t1) / 1000000) + " ms / " + ((double) (t2 - t1) / 1000000000) + " s");
    }
}
