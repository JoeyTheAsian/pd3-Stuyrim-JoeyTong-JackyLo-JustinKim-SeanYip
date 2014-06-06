import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;

public class InventoryPanel extends JPanel {
    private ArrayList<Item> inventory;
    private JTable inventoryTable;
    private JScrollPane inventoryTableScrollPane;
	
    public InventoryPanel(ArrayList<Item> inventory) {
	Object[][] inventoryTableData = new Object[inventory.size()][5];
	for (int i = 0; i < inventory.size(); i++) {
	    inventoryTableData[i][0] = new Boolean(false); //When using constructor JTable(Object[][], Object[]), booleans are automatically converted to checkboxes.
	    inventoryTableData[i][1] = inventory.get(i).getName();
	    inventoryTableData[i][2] = inventory.get(i).getLevel();
	    //inventoryTableData[i][3] = inventory.get(i).getType();
	    inventoryTableData[i][4] = inventory.get(i).getRarity();
	}
		
	//Item.compareTo will probably not be used. javax.swing.table.TableRowSorter uses java.util.Comparator to sort.
	//problem with that is that the items have to be sorted through a heierarchy sort (rarity, level, name,) can't remember the order
	setBackground(Color.BLACK);
	setVisible(false);
    }
    public void updateInventory(ArrayList<Item> inventory){
	Object[][] inventoryTableData = new Object[inventory.size()][5];
	for (int i = 0; i < inventory.size(); i++) {
	    inventoryTableData[i][0] = new Boolean(false); //When using constructor JTable(Object[][], Object[]), booleans are automatically converted to checkboxes.
	    inventoryTableData[i][1] = inventory.get(i).getName();
	    inventoryTableData[i][2] = inventory.get(i).getLevel();
	    //inventoryTableData[i][3] = inventory.get(i).getType();
	    inventoryTableData[i][4] = inventory.get(i).getRarity();
	}
		
    }
    public void paintComponent(Graphics g){
	super.paintComponent(g);

    }
}

