package Visitor;

import dominio.Energy;
import dominio.Item;
import dominio.Pokemon;
import dominio.Supporter;

public class CalculadorPoder implements Visitor {

	@Override
	public int visitarPokemon(Pokemon p) {
		return (p.getDmg()/p.getEnergias())*100;
	}

	@Override
	public int visitarItem(Item i) {
		return i.getBuff()*20;

	}

	@Override
	public int visitarSupporter(Supporter s) {
		return s.getEfectoPorTurno()*50;
	}

	@Override
	public int visitarEnergy(Energy e) {
		return 1;
	}

}
