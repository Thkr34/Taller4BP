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
		return "Pokemon "+ nombre + " [dmg=" + dmg + ", energias=" + energias + "]";
	}

	@Override
	public int aceptarVisita(Visitor v) {
		return v.visitarPokemon(this);
	}
	
}
