package model.data_structures;

public class Valor<K> {

	private K dato;
	private Valor<K> datoSiguiente;
	
	public Valor(K pDato){
		dato = pDato;
		datoSiguiente = null;
	}
	
	public K darDato(){
		return dato;
	}
	
	public Valor<K> darDatoSiguiente(){
		return datoSiguiente;
	}
	
	public void cambiarSiguiente(Valor<K> pValor){
		datoSiguiente = pValor;
	}
	
}
