package model.data_structures;


public class keyComparendo implements Comparable<keyComparendo>{

		//Atributo
		private int obj_id;
		
		//Constructor
		public keyComparendo(int pObjId){
			obj_id = pObjId;
		}
		
		//Métodos, getter y override compareTo
		public int getId(){
			return obj_id;
		}
		
		
		public int compareTo(keyComparendo arg0) {
			
			int resp = 0;
			
			if (obj_id>arg0.getId()){
				resp = 1;
			}else if (obj_id<arg0.getId()){
				resp=-1;
			}
			return resp;
		}
	
		
	}
