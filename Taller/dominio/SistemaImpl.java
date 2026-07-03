// Bastián Felipe Perines Flores
// 22.386.978-5
// ICCI
package dominio;

import java.util.ArrayList;

import Factory.*;
import Strategy.*;

public class SistemaImpl implements ISistema {
	private static SistemaImpl instance = null;
	private static Factory creadorCartas = new FactoryCartas();
	private static AdministradorEstrategias AE = new AdministradorEstrategias();

	private static ArrayList<Carta> coleccion = new ArrayList<>();

	private SistemaImpl() {
	}

	public static SistemaImpl getInstance() {
		if (instance == null) {
			instance = new SistemaImpl();
		}
		return instance;
	}

	public static ArrayList<Carta> getColeccion() {
		return coleccion;
	}

	public void crearCarta(String linea) {
		Carta c = creadorCartas.crearCarta(linea);
		if (c == null) {
			return;
		}
		coleccion.add(c);
	}

	public void ordenarNombre() {
		AE.cambiarEstrategia(new RutaNombre());
		AE.ejecutarEstrategia(coleccion);
	}

	public void ordenarPoder() {
		AE.cambiarEstrategia(new RutaPoder());
		AE.ejecutarEstrategia(coleccion);
	}

	public void ordenarRareza() {
		AE.cambiarEstrategia(new RutaRareza());
		AE.ejecutarEstrategia(coleccion);
	}

	public boolean eliminarCarta(String nombre) {
		for (Carta c : coleccion) {
			if (c.getNombre().equalsIgnoreCase(nombre)) {
				coleccion.remove(c);
				return true;
			}
		}
		return false;
	}

	public Carta buscarCarta(String nombre) {
		for (Carta c : coleccion) {
			if (c.getNombre().equalsIgnoreCase(nombre)) {
				return c;
			}
		}
		return null;
	}
}
