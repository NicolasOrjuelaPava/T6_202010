package model.data_structures;

public interface IHashSeparateChaining<K,V> {

	public int hashCode(K key);
	public void add (K key, V value);
	public V getValue(K key);
	public void reSize(int newSize);
	
	
}
