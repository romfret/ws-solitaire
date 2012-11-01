package controler;

import presentation.PSabot;
import presentation.PSolitaire;
import solitaire.application.Solitaire;

public class CSolitaire extends Solitaire {

	private PSolitaire pSolitaire;
	private PSabot pSabot;
	
	
	public CSolitaire(String arg0, CUsine u) {
		super(arg0, u);
		initialiser();
		
		pSabot = ((CSabot)this.sabot).getPresentation();
		
		pSolitaire = new PSolitaire("PSolitaire", u);	
		pSolitaire.addPSabot(pSabot);
	}

	public PSolitaire getPresentation(){
		return pSolitaire;
		
	}
	
	public static void main(String args[]) {
		CUsine u = new CUsine();
		CSolitaire cSolitaire = (CSolitaire) u.newSolitaire("solitaire", u);
	}
}
