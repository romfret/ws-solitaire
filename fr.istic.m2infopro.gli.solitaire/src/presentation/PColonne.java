package presentation;

import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import controler.CColonne;


public class PColonne extends JPanel {

	private static final long serialVersionUID = -7826492851733908362L;
	private DropTarget dropTarget;
	private CColonne cColonne;
	
	/**
	 * Le constructeur
	 * @param cachees
	 * @param visibles
	 */
	public PColonne(CColonne cColonne, PTasDeCartes cachees, PTasDeCartesAlternees visibles) {
		super();
		this.cColonne = cColonne;
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		add(cachees);
		cachees.setDxDy(0, 10);
		add(visibles);
		visibles.setDxDy(0, 30);
		
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
				System.out.println("TODO Drag enter");
			}
		});
	}
	
	public void dragExit(DropTargetEvent e) {
		System.out.println("PTDCC.dragExit");

		cColonne.p2cDragExit(pcDrop.getControle());
	}

	public void drop(DropTargetDropEvent e) {
		System.out.println("PTDCC.drop");
		
		theFinalEvent = e;
		cColonne.p2cDrop(pcDrop.getControle());
	}

}
