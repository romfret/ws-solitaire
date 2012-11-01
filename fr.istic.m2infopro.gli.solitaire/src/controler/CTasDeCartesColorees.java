package controler;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import presentation.PTasDeCartesColorees;
import solitaire.application.Carte;
import solitaire.application.TasDeCartesColorees;
import solitaire.application.Usine;

public class CTasDeCartesColorees extends TasDeCartesColorees {

	private PTasDeCartesColorees pTasDeCartesColorees;

	public CTasDeCartesColorees(String nom, int couleur, Usine u) {
		super(nom, couleur, u);
		pTasDeCartesColorees = new PTasDeCartesColorees(this);
	}

	/**
	 * Empile la carte sur cTasDeCatre<p>
	 * Empile la carte sur pTasDeCarte si la carte est sommet du cTasDeCartes
	 */
	public void empiler(Carte c) {
		super.empiler(c);
		try {

			if (c == getSommet())
				pTasDeCartesColorees.empiler(((CCarte) c).getPresentation());

		} catch (Exception e) {
			e.printStackTrace();
		}
		pTasDeCartesColorees.repaint();
	}

	/**
	 * Depile la carte de cTasDeCartesColorees si cTasDeCartesColorees n'est pas vide<p>
	 * Depile la carte de pTasDeCartesColorees si cTasDeCartesColorees n'est pas vide
	 */
	public void depiler() throws Exception {
		if (!isVide()) {
			CCarte c = (CCarte) getSommet();
			super.depiler();
			pTasDeCartesColorees.depiler(c.getPresentation());
		}
	}
	
	public PTasDeCartesColorees getPresentation() {
		return pTasDeCartesColorees;
	}
	
	
	// DnD
	
	public void p2cDragEnter(CCarte cc) {
		if(isEmpilable(cc))
			pTasDeCartesColorees.c2pShowEmpilable();
		else
			pTasDeCartesColorees.c2pShowNotEmpilable();
	}
	
	public void p2cDragExit(CCarte cc) {
		pTasDeCartesColorees.c2pShowNeutral();
	}
	
	public void p2cDrop(CCarte cc) {
		if(isEmpilable(cc)) {
			empiler(cc);
			pTasDeCartesColorees.c2pFinDnDOK();
		}
		else {
			pTasDeCartesColorees.c2pFinDnDKO();
		}
	}

	/**
	 * programme de test : � d�placer dans une classe d�di�e aux tests
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		JFrame f = new JFrame("Test CTasDeCartesColorees");
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.setLayout(new FlowLayout()); // au lieu de BorderLayout par d�faut
		f.getContentPane().setBackground(new Color(143, 143, 195)); // violet
		// p�le

		CUsine u = new CUsine();

		CTasDeCartesColorees ctdcc = new CTasDeCartesColorees("ctdcc", 1, u);
		
		PTasDeCartesColorees ptdcc = ctdcc.getPresentation();
//		ptdcc.setDxDy(0, 30);
		
		
		// une carte visible
		CCarte cc = new CCarte(1, 1);
		cc.setFaceVisible(true);
		if(ctdcc.isEmpilable(cc))
			ctdcc.empiler(cc);
		
		CCarte cc2 = new CCarte(13, 2);
		cc2.setFaceVisible(true);
		if(ctdcc.isEmpilable(cc2))
			ctdcc.empiler(cc2);

		// une carte cach�e
		CCarte cc3 = new CCarte(2, 1);
		cc3.setFaceVisible(true);
		if(ctdcc.isEmpilable(cc3))
			ctdcc.empiler(cc3);
		
		CCarte cc4 = new CCarte(4, 1);
		cc4.setFaceVisible(true);
		if(ctdcc.isEmpilable(cc4))
			ctdcc.empiler(cc4);

		f.getContentPane().add(ptdcc);
		
		
		f.pack(); // dimensionner le cadre
		f.setLocation(200, 100); // le positionner
		f.setSize(800, 600);
		f.setVisible(true); // et le rendre visible
	}
}
