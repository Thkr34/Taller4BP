package Strategy;

import java.util.ArrayList;

import dominio.*;

public class AdministradorEstrategias {
private EstrategiaOrdenar EstrategiaActual;
	
	public void cambiarEstrategia(EstrategiaOrdenar nuevaEstrat) {
		this.EstrategiaActual = nuevaEstrat;
	}
	
	public void ejecutarEstrategia(ArrayList<Carta> coleccion) {
	if (EstrategiaActual != null) {
		EstrategiaActual.ordenar(coleccion);
		} else {
			System.out.println("seleccione un tipo de ordenamiento antes de ejecutarlo.");
		}
	}
}
