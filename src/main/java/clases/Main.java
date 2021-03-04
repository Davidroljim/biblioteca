/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package clases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    
    public static void main (String [] args) {
		ArrayList<Libro> catalogo = new ArrayList<Libro>(); 	
    	while (true) {
			int opcion = menu();
			switch (opcion) {
			case 1:
				//TODO Alta de Libros
				//TODO titulo:ibsn:genero:paginas
				alta(catalogo);
				break;
			case 2:
				//TODO Lista de Libros
				listaDeLibros(catalogo);
				break;
			case 3:
				//TODO Baja de libros
				bajalibro(catalogo);
				break;
				
			case 4:
				//TODO Busqueda de libros
				busquedaDeLibros(catalogo);
				break;
				
			case 5:
				//TODO Ordenacion de libros
				ordenaCatalogo (catalogo);
				break;
				
			case 6:
				//TODO Salvar fichero
				crearFichero();
				break;
				
			case 7:
				escribirenfichero(catalogo);
				break;
				
			case 8:
				cargarfichero(catalogo);
				break;
			
			case 9:
				borrarcatalogo(catalogo);
				break;	
			}
		}
	}
    
    private static int menu() {
    	int opcion=0;
    	
    	do {
    		System.out.println("Opciones:");
    		System.out.println("1. Alta de Libro");
    		System.out.println("2. Lista de Libros");
    		System.out.println("3. Baja de Libros");
    		System.out.println("4. B�squeda de Libros");
    		System.out.println("5. Ordenacion de Libros");
    		System.out.println("Introduce la opcion:");
    	
    		opcion = leerOpcion(2);
    		
    	}while(opcion <=0);
    	
    	return opcion;
    }
    
    private static int leerOpcion(int max) {
    	int opcion = -1;
    	@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
    	try {
        	opcion = sc.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("La opci�n no es correcta");
			if(opcion > max) {
				opcion = -1;
			}
		}    	
//    	sc.close();
    	return opcion;
    }
    
    private static String leerCadena() {
    	String opcion = null;
    	@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
    	try {
        	opcion = sc.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("La opci�n no es correcta");
			
			}
    	return opcion;
		}
    
    private static Libro procesaEntrada(String entrada) {
    	Libro libro = null;
    	
    	String [] datos = entrada.split(":");
    	
    	String titulo = datos[0];
    	String isbn = datos[1];
    	Genero genero = Genero.getGenero(datos[2]);
    	String autor = datos[3];
    	Integer paginas = Integer.parseInt(datos[4]);
    	
    	libro = new Libro(titulo,isbn,genero,autor,paginas);
    	
    	return libro;
    }
    
    private static void alta(ArrayList<Libro> catalogo) {
    	//Leer de la entrada
    	String datosLibro = obtenerDatosLibro();
    	//titulo:isbn:genero:autor:paginas
    	//Procesar la entrada
    	Libro libro = procesaEntrada(datosLibro);
    	//Crear el libro con los datos de la entrada
    	catalogo.add(libro);
    	//Meter el libro en el catalogo
    }
    
    private static String obtenerDatosLibro() {
    	String datos=null;
    	
    	boolean validado=false;
    	while(!validado) {
    		System.out.println("Introduce los datos de un libro.");
    		System.out.println("Usa el formato \"titulo:isbn:genero:autor:paginas\"");
    		try {
    			datos = leerCadena();
    			if(true)//Supongo de momento que valida siempre
    				validado=true;
    		}catch (InputMismatchException e) {
				System.out.println("Datos de entrada no v�lidos");
			}
    	}
    	
    	return datos;
    }
    
    private static void listaDeLibros(ArrayList<Libro> catalogo){
    	for (int i = 0; i < catalogo.size(); i++) {
    	     Libro libro = catalogo.get(i);
    	      System.out.println(libro.getTitulo() + " " + libro.getIsbn() + " " + libro.getGenero() 
    	      + " " + libro.getAutor() + " " + libro.getPaginas());
    	      System.out.println("---------------------------------------------------------");
    	    }
    	
    }
    public static void bajalibro(ArrayList<Libro>catalogo) {
    	int librosel = 0;
    	System.out.println("Elige el libro que quiere borrar:");
    	Scanner scanner = new Scanner(System.in);
			librosel=scanner.nextInt();
		
        librosel = librosel -1;
        catalogo.remove(librosel);
    	scanner.close();
    }
    
    private static void busquedaDeLibros (ArrayList<Libro> catalogo) {
    	String isbn = "";
    	Scanner scanner = new Scanner(System.in);	
    	System.out.println("Introduzca el isbn del libro a buscar");
    	isbn = scanner.next();
    	
    	Libro l = new Libro();
    	l.setIsbn(isbn);
    	
    	int posicion = 0;
    	posicion = catalogo.indexOf(l);
    	if( posicion< 0){
    		System.out.println("El libro no se encuentra en nuestro catalogo");
    	}
    	else {
    		System.out.println(catalogo.get(posicion));
    	}
    	scanner.close();
    }
    
    private static void ordenaCatalogo (ArrayList<Libro> catalogo) {
   	 Scanner scanner = new Scanner(System.in);
   	 System.out.println("Como quieres ordenar el catalogo");
   	 System.out.println("Si quieres ordenarlo alfabeticamente pulse 1 y si quieres ordenarlo por N�mero de p�ginas pulse 2");
   	 int seguir = scanner.nextInt();
   	 
   	 if (seguir==1) {
   		Collections.sort((List)catalogo);
   	 }else {
   		 
   	 }
   	 
       scanner.close();	
       }
    
    
    public static void crearFichero (){
    	    try {
    	      File myObj = new File("ficherolibro.txt");
    	      if (myObj.createNewFile()) {
    	        System.out.println("File created: " + myObj.getName());
    	      } else {
    	        System.out.println("File already exists.");
    	      }
    	    } catch (IOException e) {
    	      System.out.println("An error occurred.");
    	      e.printStackTrace();
    	    }
    	  }
    	
    
    
    public static void borrarcatalogo(ArrayList<Libro>catalogo) {
    	 
		for(int libros = 0;libros<catalogo.size();libros = 0) {
			catalogo.remove(libros);
		}
    }
    
    public static void escribirenfichero(ArrayList<Libro>catalogo) {
        String seleccionado = null;
        System.out.println("El fichero debe ser de esta estructura 'nombredefichero.txt':");
        Scanner teclado = new Scanner(System.in);
        seleccionado = teclado.next();
        System.out.println("El fichero:"+seleccionado);
        try {
            FileWriter myWriter = new FileWriter(seleccionado);
            for (Libro l: catalogo) {
                myWriter.write(l.toString());

            }
            myWriter.close();
            System.out.println("Se ha escrito el fichero");
          } catch (IOException e) {
            System.out.println("ha ocurrido un error");
            e.printStackTrace();
          }
    }
    
    public static void cargarfichero(ArrayList<Libro>catalogo) {
        String seleccionado = null;
        System.out.println("El fichero debe ser de esta estructura 'nombredefichero.txt':");
        Scanner teclado = new Scanner(System.in);
        seleccionado = teclado.next();
        System.out.println("El fichero:"+seleccionado);
            try {
              File myObj = new File(seleccionado);
              Scanner myReader = new Scanner(myObj);
              while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                Libro libro = null;
                String [] datos = data.split(",");

                String titulo = datos[0];
                String isbn = datos[1];
                Genero genero = Genero.getGenero(datos[2]);
                String autor = datos[3];
                Integer paginas = Integer.parseInt(datos[4]);

                libro = new Libro(titulo,isbn,genero,autor,paginas);
                catalogo.add(libro);
              }
              myReader.close();
            } catch (FileNotFoundException e) {
              System.out.println("An error occurred.");
              e.printStackTrace();
            }
    }
    
    
}