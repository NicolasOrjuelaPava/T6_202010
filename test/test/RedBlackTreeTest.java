package test;

import model.data_structures.RedBlackTree;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class RedBlackTreeTest {

	//Atributo: instancia del árbol y tamaño del mismo para pruebas
	private RedBlackTree arbol;
	private int size;
	
	//Escenario 1
	public void setUp1(){
		arbol = new RedBlackTree();
		size = 500;
		//Lleno el arbol con valores arbitrarios
		for (int i=0; i<size;i++){
			arbol.put(i, i);
		}	
	}
	
	//-------Métodos---
	
	//Pruebo el tamaño
	@Test
	public void testSize(){
		setUp1();
		assertEquals(500,arbol.size());
	}

	//Pruebo el contains
	@Test
	public void testContains(){
		setUp1();
		assertTrue(arbol.contains(5));
		assertFalse(arbol.contains(501));	
		}
	
	//Pruebo el isEmpty
	@Test
	public void testEmpty(){
		setUp1();
		assertFalse(arbol.isEmpty());
	}

	
	//Pruebo el put
	@Test
	public void testPut() {
		setUp1();
		Integer value= 501;
		arbol.put(value, value);
		assertTrue(arbol.contains(501));
		assertEquals(501,arbol.size());	
	}
	
	//Pruebo el get
	@Test
	public void testGet() {
		setUp1();
		Integer value= 10;
		assertEquals(10,arbol.get(value));
	}
	
	//Pruebo el max
	@Test
	public void testMax() {
		setUp1();
		assertEquals(499,arbol.max());
	}
	
	
	//Pruebo el min
	@Test
	public void testMin() {
		setUp1();
		assertEquals(0,arbol.min());
		
	}
	

	
	
	
	
	
	
	
}
