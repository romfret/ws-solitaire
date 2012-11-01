package controler;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import presentation.PSabot;
import presentation.PTasDeCartes;
import solitaire.application.Sabot;
import solitaire.application.Tas;
import solitaire.application.TasDeCartes;

public class CSabot extends Sabot {

	private PSabot pSabot;

	public CSabot(String nom, CUsine u) {
		super(nom, u);
	}

	public void setReverse(Tas t) {
		super.setReserve(t);
		if (isCarteRetournable()) {
			pSabot.desactiverRetournerTas(); // Blindage
			pSabot.activerRetournerCarte();
		} else {
			pSabot.activerRetournerTas();
			pSabot.desactiverRetournerCarte();
		}
	}

	public void retournerCarte() throws Exception {
		super.retournerCarte();
		if (!isCarteRetournable()) {
			pSabot.desactiverRetournerCarte();
			if (isRetournable())
				pSabot.activerRetournerTas();
		}
	}

	public void retourner() throws Exception {
		super.retourner();
		if (!isRetournable())
			pSabot.desactiverRetournerCarte();
		if (isCarteRetournable())
			pSabot.activerRetournerCarte();
	}

	public void depiler() throws Exception {
		super.depiler();
		if (!isCarteRetournable() && !isRetournable())
			pSabot.desactiverRetournerTas();
	}

	public PSabot getPresentation() {
		PTasDeCartes pVisibles = ((CTasDeCartes) visibles).getPresentation();
		PTasDeCartes pCachees = ((CTasDeCartes) cachees).getPresentation();

		pSabot = new PSabot(this, pCachees, pVisibles);
		return pSabot;
	}

	public void p2cDebutDnD(CCarte cc) throws Exception {
		if (cc != null) {
			if (cc == getSommet()) {
				depiler();
				
				// Instanciation du Tas de carte transferable
				CTasDeCartes ctdc = new CTasDeCartes("Drag", new CUsine());
				ctdc.empiler(cc);
				
				pSabot.c2pDebutDnDOK(ctdc.getPresentation());
			} else {
				pSabot.c2pDebutDnDKO();
				// + comptage des erreurs + reaction
				// -> Au bout d'un certain nombre d'erreur, on peut envoyé un
				// message explicite a l'utilisateur
			}
		} else {
			pSabot.c2pDebutDnDNull();
			// + comptage des erreurs + reaction
			// -> Au bout d'un certain nombre d'erreur, on peut envoyé un
			// message explicite a l'utilisateur
		}
	}

	public void p2cDragDropEnd(boolean success, CCarte cc) {
		if (!success) {
			System.out.println("p2cDragDropEnd");
			System.out.println("CCarte : " + cc.toString());
			empiler(cc);
		}
	}

	/**
	 * programme de test : � d�placer dans une classe d�di�e aux tests
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		JFrame f = new JFrame("Test CSabot");
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.setLayout(new FlowLayout()); // au lieu de
										// PTasDeCartesColoreesorderLayout par
										// defaut
		f.getContentPane().setBackground(new Color(143, 143, 195)); // violet
		// pile

		CUsine u = new CUsine();

		CSabot cSabot = new CSabot("Sabot", u);

		CCarte cc = new CCarte(11, 1);
		cc.setFaceVisible(false);

		CCarte cc2 = new CCarte(10, 2);
		cc2.setFaceVisible(false);

		CCarte cc3 = new CCarte(11, 2);
		cc3.setFaceVisible(false);

		CCarte cc4 = new CCarte(9, 2);
		cc4.setFaceVisible(false);

		CCarte cc5 = new CCarte(5, 3);
		cc5.setFaceVisible(false);

		CCarte cc6 = new CCarte(4, 4);
		cc6.setFaceVisible(false);

		PSabot pSabot = cSabot.getPresentation();

		Tas tas = new TasDeCartes("tas1", u);
		tas.empiler(cc);
		tas.empiler(cc2);
		tas.empiler(cc3);
		tas.empiler(cc4);
		tas.empiler(cc5);
		tas.empiler(cc6);
		cSabot.setReverse(tas);

		// pSabot.activerRetournerTas();
		// pSabot.activerRetournerCarte();

		f.getContentPane().add(pSabot);

		f.pack(); // dimensionner le cadre
		f.setLocation(200, 100); // le positionner
		f.setSize(800, 600);
		f.setVisible(true); // et le rendre visible
	}
}
