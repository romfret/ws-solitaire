package dndListener;

import java.awt.Point;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceMotionListener;

import presentation.PTasDeCartes;

public class MyDragSourceMotionListener implements DragSourceMotionListener {

	private PTasDeCartes currentMovedPTasDeCarte;
	private int xSelection;
	private int ySelection;
	
	public void dragMouseMoved(DragSourceDragEvent dsde) {
		// TODO
		
//		int dx = currentMovedPTasDeCarte.getX() - xSelection;
//		int dy = currentMovedPTasDeCarte.getY() - ySelection;
//		
//		
//		currentMovedPTasDeCarte.setLocation(1 + dx + dsde.getX(), 1 + dy + dsde.getY());
		
		
		currentMovedPTasDeCarte.setLocation(1 + dsde.getX(), 1 + dsde.getY());
		
//		System.out.println("MyDragSourceMotionListener = " + currentMovedPTasDeCarte.getLocation());
	}
	
	public void setCurrentMovedPTasDeCarte(PTasDeCartes pTasDeCartes) {
		this.currentMovedPTasDeCarte = pTasDeCartes;
	}


	public void setSelection(Point position) {
		this.xSelection = position.x;
		this.ySelection = position.y;
	}

}
