// Bastián Felipe Perines Flores
// 22.386.978-5
// ICCI
package dominio;

import Visitor.Visitor;

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
		return "Energy " + nombre + " " + rareza + " [elemento=" + elemento + "]";
	}

	@Override
	public int aceptarVisita(Visitor v) {
		return v.visitarEnergy(this);
		
	}

	public void setElemento(String elemento) {
		this.elemento = elemento;
	}

	@Override
	public String convertirLinea() {
		String linea = this.nombre + ";" + this.rareza + ";" + this.tipo + ";" + this.elemento;
		return linea;
	}
}
