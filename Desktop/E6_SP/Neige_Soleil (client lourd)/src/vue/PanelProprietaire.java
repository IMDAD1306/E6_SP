 
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
import controleur.Proprietaire;
import controleur.Controleur;
import controleur.Tableau;
public class PanelProprietaire extends PanelPrincipal implements ActionListener{
 
 private JPanel panelForm = new JPanel();
 JTextField txtNom = new JTextField();
 JTextField txtPrenom = new JTextField();
 JTextField txtAdresse = new JTextField();
 JTextField txtEmail = new JTextField();
 JTextField txtTel= new JTextField();
 JTextField txtIban = new JTextField();
 
 private JButton btAnnuler = new JButton("Annuler");
 private JButton btValider = new JButton("Valider");
 private JButton btSupprimer = new JButton("Supprimer");
 private JButton btModifier = new JButton("Modifier");
 
 private JPanel panelFiltre = new JPanel();
 private JTextField txtFiltre = new JTextField();
 private JButton btFiltrer = new JButton("Filtrer");
 
 private JTable tableProprietaires ;
 private JScrollPane scrollProprietaires;
 private Tableau unTableau;
 
 
 private JLabel lbNbProprietaires = new JLabel("");
 public PanelProprietaire(String titre) {
  super(titre);
  
  //installer le panel filtrer
  this.panelFiltre.setBounds(450, 80, 460, 30);
  this.panelFiltre.setBackground(Color.gray);
  this.panelFiltre.setLayout(new GridLayout(6, 3, 10, 10));
  this.panelFiltre.add(new JLabel("Filtrer les proprietaires par :"));
  this.panelFiltre.add(this.txtFiltre);
  this.panelFiltre.add(this.btFiltrer);
  this.add(this.panelFiltre);
    
  //installer un panel
  panelForm.setBounds(50,100,300,350);
  panelForm.setBackground(Color.gray);
  panelForm.setLayout(new GridLayout(9,2,10,10));
  
  this.panelForm.add(new JLabel("Nom du proprietaire :"));
  this.panelForm.add(this.txtNom);
  
  this.panelForm.add(new JLabel("Prenom du proprietaire :"));
  this.panelForm.add(this.txtPrenom);
  
  this.panelForm.add(new JLabel("Adresse du proprietaire :"));
  this.panelForm.add(this.txtAdresse);
  
  this.panelForm.add(new JLabel("Email du proprietaire :"));
  this.panelForm.add(this.txtEmail);
  
  this.panelForm.add(new JLabel("Tel du proprietaire :"));
  this.panelForm.add(this.txtTel);
  
  this.panelForm.add(new JLabel("IBAN du proprietaire :"));
  this.panelForm.add(this.txtIban);
  
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
  String entetes [] = {"ID proprio", "Nom proprietaire", "Prenom proprietaire", "Adresse proprietaire", "Email proprietaire", "Tel proprietaire", 
   "IBAN proprietaire"};
  this.unTableau = new Tableau(this.obtenirDonnees(""),entetes); // on met les "" car nous voulons toutes ls classes
  this.tableProprietaires = new JTable(this.unTableau) ;
  this.scrollProprietaires = new JScrollPane(this.tableProprietaires);
  this.scrollProprietaires.setBounds(450, 120, 450, 240);
  this.scrollProprietaires.setBackground(Color.gray);
  this.add(this.scrollProprietaires);
  
  //activation du MouseListener sur la JTable
  this.tableProprietaires.addMouseListener(new MouseListener() {
   
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
    int numLigne = tableProprietaires.getSelectedRow();
    txtNom.setText(unTableau.getValueAt(numLigne, 1).toString());
    txtPrenom.setText(unTableau.getValueAt(numLigne, 2).toString());
    txtAdresse.setText(unTableau.getValueAt(numLigne, 3).toString());
    txtEmail.setText(unTableau.getValueAt(numLigne, 4).toString());
    txtTel.setText(unTableau.getValueAt(numLigne, 5).toString());
    txtIban.setText(unTableau.getValueAt(numLigne, 6).toString());
    
    btModifier.setEnabled(true);
    btSupprimer.setEnabled(true);
   } 
   
  });
  //placement du JLabel
  this.lbNbProprietaires.setBounds(450, 300, 400, 20);
  this.add(this.lbNbProprietaires);
  
  this.lbNbProprietaires.setText("Le nombre de proprietaire est de :" + unTableau.getRowCount());
 }
 
 public Object [] [] obtenirDonnees (String filtre) {
  ArrayList<Proprietaire> lesProprietaires = Controleur.selectAllProprietaires(filtre);
  Object [] [] matrice = new Object[lesProprietaires.size()] [7];
  int i = 0 ;
  for (Proprietaire unProprietaire : lesProprietaires) {
   matrice [i][0] = unProprietaire.getIdProprio();
   matrice [i][1] = unProprietaire.getNom();
   matrice [i][2] = unProprietaire.getPrenom();
   matrice [i][3] = unProprietaire.getAdresse();
   matrice [i][4] = unProprietaire.getEmail();
   matrice [i][5] = unProprietaire.getTel();
   matrice [i][6] = unProprietaire.getIban();
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
   this.insertProprietaire();
  }
  else if(e.getSource() == this.btModifier) {
   this.updateProprietaire();
  }
  else if(e.getSource() == this.btSupprimer) {
   this.deleteProprietaire();
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
  this.txtAdresse.setText("");
  this.txtEmail.setText("");
  this.txtTel.setText("");
  this.txtIban.setText("");
  
  
  btModifier.setEnabled(false);
  btSupprimer.setEnabled(false);
 }
 
 public void insertProprietaire () {
  String nom = this.txtNom.getText();
  String prenom = this.txtPrenom.getText();
  String adresse = this.txtAdresse.getText();
  String email = this.txtEmail.getText();
  String tel = this.txtEmail.getText();
  String iban = this.txtIban.getText();
  
  if (nom.equals("") || prenom.equals("") || adresse.equals("") || email.equals("") || tel.equals("")  || iban.equals("")) {
   JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs");
  }
  else {
   //instancier un proprietaire 
   Proprietaire unProprietaire = new Proprietaire (nom, prenom, adresse, email, tel, iban);
   
   //inserer le proprietaire dans la BDD
   Controleur.insertProprietaire (unProprietaire);
   
   JOptionPane.showMessageDialog(this, "Insertion réussie du proprietaire");
   
   //actualiser le tableau
   this.unTableau.setDonnees(this.obtenirDonnees(""));
   this.lbNbProprietaires.setText("Le nombre de proprietaire est de :" + unTableau.getRowCount());
   
   PanelAppartements.remplirIdProprio();
   
   this.viderChamps();
  }
 }
 
 public void updateProprietaire () {
  int numLigne = tableProprietaires.getSelectedRow();
  int idproprio = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
  String nom = this.txtNom.getText();
  String prenom = this.txtPrenom.getText();
  String adresse = this.txtAdresse.getText();
  String email = this.txtEmail.getText();
  String tel = this.txtTel.getText();
  String iban = this.txtIban.getText();
  if (nom.equals("") || prenom.equals("") || adresse.equals("") || email.equals("") || tel.equals("") || iban.equals("")) {
   JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs");
  }else {
   //instanciation du proprietaire
   Proprietaire unProprietaire = new Proprietaire (idproprio, nom, prenom, adresse, email, tel, iban);
   
   //modification dans la base de donnée
   Controleur.updateProprietaire(unProprietaire);
   
   JOptionPane.showMessageDialog(this, "Modification réussie du proprietaire");
   
   //actualiser le tableau
   this.unTableau.setDonnees(this.obtenirDonnees(""));
   
   PanelAppartements.remplirIdProprio();
   
   this.viderChamps();
  }
 }
 
 public void deleteProprietaire () {
  int numLigne = tableProprietaires.getSelectedRow();
  int idproprio = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
  
  int retour = JOptionPane.showConfirmDialog(this, "Voulez-vous supprimer le proprietaire ?" , "Suppression", JOptionPane.YES_NO_OPTION);
  if (retour == 0) {
   //suppression de la classe dans la base
   Controleur.deleteProprietaire(idproprio);
   
   JOptionPane.showMessageDialog(this, "Supression réussie du proprietaire");
   
   //actualiser le tableau
   this.unTableau.setDonnees(this.obtenirDonnees(""));
   
   this.lbNbProprietaires.setText("Le nombre de proprietaire est de :" + unTableau.getRowCount());
   
   PanelAppartements.remplirIdProprio();
   
   this.viderChamps();
  }
 }
 
}
 
 