package dominio;

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
		return "Supporter [efectoPorTurno=" + efectoPorTurno + "]";
	}
	
}
