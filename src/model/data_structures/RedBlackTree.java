package model.data_structures;

import java.util.ArrayList;
import java.util.Iterator;

public class RedBlackTree<K extends Comparable <K>,V> implements RedBlackTreeI<K,V> {

	//---ATRIBUTOS----
	//constantes
	private static final boolean BLACK = false;
	private static final boolean RED = true;
	
	private Node<K,V> root;
	private int cantPairs;
	
	
	//---CONSTRUCTOR----
	public RedBlackTree(){
		cantPairs =0;
		root=null;
	}
	
	
	//-----MÉTODOS---
	
	public int size() {
		return cantPairs;
	}

	public boolean isEmpty() {
		return cantPairs == 0;
	}

	
	public V get(K key) {
		V value = null;
		Node<K,V> actual = root;
		
		while (actual != null){	
			
			if (key.compareTo(actual.getKey()) <0){
				actual = actual.getLeft();
			}else if (key.compareTo(actual.getKey())>0){
				actual = actual.getRight();
			}else{
				Valor<V> nodoActual = actual.getValue();
				value = nodoActual.getData();
				return value;
			}
		}
		return value;
	}

	
	public int getHeight(K key){
		Node<K,V> x = getNodeByKey(key);
		return altura(x);
	}
	
	public boolean contains(K key){
		
		Iterator<K> iter = keys();
		
		while(iter.hasNext()){
			K ke = iter.next();
			if(ke.equals(key)){
				return true;
			}
		}
		return false;
	}
	
	public void put(K key, V value){
		root = insert(root, key,value);
		root.setColor(BLACK);
	}
	
	public boolean esRed(Node<K,V>x){
		if (x == null){
			return false;
		}else{
		return x.getColor()== RED;
		}
	}
	
	private Node<K,V> rotateRight(Node<K,V> n) {
		
		assert (n != null) && esRed(n.getLeft());
		
		Node<K,V> x = n.getLeft();
		
		n.setLeft(x.getRight());
		x.setRight(n);
		x.setColor(n.getColor());
		n.setColor(RED);
		return x;
	}
	
	private Node<K,V> rotateLeft(Node<K,V> h) {
		assert (h != null) && esRed(h.getRight());
		Node<K,V> x = h.getRight();
		h.setRight(x.getLeft());
		x.setLeft(h);
		x.setColor(h.getColor());
		h.setColor(RED);
		return x;
	}
	
	private void flipColors(Node<K,V> h) {
		assert !esRed(h) && esRed(h.getLeft()) && esRed(h.getRight());
		h.setColor(RED);
		h.getLeft().setColor(BLACK);
		h.getRight().setColor(BLACK);
	}
	
	
	public Node<K,V> insert (Node<K,V> n, K pKey, V pValue){
		if(n==null)
		{
			cantPairs++;
			return new Node<K,V>(pKey,pValue,RED); 
			}
		
		int comparar = pKey.compareTo(n.getKey());
		
		if(comparar < 0)
		{	n.setLeft(insert(n.getLeft(), pKey, pValue)); }
		else if(comparar>0)
		{	n.setRight(insert(n.getRight(), pKey, pValue)); }
		else
		{ n.changeValue(pValue); }

		if (esRed(n.getLeft())  &&  esRed(n.getLeft().getLeft()))
		{ n = rotateRight(n); }
		
		if (esRed(n.getRight()) && !esRed(n.getLeft()))
		{ n = rotateLeft(n); }
		
		if (esRed(n.getLeft())  &&  esRed(n.getRight()))
		{ flipColors(n); }

		return n;
	}
	
	
	public int height()
	{
		return altura(root); 
	}

	public K min()
	{    
		return min(root).getKey();
	} 

	public K max()
	{
		if (isEmpty()) 
		{
			return null; 
		}
		else
		{
			return max(root).getKey();
		}	
	} 

	public boolean check() 
	{
		if (!isBST())            System.out.println("It is not in symetric order");
		if (!isSizeConsistent()) System.out.println("The count of subtrees is not consistent");
		if (!isRankConsistent()) System.out.println("THe ranks are not consisten");
		if (!is23Tree())             System.out.println("It is not a 2-3 tree");
		if (!isBalanced())       System.out.println("It is not balanced");
		return isBST() && isSizeConsistent() && isRankConsistent() && is23Tree() && isBalanced();
	}

	public Iterator<K> keys() {
		if (isEmpty()) 
		{
			ArrayList<K> x= new ArrayList<K>();
			return 	x.iterator(); 
		}

		return keysInRange(min(), max());
	}

	public Iterator<V> valuesInRange(K lo, K hi) 
	{
		ArrayList<V> vals = new ArrayList<V>();
		Iterator<V> iter = vals.iterator();
		return iter;
	} 

	public Iterator<K> keysInRange(K lo, K hi) 
	{
		ArrayList<K> llas = new ArrayList<K>();
		while( keys().hasNext( ))
		{
			K a = keys().next();
			if ( a.compareTo(lo) >0 && a.compareTo(hi) <0)
				llas.add(a);
		}
		Iterator<K> iter = llas.iterator();
		return iter;
	} 

	public int rank(K key) {
		if (key == null) throw new IllegalArgumentException("argument to rank() is null");
		return rank(key, root);
	}

	public K select(int k)
	{
		if (k < 0 || k >= size()) {
			throw new IllegalArgumentException("argument to select() is invalid: " + k);
		}
		Node<K,V> x = select(root, k);
		return x.getKey();
	}

	private boolean isBST() 
	{
		return isBST(root, null, null);
	}

	private boolean isBST(Node<K,V> x, K min, K max)
	{
		if (x == null) return true;
		if (max != null && x.getKey().compareTo(max) >= 0) return false;
		if (min != null && x.getKey().compareTo(min) <= 0) return false;
		
		return isBST(x.getLeft(), min, x.getKey()) && isBST(x.getRight(), x.getKey(), max);
	} 

	private boolean isSizeConsistent()
	{
		return isSizeConsistent(root); 
	}
	
	
	private boolean isSizeConsistent(Node<K,V> x)
	{
		if (x == null) return true;
		if (x.getSize() != size(x.getLeft()) + size(x.getRight()) + 1) return false;
		return isSizeConsistent(x.getLeft()) && isSizeConsistent(x.getRight());
	} 

	
	private int size(Node<K,V> x)
	{
		if(x==null)
		{
			return 0;
		}
		return altura(x);
	}

	private boolean isRankConsistent() 
	{
		for (int i = 0; i < size(); i++)
			if (i != rank(select(i))) return false;
		while (keys().hasNext())
		{

			K key= keys().next(); 
			if (key.compareTo(select(rank(key))) != 0) return false;

			keys().next(); 

		}

		return true;
	}

	private Node<K,V> select(Node<K,V> x, int k)
	{
		int t = size(x.getLeft()); 
		if   (t > k) { 
			return select(x.getLeft(),  k);
		} 
		else if (t < k) {
			return select(x.getRight(), k-t-1);
		} 
		else {
			return x;
		}
		}
	

	private int rank(K key, Node<K,V> h) {
		if (h == null) return 0; 
		int cmp = key.compareTo(h.getKey()); 
		if      (cmp < 0) return rank(key, h.getLeft()); 
		else if (cmp > 0) return 1 + size(h.getLeft()) + rank(key, h.getRight()); 
		else              return size(h.getLeft()); 
	} 

	private boolean is23Tree() 
	{
		return is23Tree(root); 
	}

	private boolean is23Tree(Node<K,V> x) 
	{
		if (x == null) return true;
		if (esRed(x.getRight())) return false;
		if (x != root && esRed(x) && esRed(x.getLeft()))
		{
			return false;
		}
		return is23Tree(x.getLeft()) && is23Tree(x.getRight());
	} 

	private boolean isBalanced() { 
		int black = 0;   
		Node<K,V> x = root;
		while (x != null) 
		{
			if (!esRed(x)) black++;
			x = x.getLeft();
		}
		return isBalanced(root, black);
	}

	private boolean isBalanced(Node<K,V> x, int black) {
		if (x == null) return black == 0;
		if (!esRed(x)) black--;
		return isBalanced(x.getLeft(), black) && isBalanced(x.getRight(), black);
	}

	private Node<K,V> max(Node<K,V> x) 
	{ 
		if (x.getRight() == null) 
		{
			return x; 
		}
		else
		{
			return max(x.getRight()); 
		}
	}

	private int altura(Node<K,V> x) 
	{
		if (x == null)
		{
			return -1;
		}
		return 1 + Math.max(altura(x.getLeft()), altura(x.getRight()));
	}
	
	private Node<K,V> min(Node<K,V> x)
	{ 

		if (x.getLeft() == null)
		{
			return x; 
		}
		else 
		{
			return min(x.getLeft()); 
		}
	}


	
	private Node<K,V> getNodeByKey(K pK)
	{
		Node<K,V> x = null;
		return x;

	}

	
}
