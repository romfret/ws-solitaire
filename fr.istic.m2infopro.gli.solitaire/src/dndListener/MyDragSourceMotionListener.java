package dndListener;

import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceMotionListener;

import presentation.PCarte;

public class MyDragSourceMotionListener implements DragSourceMotionListener {

	private PCarte currentMovedPCarte;
	
	public void dragMouseMoved(DragSourceDragEvent dsde) {
		currentMovedPCarte.setLocation(1 + dsde.getX(), 1 + dsde.getY());
		System.out.println(currentMovedPCarte.getLocation());
	}
	
	public void setCurrentMovedCard(PCarte currentMovedPCarte) {
		this.currentMovedPCarte = currentMovedPCarte;
	}


}
