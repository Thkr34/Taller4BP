package logica;
import java.io.File;
import java.util.*;

import dominio.*;

public class App {
	
	private static SistemaImpl sistema = SistemaImpl.getInstance();
	
	public static void main(String[] args) {
		leerSobres();
		sistema.print();
	}
	
	public static void leerSobres() {
		try(Scanner lectorSobres = new Scanner(new File("Sobres.txt"))) {
			while(lectorSobres.hasNextLine()) {
				String linea = lectorSobres.nextLine();
				sistema.crearCarta(linea);
			}
		} catch(Exception e) {
			System.out.println("ocurrio un error");
		}
	}
}
