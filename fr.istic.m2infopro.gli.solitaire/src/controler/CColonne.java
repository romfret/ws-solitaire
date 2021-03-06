package controler;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import presentation.PColonne;
import presentation.PTasDeCartes;
import presentation.PTasDeCartesAlternees;
import solitaire.application.Carte;
import solitaire.application.Colonne;
import solitaire.application.Tas;
import solitaire.application.TasDeCartes;

public class CColonne extends Colonne {
	
	private PColonne pColonne;
	private CUsine usine;

	public CColonne(String nom, CUsine u){
		super(nom, u);
		usine = u;
		PTasDeCartesAlternees pVisibles = ((CTasDeCartesAlternees)visibles).getPresentation();
		PTasDeCartes pCachees = ((CTasDeCartes)cachees).getPresentation();
	
		pColonne = new PColonne(this, pCachees, pVisibles);
	}
	
	public CTasDeCartesAlternees getVisibles(){
		return (CTasDeCartesAlternees) visibles;
	}
	public CTasDeCartes getCachees(){
		return (CTasDeCartes) cachees;
	}
	
	public PColonne getPresentation() {
		return pColonne;
	}
	
	@Override
	public void empiler(Carte carte) {
		super.empiler(carte);
		pColonne.updateUI();
	}
	
	@Override
	public void depiler() throws Exception {
		super.depiler();
		pColonne.updateUI();
	}
	
	// DnD
	
	public void p2cDragEnter(CTasDeCartes ctdc) {
		try {
			if(isEmpilable(ctdc))
				pColonne.c2pShowEmpilable();
			else
				pColonne.c2pShowNotEmpilable();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void p2cDragExit(CTasDeCartes ctdc) {
		pColonne.c2pShowNeutral();
	}
	
	public void p2cDrop(CTasDeCartes ctdc) {
		try {
			if(isEmpilable(ctdc)) {
				empiler(ctdc);
				pColonne.c2pFinDnDOK();
			}
			else {
				pColonne.c2pFinDnDKO();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void p2cDebutDnD(CCarte cc) throws Exception {
		if (cc != null) {
				// Instanciation du Tas de carte transferable contenant la carte a transferer
				Stack<CCarte> tampon = new Stack<CCarte>();
				
				CCarte c = null;
				while(c!=cc){
					c = (CCarte) visibles.getSommet();
					depiler();
					tampon.push(c);
				} 
				
				CTasDeCartes ctdc = (CTasDeCartes) usine.newTasDeCartes("TDC cree a partir dune colonne", usine);
				ctdc.getPresentation().setDxDy(0, 20);
				
				while(!tampon.isEmpty()){
					ctdc.empiler((CCarte) tampon.pop());
				}
				
				pColonne.c2pDebutDnDOK(ctdc.getPresentation());
		} else {
			pColonne.c2pDebutDnDNull();
			// + comptage des erreurs + reaction
			// -> Au bout d'un certain nombre d'erreur, on peut envoyé un
			// message explicite a l'utilisateur
		}
	}

	public void p2cDragDropEnd(boolean success, CTasDeCartes cc) {
		if (!success) {
			System.out.println("CColonne.p2cDragDropEnd");
			System.out.println("  CColonne -> CTasDeCartes : " + cc.toString());
			empiler(cc);
		} else {
			try {
				this.retournerCarte();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
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
