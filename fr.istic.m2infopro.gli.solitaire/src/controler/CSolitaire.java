package controler;

import javax.swing.JFrame;

import presentation.PSabot;
import presentation.PSolitaire;
import presentation.PTasDeCartesColorees;
import solitaire.application.Colonne;
import solitaire.application.Solitaire;
import solitaire.application.TasDeCartesColorees;

public class CSolitaire extends Solitaire {

	private PSolitaire pSolitaire;
	private PSabot pSabot;
	
	
	public CSolitaire(String arg0, CUsine u) {
		super(arg0, u);
//		initialiser();
	}

	public PSolitaire getPresentation(){
		return pSolitaire;
		
	}
	
	@Override
	public void initialiser() {
		super.initialiser();

		this.pSolitaire = new PSolitaire(this);

		PSabot pSabot = ((CSabot) sabot).getPresentation();
		pSolitaire.addPSabot(pSabot);

		for (TasDeCartesColorees tdcc : this.pilesColorees) {
			PTasDeCartesColorees pTdcc = ((CTasDeCartesColorees) tdcc).getPresentation();
			pSolitaire.addTasDeCartesColorees(pTdcc);
		}

		for (Colonne c : this.pilesAlternees) {
			pSolitaire.addPColonne(((CColonne) c).getPresentation());
		}

	}

	
	public static void main(String args[]) {
		CSolitaire solitaire = new CSolitaire("CSolitaire", new CUsine());
		solitaire.initialiser();

		JFrame frame = new JFrame("Test CSolitaire");
		frame.getContentPane().add(solitaire.getPresentation());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(830, 700);
		frame.setResizable(false);
		frame.setVisible(true);

		solitaire.run();
	}
}
