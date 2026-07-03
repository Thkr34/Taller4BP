// Bastián Felipe Perines Flores
// 22.386.978-5
// ICCI
package dominio;

import Visitor.Visitor;

public class Supporter extends Carta {
	private int efectoPorTurno;

	public Supporter(String nombre, int rareza, String tipo, int efectoPorTurno) {
		super(nombre, rareza, tipo);
		this.efectoPorTurno = efectoPorTurno;
	}

	public int getEfectoPorTurno() {
		return efectoPorTurno;
	}

	@Override
	public String toString() {
		return "Supporter  "+ nombre + " " + rareza + " [efectoPorTurno=" + efectoPorTurno + "]";
	}

	@Override
	public int aceptarVisita(Visitor v) {
		return v.visitarSupporter(this);
	}

	public void setEfectoPorTurno(int efectoPorTurno) {
		this.efectoPorTurno = efectoPorTurno;
	}

	@Override
	public String convertirLinea() {
		String linea = this.nombre + ";" + this.rareza + ";" + this.tipo + ";" + this.efectoPorTurno;
		return linea;
	}
}
