package presentation;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.JPanel;

/**
 * 
 * Implement Transferable pour la gestion du DnD
 *
 */
public class PTasDeCartes extends JPanel implements Transferable {

	private static final long serialVersionUID = 8538693189076549058L;

//	private CTasDeCartes cTasDeCartes;
	private int dx;
	private int dy;
	private int xCourant;
	private int yCourant;

	public PTasDeCartes(/*CTasDeCartes cTasDeCartes*/) {
		super();

		// Permet d'empiler les cartes selon nos positions
		setLayout(null);

		xCourant = 0;
		yCourant = 0;
//		this.cTasDeCartes = cTasDeCartes;
	}

	public void empiler(PCarte pc) {
		// Dimensionnement du tas de cartes
		setSize(PCarte.largeur + xCourant, PCarte.hauteur + yCourant);
		setPreferredSize(getSize());

		// Placement de la carte dans le tas de carte
		pc.setLocation(xCourant, yCourant);
		add(pc, 0);
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
	
	// Tranferable methodes
	@Override
	public Object getTransferData(DataFlavor flavor)
			throws UnsupportedFlavorException, IOException {
		return this;
	}

	@Override
	public DataFlavor[] getTransferDataFlavors() {
		return new DataFlavor[] {new DataFlavor(getClass(), null)};
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return true;
	}

}
