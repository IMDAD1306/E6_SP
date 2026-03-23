package vue;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class PanelPrincipal extends JPanel
{
	//panel generique qui sert de base pour créer les autres panels 
	// garder les caractéristiques communes 
	
	public PanelPrincipal(String titre) {
		this.setBackground(Color.gray);
		this.setBounds(10,60,960,500);
		this.setLayout(null);
		
		JLabel lbTitre = new JLabel(titre);
		Font unePolice = new Font ("Arial", Font.BOLD, 18);
		lbTitre.setFont(unePolice);
		lbTitre.setBounds(340,10,400,20);
		this.add(lbTitre);
		
		this.setVisible(false);
		
	}

}
