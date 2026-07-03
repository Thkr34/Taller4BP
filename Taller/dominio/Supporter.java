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
		return "Supporter  "+ nombre + " [efectoPorTurno=" + efectoPorTurno + "]";
	}

	@Override
	public int aceptarVisita(Visitor v) {
		return v.visitarSupporter(this);
	}
	
}
