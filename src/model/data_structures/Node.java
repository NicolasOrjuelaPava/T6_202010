package model.data_structures;

public class Node<K extends Comparable <K> ,V> {

	
	//ATRIBUTOS
	private K key;
	private Valor<V> value;
	private int size;
	private boolean color;
	private Node<K,V> left, right;

	
	//CONSTRUCTOR
	public Node(K k, V v, boolean c){
		key = k;
		color = c;
		value = new Valor<V>(v);
		left=null;
		right=null;
	}
	
	
	//MÉTODOS
	//Getters and Setters
	public K getKey(){
		return key;
	}
	
	public Valor<V> getValue(){
		return value;
	}
	
	public Node<K,V> getRight(){
		return right;
	}
	
	public Node<K,V> getLeft(){
		return left;
	}
	
	public int getSize(){
		return size;
	}
	

	
	public boolean getColor(){
		return color;
	}
	

	
	public void setLeft(Node<K,V> pLeft){
		left = pLeft;
	}
	
	public void setColor(boolean pColor){
		color = pColor;
	}
	
	
	public void setRight(Node<K,V> pRight){
		right= pRight;
	}
	

	//métodos adicionales
	public void addValue(V pValue){
		value = new Valor<V>(pValue);
		Valor<V> tmp = value;
		value.setNext(tmp);
		size++;
	}
	
	public void changeValue(V pValue){
		value = new Valor<V>(pValue);
		size = 1;
	}
	
}
