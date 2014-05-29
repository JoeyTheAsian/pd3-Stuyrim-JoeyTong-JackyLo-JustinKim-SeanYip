import java.util.ArrayList;

public class Inventory {
    private static final int MAX_SIZE = 30;

    private ArrayList<Item> items;

    public Inventory() {
        items = new ArrayList<Item>(MAX_SIZE);
    }

    public boolean addItem(Item newItem) {
        if (isFull())
            return false;

        items.add(newItem);
        return true;
    }

    public boolean isFull() {
        return items.size() >= MAX_SIZE;
    }

    public void sort() {
        items = ItemSort.sort(items);
    }
}