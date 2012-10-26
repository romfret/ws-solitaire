package presentation;

import javax.swing.JPanel;

import controler.CTasDeCartes;

public class PTasDeCartes extends JPanel {

	private static final long serialVersionUID = 8538693189076549058L;
	
	private CTasDeCartes cTasDeCartes;
	private int dx;
	private int dy;
	private int xCourant;
	private int yCourant;

	public PTasDeCartes(CTasDeCartes cTasDeCartes) {
		super();
		
		// Permet d'empiler les cartes selon nos positions
		setLayout(null);
		
		xCourant = 0;
		yCourant = 0;
		this.cTasDeCartes = cTasDeCartes;
	}
	
	public void empiler(PCarte pc) {
		// Dimensionnement du tas de cartes
		setSize(PCarte.largeur + xCourant, PCarte.hauteur + yCourant);
		setPreferredSize(getSize());
		
		// Placement de la carte dans le tas de carte
		pc.setLocation(xCourant, yCourant);
		add(pc, 0);
		xCourant +=  dx;
		yCourant += dy;
	}
	
	public void depiler(PCarte c) {
		remove(c);
		xCourant -= dx;
		yCourant -= dy;
		setSize(PCarte.largeur + xCourant, PCarte.hauteur + yCourant);
	}
	
	public void setDxDy(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
	}

}
