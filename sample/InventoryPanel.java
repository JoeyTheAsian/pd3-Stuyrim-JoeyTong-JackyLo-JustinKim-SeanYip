import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class InventoryPanel extends JPanel {
	private BufferedImage image;
    private Inventory inventory;
    private JTable inventoryTable;
    private JScrollPane inventoryTableScrollPane;
    private int height =  (Toolkit.getDefaultToolkit().getScreenSize().height-37)/2;
    private int width =  (Toolkit.getDefaultToolkit().getScreenSize().width)/2;
	private final String[] COLUMN_NAMES = {"Equip", "Name", "Level", "Type", "Rarity"};

    public InventoryPanel(Inventory inventory) {
	this.inventory = inventory;
	inventoryTable = new JTable(getInventoryArray(), COLUMN_NAMES);
	TableRowSorter<TableModel> inventoryTableRowSorter = new TableRowSorter<>(inventoryTable.getModel());
	//inventoryTableRowSorter.setComparator(0, (Item i1, Item i2) -> i1.isEquipped().compareTo(i2.isEquipped())); //isEquipped has not been implmented yet
	inventoryTableRowSorter.setComparator(1, (Item i1, Item i2) -> i1.getName().compareTo(i2.getName()));
	inventoryTableRowSorter.setComparator(2, (Item i1, Item i2) -> (new Integer(i1.getLevel())).compareTo(i2.getLevel()));
	//inventoryTableRowSorter.setComparator(3, (Item i1, Item i2) -> i1.getType().compareTo(i2.getType()));
	inventoryTableRowSorter.setComparator(4, (Item i1, Item i2) -> (new Integer(i1.getRarity())).compareTo(i2.getRarity()));
	inventoryTable.setRowSorter(inventoryTableRowSorter);
	inventoryTableScrollPane = new JScrollPane(inventoryTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	try {image = ImageIO.read(new File("GUI Images/trimmed wood background.png"));}
	catch (Exception e) {Utilities.showErrorMessage(this, e);}
		add(inventoryTableScrollPane);
	addFocusListener(new FocusListener() {
		public void focusGained(FocusEvent e) {
			for (Object[] a : getInventoryArray()) {System.out.print(Arrays.toString(a));}
			System.out.println();
			DefaultTableModel inventoryTableModel = new DefaultTableModel(getInventoryArray(), COLUMN_NAMES);
			inventoryTable.setModel(inventoryTableModel);
			inventoryTableModel.fireTableDataChanged();
			inventoryTable.revalidate();
			inventoryTable.repaint();
			inventoryTableScrollPane.repaint();
			repaint();
			//Still no table refresh.
		}
		public void focusLost(FocusEvent e) {
		    requestFocusInWindow();
		}	
		});
	inventoryTable.setOpaque(false);
	inventoryTableScrollPane.setOpaque(false);
	inventoryTableScrollPane.getViewport().setOpaque(false);
	setVisible(false);
    }
	
    public Inventory getInventory() {
	return inventory;
    }
	
	protected Object[][] getInventoryArray() {
		Object[][] inventoryTableData = new Object[inventory.size()][5];
		for (int i = 0; i < inventory.size(); i++) {
			inventoryTableData[i][0] = new Boolean(false);
			inventoryTableData[i][1] = inventory.get(i).getName();
			inventoryTableData[i][2] = inventory.get(i).getLevel();
			inventoryTableData[i][3] = null;
			inventoryTableData[i][4] = inventory.get(i).getRarity();
		}
		return inventoryTableData;
	}
    public void setInventory(Inventory inventory) {
	this.inventory = inventory;
    }
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	g.drawImage(image,0,0,width,height,null);
    }

}
