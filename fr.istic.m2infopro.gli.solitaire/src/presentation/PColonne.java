package presentation;

import javax.swing.JPanel;


public class PColonne extends JPanel {

	private static final long serialVersionUID = -7826492851733908362L;
	
	private PTasDeCartes cachees;
	private PTasDeCartesAlternees visibles;
	/**
	 * Le constructeur
	 * @param cachees
	 * @param visibles
	 */
	public PColonne(PTasDeCartes cachees, PTasDeCartesAlternees visibles) {
		super();
		this.cachees = cachees;
		this.visibles = visibles;
		
		
		add(cachees);
		cachees.setDxDy(0, 10);
		add(visibles);
		visibles.setDxDy(0, 30);
		
	}

}
