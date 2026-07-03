package Strategy;

import java.util.ArrayList;

import dominio.Carta;
import Visitor.*;

public class RutaPoder implements EstrategiaOrdenar {
	Visitor auxiliar = new CalculadorPoder();
	@Override
	public void ordenar(ArrayList<Carta> coleccion) {
		for (int i = 0; i < coleccion.size(); i++) {
			for (int j = i+1; j < coleccion.size(); j++) {
				if (coleccion.get(i).aceptarVisita(auxiliar) < coleccion.get(j).aceptarVisita(auxiliar)) {
					Carta aux = coleccion.get(i);
					coleccion.set(i, coleccion.get(j));
					coleccion.set(j, aux);
				}
			}
		}
		
	}

}
