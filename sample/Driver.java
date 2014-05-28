import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
public class Driver{ 

    public static void main(String[] args){
	try{
	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (Exception e) {Utilities.showErrorMessage(null, e);}
	long t1 = System.nanoTime();
	SwingUtilities.invokeLater(() ->{Game Game = new Game();});
	long t2 = System.nanoTime();
	System.out.println("Initialization time: "
			   + (t2 - t1) + " ns / " + ((double) (t2 - t1) / 1000000) +
			   " ms / " + ((double) (t2 - t1) / 1000000000) + " s");
    }	
}
