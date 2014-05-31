import java.awt.Component;
import java.io.File;
import java.nio.file.Files;
import java.security.MessageDigest;
import javax.swing.JOptionPane;
import javax.xml.bind.DatatypeConverter;

public class Utilities {
	public static String hash(String file, String algorithm) {
		try {return DatatypeConverter.printHexBinary(MessageDigest.getInstance(algorithm).digest(Files.readAllBytes((new File(file)).toPath()))).toLowerCase();}
		catch (Exception e) {showErrorMessage(null, e);}
		return "";
	}

	public static void showErrorMessage(Component parent, Exception e) {JOptionPane.showMessageDialog(parent, "An error has occurred. Stack trace:\n\n" + stackTraceToString(e), "Error", JOptionPane.ERROR_MESSAGE);}

	public static String stackTraceToString(Throwable e) {
		String stackTrace = e.toString();
		for (StackTraceElement ste : e.getStackTrace()) {stackTrace += "\n\tat " + ste;}
		return stackTrace;
	}
	
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Usage: java Utilities <file> <algoritm>");
			return;
		}
		System.out.println(hash(args[0], args[1]));
	}
}
