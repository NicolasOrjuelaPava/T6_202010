package model.data_structures;

public class Stack<E> implements IStack <E>{

	Node<E> head;
	int currentSize;
	
	class Node<E> {
		E data;
		Node<E> next;
		private Node(E obj){
			data = obj;
			next = null;
		}
	}
	
	public Stack(){
		head= null;
		currentSize=0;
	}
	
	
	@Override
	public E pop() {
//ojo puede generar null pointer
		E aEliminar = head.data;
		if(empty() == true){
			return null;
		}
		if (head.next == null){
			head = null;
			currentSize--;
		}else{
			head = head.next;
			currentSize--;
		}
		
		return aEliminar;
	}

	public void push(E obj) {
		Node<E> node = new Node<E>(obj);
		if (head == null){
			head = node;
			return;
		}
		
		node.next = head;
		head = node;
		currentSize++;
		
	}

	@Override
	public E peek() {
		if (empty()== true){
			return null;
		}else{
			return head.data;
		}
		
	}

	@Override
	public boolean empty() {
		if (head == null){
			return true;
		}else{
			return false;
		}
	}

	public int getSize(){
		return currentSize;
	}
	
	
	
	
	
}
