// Bastián Felipe Perines Flores
// 22.386.978-5
// ICCI
package logica;

import dominio.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class ActualizadorArchivos {
	private static ActualizadorArchivos instance = null;
	
	private ActualizadorArchivos() {
	}
	
	public static ActualizadorArchivos getInstance() {
		if (instance == null) {
			instance = new ActualizadorArchivos();
		}
		return instance;
	}
	
	public void sobreEscribirSobres(ArrayList<Carta> arrayList) {
		try (BufferedWriter sobreEscribirSobres = new BufferedWriter(new FileWriter("Sobres.txt"))) {
			for (int i = 0; i < arrayList.size(); i++) {
				if (i == arrayList.size()-1) {
					sobreEscribirSobres.write(arrayList.get(i).convertirLinea());
				} else {
					sobreEscribirSobres.write(arrayList.get(i).convertirLinea());
					sobreEscribirSobres.newLine();
				}
			}
		} catch (IOException e) {
			System.out.println("No se encontro el archivo Hechizos.txt");
		}
	}
}
