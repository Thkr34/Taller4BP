package dominio;

public class Energy extends Carta {
	private String elemento;

	public Energy(String nombre, int rareza, String tipo, String elemento) {
		super(nombre, rareza, tipo);
		this.elemento = elemento;
	}

	public String getElemento() {
		return elemento;
	}

	@Override
	public String toString() {
		return "Energy [elemento=" + elemento + "]";
	}
	
	
}
