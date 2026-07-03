package dominio;
import java.util.ArrayList;

import Factory.*;
import Visitor.*;
import Strategy.*;

public class SistemaImpl implements ISistema {
	private static SistemaImpl instance = null;
	private static Factory creadorCartas = new FactoryCartas();
	private static Visitor calculadorPoder = new CalculadorPoder();
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
	
	public void crearCarta(String linea) {
		Carta c = creadorCartas.crearCarta(linea);
		if (c == null) {
			return;
		}
		coleccion.add(c);
	}
	
	public void print() {
		for (Carta carta : coleccion) {
			System.out.println(carta);
			System.out.println(carta.aceptarVisita(calculadorPoder));
		}
	}
}
