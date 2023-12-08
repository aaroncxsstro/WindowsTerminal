package Terminal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Metodos {

	

public static void comandoColor(String ruta, String[] partido) {
		
		if (partido.length!=2) {
			
			System.err.println("Numero de argumentos no válido");
			Main.generarMenu(ruta);
		}else {
			switch(partido[1]) {
			
			case "black":
				Main.setColor("\u001B[30m");
				break;
			case "red":
				Main.setColor("\u001B[31m");
				break;
			case "green":
				Main.setColor("\u001B[32m");
				break;
			case "yellow":
				Main.setColor("\u001B[33m");
				break;
			case "blue":
				Main.setColor("\u001B[34m");
				break;
			case "purple":
				Main.setColor("\u001B[35m");
				break;
			case "cyan":
				Main.setColor("\u001B[36m");
				break;
			case "white":
				Main.setColor("\u001B[37m");
				break;	
				
			default: 
				System.out.println("El color introducido no es valido (black, red, green, yellow, blue, purple, cyan, white)");
				break;
			}
			Main.generarMenu(ruta);
		}
			
	
}
public static void comandoHelp(String ruta, String[] partido) {
	
	
	if(partido.length!=1) {
		
	System.err.println("Numero de parametros no valido");	
	
	}else {
		
	System.out.println("help -> lista todos comandos\".\r\n"
			+ "cd -> muestra la ruta del directorio actual\r\n"
			+ "cd .. -> Se mueve al directorio padre.\r\n"
			+ "cd + nombre -> Entrar en el.\r\n"
			+ "color + <nombrecolor> -> Cambia el color de la terminal (black, red, green, yellow, blue, purple, cyan, white).\r\n"
			+ "mkdir -> crea un directorio de la ruta actual\r\n"
			+ "info <nombre> -> Muestra la información del elemento Indicando\r\n"
			+ "cat <nombreFichero> -> Muestra el contenido de un fichero.\r\n"
			+ "top <numeroLineas><NombreFichero> -> Muestra las líneas especificadas de un fichero.\r\n"
			+ "mkfile <nombreFichero> <texto> ->  Crea un fichero con ese nombre y el contenido de texto.\r\n"
			+ "write <nombreFichero> <texto>-> Añade 'texto' al final del fichero especificado.\r\n"
			+ "dir -> Lista los archivos o directorios de la ruta actual.\r\n"
			+ "tree -> Lista los archivos o directorios de la ruta actual y sus subdirectorios.\r\n"
			+ "readpoint <nombreFichero1> <posición> -> Lee un archivo desde una determinada posición del puntero.\r\n"
			+ "delete <nombre> -> Borra el fichero, si es un directorio borra todo su contenido y a si mismo.\r\n"
			+ "Start <programa> - >ejecuta este proceso.\r\n"
			+ "find <nombre> -> busca un archivo en las subcarpetas\r\n"
			+ "rename <nombreAntiguo> <nombreNuevo> -> modifica el nombre de un archivo.\r\n"
			+ "close -> Cierra el programa.\r\n"
			+ "Clear -> Vacía la lista.");
	
	}	
		Main.generarMenu(ruta);
	
}


public static void comandoCd(String ruta, String[] partido) {
	
	if (partido.length==1) {
		
		System.out.println(ruta);
		Main.generarMenu(ruta);
		
	}else if(partido[1].equalsIgnoreCase("..") && partido.length==2){
		
		String[] rutapartida = ruta.split("\\\\");
		String rutatemp="C:";
		
		for(int i=1; i<rutapartida.length-1; i++) {
			
			rutatemp=rutatemp+"\\"+rutapartida[i];
		
		}
		
		ruta=rutatemp;
		Main.generarMenu(ruta);
		
	}else if (partido.length>=2){{
		
		String rutanueva = (ruta+"\\");
		rutanueva=rutanueva + partido[1];
		
		for(int i = 2; i<partido.length; i++) {
			
			rutanueva=rutanueva+" "+partido[i];
			
		}
		
		 File directorio = new File(rutanueva);
	        if (directorio.exists() && directorio.isDirectory()) {
	        	
		ruta=rutanueva;
		
	        }else {
	        	
	        	System.err.println("La carpeta no existe");
	        	
	        }
		Main.generarMenu(ruta);
	}

		
	}
		Main.generarMenu(ruta);
		
	
}

public static void comandoMkdir(String ruta, String[] partido) {
	
	Path path = Path.of(ruta);
	
	if(partido.length==1) {
		
		System.err.println("Debes introducir el nombre de la carpeta");
		
	}
	
	for (int i=1; i<partido.length; i++) {
		
		String rutaNueva=ruta+"\\"+partido[i];
        File directorio = new File(rutaNueva);
        
        if (!directorio.exists()) {
        	
            if (directorio.mkdirs()) {
            	
                System.out.println("Directorio creado");
                
            } else {
            	
                System.err.println("Error al crear directorio");
                
            }
            
        }else {
        	
        	System.err.println("Error al crear el directorio: Ya existe");
        	
        }
	}
	
	Main.generarMenu(ruta);

}

public static void comandoInfo(String ruta, String[] partido) {
	
	if (partido.length<2) {
		
		System.err.println("Numero de argumentos no válido");
		Main.generarMenu(ruta);
		
	} else {
		String rutaNueva=ruta+"\\"+partido[1];
		for (int i=2;i<partido.length;i++) {
		    rutaNueva=rutaNueva+" "+partido[i];
		}
		
        File archivo = new File(rutaNueva);
        if (archivo.exists()) {
            System.out.println("Nombre de Archivo: " + archivo.getName());
            System.out.println("Absolute path: " + archivo.getAbsolutePath());
            System.out.println("Permisos de escritura: " + archivo.canWrite());
            System.out.println("Permisos de lectura: " + archivo.canRead());
            System.out.println("Tamaño del archivo: " + archivo.length());
            System.out.println("Carpeta contenedora: " + archivo.getParent());
            System.out.println("Es archivo: " + archivo.isFile());
            System.out.println("Es directorio " + archivo.isDirectory());
            System.out.println("Es oculto: " + archivo.isHidden());
            
        } else {
        	
            System.err.println("El archivo no existe.");
            
        }	
        
        Main.generarMenu(ruta);
        
	}
	
	
}

public static void comandoCat(String ruta, String[] partido) {
	
	if(partido.length<2) {
		
		System.err.println("Numero de argumentos no valido ");
		
	}else {
	String rutaNueva=ruta+"\\"+partido[1];
	for (int i=2;i<partido.length;i++) {
	    rutaNueva=rutaNueva+" "+partido[i];
	}
	File fichero = new File (rutaNueva);
	
    try (BufferedReader br = new BufferedReader(new FileReader(rutaNueva))) {
    	
        String linea;
        while ((linea = br.readLine()) != null) {
        	
            System.out.println(linea);
            
        }
        
    } catch (IOException e) {
    	
        System.err.println("El archivo no existe");
        Main.generarMenu(ruta);
        
    }
	
	}
	Main.generarMenu(ruta);
	
	
}

public static void comandoTop(String ruta, String[] partido) {
	
	if(partido.length < 3) {
		
		System.err.println("Numero de argumentos no valido ");
		
	}else {
		
	String rutaNueva=ruta+"\\"+partido[2];
	for (int i=3;i<partido.length;i++) {
	 rutaNueva=rutaNueva+" "+partido[i];
		
	}
	System.out.println(rutaNueva);
	File fichero = new File (rutaNueva);
	
    try {
    	
        int numero = Integer.parseInt(partido[1]);
	    int numeroLineaDeseada = numero;
	    
        try (BufferedReader br = new BufferedReader(new FileReader(rutaNueva))) {
        	
            int numeroLinea = 1;
            String linea;
            
            while ((linea = br.readLine()) != null) {
            	
                if (numeroLinea == numeroLineaDeseada) {
                	
                    System.out.println(linea);
                    
                    break; 
                    
                }
                numeroLinea++;
            }
    } catch (NumberFormatException e) {
    	
        System.err.println("La cadena no es un número válido.");
        
    }


               
        } catch (IOException e) {
        	
            System.err.println("El archivo indicado no existe");
            
        }
    }
	
	Main.generarMenu(ruta);

}

public static void comandoMkfile(String ruta, String[] partido) {
	

    if (partido.length < 2) {
        System.err.println("Debes introducir al menos un nombre de archivo y contenido");
        Main.generarMenu(ruta);
    }

    String nombreArchivo = partido[1]; // El primer argumento es el nombre del archivo

    String rutaCompleta = ruta + "\\" + nombreArchivo;

    try {
    	
        File fichero = new File(rutaCompleta);

        if (fichero.exists()) {
        	
            System.err.println("El fichero ya existe");
            
        } else {
        	
            fichero.createNewFile();
            
            System.out.println("Fichero creado con éxito");

            FileWriter writer = new FileWriter(fichero);

            for (int i = 2; i < partido.length; i++) {
            	
                writer.write(partido[i]);
                
                if (i < partido.length - 1) {
                	
                    writer.write(" ");
                }
            }

            writer.close();
        }
    } catch (IOException e) {
        System.err.println("No se ha podido crear el fichero");
    }

    Main.generarMenu(ruta);
}

public static void comandoRename(String ruta, String[] partido) {
	
    if (partido.length != 3) {
    	
        System.err.println("Número de argumentos no válido.");
        Main.generarMenu(ruta);
        
    }

    String nombreAntiguo = partido[1];
    String nombreNuevo = partido[2];

    File archivoDirectorioAntiguo = new File(ruta+"//"+nombreAntiguo);
    File archivoDirectorioNuevo = new File(ruta+"//"+nombreNuevo);

    if (!archivoDirectorioAntiguo.exists()) {
    	
        System.err.println("El archivo o directorio con nombre antiguo no existe.");
        Main.generarMenu(ruta);
        
    }

    if (archivoDirectorioNuevo.exists()) {
    	
        System.err.println("Ya existe un archivo o directorio con el nombre nuevo.");
        Main.generarMenu(ruta);
        
    }

    if (archivoDirectorioAntiguo.renameTo(archivoDirectorioNuevo)) {
    	
        System.out.println("El archivo o directorio ha sido renombrado correctamente.");
        
    } else {
    	
        System.err.println("No se pudo renombrar el archivo o directorio.");
        Main.generarMenu(ruta);
        
    }

    Main.generarMenu(ruta);
}


public static void comandoWrite(String ruta, String[] partido) {
	
	if (partido.length==1) {
		
		System.err.println("Numero de argumentos no valido");
		
	}else {
		
       if (!partido[1].endsWith(".txt")) {
    	   
            System.err.println("El archivo debe tener una extensión .txt.");
            Main.generarMenu(ruta);
            
        }
       
       String rutaNueva=ruta+"\\"+partido[1];
		File fichero = new File (rutaNueva);
       String textoAEscribir="";
      
       if (fichero.exists()) {
    	   
       for (int i=2; i<partido.length;i++) {
    	 textoAEscribir=textoAEscribir+" "+partido[i];
    	 
       }
       
       
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fichero, true))) {
        	
            bw.write(textoAEscribir);
            bw.newLine();
            System.out.println("Texto escrito en el archivo.");
            
        } catch (IOException e) {
        	
            System.err.println("El archivo no existe");

        }
      
       }else {
    	   
    	   System.err.println("El archivo no existe");
    	   
       }
        }
	
	Main.generarMenu(ruta);
	
}

public static void comandoDir(String ruta, String[] partido) {

if(partido.length!=1) {
		
		System.err.println("Numero de argumentos no valido");
		Main.generarMenu(ruta);
		
	}
	
	Path p = Path.of(ruta);
	
	try {
		
		Stream<Path> stream = Files.list(p);
		ArrayList<Path> lista = (ArrayList <Path>)stream.collect(Collectors.toList());
		
		for (Path item : lista) {
			
			File Fichero = new File(item.toString());
			long ms = Fichero.lastModified();
			Date d = new Date(ms);
			Calendar c = new GregorianCalendar(); 
			c.setTime(d);
			String[] archivo=item.toString().split("\\\\");
			
			if((c.get(Calendar.MINUTE))<10) {
				
			System.out.println(Integer.toString(c.get(Calendar.YEAR))+"/"+(Integer.toString(c.get(Calendar.MONTH)))+"/"+
			(Integer.toString(c.get(Calendar.DATE))+" "+Integer.toString(c.get(Calendar.HOUR_OF_DAY))+":0"+Integer.toString(c.get(Calendar.MINUTE))+"  "+archivo[archivo.length-1]));
			;
			
			}else {
				
			System.out.println(Integer.toString(c.get(Calendar.YEAR))+"/"+(Integer.toString(c.get(Calendar.MONTH)))+"/"+
			(Integer.toString(c.get(Calendar.DATE))+" "+Integer.toString(c.get(Calendar.HOUR_OF_DAY))+":"+Integer.toString(c.get(Calendar.MINUTE))+"  "+archivo[archivo.length-1]));
			
			}
		}
		
	}catch (NoSuchFileException e) {
		
	    System.err.println("El directorio especificado no existe: " + p.toString());
	    Main.generarMenu(ruta);
	    
	} catch (IOException e) {
		
		Main.generarMenu(ruta);
		
	}
	
	Main.generarMenu(ruta);
	
}

	
public static void comandoTree(String ruta, String[] partido) {
	

   if (partido.length != 1) {
        System.err.println("Número de argumentos no válido");
        Main.generarMenu(ruta);
        return;
    }

    Path p = Path.of(ruta);

    try {
        listarContenido(p, "");
    } catch (NoSuchFileException e) {
        System.err.println("El directorio especificado no existe: " + p.toString());
    } catch (IOException e) {
        System.err.println("Error al listar el contenido del directorio: " + e.getMessage());
    }

    Main.generarMenu(ruta);
}

public static void comandoFind(String ruta, String[] partido) {

if (partido.length != 2) {
	
    System.err.println("Número de argumentos no válido. Uso: find <nombre>");
    
    return;
}

String nombreABuscar = partido[1];

buscarArchivoEnDirectorio(new File(ruta), nombreABuscar);

Main.generarMenu(ruta);
}

private static void buscarArchivoEnDirectorio(File directorio, String nombreABuscar) {

File[] archivos = directorio.listFiles();

if (archivos != null) {
	
    for (File archivo : archivos) {
    	
        if (archivo.isFile() && archivo.getName().contains(nombreABuscar)) {
        	
            System.out.println("Encontrado en: " + archivo.getAbsolutePath());
            
        } else if (archivo.isDirectory()) {
        	

            buscarArchivoEnDirectorio(archivo, nombreABuscar);
        }
    }
}
}

public static void listarContenido(Path directorio, String separador) throws IOException {
	
    try (Stream<Path> stream = Files.list(directorio)) {
    	
        ArrayList<Path> lista = (ArrayList<Path>) stream.collect(Collectors.toList());

        for (int i = 0; i < lista.size(); i++) {
        	
            Path item = lista.get(i);
            File fichero = item.toFile();
            long ms = fichero.lastModified();
            Date d = new Date(ms);
            Calendar c = new GregorianCalendar();
            c.setTime(d);
            String[] archivo = item.toString().split("\\\\");
            String nombre = archivo[archivo.length - 1];

            if (c.get(Calendar.MINUTE) < 10) {
            	
                System.out.println(
                		
                        separador + "|-- " + Integer.toString(c.get(Calendar.YEAR)) + "/"
                                + (Integer.toString(c.get(Calendar.MONTH))) + "/"
                                + (Integer.toString(c.get(Calendar.DATE)) + " "
                                        + Integer.toString(c.get(Calendar.HOUR_OF_DAY)) + ":0"
                                        + Integer.toString(c.get(Calendar.MINUTE)) + "  " + nombre));
                
            } else {
            	
                System.out.println(
                		
                        separador + "|-- " + Integer.toString(c.get(Calendar.YEAR)) + "/"
                                + (Integer.toString(c.get(Calendar.MONTH))) + "/"
                                + (Integer.toString(c.get(Calendar.DATE)) + " "
                                        + Integer.toString(c.get(Calendar.HOUR_OF_DAY)) + ":"
                                        + Integer.toString(c.get(Calendar.MINUTE)) + "  " + nombre));
                
            }

            if (Files.isDirectory(item)) {
            	
                String nuevoSeparador = separador + "    ";
                
                if (i == lista.size() - 1) {
                	
                    nuevoSeparador += "    ";
                }
                
                listarContenido(item, nuevoSeparador); // Llamada recursiva para listar el contenido de los subdirectorios.
            }
        }
    }
}
		

	

public static void comandoReadpoint(String ruta, String[] partido) {
	
    if (partido.length != 3) {
    	
        System.err.println("Numero de argumentos no valido");
        Main.generarMenu(ruta);
        
    }
    
    try {
    	
    String nombreFichero = ruta+"\\"+partido[1];
    long posicion = Long.parseLong(partido[2]);

    try (RandomAccessFile archivo = new RandomAccessFile(nombreFichero, "r")) {
    	
        archivo.seek(posicion); 

        int byteLeido;
        while ((byteLeido = archivo.read()) != -1) {
            System.out.print((char) byteLeido); 
        }
        
    } catch (IOException e) {
    	
       System.err.println("El archivo no existe");
       Main.generarMenu(ruta);
       
    }}catch (NumberFormatException e) {
    	
    	System.err.println("La cadena no es un numero valido");
    	Main.generarMenu(ruta);
    	
    }
    
    Main.generarMenu(ruta);

}

public static void comandoDelete(String ruta, String[] partido) {

if (partido.length != 2) {
    System.err.println("Número de argumentos no válido");
    return;
}

String nombre = ruta+"//"+partido[1];

File archivoDirectorio = new File(nombre);

if (!archivoDirectorio.exists()) {
    System.err.println("El archivo o directorio especificado no existe.");
    Main.generarMenu(ruta);
    return;
}

try {
    if (archivoDirectorio.isDirectory()) {
        borrarDirectorio(archivoDirectorio);
    } else {
        archivoDirectorio.delete();
    }

    System.out.println("El archivo o directorio ha sido borrado correctamente.");
} catch (Exception e) {
    System.err.println("Error al borrar el archivo o directorio: " + e.getMessage());
}

Main.generarMenu(ruta);
}

public static void borrarDirectorio(File directorio) {

File[] archivos = directorio.listFiles();
if (archivos != null) {
	
    for (File archivo : archivos) {
    	
        if (archivo.isDirectory()) {
        	
            borrarDirectorio(archivo);
            
        } else {
        	
            archivo.delete();
            
        }
    }
}

directorio.delete();

}

public static void comandoStart(String ruta, String[] partido) {

 if (partido.length != 2) {
	 
       System.err.println("Numero de argumentos no valido");
       Main.generarMenu(ruta);
       
   }

   String programa = partido[1];
   ProcessBuilder processBuilder = new ProcessBuilder(programa);

   try {
   	
       Process proceso = processBuilder.start();


   } catch (IOException e) {
   	
      System.err.println("El proceso no existe");
      
}
   Main.generarMenu(ruta);

}

public static void comandoClear(String ruta, String[] partido) {

for (int i = 0; i < 50; i++) {
	
    System.out.println();
    
}
Main.generarMenu(ruta);
	
}

}

