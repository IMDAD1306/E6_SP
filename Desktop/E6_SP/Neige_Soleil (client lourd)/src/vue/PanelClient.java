 
package vue;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import controleur.Client;
import controleur.Controleur;
import controleur.Tableau;
public class PanelClient extends PanelPrincipal implements ActionListener{
 
 private JPanel panelForm = new JPanel();
 JTextField txtNom = new JTextField();
 JTextField txtPrenom = new JTextField();
 JTextField txtEmail = new JTextField();
 JTextField txtTel= new JTextField();

 
 private JButton btAnnuler = new JButton("Annuler");
 private JButton btValider = new JButton("Valider");
 private JButton btSupprimer = new JButton("Supprimer");
 private JButton btModifier = new JButton("Modifier");
 
 private JPanel panelFiltre = new JPanel();
 private JTextField txtFiltre = new JTextField();
 private JButton btFiltrer = new JButton("Filtrer");
 
 private JTable tableClients ;
 private JScrollPane scrollClients;
 private Tableau unTableau;
 
 
 private JLabel lbNbClients = new JLabel("");
 public PanelClient(String titre) {
  super(titre);
  
  //installer le panel filtrer
  this.panelFiltre.setBounds(450, 80, 460, 30);
  this.panelFiltre.setBackground(Color.gray);
  this.panelFiltre.setLayout(new GridLayout(6, 3, 10, 10));
  this.panelFiltre.add(new JLabel("Filtrer les clients par :"));
  this.panelFiltre.add(this.txtFiltre);
  this.panelFiltre.add(this.btFiltrer);
  this.add(this.panelFiltre);
    
  //installer un panel
  panelForm.setBounds(50,100,300,350);
  panelForm.setBackground(Color.gray);
  panelForm.setLayout(new GridLayout(9,2,10,10));
  
  this.panelForm.add(new JLabel("Nom du client :"));
  this.panelForm.add(this.txtNom);
  
  this.panelForm.add(new JLabel("Prenom du client :"));
  this.panelForm.add(this.txtPrenom);
  
  
  this.panelForm.add(new JLabel("Email du client :"));
  this.panelForm.add(this.txtEmail);
  
  this.panelForm.add(new JLabel("Tel du client :"));
  this.panelForm.add(this.txtTel);
  
  
  this.panelForm.add(this.btAnnuler);
  this.panelForm.add(this.btValider);
  
  this.panelForm.add(this.btSupprimer);
  this.panelForm.add(this.btModifier);
  
  this.btSupprimer.setEnabled(true);
  this.btModifier.setEnabled(true);
  
  this.add(this.panelForm);
  
  //rendre les boutons ecoutables
  this.btAnnuler.addActionListener(this);
  this.btValider.addActionListener(this);
  this.btModifier.addActionListener(this);
  this.btSupprimer.addActionListener(this);
  this.btFiltrer.addActionListener(this);
  this.txtFiltre.addActionListener(this);
  
  //placement de la table des classes
  String entetes [] = {"ID client", "Nom client", "Prenom client", "Email client", "Tel client", };
  this.unTableau = new Tableau(this.obtenirDonnees(""),entetes); // on met les "" car nous voulons toutes ls classes
  this.tableClients = new JTable(this.unTableau) ;
  this.scrollClients = new JScrollPane(this.tableClients);
  this.scrollClients.setBounds(450, 120, 450, 240);
  this.scrollClients.setBackground(Color.gray);
  this.add(this.scrollClients);
  
  //activation du MouseListener sur la JTable
  this.tableClients.addMouseListener(new MouseListener() {
   
   @Override
   public void mouseReleased(MouseEvent e) {
    // TODO Auto-generated method stub
    
   }
   
   @Override
   public void mousePressed(MouseEvent e) {
    // TODO Auto-generated method stub
    
   }
   
   @Override
   public void mouseExited(MouseEvent e) {
    // TODO Auto-generated method stub
    
   }
   
   @Override
   public void mouseEntered(MouseEvent e) {
    // TODO Auto-generated method stub
    
   }

   @Override
   public void mouseClicked(MouseEvent e) {
    int numLigne = tableClients.getSelectedRow();
    txtNom.setText(unTableau.getValueAt(numLigne, 1).toString());
    txtPrenom.setText(unTableau.getValueAt(numLigne, 2).toString());
    txtEmail.setText(unTableau.getValueAt(numLigne, 3).toString());
    txtTel.setText(unTableau.getValueAt(numLigne, 4).toString());
   
    
    btModifier.setEnabled(true);
    btSupprimer.setEnabled(true);
   } 
   
  });
  //placement du JLabel
  this.lbNbClients.setBounds(450, 300, 400, 20);
  this.add(this.lbNbClients);
  
  this.lbNbClients.setText("Le nombre de client est de :" + unTableau.getRowCount());
 }
 
 public Object [] [] obtenirDonnees (String filtre) {
  ArrayList<Client> lesClients = Controleur.selectAllClients(filtre);
  Object [] [] matrice = new Object[lesClients.size()] [5];
  int i = 0 ;
  for (Client unClient : lesClients) {
   matrice [i][0] = unClient.getIdclient();
   matrice [i][1] = unClient.getNom();
   matrice [i][2] = unClient.getPrenom();
   matrice [i][3] = unClient.getEmail();
   matrice [i][4] = unClient.getTel();
   i++;
  }
  return matrice ;
 }
 @Override
 public void actionPerformed(ActionEvent e) {
  if(e.getSource() == this.btAnnuler) {
   this.viderChamps();
  }
  else if(e.getSource() == this.btValider) {
   this.insertClient();
  }
  else if(e.getSource() == this.btModifier) {
   this.updateClient();
  }
  else if(e.getSource() == this.btSupprimer) {
   this.deleteClient();
  }
  else if(e.getSource() == this.btFiltrer || e.getSource() == this.txtFiltre) {
   String filtre = this.txtFiltre.getText();
   //actualiser les donnees par la recherche filtre
   this.unTableau.setDonnees(this.obtenirDonnees(filtre));
  }
  
 }
 
 public void viderChamps () {
  this.txtNom.setText("");
  this.txtPrenom.setText("");
  this.txtEmail.setText("");
  this.txtTel.setText("");
 
  
  
  btModifier.setEnabled(false);
  btSupprimer.setEnabled(false);
 }
 
 public void insertClient () {
  String nom = this.txtNom.getText();
  String prenom = this.txtPrenom.getText();
  String email = this.txtEmail.getText();
  String tel = this.txtTel.getText();

 
  
  if (nom.equals("") || prenom.equals("") || email.equals("") || tel.equals("")) {
   JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs");
  }
  else {
   //instancier un proprietaire 
   Client unClient = new Client (nom, prenom, email, tel);
   
   //inserer le proprietaire dans la BDD
   Controleur.insertClient (unClient);
   
   JOptionPane.showMessageDialog(this, "Insertion réussie du client");
   
   //actualiser le tableau
   this.unTableau.setDonnees(this.obtenirDonnees(""));
   this.lbNbClients.setText("Le nombre de client est de :" + unTableau.getRowCount());
   
   PanelAppartements.remplirIdProprio();
   
   this.viderChamps();
  }
 }
 
 public void updateClient() {
	    int numLigne = tableClients.getSelectedRow();
	    int idclient = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());

	    String nom = this.txtNom.getText();
	    String prenom = this.txtPrenom.getText();
	    String email = this.txtEmail.getText();
	    String tel = this.txtTel.getText();

	    if (nom.equals("") || prenom.equals("") || email.equals("") || tel.equals("")) {
	        JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs");
	    } else {
	        Client unClient = new Client(idclient, nom, prenom, email, tel);
	        Controleur.updateClient(unClient);

	        JOptionPane.showMessageDialog(this, "Modification réussie du client");

	        this.unTableau.setDonnees(this.obtenirDonnees(""));
	        this.lbNbClients.setText("Le nombre de client est de :" + unTableau.getRowCount());
	        this.viderChamps();
	    }
	}

 
 public void deleteClient() {
	    int numLigne = tableClients.getSelectedRow();
	    int idclient = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());

	    int retour = JOptionPane.showConfirmDialog(this, "Voulez-vous supprimer le client ?", "Suppression", JOptionPane.YES_NO_OPTION);
	    if (retour == 0) {
	        Controleur.deleteClient(idclient);

	        JOptionPane.showMessageDialog(this, "Suppression réussie du client");

	        this.unTableau.setDonnees(this.obtenirDonnees(""));
	        this.lbNbClients.setText("Le nombre de client est de :" + unTableau.getRowCount());
	        this.viderChamps();
	    }
	}

 
}
 
 