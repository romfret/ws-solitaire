package presentation;

import java.awt.Color;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceContext;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.dnd.DragSourceMotionListener;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import controler.CCarte;
import controler.CColonne;
import controler.CTasDeCartes;
import dndListener.MyDragSourceMotionListener;

public class PColonne extends JPanel {

	private static final long serialVersionUID = -7826492851733908362L;
	private DropTarget dropTarget;
	private CColonne cColonne;
	private DropTargetDropEvent theFinalEvent;
	private PCarte pcDrop;
	private DragSource dragSource;
	private DragGestureEvent theInitialEvent;
	private MyDragSourceMotionListener dragSourceMotionListener;
	private DragSourceListener dragSourceListener;
	
	private PTasDeCartesAlternees visibles;
	private PTasDeCartes cachees;
	

	/**
	 * Le constructeur
	 * 
	 * @param cachees
	 * @param visibles
	 */
	public PColonne(CColonne cColonne, PTasDeCartes cachees,
			PTasDeCartesAlternees visibles) {
		super();
		this.cColonne = cColonne;
		this.visibles = visibles;
		this.cachees = cachees;
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		// this.setSize(PCarte.largeur+20, 550);
		// this.setPreferredSize(getSize());
		this.setBackground(Color.BLUE);

		add(cachees);
		cachees.setDxDy(0, 10);
		add(visibles);
		visibles.setDxDy(0, 30);

		// DnD

		dragSource = new DragSource();

		dragSource.createDefaultDragGestureRecognizer(visibles,
				DnDConstants.ACTION_MOVE, new DragGestureListener() {

					@Override
					public void dragGestureRecognized(DragGestureEvent arg0) {
						PColonne.this.dragGestureRecognized(arg0);
					}
				});

		dragSourceMotionListener = new MyDragSourceMotionListener();
		dragSource
				.addDragSourceMotionListener((DragSourceMotionListener) dragSourceMotionListener);

		dragSourceListener = new DragSourceListener() {

			@Override
			public void dropActionChanged(DragSourceDragEvent arg0) {

			}

			@Override
			public void dragOver(DragSourceDragEvent arg0) {

			}

			@Override
			public void dragExit(DragSourceEvent arg0) {

			}

			@Override
			public void dragEnter(DragSourceDragEvent arg0) {

			}

			@Override
			public void dragDropEnd(DragSourceDropEvent arg0) {
				PColonne.this.dragDropEnd(arg0);
			}
		};

		// DnD
		dropTarget = new DropTarget(this, new DropTargetListener() {

			@Override
			public void dropActionChanged(DropTargetDragEvent dtde) {

			}

			@Override
			public void drop(DropTargetDropEvent dtde) {
				PColonne.this.drop(dtde);
			}

			@Override
			public void dragOver(DropTargetDragEvent dtde) {

			}

			@Override
			public void dragExit(DropTargetEvent dte) {
				PColonne.this.dragExit(dte);
			}

			@Override
			public void dragEnter(DropTargetDragEvent dtde) {
				try {
					PColonne.this.dragEnter(dtde);
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
	
	public void dragGestureRecognized(DragGestureEvent e) {
		System.out.println("\n");
		System.out.println("PSabot.dragGestureRecognized");
		
		
		CCarte cc;
		PCarte pc;
		theInitialEvent = e;
		try {
			
			// Recuperation de la carte sous le curseur
			
			
			pc = (PCarte) visibles.getComponentAt(e.getDragOrigin());
			
			
			cc = pc.getControle();
			
			System.out.println("    PSabot -> CCarte : " + cc.toString());
			
			// C'est le controle qui gere lui meme si cc est null apres que
			// l'utilisateur est pas selectionne la bonne carte
			cColonne.p2cDebutDnD(cc);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public void dragDropEnd(DragSourceDropEvent e) {
		System.out.println("PSabot.dragDropEnd");
		
		// Recuperation du contexte du drag
		DragSourceContext dsc = (DragSourceContext) e.getSource();
		
		PTasDeCartes ptdc = null;
		try {
			
			// Recuperation de l'objet Transferable PTasDeCartes
			ptdc = (PTasDeCartes) dsc.getTransferable().getTransferData(PTasDeCartes.FLAVOR);
			
			cColonne.p2cDragDropEnd(e.getDropSuccess(),
					(CCarte) ptdc.getControle().getSommet());
			
		} catch (UnsupportedFlavorException e2) {
			e2.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		// Permet de re-afficher une carte draguee dont le drop n'aurait pas abouti 
		repaint();
	}

	public void dragEnter(DropTargetDragEvent e) throws Exception {
		System.out.println("PTDCC.dragEnter");

		if (e.isDataFlavorSupported(PTasDeCartes.FLAVOR)) {
			// Recuperation de l'objet transfere
			Transferable transferable = e.getTransferable();

			// Recuperation de la carte passee via le DnD
			PTasDeCartes ptdc = (PTasDeCartes) transferable
					.getTransferData(PTasDeCartes.FLAVOR);
			CTasDeCartes ctdc = ptdc.getControle();
			pcDrop = ((CCarte) ctdc.getSommet()).getPresentation();

			cColonne.p2cDragEnter(pcDrop.getControle());
		} else {
			System.err
					.println("  Transferable : N'est pas un objet PTasCeCartes");
		}
	}

	public void dragExit(DropTargetEvent e) {
		cColonne.p2cDragExit(pcDrop.getControle());
	}

	public void drop(DropTargetDropEvent e) {
		theFinalEvent = e;
		cColonne.p2cDrop(pcDrop.getControle());
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
	
	public void c2pDebutDnDOK(PTasDeCartes pTasDeCartes) throws UnsupportedFlavorException, IOException {
		System.out.println("PSabot.c2pDebutDnDOK");
		System.out.println("  PSabot -> " + pTasDeCartes.getControle().toString());
		
		// Pour le deplacement graphique de la carte
		dragSourceMotionListener.setCurrentMovedPTasDeCarte(pTasDeCartes);
		
		// Lancement du drag
		dragSource.startDrag(theInitialEvent, DragSource.DefaultMoveDrop, pTasDeCartes, dragSourceListener);
		
		// startDrag -> fait a l'aide de la presentation sabot + donnee
		// transferee (= pc) + event (= theInitialEvent)
	}


	public void c2pDebutDnDKO() {
		System.out.println("PSabot.c2pDebutDnDKO : Le drag and drop n'a pas fonctionné");
		
		// S'il y avait besoin de faire un traitement sur un plantage du DnD
		// Ici, il n'y a pas besoin de traitement en utilisanat AWT
	}

	public void c2pDebutDnDNull() {
		System.out.println("PSabot.c2pDebutDnDNull : La PCarte est nulle");
		
		// S'il y avait besoin de faire un traitement sur un plantage du DnD
		// Ici, il n'y a pas besoin de traitement en utilisanat AWT
	}
	

}
