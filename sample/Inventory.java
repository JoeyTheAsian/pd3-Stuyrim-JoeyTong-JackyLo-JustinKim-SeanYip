import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class Inventory implements Collection<Item> {
	private static final int MAX_SIZE = 30;
	
	private ArrayList<Item> inventory = new ArrayList<>(30);
	
	public Inventory() {}
	
	public boolean add(Item i) {
		if ((i == null) || (inventory.size() >= MAX_SIZE)) {return false;}
		inventory.add(i);
		return true;
	}
	
	public boolean add(int index, Item i) {
		if ((i == null) || (inventory.size() >= MAX_SIZE)) {return false;}
		inventory.add(index, i);
		return true;
	}
	
	public boolean addAll(Collection<? extends Item> c) {
		Iterator<? extends Item> cI = c.iterator();
		while (cI.hasNext()) {if (!(add(cI.next()))) {return false;}}
		return true;
	}
	
	public boolean addAll(int index, Collection<? extends Item> c) {
		Iterator<? extends Item> cI = c.iterator();
		for (int i = 0; cI.hasNext(); i++) {if (!(add(index + i, cI.next()))) {return false;}}
		return true;
	}
	
	public void clear() {inventory.clear();}
	
	public boolean contains(Object i) {return inventory.contains(i);}
	
	public boolean containsAll(Collection<?> c) {return inventory.containsAll(c);}
	
	public void ensureCapacity(int c) {inventory.ensureCapacity(c);}
	
	public boolean equals(Inventory i) {return Arrays.equals(toArray(), i.toArray());}
	
	public Item get(int index) {return inventory.get(index);}
	
	public int hashCode() {return inventory.hashCode();}
	
	public int indexOf(Object i) {return inventory.indexOf(i);}
	
	public boolean isEmpty() {return inventory.isEmpty();}
	
	public Iterator<Item> iterator() {return new Iterator<Item>() {
		int currentIndex = 0;
		public boolean hasNext() {return currentIndex < MAX_SIZE;}
		public Item next() {return inventory.get(currentIndex++);}
		public void remove() {throw new UnsupportedOperationException();}
		};
	}
	
	public int lastIndexOf(Object i) {return inventory.lastIndexOf(i);}
	
	public Item remove(int index) {return inventory.remove(index);}
	
	public boolean remove(Object i) {return inventory.remove(i);}
	
	public boolean removeAll(Collection<?> c) {return inventory.removeAll(c);}
	
	//public void removeRange(int start, int end) {inventory.removeRange(start, end);} ArrayList.removeRange has protected access
	
	public boolean retainAll(Collection<?> c) {return inventory.retainAll(c);}
	
	public Item set(int index, Item i) {return inventory.set(index, i);}
	
	public int size() {return inventory.size();}
	
	public Object[] toArray() {return inventory.toArray();}
	
	public <T> T[] toArray(T[] a) {return inventory.toArray(a);}
	
	public String toString() {return inventory.toString();}
	
	public void trimToSize() {inventory.trimToSize();}
}