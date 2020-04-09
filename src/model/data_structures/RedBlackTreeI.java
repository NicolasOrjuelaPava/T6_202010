package model.data_structures;

import java.util.Iterator;

public interface RedBlackTreeI<K,V> {

	//METODOS QUE PIDE EL ENUNCIADO
	public int size();
	public boolean isEmpty();
	public V get(K key);
	public int getHeight(K key);
	public boolean contains(K key);
	public void put (K key, V value);
	public int height();
	public K min();
	public K max();
	public  boolean check();
	public Iterator<V> valuesInRange(K init, K end);
	public Iterator<K> keysInRange(K init, K end);
	public Iterator <K> keys();




}
