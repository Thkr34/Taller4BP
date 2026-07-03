package dominio;

import Visitor.Visitor;

public class Item extends Carta {
	private int buff;

	public Item(String nombre, int rareza, String tipo, int buff) {
		super(nombre, rareza, tipo);
		this.buff = buff;
	}

	public int getBuff() {
		return buff;
	}

	@Override
	public String toString() {
		return "Item "+ nombre+ " [buff=" + buff + "]";
	}

	@Override
	public int aceptarVisita(Visitor v) {
		return v.visitarItem(this);
		
	}
	
}
