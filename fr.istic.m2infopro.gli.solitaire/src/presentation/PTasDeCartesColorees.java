package presentation;

import java.awt.Color;
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
import controler.CTasDeCartesColorees;

public class PTasDeCartesColorees extends JPanel {

	private static final long serialVersionUID = 8538693189076549058L;

	private CTasDeCartesColorees cTasDeCartesColorees;
	private int dx;
	private int dy;
	private int xCourant;
	private int yCourant;
	
	// Initialise par le dragEnter()
	private PCarte pcDrop;
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
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void drop(DropTargetDropEvent dtde) {
				PTasDeCartesColorees.this.drop(dtde);
			}
			
			@Override
			public void dragOver(DropTargetDragEvent dtde) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void dragExit(DropTargetEvent dte) {
				PTasDeCartesColorees.this.dragExit(dte);
			}
			
			@Override
			public void dragEnter(DropTargetDragEvent dtde) {
				PTasDeCartesColorees.this.dragEnter(dtde);
			}
		}) ;

	}

	public void empiler(PCarte c) {
		c.setLocation(xCourant, yCourant);
		add(c, 0);
	}

	public void depiler(PCarte c) {
		remove(c);
	}

	// DnD
	
	public void dragEnter(DropTargetDragEvent e) {
		System.out.println("PTDCC.dragEnter");
		
		
//		PTasDeCartes ptdc = null;
//		try {
//			System.out.println("PTDCC.dragEnter -> in try 1");
//			ptdc = (PTasDeCartes) e.getTransferable().getTransferData(null);
//			System.out.println("PTDCC.dragEnter -> out try 1");
//		} catch (UnsupportedFlavorException e2) {
//			e2.printStackTrace();
//		} catch (IOException e2) {
//			e2.printStackTrace();
//		}
//		
//		CTasDeCartes ctdc = ptdc.getControle();
//		
//		try {
//			System.out.println("PTDCC.dragEnter -> in try 2");
//			pcDrop = ((CCarte)ctdc.getSommet()).getPresentation();
//			System.out.println("PTDCC.dragEnter -> out try 2");
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//		
//		System.out.println("CCarte : " + pcDrop.getControle().toString());
//		
//		cTasDeCartesColorees.p2cDragEnter(pcDrop.getControle());
		cTasDeCartesColorees.p2cDragEnter(new CCarte(1, 1));
	}

	public void dragExit(DropTargetEvent e) {
		System.out.println("PTDCC.dragExit");
		
		cTasDeCartesColorees.p2cDragExit(pcDrop.getControle());
	}

	public void drop(DropTargetDropEvent e) {
		System.out.println("PTDCC.drop");
		
		try {
			System.out.println("PTDCC.drop -> getTransferable() : " + ((PTasDeCartes)e.getTransferable().getTransferData(null)).getControle().toString());
		} catch (UnsupportedFlavorException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		cTasDeCartesColorees.p2cDrop(pcDrop.getControle());
		theFinalEvent = e;
	}

	
	public void c2pFinDnDOK() {
		System.out.println("PTDCC.c2pFinDnDOK");
		
		theFinalEvent.acceptDrop(DnDConstants.ACTION_MOVE); // Voir le support de cours pour connaitre le parametre ad'hoc
		theFinalEvent.getDropTargetContext().dropComplete(true);
	}
	
	public void c2pFinDnDKO() {
		// TODO
		// Gestion du foirage de la fin du DnD
		System.out.println("PTDCC.c2pFinDnDKO");
	}
	
	public void c2pShowEmpilable() {
		// TODO
		// Afficher un effet visuel sur pcDrop pour informer le joueur que la carte peut etre emiplee
		System.out.println("PTDCC.c2pShowEmpilable");
	}

	public void c2pShowNotEmpilable() {
		// TODO
		// Afficher un effet visuel sur pcDrop pour informer le joueur que la carte ne peut pas etre empilee.
		System.out.println("PTDCC.c2pShowNotEmpilable");
	}

	public void c2pShowNeutral() {
		// TODO
		// Affiche la carte de faco)n normale (contrairement au changement de couleur lors de la selection)
		System.out.println("PTDCC.c2pShowNeutral");
	}
	

}
