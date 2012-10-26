package presentation;

import javax.swing.JPanel;

import controler.CTasDeCartesColorees;

public class PTasDeCartesColorees extends JPanel {

	private static final long serialVersionUID = 8538693189076549058L;

	private CTasDeCartesColorees cTasDeCartesColorees;
	private int dx;
	private int dy;
	private int xCourant;
	private int yCourant;

	public PTasDeCartesColorees(CTasDeCartesColorees cTasDeCartesColorees) {
		super();
		this.cTasDeCartesColorees = cTasDeCartesColorees;
		
		// Permet d'empiler les cartes selon nos positions
		setLayout(null);
		setSize(PCarte.largeur, PCarte.hauteur);
		setPreferredSize(getSize());
	}

	public void empiler(PCarte c) {
		c.setLocation(xCourant, yCourant);
		add(c, 0);
	}

	public void depiler(PCarte c) {
		remove(c);
	}

}
