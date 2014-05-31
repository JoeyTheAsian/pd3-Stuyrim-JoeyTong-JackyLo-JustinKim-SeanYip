import java.awt.KeyboardFocusManager;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Driver { 
	public static void main(String[] args) {
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch (Exception e) {Utilities.showErrorMessage(null, e);}
		long t1 = System.nanoTime();
		SwingUtilities.invokeLater(() -> new Game());
		long t2 = System.nanoTime();
		System.out.println("Initialization time: " + (t2 - t1) + " ns / " + ((double) (t2 - t1) / 1_000_000) + " ms / " + ((double) (t2 - t1) / 1_000_000_000) + " s");
		for (;;) {
			t2 = System.nanoTime();
			if ((t2 - t1) >= 1_000_000_000) {
				System.out.println(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner());
				t1 = System.nanoTime();
			}
		}
	}
}
