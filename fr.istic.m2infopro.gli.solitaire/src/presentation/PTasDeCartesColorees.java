package presentation;

import java.awt.Color;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.IOException;

import javax.swing.JPanel;

import controler.CCarte;
import controler.CTasDeCartes;
import controler.CTasDeCartesColorees;

public class PTasDeCartesColorees extends JPanel {

	private static final long serialVersionUID = 8538693189076549058L;

	private CTasDeCartesColorees cTasDeCartesColorees;
	private int xCourant;
	private int yCourant;

	// Initialise par le dragEnter()
	private PCarte pcDrop;
	
	@SuppressWarnings("unused")
	private DropTarget dropTarget;

	private DropTargetDropEvent theFinalEvent; // Initialise par drop()

	public PTasDeCartesColorees(CTasDeCartesColorees cTasDeCartesColorees) {
		super();
		this.cTasDeCartesColorees = cTasDeCartesColorees;
		setBackground(new Color(204, 153, 255));

		// Permet d'empiler les cartes selon nos positions
		setLayout(null);
		setSize(PCarte.largeur, PCarte.hauteur);
		setPreferredSize(getSize());

		// DnD
		dropTarget = new DropTarget(this, new DropTargetListener() {

			@Override
			public void dropActionChanged(DropTargetDragEvent dtde) {

			}

			@Override
			public void drop(DropTargetDropEvent dtde) {
				PTasDeCartesColorees.this.drop(dtde);
			}

			@Override
			public void dragOver(DropTargetDragEvent dtde) {

			}

			@Override
			public void dragExit(DropTargetEvent dte) {
				PTasDeCartesColorees.this.dragExit(dte);
			}

			@Override
			public void dragEnter(DropTargetDragEvent dtde) {
				try {
					PTasDeCartesColorees.this.dragEnter(dtde);
				} catch (UnsupportedFlavorException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public void empiler(PCarte c) {
		c.setLocation(xCourant, yCourant);
		add(c, 0);
	}

	public void depiler(PCarte c) {
		remove(c);
	}

	// DnD

	public void dragEnter(DropTargetDragEvent e) throws Exception {
		System.out.println("PTDCC.dragEnter");
		
		if(e.isDataFlavorSupported(PTasDeCartes.FLAVOR)) {
			
			// Recuperation de l'objet transfere
			Transferable transferable = e.getTransferable();
			System.out.println("  Transferable : " + transferable);
			
			// Recuperation de la carte passee via le DnD
			PTasDeCartes ptdc = (PTasDeCartes) transferable.getTransferData(PTasDeCartes.FLAVOR);
			CTasDeCartes ctdc = ptdc.getControle();
			pcDrop = ((CCarte)ctdc.getSommet()).getPresentation();
			
			System.out.println("  PTDCC -> CCarte : " + pcDrop.getControle().toString());
			
			cTasDeCartesColorees.p2cDragEnter(pcDrop.getControle());
		}
		else {
			System.err.println("  Transferable : N'est pas un objet PTasCeCartes");
		}
	}

	public void dragExit(DropTargetEvent e) {
		System.out.println("PTDCC.dragExit");

		cTasDeCartesColorees.p2cDragExit(pcDrop.getControle());
	}

	public void drop(DropTargetDropEvent e) {
		System.out.println("PTDCC.drop");
		
		theFinalEvent = e;
		cTasDeCartesColorees.p2cDrop(pcDrop.getControle());
	}

	public void c2pFinDnDOK() {
		theFinalEvent.acceptDrop(DnDConstants.ACTION_MOVE);
		theFinalEvent.getDropTargetContext().dropComplete(true);
		this.setBackground(new Color(204, 153, 255));
	}

	public void c2pFinDnDKO() {
		// Gestion du foirage de la fin du DnD
		this.setBackground(new Color(204, 153, 255));
	}

	public void c2pShowEmpilable() {
		this.setBackground(Color.green);
	}

	public void c2pShowNotEmpilable() {
		// Afficher un effet visuel sur pcDrop pour informer le joueur que la
		// carte ne peut pas etre empilee.
		this.setBackground(Color.red);
	}

	public void c2pShowNeutral() {
		// Affiche la carte de facon normale (contrairement au changement de
		// couleur lors de la selection)
		this.setBackground(new Color(204, 153, 255));
	}

}
