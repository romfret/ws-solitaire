package presentation;

//import solitaire.controle.* ;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import controler.CCarte;

/**
 * Composant Pr�sentation d'une carte
 */
public class PCarte extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 protected CCarte controle; // contr�leur associ�
	protected JLabel face, dos;
	protected ImageIcon icone; // image de la face
	protected static ImageIcon iconeDos; // image du dos
	public static int largeur, hauteur;

	/**
	 * initialiser une carte
	 * 
	 * @param chaine
	 *            : nom de la carte (exemple "3H" = 3 Heart)
	 */
	 public PCarte (final String chaine, final CCarte controle) {
//	public PCarte(final String chaine) {
		 this.controle = controle;

		// image de la face
		icone = new ImageIcon("cartesCSHD/" + chaine + ".gif");
		face = new JLabel(icone);
		add(face);
		face.setLocation(0, 0);
		face.setSize(largeur, hauteur);

		// image du dos
		dos = new JLabel(iconeDos);
		add(dos);
		dos.setLocation(0, 0);
		dos.setSize(largeur, hauteur);

		// le JPanel
		setLayout(null);
		setBackground(Color.yellow);
		setOpaque(true);
		setSize(face.getSize());
		setPreferredSize(getSize());
		setFaceVisible(false);
	} // constructeur

	/**
	 * changer la visibilit� de la carte
	 * 
	 * @param faceVisible
	 *            : vrai si la face est visible, faux sinon
	 */
	public void setFaceVisible(boolean faceVisible) {
		face.setVisible(faceVisible);
		dos.setVisible(!faceVisible);
	}

	 public final CCarte getControle () {
	 return (controle) ;
	 }

	public ImageIcon getIcone() {
		return icone;
	}

	/**
	 * initialiser l'image du dos et les dimensions d'une PCarte
	 */
	static {
		iconeDos = new ImageIcon("cartesCSHD/dos.jpg");
		largeur = iconeDos.getIconWidth() + 4;
		hauteur = iconeDos.getIconHeight() + 4;
	}

	/**
	 * programme de test : � d�placer dans une classe d�di�e aux tests
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		JFrame f = new JFrame("Test PCarte");
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.setLayout(new FlowLayout()); // au lieu de BorderLayout par d�faut
		f.getContentPane().setBackground(new Color(143, 143, 195)); // violet
																	// p�le
		CCarte cc = new CCarte(1, 1);
		// une carte visible
		PCarte pc = new PCarte("QH", cc);
		pc.setFaceVisible(true);
		f.getContentPane().add(pc);

		// une carte cach�e
		pc = new PCarte("1D", cc);
		pc.setFaceVisible(false);
		f.getContentPane().add(pc);

		f.pack(); // dimensionner le cadre
		f.setLocation(200, 100); // le positionner
		f.setVisible(true); // et le rendre visible
	} // main

} // PCarte
