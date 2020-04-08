package model.data_structures;

import java.util.Iterator;

public class RedBlackTree<K extends Comparable <K>,V> implements RedBlackTreeI<K,V> {

	//---ATRIBUTOS----
	private static final boolean BLACK = false;
	private static final boolean RED = true;
	private Node<K,V> raiz;
	private int numParejas;
	
	//---CONSTRUCTOR----
	public RedBlackTree(){
		numParejas =0;
		raiz=null;
	}
	
	//-----MÉTODOS---
	
	public int size() {
		return numParejas;
	}

	public boolean isEmpty() {
		return numParejas == 0;
	}

	public V get(K pK) {
		Node<K,V> actual = raiz;
		V val = null;
		while (actual != null){
			
			int comparacion = pK.compareTo(actual.darLlave());
			if (comparacion <0){
				actual = actual.darIzquierda();
			}else if (comparacion>0){
				actual = actual.darDerecha();
			}else{
				Valor<V> nodoActual = actual.darValor();
				val = nodoActual.darDato();
				return val;
			}
		}
		return val;
	}

/*
	public int getHeight(K key){
		Node<K,V> x = darNodoPorLlave(key);
		return altura(x);
	}
	
	public boolean conatins(K key){
		Iterator<K> iter = keys();
		while(iter.hasNext()){
			K pk = iter.next();
			if(pk.equals(key)){
				return true;
			}
		}
		return false;
	}
	*/
	public void put(K pK, V pV){
		raiz = insertar(raiz, pK,pV);
		raiz.cambiarColor(BLACK);
	}
	
	public boolean esRojo(Node<K,V>x){
		if (x == null){
			return false;
		}
		return x.darColor()== RED;
	}
	
	private Node<K,V> rotateRight(Node<K,V> h) {
		assert (h != null) && esRojo(h.darIzquierda());
		Node<K,V> x = h.darIzquierda();
		h.cambiarIzquierda(x.darDerecha());
		x.cambiarDerecha(h);
		x.cambiarColor(h.darColor());
		h.cambiarColor(RED);
		return x;
	}
	
	private Node<K,V> rotateLeft(Node<K,V> h) {
		assert (h != null) && esRojo(h.darDerecha());
		Node<K,V> x = h.darDerecha();
		h.cambiarDerecha(x.darIzquierda());
		x.cambiarIzquierda(h);
		x.cambiarColor(h.darColor());
		h.cambiarColor(RED);
		return x;
	}
	
	private void flipColors(Node<K,V> h) {
		assert !esRojo(h) && esRojo(h.darIzquierda()) && esRojo(h.darDerecha());
		h.cambiarColor(RED);
		h.darIzquierda().cambiarColor(BLACK);
		h.darDerecha().cambiarColor(BLACK);
	}
	
	
	public Node<K,V> insertar (Node<K,V> n, K pK, V pV){
		if(n==null)
		{
			numParejas++;
			return new Node<K,V>(pK,pV,RED);
		}

		int comparar = pK.compareTo(n.darLlave());
		if(comparar < 0)
		{
			n.cambiarIzquierda(insertar(n.darIzquierda(), pK, pV));
		}
		else if(comparar>0)
		{
			n.cambiarDerecha(insertar(n.darDerecha(), pK, pV));
		}
		else
		{
			n.remplazarValor(pV);
		}

		if (esRojo(n.darDerecha()) && !esRojo(n.darIzquierda()))
		{
			n = rotateLeft(n);
		}
		if (esRojo(n.darIzquierda())  &&  esRojo(n.darIzquierda().darIzquierda()))
		{
			n = rotateRight(n);
		}
		if (esRojo(n.darIzquierda())  &&  esRojo(n.darDerecha()))
		{
			flipColors(n);
		}

		return n;
	}
	
}
