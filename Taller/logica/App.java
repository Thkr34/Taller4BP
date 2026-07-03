// Bastián Felipe Perines Flores
// 22.386.978-5
// ICCI
package logica;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.*;

import javax.swing.*;

import Visitor.CalculadorPoder;
import dominio.*;

public class App {
	
	private static SistemaImpl sistema = SistemaImpl.getInstance();
	
	public static void main(String[] args) {
		// Bastián Felipe Perines Flores
		// 22.386.978-5
		// ICCI
		leerSobres();
		new VentanaPrincipal();
		
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
	