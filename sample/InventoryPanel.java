import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
//import javax.swing.table.TableRowSorter;

public class InventoryPanel extends JPanel {
	private Inventory inventory;
	private JTable inventoryTable;
	private JScrollPane inventoryTableScrollPane;
	
	public InventoryPanel(Inventory inventory) { //I did not see the Inventory class in the /items directory
		this.inventory = inventory;
		Object[][] inventoryTableData = new Object[inventory.size()][5];
		for (int i = 0; i < inventory.size(); i++) {
			inventoryTableData[i][0] = new Boolean(false); //When using constructor JTable(Object[][], Object[]), booleans are automatically converted to checkboxes.
			inventoryTableData[i][1] = inventory.get(i).getName();
			inventoryTableData[i][2] = inventory.get(i).getLevel();
			//inventoryTableData[i][3] = inventory.get(i).getType();
			inventoryTableData[i][4] = inventory.get(i).getRarity();
		}
		inventoryTable = new JTable(inventoryTableData, new String[] {"Equip", "Name", "Level", "Type", "Rarity"});
		TableRowSorter<TableModel> inventoryTableRowSorter = new TableRowSorter<>(inventoryTable.getModel());
		//inventoryTableRowSorter.setComparator(0, (Item i1, Item i2) -> i1.isEquipped().compareTo(i2.isEquipped())); //isEquipped has not been implmented yet
		inventoryTableRowSorter.setComparator(1, (Item i1, Item i2) -> i1.getName().compareTo(i2.getName()));
		inventoryTableRowSorter.setComparator(2, (Item i1, Item i2) -> (new Integer(i1.getLevel())).compareTo(i2.getLevel()));
		//inventoryTableRowSorter.setComparator(3, (Item i1, Item i2) -> i1.getType().compareTo(i2.getType()));
		inventoryTableRowSorter.setComparator(4, (Item i1, Item i2) -> (new Integer(i1.getRarity())).compareTo(i2.getRarity()));
		inventoryTable.setRowSorter(inventoryTableRowSorter);
		inventoryTableScrollPane = new JScrollPane(inventoryTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(inventoryTableScrollPane);
		setVisible(true);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
