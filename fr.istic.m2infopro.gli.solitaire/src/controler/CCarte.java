package controler;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import presentation.PCarte;
import solitaire.application.Carte;

public class CCarte extends Carte {

	private PCarte pCarte;

	public CCarte(int v, int c) {
		super(Math.max(1, Math.min(v, Carte.NbCartesParCouleur)), Math
				.max(1, Math.min(c, Carte.NbCouleurs)));
		pCarte = new PCarte(valeurs[getValeur()-1] + couleurs[getCouleur()-1], this);
		pCarte.setFaceVisible(isFaceVisible());
	}
	
	/**
	 * Mettre la carte visible si b vrai 
	 */
	public void setFaceVisible(boolean b) {
		super.setFaceVisible(b);
		pCarte.setFaceVisible(isFaceVisible());
	}
	
	public PCarte getPresentation() {
		return pCarte;
	}
	
	/**
	 * programme de test : � d�placer dans une classe d�di�e aux tests
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		JFrame f = new JFrame("Test CCarte");
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.setLayout(new FlowLayout()); // au lieu de BorderLayout par d�faut
		f.getContentPane().setBackground(new Color(143, 143, 195)); // violet
																	// p�le

		// une carte visible
		CCarte cc = new CCarte(11, 1);
		cc.setFaceVisible(true);
		f.getContentPane().add(cc.getPresentation());

		// une carte cach�e
		cc = new CCarte(12, 2);
		cc.setFaceVisible(false);
		f.getContentPane().add(cc.getPresentation());

		f.pack(); // dimensionner le cadre
		f.setLocation(200, 100); // le positionner
		f.setVisible(true); // et le rendre visible
	} // main
}
