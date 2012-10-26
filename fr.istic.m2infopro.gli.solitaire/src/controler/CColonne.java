package controler;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import presentation.PColonne;
import presentation.PTasDeCartes;
import presentation.PTasDeCartesAlternees;
import solitaire.application.Colonne;
import solitaire.application.Tas;
import solitaire.application.TasDeCartes;
import solitaire.application.TasDeCartesAlternees;

public class CColonne extends Colonne {
	
	private PColonne pColonne;

	public CColonne(String nom, CUsine u){
		super(nom, u);
		
		PTasDeCartesAlternees pVisibles = ((CTasDeCartesAlternees)visibles).getPresentation();
		PTasDeCartes pCachees = ((CTasDeCartes)cachees).getPresentation();
	
		pColonne = new PColonne(pCachees, pVisibles);
	}
	
	public PColonne getPresentation() {
		return pColonne;
	}
	
	/**
	 * programme de test : � d�placer dans une classe d�di�e aux tests
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		JFrame f = new JFrame("Test CColonne");
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.setLayout(new FlowLayout()); // au lieu de PTasDeCartesColoreesorderLayout par defaut
		f.getContentPane().setBackground(new Color(143, 143, 195)); // violet

		CUsine u = new CUsine();
		CColonne cColonne = new CColonne("CColonne", u);
		
		CCarte cc = new CCarte(11, 1);
		cc.setFaceVisible(false);
		
		CCarte cc2 = new CCarte(10, 2);
		cc2.setFaceVisible(false);

		CCarte cc3 = new CCarte(11, 1);
		cc3.setFaceVisible(false);

		CCarte cc4 = new CCarte(9, 2);
		cc4.setFaceVisible(false);

		CCarte cc5 = new CCarte(5, 1);
		cc5.setFaceVisible(false);
		
		CCarte cc6 = new CCarte(4, 2);
		cc6.setFaceVisible(true);
		
		PColonne pColonne = cColonne.getPresentation();
		
		Tas tas = new TasDeCartes("tas1", u);
		tas.empiler(cc);
		tas.empiler(cc2);
		tas.empiler(cc3);
		tas.empiler(cc4);
		tas.empiler(cc5);
		tas.empiler(cc6);
		
		cColonne.empiler(cc6);
		cColonne.setReserve(tas);
		
		f.getContentPane().add(pColonne);
		f.pack(); // dimensionner le cadre
		f.setLocation(200, 100); // le positionner
		f.setSize(800, 600);
		f.setVisible(true); // et le rendre visible
	}
}
