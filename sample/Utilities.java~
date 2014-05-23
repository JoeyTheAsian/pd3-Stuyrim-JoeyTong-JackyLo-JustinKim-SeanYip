import java.awt.Component;
import javax.swing.JOptionPane;

public class Utilities {
	public static void showErrorMessage(Component parent, Exception e) {JOptionPane.showMessageDialog(parent, "An error has occurred. Stack trace:\n\n" + stackTraceToString(e), "Error", JOptionPane.ERROR_MESSAGE);}
	
	public static String stackTraceToString(Throwable e) {
		String stackTrace = e.toString();
		for (StackTraceElement ste : e.getStackTrace()) {stackTrace += "\n\tat " + ste;}
		return stackTrace;
	}
}
