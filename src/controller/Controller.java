package controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.LineNumberInputStream;

import com.google.gson.Gson;

import model.data_structures.Comparendo;
import model.logic.Modelo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Scanner;

import com.google.gson.Gson;


public class Controller {
	
	//------------------------ATRIBUTOS-----------------------------------------------
	Scanner sc;
	Modelo modelo;
	
	
	//------------------------CONSTRUCTOR-----------------------------------------------
	public Controller(){
		sc = new Scanner(System.in);
		modelo = new Modelo();
		
	}
	
	
	
	//------------------------MÉTODOS-----------------------------------------------
	public void cargar(){
		modelo.cargar();
	}
	
	//----PARTE A----
	public  void req1A(){
		System.out.println("Ingrese el número de comparendos que desea obtener:");
		int numero = Integer.parseInt(sc.nextLine());
		modelo.req1A(numero);
		
	}
	

	
	
	
	
	
	
	
	
}
