import java.awt.Container;
import java.awt.Dimension;
import static java.awt.EventQueue.invokeLater;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import static java.lang.System.nanoTime;
import static java.lang.System.out;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Game extends JFrame {
	JButton button1 = new JButton("Button 1");
	JButton button2 = new JButton("Button 2");
	JButton button3 = new JButton("Button 3");
	JComboBox<String> themeComboBox;
	
	public Game() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Game");
		setLayout(null);
		setSize(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getSize());
		setMinimumSize(new Dimension(450, 100));
		
		UIManager.LookAndFeelInfo[] installedLookAndFeels = UIManager.getInstalledLookAndFeels();
		HashMap<String, String> _lookAndFeels = new HashMap<>();
		String[] installedLookAndFeelsNames = new String[installedLookAndFeels.length];
		for (int i = 0; i < installedLookAndFeels.length; i++) {
			_lookAndFeels.put(installedLookAndFeels[i].getName(), installedLookAndFeels[i].getClassName());
			installedLookAndFeelsNames[i] = installedLookAndFeels[i].getName();
		}
		final HashMap<String, String> lookAndFeels = _lookAndFeels;
		themeComboBox = new JComboBox<>(installedLookAndFeelsNames);
		themeComboBox.setSelectedItem(UIManager.getLookAndFeel().getName());
		themeComboBox.addActionListener(e -> {
				try {UIManager.setLookAndFeel(lookAndFeels.get((String) themeComboBox.getSelectedItem()));}
				catch (Exception e2) {Utilities.showErrorMessage(this, e2);}
				//for (Component c : getComponents()) {c.paint(getGraphics());}
				SwingUtilities.updateComponentTreeUI(this);
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
	}
	
	public static void main(String[] args) {
		long t1 = nanoTime();
		invokeLater(() -> new Game().setVisible(true));
		long t2 = nanoTime();
		out.println("Initialization time: " + (t2 - t1) + " ns / " + ((double) (t2 - t1) / 1000000) + " ms / " + ((double) (t2 - t1) / 1000000000) + " s");
	}
}
