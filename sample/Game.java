import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import static java.lang.System.out;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class Game extends JFrame {
	BufferedImage image;
	JButton button1 = new JButton("Button 1");
	JButton button2 = new JButton("Button 2");
	JButton button3 = new JButton("Button 3");
	JComboBox<String> themeComboBox;
	
	public Game() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Game");
		setLayout(null);
		setSize(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getSize());
		setMinimumSize(new Dimension(660, 380));
		
		LookAndFeelInfo[] installedLookAndFeels = UIManager.getInstalledLookAndFeels();
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
		});
		
		try {image = ImageIO.read(new File("Quote2.png"));}
		catch (Exception e) {Utilities.showErrorMessage(this, e);}
		
		addWindowListener(new WindowListener() {
			public void windowActivated(WindowEvent e) {paint(getGraphics());}
			public void windowClosed(WindowEvent e) {}
			public void windowClosing(WindowEvent e) {}
			public void windowDeactivated(WindowEvent e) {}
			public void windowDeiconified(WindowEvent e) {}
			public void windowIconified(WindowEvent e) {}
			public void windowOpened(WindowEvent e) {}
		});
		Container pane = getContentPane();
		button1.setSize(100, 25);
		button2.setSize(100, 25);
		button3.setSize(100, 25);
		themeComboBox.setSize(100, 25);
		
		pane.add(button1);
		pane.add(button2);
		pane.add(button3);
		pane.add(themeComboBox);
		pack();
		setLocationRelativeTo(null);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		button1.setLocation(getWidth() / 2 - 215, 10);
		button2.setLocation(getWidth() / 2 - 105, 10);
		button3.setLocation(getWidth() / 2 + 5, 10);
		themeComboBox.setLocation(getWidth() / 2 + 115, 10);
		g.drawImage(image, getWidth() / 2 - image.getWidth() / 2, 70, null);
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
		} catch (Exception e) {e.printStackTrace();}
		long t1 = System.nanoTime();
		SwingUtilities.invokeLater(() -> new Game().setVisible(true));
		long t2 = System.nanoTime();
		out.println("Initialization time: " + (t2 - t1) + " ns / " + ((double) (t2 - t1) / 1000000) + " ms / " + ((double) (t2 - t1) / 1000000000) + " s");
	}
}
