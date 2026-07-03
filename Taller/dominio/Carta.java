// Bastián Felipe Perines Flores
// 22.386.978-5
// ICCI
package dominio;
import Visitor.*;

public abstract class Carta {
	protected String nombre;
	protected int rareza;
	protected String tipo;
	
	public Carta(String nombre, int rareza, String tipo) {
		super();
		this.nombre = nombre;
		this.rareza = rareza;
		this.tipo = tipo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public int getRareza() {
		return rareza;
	}
	
	public String getTipo() {
		return tipo;
	}

	@Override
	public String toString() {
		return "Carta [nombre=" + nombre + ", rareza=" + rareza + ", tipo=" + tipo + "]";
	}
	
	public abstract int aceptarVisita(Visitor v);
	
	
}
