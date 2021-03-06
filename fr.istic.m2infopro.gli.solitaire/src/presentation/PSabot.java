package presentation;

import java.awt.Cursor;
import java.awt.Point;
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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JPanel;

import controler.CCarte;
import controler.CSabot;
import dndListener.MyDragSourceMotionListener;

public class PSabot extends JPanel {

	private static final long serialVersionUID = -7826492851733908362L;

	private CSabot cSabot;

	private PTasDeCartes cachees;
	private PTasDeCartes visibles;
	private RetournerCartesListener rcl;
	private RetournerTasListener rtl;

	// DnD
	private DragGestureEvent theInitialEvent;
	private DragSource dragSource;
	private MyDragSourceMotionListener dragSourceMotionListener;
	private DragSourceListener dragSourceListener;
	
	private PTasDeCartes currentMovedPTasDeCarte;
	/**
	 * Le constructeur
	 * 
	 * @param cachees
	 * @param visibles
	 */
	public PSabot(CSabot cSabot, PTasDeCartes cachees, PTasDeCartes visibles) {
		super();
		this.cachees = cachees;
		this.visibles = visibles;
		this.cSabot = cSabot;
		rcl = new RetournerCartesListener();
		rtl = new RetournerTasListener();		// Recuperation de la position avant drag de la carte
		// Permettra de setter la position initiale de currentMovedPTasDeCarte pour le deplacement.
		

		add(cachees);
		cachees.setDxDy(0, 0);
		add(visibles);
		visibles.setDxDy(25, 0);
		
		this.activerRetournerCarte();

		// DnD
		
		dragSource = new DragSource();
		
		dragSource.createDefaultDragGestureRecognizer(visibles,
		DnDConstants.ACTION_MOVE, new DragGestureListener() {
			
			@Override
			public void dragGestureRecognized(DragGestureEvent arg0) {
				PSabot.this.dragGestureRecognized(arg0);
			}
		});
		
		dragSourceMotionListener = new MyDragSourceMotionListener();
		dragSource.addDragSourceMotionListener((DragSourceMotionListener) dragSourceMotionListener);

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
				PSabot.this.dragDropEnd(arg0);
			}
		};
	}

	public void activerRetournerTas() {
		cachees.addMouseListener(rtl);
		cachees.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	}

	public void desactiverRetournerTas() {
		cachees.removeMouseListener(rtl);
		cachees.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

	public void activerRetournerCarte() {
		cachees.addMouseListener(rcl);
		cachees.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	public void desactiverRetournerCarte() {
		cachees.removeMouseListener(rcl);
		cachees.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
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
			
			
			
			// Recuperation de la position avant drag de la carte
			// Permettra de setter la position initiale de currentMovedPTasDeCarte pour le deplacement.
			Point cLoc = pc.getLocation();
			Point pcLoc = pc.getParent().getLocation();
			new Point(cLoc.x + pcLoc.x, cLoc.y + pcLoc.y);			
			
			
			System.out.println("    PSabot -> CCarte : " + cc.toString());
			
			// C'est le controle qui gere lui meme si cc est null apres que
			// l'utilisateur est pas selectionne la bonne carte
			cSabot.p2cDebutDnD(cc);
			
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void c2pDebutDnDOK(PTasDeCartes pTasDeCartes) throws UnsupportedFlavorException, IOException {
		System.out.println("PSabot.c2pDebutDnDOK -> ");
		System.out.println("  PSabot -> " + pTasDeCartes.getControle().toString());
		
		pTasDeCartes.validate();
		System.out.println("size="+pTasDeCartes.getSize());
		
		currentMovedPTasDeCarte = pTasDeCartes;

	
		// Initialisation de la position du PTasDeCartes destine au transfert avec la position de la carte avant le DnD 
		//currentMovedPTasDeCarte.setLocation(initialCurrentMovedPTasDeCartesPosition);

		
		// Encrage du PTasDeCartes, au premier plan, dans le panel root
		// (rend visible le pTasDeCartes durant le deplacement)
		getRootPane().add(currentMovedPTasDeCarte, 0);
		
		
		// Gestion du deplacement du PTasDeCartes en meme temps que la souris
		dragSourceMotionListener.setCurrentMovedPTasDeCarte(currentMovedPTasDeCarte);

		
		// Initialisation de toutes les coordonnees relatives au deplacement graphique du PTasDeCartes
		System.out.println("getRootPane().getLocationOnScreen()"+getRootPane().getLocationOnScreen());
		System.out.println("getLocationOnScreen()"+getLocationOnScreen());
		System.out.println("theInitialEvent.getDragOrigin"+theInitialEvent.getDragOrigin());
		Point p1 = getRootPane().getLocationOnScreen();
		Point p2 = theInitialEvent.getDragOrigin();
		Point p3 = new Point(p1.x-p2.x+PCarte.largeur/2,p1.y-p2.y+PCarte.hauteur/2);
		dragSourceMotionListener.setSelection(p3,  theInitialEvent.getDragOrigin());	
		
		
		// Lancement du drag
		dragSource.startDrag(theInitialEvent, DragSource.DefaultMoveDrop, currentMovedPTasDeCarte, dragSourceListener);
		
		// startDrag -> fait a l'aide de la presentation sabot + donnee
		// transferee (= pc) + event (= theInitialEvent)
	}
	
	public void dragDropEnd(DragSourceDropEvent e) {
		System.out.println("PSabot.dragDropEnd");
		
		// Recuperation du contexte du drag
		DragSourceContext dsc = (DragSourceContext) e.getSource();
		
		PTasDeCartes ptdc = null;
		try {
			
			// Recuperation de l'objet Transferable PTasDeCartes
			ptdc = (PTasDeCartes) dsc.getTransferable().getTransferData(PTasDeCartes.FLAVOR);
			
			cSabot.p2cDragDropEnd(e.getDropSuccess(),ptdc.getControle());
			
		} catch (UnsupportedFlavorException e2) {
			e2.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		// De-encrage du pTasDeCartes du root panel
		// (supprime la visibillite du PTasDeCartes a la fin du deplacement)
		getRootPane().remove(currentMovedPTasDeCarte);
		
		
		// Permet de rafraichir tout l'affichage du solitaire en cas de DnD non acheve
		getRootPane().repaint();
	}

	public void c2pDebutDnDKO() {
		System.out.println("PSabot.c2pDebutDnDKO : Le drag and drop n'a pas fonctionné");
		
		// S'il y avait besoin de faire un traitement sur un plantage du DnD
		// Ici, il n'y a pas besoin de traitement en utilisanat Swing
	}

	public void c2pDebutDnDNull() {
		System.out.println("PSabot.c2pDebutDnDNull : La PCarte est nulle");
		
		// S'il y avait besoin de faire un traitement sur un plantage du DnD
		// Ici, il n'y a pas besoin de traitement en utilisanat Swing
	}
	

	public class RetournerTasListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			try {
				cSabot.retourner();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		@Override
		public void mouseEntered(MouseEvent arg0) {}
		@Override
		public void mouseExited(MouseEvent arg0) {}
		@Override
		public void mousePressed(MouseEvent arg0) {}
		@Override
		public void mouseReleased(MouseEvent arg0) {}
	}

	public class RetournerCartesListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			try {
				// System.out.println("retournerCartesListener");
				cSabot.retournerCarte();
			} catch (Exception e) {
				// System.err.println("PSabot MouseClicked error");
				e.printStackTrace();
			}
		}
		@Override
		public void mouseEntered(MouseEvent arg0) {}
		@Override
		public void mouseExited(MouseEvent arg0) {}
		@Override
		public void mousePressed(MouseEvent arg0) {}
		@Override
		public void mouseReleased(MouseEvent arg0) {}
	}

}
