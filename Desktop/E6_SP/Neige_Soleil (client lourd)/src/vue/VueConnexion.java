package vue;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controleur.Controleur;
import controleur.Neige_soleil;
import controleur.User;

public class VueConnexion extends JFrame implements ActionListener, KeyListener
{
	
	private JPanel panelForm = new JPanel();
	private JTextField txtEmail = new JTextField("imdad@gmail.com");
	private JPasswordField txtMdp = new JPasswordField("123");
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btValider = new JButton("Valider");
	
	public VueConnexion() {
		this.setTitle("Client Lourd Neige_et_soleil");
		this.setBounds(300,10,300,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);
		this.getContentPane().setBackground(Color.gray);
		//placement du logo
		ImageIcon uneImage= new ImageIcon("src/images/logo.png");
		JLabel lblogo= new JLabel(uneImage);
		lblogo.setBounds(20,25,250,200);
		this.add(lblogo);
		
		//Placement du panelForm
		this.panelForm.setBounds(25,280,250,140);
		this.panelForm.setBackground(Color.gray); 
		this.panelForm.setLayout(new GridLayout(3,2,10,10));
		
		
		
		
		
		//ajout des zones de textes
		
		this.panelForm.add(new JLabel("Email : "));
		this.panelForm.add(this.txtEmail);
		this.panelForm.add(new JLabel("MDP : "));
		this.panelForm.add(this.txtMdp);
		
		//ajout des bouttons 
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btValider);
		
		//ajout du panel de la fenetre 
		this.add(this.panelForm);
		
		
		
		//rendre les bouttons ecoutables
		this.btAnnuler.addActionListener(this);
		this.btValider.addActionListener(this);
		
		this.txtEmail.addKeyListener(this);
		this.txtMdp.addKeyListener(this);
		
		
		
		
	this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()== this.btAnnuler) {
			this.viderChamps();
			
			
		} else if (e.getSource()== this.btValider) {
			this.traitement();
		}
		
	}
	public void viderChamps() {
		this.txtEmail.setText("");
		this.txtMdp.setText("");
	}
	
	public void traitement() {
		String email = this.txtEmail.getText();
		String mdp =new String (this.txtMdp.getPassword());
		
		if(email.equals("") ||  mdp.equals("")){
			JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs!");
		}
		else {
			
			User unUser =Controleur.selectWhereUser(email, mdp);
			if (unUser ==null) {
				JOptionPane.showMessageDialog(this,"Veuillez vérifier vos identifiants");
			}else {
				JOptionPane.showMessageDialog(this, "Bienvenue"+ unUser.getNom()+ "" +unUser.getPrenom());
				
				
				//On lui ouvre le logiciel 
				Neige_soleil.rendreVisibleVueConnexion(false);
				Neige_soleil.creerDetruireVueGenerale(true);
				
				
			}
			
		}
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
			traitement();
		}
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}