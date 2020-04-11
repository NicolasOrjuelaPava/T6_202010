package model.logic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import com.google.gson.Gson;

import controller.Controller;
import model.data_structures.Comparendo;
import model.data_structures.Node;
import model.data_structures.RedBlackTree;
import model.data_structures.keyComparendo;
import model.data_structures.Comparendo;



public class Modelo {


	//------------------------CONSTRUCTOR-----------------------------------------------
	public Modelo(){
		tiempoCarga=0;
		tiempoInicio=0;
		tiempoFin=0;
		valMax=0;
		valMin=0;
	}
	
	

	//------------------------ATRIBUTOS-----------------------------------------------	
		private static Modelo modelo;
		Controller controller;
		
		private static final String ARCHIVO_PEQUENO = "./data/Comparendos_DEI_2018_Bogotá_D.C_small.geojson";
		private static final String ARCHIVO_MEDIANO = "./data/Comparendos_DEI_2018_Bogotá_D.C_50000_.geojson";
		private static final String ARCHIVO_GRANDE = "./data/Comparendos_DEI_2018_Bogotá_D.C.geojson";
		public static double tiempoCarga;
		public static double tiempoInicio;
		public static double tiempoFin;
		Scanner sc;
		//CREAR ATRIBUTO DE LA ESTRUCTURA
		static RedBlackTree arbol;
		
		static int valMin;
		static int valMax;


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
			
	
			String fecha = "";
			String infraccion ="";
			String clase_vehiculo = "";
			String tipo_servicio ="";
			String localidad ="";
			
			//INICIALIZAR LA ESTRUCTURA
			arbol = new RedBlackTree();


			try {
				FileInputStream inputStream;
				inputStream = new FileInputStream(ARCHIVO_PEQUENO);
				InputStreamReader inputStreamreader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamreader);

				Json coleccionComparendos =  new Gson().fromJson(bufferedReader, Json.class);

				tiempoInicio = System.currentTimeMillis();
				
				valMin = coleccionComparendos.features[0].properties.OBJECTID;
				
				for (int i=0; i<coleccionComparendos.features.length;i++){
					
					//CARGAR EN LA ESTRUCTURA
					Comparendo comp = new Comparendo(
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
		        			coleccionComparendos.features[i].geometry.coordinates[1]) ;
					arbol.put(comp.getKey(), comp);
					
					
	

					//coger el mayor de una vez y cogerme la info
					int temp = coleccionComparendos.features[i].properties.OBJECTID;;
					if (temp > valMax){
						valMax = temp;
					}
					
					/*
					fecha = coleccionComparendos.features[i].properties.FECHA_HORA;
					infraccion = coleccionComparendos.features[i].properties.INFRACCION;
					clase_vehiculo = coleccionComparendos.features[i].properties.CLASE_VEHICULO;
					tipo_servicio = coleccionComparendos.features[i].properties.TIPO_SERVICIO;
					localidad = coleccionComparendos.features[i].properties.LOCALIDAD;
					*/
					
					
					//Coger el menor ID
					int temp2 = coleccionComparendos.features[i].properties.OBJECTID;
					if (temp2<valMin){
						valMin = temp2;
					}
					
				
				}
				

				
				
				tiempoFin = System.currentTimeMillis();
				tiempoCarga = (tiempoFin-tiempoInicio)/1000;
				
			
			}catch (Exception e){
				//System.out.println("No se encontró el archivo de datos");
				e.getStackTrace();
			}
			
			//OUTPUT en consola con información de la carga
			System.out.println("");
			req1();

		}
	
	
	
	//------------------------REQUERIMIENTOS-----------------------------------------------
		

	//-----PARTE A----- 
	
	// 1 carga
	public static void req1(){

			//System.out.println(arbol.height());
			System.out.println("El tamanio es: " +arbol.size());
			System.out.println("El valor MINIMO de OBJECT ID es: " + valMin);
			System.out.println("El valor MAXIMO de OBJECT ID es: " + valMax);
	}
	
	//Funciona perfecto
	public void req2(int idBuscado){
		keyComparendo k = new keyComparendo(idBuscado);
		
		try{
		if (arbol.get(k).toString() == null){
			System.out.println("No existe un comparendo con ese OBJECTID");
		}else{
			System.out.println(arbol.get(k).toString());
			System.out.println("");
			}
		}catch (Exception e){
			System.out.println("");
			System.out.println("No existe un comparendo con ese OBJECTID");
			System.out.println("");
		}
	}
	
	//Consultar los comparendos con un id en un rango específico
	public void req3(int id_inferior, int id_superior){
		
		keyComparendo k_inf = new keyComparendo(id_inferior);
		keyComparendo k_sup = new keyComparendo(id_superior);
		
		/*
		while(arbol.valuesInRange(k_inf, k_sup).hasNext()){
			System.out.print(arbol.valuesInRange(k_inf, k_sup).next().toString());
		}
		
		while(arbol.keysInRange(k_inf, k_sup).hasNext()){
			System.out.print(arbol.keysInRange(k_inf, k_sup).next().toString());
		} */
		

		Iterable<keyComparendo> it= (Iterable<keyComparendo>) arbol.keysInRange(k_inf, k_sup);
		
		Iterator<keyComparendo> ite= it.iterator();
		
		while(ite.hasNext()) 
		{
			
			keyComparendo k = (keyComparendo) ite.next();
			
			System.out.println(arbol.get(k).toString());
		}
		
		
	}
	
	
	
	
	
	
	
	
}
