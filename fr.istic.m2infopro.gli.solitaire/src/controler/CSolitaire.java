package controler;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import presentation.PSabot;
import presentation.PSolitaire;
import presentation.PTasDeCartesColorees;
import solitaire.application.Colonne;
import solitaire.application.Solitaire;
import solitaire.application.TasDeCartesColorees;

public class CSolitaire extends Solitaire {

	private PSolitaire pSolitaire;
	public CSolitaire(String arg0, CUsine u) {
		super(arg0, u);
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

		JFrame frame = new JFrame("Le solitaire le plus sexy de la terre :)");
		frame.getContentPane().add(solitaire.getPresentation());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(850, 700);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		frame.setResizable(true);
		frame.setVisible(true);

		solitaire.run();
	}
}
