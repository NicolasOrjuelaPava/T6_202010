package model.logic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;

import controller.Controller;
import model.data_structures.Comparendo;
import model.data_structures.RedBlackTree;
import model.data_structures.Comparendo;



public class Modelo {


	//------------------------CONSTRUCTOR-----------------------------------------------
	public Modelo(){
		tiempoCarga=0;
		tiempoInicio=0;
		tiempoFin=0;
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
			
			//INICIALIZAR LA ESTRUCTURA
			arbol = new RedBlackTree();


			try {
				FileInputStream inputStream;
				inputStream = new FileInputStream(ARCHIVO_MEDIANO);
				InputStreamReader inputStreamreader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamreader);

				Json coleccionComparendos =  new Gson().fromJson(bufferedReader, Json.class);

				tiempoInicio = System.currentTimeMillis();
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
			
			//NO OLVIDAR PONER ESTO
			//System.out.println("Total comparendos en el archivo: "+ GETSIZE.getSize());

			System.out.println("El comparendo con el mayor OBJECTID encontrado es: ");
			System.out.println( mayorID+ " " + fecha +" " + infraccion + " " + clase_vehiculo + " " + tipo_servicio + " " + localidad);

		}
	
	
	
	//------------------------REQUERIMIENTOS-----------------------------------------------
		

	//-----PARTE A----- 
	
	// 1A - Obtener los M comparendos con mayor gravedad
	public void req1A(int m){
			System.out.println("el tamanio es: " +arbol.size());
	//
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
