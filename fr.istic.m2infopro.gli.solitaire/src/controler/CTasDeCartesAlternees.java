package controler;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import presentation.PTasDeCartesAlternees;
import solitaire.application.Carte;
import solitaire.application.TasDeCartesAlternees;

public class CTasDeCartesAlternees extends TasDeCartesAlternees {

	private PTasDeCartesAlternees pTasDeCartesAlternees;

	public CTasDeCartesAlternees(String nom, CUsine u) {
		super(nom, u);
		pTasDeCartesAlternees = new PTasDeCartesAlternees(this);
	}

	/**
	 * Empile la carte sur cTasDeCatre<p>
	 * Empile la carte sur pTasDeCarte si la carte est sommet du cTasDeCartes
	 */
	public void empiler(Carte c) {
		super.empiler(c);
		try {

			if (c == getSommet())
				pTasDeCartesAlternees.empiler(((CCarte) c).getPresentation());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Depile la carte de cTasDeCartesAlternees si cTasDeCartesAlternees n'est pas vide<p>
	 * Depile la carte de pTasDeCartesAlternees si cTasDeCartesAlternees n'est pas vide
	 */
	public void depiler() throws Exception {
		if (!isVide()) {
			CCarte c = (CCarte) getSommet();
			super.depiler();
			pTasDeCartesAlternees.depiler(c.getPresentation());
		}
		pTasDeCartesAlternees.repaint();
	}
	
	public PTasDeCartesAlternees getPresentation() {
		return pTasDeCartesAlternees;
	}

	/**
	 * programme de test : � d�placer dans une classe d�di�e aux tests
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		JFrame f = new JFrame("Test CTasDeCartesAlternees");
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.setLayout(new FlowLayout()); // au lieu de BorderLayout par d�faut
		f.getContentPane().setBackground(new Color(143, 143, 195)); // violet
		// p�le

		CUsine u = new CUsine();

		CTasDeCartesAlternees ctdca = new CTasDeCartesAlternees("ctdca", u);
		
		PTasDeCartesAlternees ptdca = ctdca.getPresentation();
		ptdca.setDxDy(0, 30);
		
		
		// une carte visible
		CCarte cc = new CCarte(11, 1);
		cc.setFaceVisible(true);
		if(ctdca.isEmpilable(cc))
			ctdca.empiler(cc);
		
		CCarte cc2 = new CCarte(13, 2);
		cc2.setFaceVisible(true);
		if(ctdca.isEmpilable(cc2))
			ctdca.empiler(cc2);

		// une carte cach�e
		CCarte cc3 = new CCarte(12, 3);
		cc3.setFaceVisible(true);
		if(ctdca.isEmpilable(cc3))
			ctdca.empiler(cc3);
		
		CCarte cc4 = new CCarte(13, 3);
		cc4.setFaceVisible(true);
		if(ctdca.isEmpilable(cc4))
			ctdca.empiler(cc4);

		f.getContentPane().add(ptdca);
		
		
		f.pack(); // dimensionner le cadre
		f.setLocation(200, 100); // le positionner
		f.setSize(800, 600);
		f.setVisible(true); // et le rendre visible
	}
}
