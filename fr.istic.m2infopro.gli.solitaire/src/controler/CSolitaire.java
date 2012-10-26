package controler;

import presentation.PSolitaire;
import solitaire.application.Solitaire;
import solitaire.application.Usine;

public class CSolitaire extends Solitaire {

	private PSolitaire pSolitaire;
	
	public CSolitaire(String arg0, Usine arg1) {
		super(arg0, arg1);
		
		pSolitaire = new PSolitaire("PSolitaire", arg1);	
		
	}

	public PSolitaire getPresentation(){
		return pSolitaire;
		
	}
	
	public static void main(String args[]) {
		CUsine u = new CUsine();
		CSolitaire cSolitaire = (CSolitaire) u.newSolitaire("solitaire", u);
	}
}
