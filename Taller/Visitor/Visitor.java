// Bastián Felipe Perines Flores
// 22.386.978-5
// ICCI
package Visitor;
import dominio.*;

public interface Visitor {
	int visitarPokemon(Pokemon p);
	int visitarItem(Item i);
	int visitarSupporter(Supporter s);
	int visitarEnergy(Energy e);
}
