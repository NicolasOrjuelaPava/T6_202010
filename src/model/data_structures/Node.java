package model.data_structures;

public class Node<K extends Comparable <K> ,V> {

	
	//ATRIBUTOS
	private K llave;
	private Valor<V> valor;
	private Node<K,V> izq, dere;
	private boolean color;
	private int tamanio;
	
	//CONSTRUCTOR
	public Node(K pK, V pV, boolean pColor){
		llave = pK;
		valor = new Valor<V>(pV);
		color = pColor;
		izq=null;
		dere=null;
	}
	
	//MÉTODOS
	//Getters and Setters
	public K darLlave(){
		return llave;
	}
	
	public Valor<V> darValor(){
		return valor;
	}
	
	public Node<K,V> darIzquierda(){
		return izq;
	}
	
	public Node<K,V> darDerecha(){
		return dere;
	}
	
	public boolean darColor(){
		return color;
	}
	
	public int darTamano(){
		return tamanio;
	}
	
	public void cambiarIzquierda(Node<K,V> pIzq){
		izq = pIzq;
	}
	
	public void cambiarDerecha(Node<K,V> pDere){
		dere= pDere;
	}
	
	public void cambiarColor(boolean pColor){
		color = pColor;
	}
	
	//métodos adicionales
	public void agregarValor(V pV){
		Valor<V> anteriorPrimero = valor;
		valor = new Valor<V>(pV);
		valor.cambiarSiguiente(anteriorPrimero);
		tamanio++;
	}
	
	public void remplazarValor(V pV){
		valor = new Valor<V>(pV);
		tamanio = 1;
	}
	
}
