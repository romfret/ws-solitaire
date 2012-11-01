package presentation;

import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDropEvent;

import javax.swing.JPanel;

import controler.CTasDeCartesColorees;

public class PTasDeCartesColorees extends JPanel {

	private static final long serialVersionUID = 8538693189076549058L;

	private CTasDeCartesColorees cTasDeCartesColorees;
	private int dx;
	private int dy;
	private int xCourant;
	private int yCourant;
	
	// Initialise par le dragEnter()
	private PCarte pc;
		
	private DropTargetDropEvent theFinalEvent; // Initialise par drop()

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

	// DnD
	
	public void c2pFinDnDOK() {
		System.out.println("c2pFinDnDOK");
		
		theFinalEvent.acceptDrop(DnDConstants.ACTION_MOVE); // Voir le support de cours pour connaitre le parametre ad'hoc
		theFinalEvent.getDropTargetContext().dropComplete(true);
	}
	
	public void c2pFinDnDKO() {
		// TODO
		// Gestion du foirage de la fin du DnD
		System.out.println("c2pFinDnDKO");
	}
	
	public void c2pShowEmpilable() {
		// TODO
		// Afficher un effet visuel pour informer le joueur que la carte peut etre emiplee
		System.out.println("c2pShowEmpilable");
	}

	public void c2pShowNotEmpilable() {
		// TODO
		// Afficher un effet visuel pour informer le joueur que la carte ne peut pas etre empilee.
		System.out.println("c2pShowNotEmpilable");
	}

	public void c2pShowNeutral() {
		// TODO
		// Affiche la carte de facon normale (contrairement au changement de couleur lors de la selection)
		System.out.println("c2pShowNeutral");
	}
	

}
