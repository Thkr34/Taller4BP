// Bastián Felipe Perines Flores
// 22.386.978-5
// ICCI
package Strategy;

import java.util.ArrayList;

import dominio.Carta;

public class RutaNombre implements EstrategiaOrdenar {
	
	@Override
	public void ordenar(ArrayList<Carta> coleccion) {
		for (int i = 0; i < coleccion.size(); i++) {
			for (int j = i+1; j < coleccion.size(); j++) {
				if (coleccion.get(i).getNombre().compareTo(coleccion.get(j).getNombre()) > 0) {
					Carta aux = coleccion.get(i);
					coleccion.set(i, coleccion.get(j));
					coleccion.set(j, aux);
				}
			}
		}
	}

}
