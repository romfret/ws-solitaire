package presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

import controler.CSolitaire;

public class PSolitaire extends JPanel {
	private static final long serialVersionUID = -7160926421677040759L;

	
	private PSabot pSabot;
	private int i = 0;
	private JPanel panels[];
	private JPanel panelTDCC;
	
	public PSolitaire(CSolitaire cSolitaire) {
		this.setLayout(new BorderLayout());
		
		JPanel panelColonnes = getPanelColonnes();
		this.add(panelColonnes, BorderLayout.WEST);

	}

	private JPanel getPanelColonnes() {
		JPanel panelColonnes = new JPanel();
		panels = new JPanel[8];
		for(int i=0; i<8; i++){
			panels[i] = new JPanel();
			System.out.println("panel " + i + " = " + Integer.toString(PCarte.largeur+20) + " et " + Integer.toString(this.getHeight()-20) );
			
			panels[i].setSize(PCarte.largeur+20, 800);
			panels[i].setPreferredSize(panels[i].getSize());
			
			panels[i].setBackground(Color.blue);

			panelColonnes.add(panels[i]);
		}
		return panelColonnes;
	}
	
	public void addPSabot(PSabot pSabot){
		this.pSabot = pSabot;
		pSabot.setLayout(new FlowLayout(FlowLayout.LEFT));
		pSabot.setSize(this.getWidth(), PCarte.hauteur);		
		this.add(pSabot, BorderLayout.PAGE_START);
	}

	public void addTasDeCartesColorees(PTasDeCartesColorees pTdcc) {
		panels[7].add(pTdcc);
	}

	public void addPColonne(PColonne presentation) {
		// TODO Auto-generated method stub
		panels[i].add(presentation);
		i++;
	}

}
