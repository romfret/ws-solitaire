package dndListener;

import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;

import presentation.PSabot;

public class MyDragGestureListener implements DragGestureListener {

	private PSabot pSabot;
	
	public MyDragGestureListener(PSabot pSabot) {
		this.pSabot = pSabot;
	}
	
	@Override
	public void dragGestureRecognized(DragGestureEvent dge) {
		pSabot.dragGestureRecognized(dge);
	}

}
