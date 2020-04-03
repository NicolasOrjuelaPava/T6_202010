package model.data_structures;

public class Queue<E> implements IQueue<E> {

	public int currentSize;
	public Node<E> head;
	public Node<E> tail;
	
	public Queue(){
		currentSize = 0;
		head = null;
		tail = null;
	}
	
	
	class Node<E>{
		E data;
		Node<E> next;
		public Node(E obj){
			data = obj;
			next = null;
		}
	}
	

	
	public void enqueue(E obj) {
		Node<E> node = new Node<E>(obj);
		if  (head == null){
			head = node;
			tail = node;
			currentSize++;
		}else{
			node.next = head;
			tail = node.next;
			head = node;
			currentSize++;
		}
	}

	
	public void dequeue() {
		if (head == null){
			return;
		}else if(head.next == null){
			head = null;
			currentSize--;
		}else{
			Node<E> tmp = head;
			tail = null;
			while (tmp.next != null){
				tmp = tmp.next;
				tail = tmp;
			}
		}
	}

	
	public E peek() {
		return head.data;
	}

	public int getSize(){
		return currentSize;
	}
}
