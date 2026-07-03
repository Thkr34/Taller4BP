// Bastián Felipe Perines Flores
// 22.386.978-5
// ICCI
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
		return "Item "+ nombre + " " + rareza + " [buff=" + buff + "]";
	}

	@Override
	public int aceptarVisita(Visitor v) {
		return v.visitarItem(this);
		
	}

	public void setBuff(int buff) {
		this.buff = buff;
	}

	@Override
	public String convertirLinea() {
		String linea = this.nombre + ";" + this.rareza + ";" + this.tipo + ";" + this.buff;
		return linea;
	}
}
