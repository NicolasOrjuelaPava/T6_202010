package model.data_structures;

public class MaxHeapPQ <K extends Comparable<K>> implements IMaxHeapPQ<K> {

	//atributos
	private K[] heapArray;
	private int heapSize;
	
	public MaxHeapPQ(int c){
		
		heapSize = 0;
		heapArray = (K[]) new Comparable[c+1];
		
		/*
		for (int i=0; i<heapArray.length;i++){
			heapArray[i] = ;
		} */
	}

	public MaxHeapPQ(){
		this(3);
	}
	
	public MaxHeapPQ(K[] e){
		
		heapSize = e.length;
		heapArray = (K[]) new Comparable[e.length+1];
		
		for (int i =0; i<heapSize;i++){
			heapArray[i+1] = e[i];
		}
		for (int j = heapSize/2; j>=1; j++){
			sink(j);
		}
		
	}
	
	//getters
	public int getSize(){
		return heapSize;
	}
	
	public K[] getHeap(){
		return heapArray;
	}
	
	
	public void heapSizeChange(int c){
		K[] arr = (K[]) new Comparable[heapSize+c];

			
			for (int i=1; i<heapSize;i++){
				arr[i] = heapArray[i];
			}
			heapArray = arr;
		}

	
	
	public boolean less (int p1, int p2){
		return heapArray[p1].compareTo(heapArray[p2])<0;
	}
	
	public void change (int p1, int p2){
	
	
		K tmp = heapArray[p1];
		heapArray[p1] = heapArray[p2];
		heapArray[p2] = tmp;
	
		
	}
	
	public void swim (int i){
		
		while (i>1 && less(i/2, i)){
			
			change(i,i/2);
			i = i/2;
		}
	}
	
	public void sink(int i){
		while (2*i <= heapSize){
			int j = 2*i;
			
			if (j < heapSize && less(j, j+1)){
				j++;
			}
			
			if(!less(i,j)){
				break;
			}
			change (i,j);
			i= j;
		}
	}
	
	public void add (K e){
		
		if (heapSize == heapArray.length - 1){
			heapSizeChange(heapArray.length*2);
		}else if (heapSize == 0){
			heapArray[heapSize++] = e;
		}else{
			heapArray[heapSize++] = e;
			swim(heapSize);
		}
	}
	
	
	public boolean empty(){
		if (heapSize != 0){
			return false;
		}else{
			return true;
		}
	}
	
	public K getMax(){
		return heapArray[1];
	}
	
	
	public K deleteMax(){
		K max = heapArray[1];
		change(1, heapSize--);
		sink(1);
		heapArray[heapSize++] = null;
		return max;
	}
	
}
