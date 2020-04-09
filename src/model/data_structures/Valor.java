package model.data_structures;

public class Valor<K> {
	//Atributos
	private K data;
	private Valor<K> next;
	
	//Constructor
	public Valor(K pData){
		data = pData;
		next = null;
	}
	
	//Métodos, getters y setters
	public K getData(){
		return data;
	}
	
	public Valor<K> getNext(){
		return next;
	}
	
	public void setNext(Valor<K> pValue){
		next = pValue;
	}
	
}
