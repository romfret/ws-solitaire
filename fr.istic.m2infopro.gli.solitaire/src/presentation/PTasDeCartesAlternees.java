package presentation;

import javax.swing.JPanel;

import controler.CTasDeCartesAlternees;

public class PTasDeCartesAlternees extends JPanel {

	private static final long serialVersionUID = 8538693189076549058L;
	
	private CTasDeCartesAlternees cTasDeCartesAlternees;
	private int dx;
	private int dy;
	private int xCourant;
	private int yCourant;

	public PTasDeCartesAlternees(CTasDeCartesAlternees cTasDeCartesAlternees) {
		super();
		this.cTasDeCartesAlternees = cTasDeCartesAlternees;
		
		// Permet d'empiler les cartes selon nos positions
		setLayout(null);
		
	}
	
	public void empiler(PCarte c) {
		// Dimensionnement du tas de cartes
		setSize(PCarte.largeur + xCourant, PCarte.hauteur + yCourant);
		setPreferredSize(getSize());
		
		// Placement de la carte dans le tas de carte
		c.setLocation(xCourant, yCourant);
		add(c, 0);
		xCourant += dx;
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
