package presentation;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import solitaire.application.Usine;

public class PSolitaire extends JFrame {
	private static final long serialVersionUID = -7160926421677040759L;

	public PSolitaire(String arg0, Usine arg1) {
		
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		this.setLocation(200, 100);
		this.setSize(800, 600);

		JPanel panelSabot = new JPanel();
		JPanel panels[] = new JPanel[7];
		for(int i=0; i<7; i++){
			panels[i] = new JPanel();
			System.out.println("panel " + i + " = " + Integer.toString(PCarte.largeur+20) + " et " + Integer.toString(this.getHeight()-20) );
			
			panels[i].setSize(PCarte.largeur+20, this.getHeight()-20);
			panels[i].setPreferredSize(panels[i].getSize());
			
			if(i%2==0){
				panels[i].setBackground(Color.blue);
			} else {
				panels[i].setBackground(Color.red);
			}

			this.add(panels[i]);
		}
		

		this.pack();
		this.setVisible(true); 
		
	}

}
