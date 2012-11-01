package controler;

import solitaire.application.Carte;
import solitaire.application.Colonne;
import solitaire.application.DoubleTas;
import solitaire.application.Sabot;
import solitaire.application.Solitaire;
import solitaire.application.Tas;
import solitaire.application.TasDeCartes;
import solitaire.application.TasDeCartesAlternees;
import solitaire.application.TasDeCartesColorees;
import solitaire.application.Usine;

public class CUsine extends Usine {

	@Override
	public Carte newCarte(int arg0, int arg1) {
		return new CCarte(arg0, arg1);
	}

	@Override
	public Colonne newColonne(String arg0, Usine arg1) {
		return new CColonne(arg0, (CUsine)arg1);
	}

	@Override
	public DoubleTas newDoubleTas(Tas arg0, Tas arg1) {
		return super.newDoubleTas(arg0, arg1);
	}

	@Override
	public Sabot newSabot(String arg0, Usine arg1) {
		return new CSabot(arg0, (CUsine)arg1);
	}

	@Override
	public Solitaire newSolitaire(String arg0, Usine arg1) {
		return new CSolitaire(arg0, (CUsine)arg1);
	}

	@Override
	public TasDeCartes newTasDeCartes(String arg0, Usine arg1) {
		return new CTasDeCartes(arg0, (CUsine) arg1);
	}

	@Override
	public TasDeCartesAlternees newTasDeCartesAlternees(String arg0, Usine arg1) {
		return new CTasDeCartesAlternees(arg0, (CUsine)arg1);
	}

	@Override
	public TasDeCartesColorees newTasDeCartesColorees(String arg0, int arg1,
			Usine arg2) {
		return new CTasDeCartesColorees(arg0, arg1, (CUsine)arg2);
	}

}
