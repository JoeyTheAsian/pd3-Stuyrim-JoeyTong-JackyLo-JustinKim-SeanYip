import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
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

public class Game extends JFrame {
	BufferedImage image;
	JButton optionsButton = new JButton("Options");
	JComboBox<String> themeComboBox;
	JPanel gamePanel;
	JSeparator separator1 = new JSeparator();
	
	public Game() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Game");
		setLayout(null);
		setSize(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getSize());
		setMinimumSize(new Dimension(670, 390));
		Container pane = getContentPane();
		gamePanel = new GamePanel();
		pane.add(gamePanel);
		pack();
		setLocationRelativeTo(null);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		gamePanel.setSize(getSize());
	}
	
	public class GamePanel extends JPanel {
		public GamePanel() {
			setLayout(null);
			setMinimumSize(new Dimension(670, 390));
			setSize(getMinimumSize());

			/*LookAndFeelInfo[] installedLookAndFeels = UIManager.getInstalledLookAndFeels();
			HashMap<String, String> lookAndFeels = new HashMap<>();
			String[] installedLookAndFeelsNames = new String[installedLookAndFeels.length];
			for (int i = 0; i < installedLookAndFeels.length; i++) {
				lookAndFeels.put(installedLookAndFeels[i].getName(), installedLookAndFeels[i].getClassName());
				installedLookAndFeelsNames[i] = installedLookAndFeels[i].getName();
			}
			themeComboBox = new JComboBox<>(installedLookAndFeelsNames);
			themeComboBox.setSelectedItem(UIManager.getLookAndFeel().getName());
			themeComboBox.addActionListener(e -> {
					try {UIManager.setLookAndFeel(lookAndFeels.get((String) themeComboBox.getSelectedItem()));}
					catch (Exception e2) {Utilities.showErrorMessage(this, e2);}
					SwingUtilities.updateComponentTreeUI(this);
			});*/
			try {image = ImageIO.read(new File("Title_Screen_1.png"));}
			catch (Exception e) {Utilities.showErrorMessage(this, e);}
			
			//themeComboBox.setSize(100, 25);
			/*optionsButton.addActionListener(e -> {
					JOptionPane.
			});*/
			optionsButton.setSize(100, 25);
			separator1.setLocation(10, 320);
			optionsButton.setLocation(10, 330);
			
			add(separator1);
			add(optionsButton);
			pack();
			setVisible(true);
		}
		
		public void paintComponent(Graphics g) {
			//themeComboBox.setLocation(getWidth() / 2 + 115, 10);
			g.drawImage(image, getWidth() / 2 - image.getWidth() / 2, 10, null);
			separator1.setSize(getWidth() - 20, 1);
			
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
		SwingUtilities.invokeLater(() -> new Game().setVisible(true));
		long t2 = System.nanoTime();
		out.println("Initialization time: " + (t2 - t1) + " ns / " + ((double) (t2 - t1) / 1000000) + " ms / " + ((double) (t2 - t1) / 1000000000) + " s");
	}
}
