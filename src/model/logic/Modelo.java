package model.logic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;

import controller.Controller;
import model.data_structures.Comparendo;
import model.data_structures.HashSeparateChaining;
import model.data_structures.LinkedList;
import model.data_structures.MaxHeapPQ;
import model.data_structures.Queue;
import model.data_structures.Stack;
import model.data_structures.Comparendo;



public class Modelo {


	//------------------------CONSTRUCTOR-----------------------------------------------
	public Modelo(){
		tiempoCarga=0;
		tiempoInicio=0;
		tiempoFin=0;
	}
	
	

	//------------------------ATRIBUTOS-----------------------------------------------
		private static LinkedList<Comparendo> lista;
		private static Stack<Comparendo> stack;
		private static Queue<Comparendo> queue;
		private static HashSeparateChaining hashTableSC;
		private static MaxHeapPQ maxHeap;
		
		private static Modelo modelo;
		Controller controller;
		
		private static final String ARCHIVO_PEQUENO = "./data/Comparendos_DEI_2018_Bogotá_D.C_small.geojson";
		private static final String ARCHIVO_MEDIANO = "./data/Comparendos_DEI_2018_Bogotá_D.C_50000_.geojson";
		private static final String ARCHIVO_GRANDE = "./data/Comparendos_DEI_2018_Bogotá_D.C.geojson";
		public static double tiempoCarga;
		public static double tiempoInicio;
		public static double tiempoFin;
		Scanner sc;


		//------------------------CLASES INTERNAS-----------------------------------------------
		//modelamiento del JSon
		static class Json{
			String type;
			Features[] features;
		}
		
		static class Features{
			String type;
			Properties properties;
			Geometry geometry;
		}

		static class Properties{
			int OBJECTID;
			String FECHA_HORA;
			String MEDIO_DETECCION;
			String CLASE_VEHICULO;
			String TIPO_SERVICIO;
			String INFRACCION;
			String DES_INFRACCION;
			String LOCALIDAD;
			String MUNICIPIO;
		}
		
		static class Geometry{
			String type;
			double[] coordinates;
		}
		
		
		
		//------------------------MÉTODOS-----------------------------------------------
		
		//Metodo que carga los datos en la estructura
		@SuppressWarnings("unchecked")
		public static void cargar(){
			
			int mayorID = 0;
			String fecha = "";
			String infraccion ="";
			String clase_vehiculo = "";
			String tipo_servicio ="";
			String localidad ="";
			
			lista = new LinkedList<Comparendo>();
			stack = new Stack<Comparendo>();
			queue = new Queue<Comparendo>();
			hashTableSC = new HashSeparateChaining();
			maxHeap = new MaxHeapPQ();
			
			try {
				FileInputStream inputStream;
				inputStream = new FileInputStream(ARCHIVO_MEDIANO);
				InputStreamReader inputStreamreader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamreader);

				Json coleccionComparendos =  new Gson().fromJson(bufferedReader, Json.class);

				tiempoInicio = System.currentTimeMillis();
				for (int i=0; i<coleccionComparendos.features.length;i++){
					
					//Carga en LinkedList
					lista.addFirst(new Comparendo(
							coleccionComparendos.features[i].properties.OBJECTID, 
		        			coleccionComparendos.features[i].properties.FECHA_HORA, 
		        			coleccionComparendos.features[i].properties.MEDIO_DETECCION,
		        			coleccionComparendos.features[i].properties.CLASE_VEHICULO,
		        			coleccionComparendos.features[i].properties.TIPO_SERVICIO, 
		        			coleccionComparendos.features[i].properties.INFRACCION, 
		        			coleccionComparendos.features[i].properties.DES_INFRACCION, 
		        			coleccionComparendos.features[i].properties.LOCALIDAD, 
		        			coleccionComparendos.features[i].properties.MUNICIPIO, 
		        			coleccionComparendos.features[i].geometry.coordinates[0], 
		        			coleccionComparendos.features[i].geometry.coordinates[1])) ;
					
					//Carga en Stack
					stack.push(new Comparendo(
							coleccionComparendos.features[i].properties.OBJECTID, 
		        			coleccionComparendos.features[i].properties.FECHA_HORA, 
		        			coleccionComparendos.features[i].properties.MEDIO_DETECCION,
		        			coleccionComparendos.features[i].properties.CLASE_VEHICULO,
		        			coleccionComparendos.features[i].properties.TIPO_SERVICIO, 
		        			coleccionComparendos.features[i].properties.INFRACCION, 
		        			coleccionComparendos.features[i].properties.DES_INFRACCION, 
		        			coleccionComparendos.features[i].properties.LOCALIDAD, 
		        			coleccionComparendos.features[i].properties.MUNICIPIO, 
		        			coleccionComparendos.features[i].geometry.coordinates[0], 
		        			coleccionComparendos.features[i].geometry.coordinates[1]));
					
					
					//Carga en Queue
					queue.enqueue(new Comparendo(
							coleccionComparendos.features[i].properties.OBJECTID, 
		        			coleccionComparendos.features[i].properties.FECHA_HORA, 
		        			coleccionComparendos.features[i].properties.MEDIO_DETECCION,
		        			coleccionComparendos.features[i].properties.CLASE_VEHICULO,
		        			coleccionComparendos.features[i].properties.TIPO_SERVICIO, 
		        			coleccionComparendos.features[i].properties.INFRACCION, 
		        			coleccionComparendos.features[i].properties.DES_INFRACCION, 
		        			coleccionComparendos.features[i].properties.LOCALIDAD, 
		        			coleccionComparendos.features[i].properties.MUNICIPIO, 
		        			coleccionComparendos.features[i].geometry.coordinates[0], 
		        			coleccionComparendos.features[i].geometry.coordinates[1]));
					

					
					//Carga en Hash Table Separate Chaining
					hashTableSC.add((new Comparendo(coleccionComparendos.features[i].properties.OBJECTID, 
		        			coleccionComparendos.features[i].properties.FECHA_HORA, 
		        			coleccionComparendos.features[i].properties.MEDIO_DETECCION,
		        			coleccionComparendos.features[i].properties.CLASE_VEHICULO,
		        			coleccionComparendos.features[i].properties.TIPO_SERVICIO, 
		        			coleccionComparendos.features[i].properties.INFRACCION, 
		        			coleccionComparendos.features[i].properties.DES_INFRACCION, 
		        			coleccionComparendos.features[i].properties.LOCALIDAD, 
		        			coleccionComparendos.features[i].properties.MUNICIPIO, 
		        			coleccionComparendos.features[i].geometry.coordinates[0], 
		        			coleccionComparendos.features[i].geometry.coordinates[1])).key.hashCOde()
							,new Comparendo(coleccionComparendos.features[i].properties.OBJECTID, 
		        			coleccionComparendos.features[i].properties.FECHA_HORA, 
		        			coleccionComparendos.features[i].properties.MEDIO_DETECCION,
		        			coleccionComparendos.features[i].properties.CLASE_VEHICULO,
		        			coleccionComparendos.features[i].properties.TIPO_SERVICIO, 
		        			coleccionComparendos.features[i].properties.INFRACCION, 
		        			coleccionComparendos.features[i].properties.DES_INFRACCION, 
		        			coleccionComparendos.features[i].properties.LOCALIDAD, 
		        			coleccionComparendos.features[i].properties.MUNICIPIO, 
		        			coleccionComparendos.features[i].geometry.coordinates[0], 
		        			coleccionComparendos.features[i].geometry.coordinates[1]));
					
					//Carga en MxHeapPQ - Priority Queue
				/*	maxHeap.add(new Comparendo(
							coleccionComparendos.features[i].properties.OBJECTID, 
		        			coleccionComparendos.features[i].properties.FECHA_HORA, 
		        			coleccionComparendos.features[i].properties.MEDIO_DETECCION,
		        			coleccionComparendos.features[i].properties.CLASE_VEHICULO,
		        			coleccionComparendos.features[i].properties.TIPO_SERVICIO, 
		        			coleccionComparendos.features[i].properties.INFRACCION, 
		        			coleccionComparendos.features[i].properties.DES_INFRACCION, 
		        			coleccionComparendos.features[i].properties.LOCALIDAD, 
		        			coleccionComparendos.features[i].properties.MUNICIPIO, 
		        			coleccionComparendos.features[i].geometry.coordinates[0], 
		        			coleccionComparendos.features[i].geometry.coordinates[1]));
					*/
					
					//coger el mayor de una vez y cogerme la info
					mayorID=coleccionComparendos.features[i].properties.OBJECTID;
					
					if (coleccionComparendos.features[i].properties.OBJECTID>mayorID){
						mayorID = coleccionComparendos.features[i].properties.OBJECTID;
					}
					
					fecha = coleccionComparendos.features[i].properties.FECHA_HORA;
					infraccion = coleccionComparendos.features[i].properties.INFRACCION;
					clase_vehiculo = coleccionComparendos.features[i].properties.CLASE_VEHICULO;
					tipo_servicio = coleccionComparendos.features[i].properties.TIPO_SERVICIO;
					localidad = coleccionComparendos.features[i].properties.LOCALIDAD;
				}
				
				
				
				tiempoFin = System.currentTimeMillis();
				tiempoCarga = (tiempoFin-tiempoInicio)/1000;
				
			}catch (Exception e){
				//System.out.println("No se encontró el archivo de datos");
				e.getStackTrace();
			}
			
			//OUTPUT en consola con información de la carga
			System.out.println("");
			System.out.println("Total comparendos en el archivo: "+ hashTableSC.getSize());
			//System.out.println(hashTableSC.getPrimero());
			System.out.println("El comparendo con el mayor OBJECTID encontrado es: ");
			System.out.println( mayorID+ " " + fecha +" " + infraccion + " " + clase_vehiculo + " " + tipo_servicio + " " + localidad);

		}
	
	
	
	//------------------------REQUERIMIENTOS-----------------------------------------------
		

	//-----PARTE A----- 
	
	// 1A - Obtener los M comparendos con mayor gravedad
	public void req1A(int m){
			
	//ArrayList<Comparendo> resp = new ArrayList<Comparendo>();
		
		if (m ==1 ){
			System.out.println("" +hashTableSC.getSize());
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
