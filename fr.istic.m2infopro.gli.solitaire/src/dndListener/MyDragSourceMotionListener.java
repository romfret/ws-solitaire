package dndListener;

import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceMotionListener;

import presentation.PTasDeCartes;

public class MyDragSourceMotionListener implements DragSourceMotionListener {

	private PTasDeCartes currentMovedPTasDeCarte;
	
	public void dragMouseMoved(DragSourceDragEvent dsde) {
		currentMovedPTasDeCarte.setLocation(1 + dsde.getX(), 1 + dsde.getY());
//		currentMovedPTasDeCarte.repaint();
//		System.out.println("MyDragSourceMotionListener = " + currentMovedPTasDeCarte.getLocation());
	}
	
	public void setCurrentMovedPTasDeCarte(PTasDeCartes pTasDeCartes) {
		this.currentMovedPTasDeCarte = pTasDeCartes;
	}


}
