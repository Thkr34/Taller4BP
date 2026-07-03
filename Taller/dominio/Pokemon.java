// Bastián Felipe Perines Flores
// 22.386.978-5
// ICCI
package dominio;

import Visitor.Visitor;

public class Pokemon extends Carta {
	private int dmg;
	private int energias;
	
	public Pokemon(String nombre, int rareza, String tipo, int dmg, int energias) {
		super(nombre, rareza, tipo);
		this.dmg = dmg;
		this.energias = energias;
	}
	
	public int getDmg() {
		return dmg;
	}
	
	public int getEnergias() {
		return energias;
	}

	@Override
	public String toString() {
		return "Pokemon "+ nombre + " " + rareza + " [dmg=" + dmg + ", energias=" + energias + "]";
	}

	@Override
	public int aceptarVisita(Visitor v) {
		return v.visitarPokemon(this);
	}

	public void setDmg(int dmg) {
		this.dmg = dmg;
	}

	public void setEnergias(int energias) {
		this.energias = energias;
	}

	@Override
	public String convertirLinea() {
		String linea = this.nombre + ";" + this.rareza + ";" + this.tipo + ";" + this.dmg + ";" + this.energias;
		return linea;
	}
}
