package Terminal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.File;
import java.io.RandomAccessFile;

public class Main {

	static String ruta;
	static String color="";
	public static void main(String[] args) {
		
		ruta = System.getProperty("user.dir");

			generarMenu(ruta);


	}
	public static void generarMenu(String ruta) {
		
		Scanner tec = new Scanner(System.in);
		
		System.out.println(color + "\r\n"+ruta+">");
		String comando = tec.nextLine();
		String[] partido = comando.split(" ");
		
		switch (partido[0].toLowerCase()) {
		case "help": 

			Metodos.comandoHelp(ruta, partido);
			
			break;
		case "cd":

			Metodos.comandoCd(ruta, partido);
			
			break;
			
		case "mkdir":

			Metodos.comandoMkdir(ruta, partido);
			
			break;
			
		case "info":
			
			Metodos.comandoInfo(ruta, partido);

			break;
			
		case "cat":

			Metodos.comandoCat(ruta, partido);
			
			break;
			
		case "top":
			
			Metodos.comandoTop(ruta, partido);
			
			break;
			
		case "mkfile":
			
			Metodos.comandoMkfile(ruta, partido);
			
			break;
			
		case "write":
			
			Metodos.comandoWrite(ruta, partido);
			
			break;
			
		case "rename":
			
			Metodos.comandoRename(ruta, partido);
			
			break;
			
		case "tree":
			
			Metodos.comandoTree(ruta, partido);
			
		case "dir":

			Metodos.comandoDir(ruta, partido);
			
			break;
			
		case "find":
			
			Metodos.comandoFind(ruta,partido);
			
			break;
			
		case "readpoint":
			
			Metodos.comandoReadpoint(ruta, partido);
			
			break;
			

		case "delete":
			
			Metodos.comandoDelete(ruta, partido);
			
			break;
			
		case "start":
			
			Metodos.comandoStart(ruta, partido);
			
			break;
			
		case "close":
			
			System.exit(0);
			
			break;
			
		case "clear":

			Metodos.comandoClear(ruta, partido);
			
			break;
			
		case "color":
			
			Metodos.comandoColor(ruta, partido);
			
			break;
			
		default: 
			
			System.out.println("Comando no valido (usa help para ver los comandos)");
			generarMenu(ruta);
			
				break;
		}
	}
	public static String getRuta() {
		return ruta;
	}
	public static void setRuta(String ruta) {
		Main.ruta = ruta;
	}
	public static String getColor() {
		return color;
	}
	public static void setColor(String color) {
		Main.color = color;
	}
}

