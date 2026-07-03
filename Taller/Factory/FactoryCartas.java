package Factory;

import dominio.*;

public class FactoryCartas implements Factory {

	public Carta crearCarta(String linea) {
		String[] partes = linea.split(";");
		String nombre = partes[0];
		int rareza = Integer.parseInt(partes[1]);
		String tipo = partes[2];
		switch(tipo) {
		case("Pokemon"):
			int dmg = Integer.parseInt(partes[3]);
			int energias = Integer.parseInt(partes[4]);
			Pokemon p = new Pokemon(nombre, rareza, tipo, dmg, energias);
			return p;
		case("Item"):
			int buff = Integer.parseInt(partes[3]);
			Item i = new Item(nombre, rareza, tipo, buff);
			return i;
		case("Supporter"):
			int efectosPorTurno = Integer.parseInt(partes[3]);
			Supporter s = new Supporter(nombre, rareza, tipo, efectosPorTurno);
			return s;
		case("Energy"):
			String elemento = partes[3];
			Energy e = new Energy(nombre, rareza, tipo, elemento);
			return e;
		}
		return null;
	}

}
