package model.data_structures;

public interface IMaxHeapPQ<K> {

	public int getSize();
	public K[] getHeap();
	public void heapSizeChange(int c);
	public boolean less(int p1, int p2);
	public void change (int p1, int p2);
	public void swim (int i);
	public void sink (int i);
	public void add (K e);
	public boolean empty();
	public K getMax();
	public K deleteMax();
	
	
}
