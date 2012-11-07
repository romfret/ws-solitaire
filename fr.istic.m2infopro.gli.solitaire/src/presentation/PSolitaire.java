package presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controler.CSolitaire;

public class PSolitaire extends JPanel {
	private static final long serialVersionUID = -7160926421677040759L;

	
	private int i = 0;
	private int j = 0;
	private JPanel panels[];
	public PSolitaire(CSolitaire cSolitaire) {
		this.setLayout(new BorderLayout());
		
		JPanel panelColonnes = getPanelColonnes();
		this.add(panelColonnes, BorderLayout.WEST);

	}

	private JPanel getPanelColonnes() {
		JPanel panelColonnes = new JPanel();
		panels = new JPanel[8];
		for(int i=0; i<8; i++){
			System.out.println("panel " + i + " = " + Integer.toString(PCarte.largeur+20) + " et " + Integer.toString(this.getHeight()-20) );

			panels[i] = new JPanel();
			if(i!=7){
				panels[i].setSize(PCarte.largeur+20, 550);
				Color colr = new Color(51,153,204);
				panels[i].setBackground(colr);
			}else{
				panels[i].setSize(PCarte.largeur+50, 470);
				panels[i].setLayout(new BoxLayout(panels[i], BoxLayout.PAGE_AXIS));
				panels[i].setBackground(Color.darkGray);
			}
			
			panels[i].setPreferredSize(panels[i].getSize());
			

			panelColonnes.add(panels[i]);
		}
		return panelColonnes;
	}
	
	public void addPSabot(PSabot pSabot){
		pSabot.setLayout(new FlowLayout(FlowLayout.LEFT));
		pSabot.setSize(this.getWidth(), PCarte.hauteur);		
		this.add(pSabot, BorderLayout.PAGE_START);
	}

	public void addTasDeCartesColorees(PTasDeCartesColorees pTdcc) {
		switch(j){
		case 0:
			JLabel lab1 = new JLabel("Carreau");
			lab1.setForeground(Color.white);
			lab1.setAlignmentX(Component.CENTER_ALIGNMENT);
			panels[7].add(lab1);
			panels[7].add(pTdcc);
			break;

		case 1:
			JLabel lab2 = new JLabel("Pique");
			lab2.setForeground(Color.white);
			lab2.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			panels[7].add(lab2);
			panels[7].add(pTdcc);
			break;

		case 2:
			JLabel lab3 = new JLabel("Coeur");
			lab3.setForeground(Color.white);
			lab3.setAlignmentX(Component.CENTER_ALIGNMENT);
			panels[7].add(lab3);
			panels[7].add(pTdcc);
			break;

		case 3:
			JLabel lab4 = new JLabel("Treffle");
			lab4.setForeground(Color.white);
			lab4.setAlignmentX(Component.CENTER_ALIGNMENT);
			panels[7].add(lab4);
			panels[7].add(pTdcc);
			break;
			
		default:
		}
		j++;
	}

	public void addPColonne(PColonne presentation) {
		// TODO Auto-generated method stub
		panels[i].add(presentation);
		i++;
	}

}
