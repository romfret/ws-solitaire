package dndListener;

import java.awt.Point;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceMotionListener;

import presentation.PTasDeCartes;

public class MyDragSourceMotionListener implements DragSourceMotionListener {

	private PTasDeCartes currentMovedPTasDeCarte;
	private int xFramePosition;
	private int yFramePosition;
	private int dx;
	private int dy;

	public void dragMouseMoved(DragSourceDragEvent dsde) {

		currentMovedPTasDeCarte.setLocation(dsde.getX() - xFramePosition - dx,
				dsde.getY() - yFramePosition - dy);
	}

	/**
	 * Initialilsation du PTasDeCartes subissant le DnD 
	 */
	public void setCurrentMovedPTasDeCarte(PTasDeCartes pTasDeCartes) {
		this.currentMovedPTasDeCarte = pTasDeCartes;
	}

	/**
	 * Initialise les differents parametres pour la gestion graphique du DnD
	 * (deplacement).<p>
	 * La variable <code>currentMovedPTasDeCarte</code> doit etre
	 * initialisee en premier lieu.
	 * 
	 * @param framePosition
	 *            : position de la frame principale de l'application
	 * @param cursorPositionOnFrame
	 *            : position du curseur de la souris par rapport au repere de la
	 *            frame
	 */
	public void setSelection(Point framePosition, Point cursorPositionOnFrame) {
		xFramePosition = framePosition.x;
		yFramePosition = framePosition.y;

		// Coordonnes du curseur de la souris par rapport au PTasDeCartes
		dx = cursorPositionOnFrame.x - currentMovedPTasDeCarte.getX();
		dy = cursorPositionOnFrame.y- currentMovedPTasDeCarte.getY();
	}

}
