package controler;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import presentation.PTasDeCartes;
import solitaire.application.Carte;
import solitaire.application.TasDeCartes;

public class CTasDeCartes extends TasDeCartes {

	private PTasDeCartes pTasDeCartes;

	public CTasDeCartes(String nom, CUsine u) {
		super(nom, u);
		pTasDeCartes = new PTasDeCartes(this);
	}

	/**
	 * Empile la carte sur cTasDeCatre
	 * <p>
	 * Empile la carte sur pTasDeCarte si la carte est sommet du cTasDeCartes
	 */
	public void empiler(Carte c) {
		super.empiler(c);
		try {

			if (c == getSommet())
				pTasDeCartes.empiler(((CCarte) c).getPresentation());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Depile la carte de cTasDeCartes si cTasDeCartes n'est pas vide
	 * <p>
	 * Depile la carte de pTasDeCartes si cTasDeCartes n'est pas vide
	 */
	public void depiler() throws Exception {
		if (!isVide()) {
			CCarte c = (CCarte) getSommet();
			super.depiler();
			pTasDeCartes.depiler(c.getPresentation());
		}
	}

	public PTasDeCartes getPresentation() {
		return pTasDeCartes;
	}

	/**
	 * programme de test : � d�placer dans une classe d�di�e aux tests
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		JFrame f = new JFrame("Test CTasDeCartes");
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.setLayout(new FlowLayout()); // au lieu de BorderLayout par d�faut
		f.getContentPane().setBackground(new Color(143, 143, 195)); // violet
		// p�le

		CUsine u = new CUsine();
		CTasDeCartes ctdc = new CTasDeCartes("ctdc", u);

		PTasDeCartes ptdc = ctdc.getPresentation();
		ptdc.setDxDy(0, 30);

		// une carte visible
		CCarte cc = new CCarte(11, 1);
		cc.setFaceVisible(true);
		ctdc.empiler(cc);
		
		CCarte cc2 = new CCarte(13, 3);
		cc2.setFaceVisible(true);
		ctdc.empiler(cc2);

		// une carte cach�e
		CCarte cc3 = new CCarte(12, 2);
		cc3.setFaceVisible(true);
		ctdc.empiler(cc3);

		f.getContentPane().add(ptdc);
		
		
		f.pack(); // dimensionner le cadre
		f.setLocation(200, 100); // le positionner
		f.setSize(800, 600);
		f.setVisible(true); // et le rendre visible
	} // main
}
