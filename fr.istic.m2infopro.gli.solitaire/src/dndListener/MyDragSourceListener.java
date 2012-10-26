package dndListener;

import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;

import presentation.PSabot;

public class MyDragSourceListener implements DragSourceListener {

	private PSabot pSabot;
	
	public MyDragSourceListener(PSabot pSabot) {
		this.pSabot = pSabot;
	}
	
	@Override
	public void dragDropEnd(DragSourceDropEvent arg0) {
		pSabot.dragDropEnd(arg0);
	}

	@Override
	public void dragEnter(DragSourceDragEvent dsde) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dragExit(DragSourceEvent dse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dragOver(DragSourceDragEvent dsde) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dropActionChanged(DragSourceDragEvent dsde) {
		// TODO Auto-generated method stub
		
	}

}
