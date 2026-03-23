package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import controleur.Appartement;
 
import controleur.Controleur;
import controleur.Materiel;
import controleur.Tableau;

public class PanelMateriels extends PanelPrincipal implements ActionListener {

    private JPanel panelForm = new JPanel();

    private JTextField txtLibelle = new JTextField();
    private JTextField txtType = new JTextField();
    private JTextField txtEtat = new JTextField();
    private JTextField txtPrix = new JTextField();
    private static JComboBox<String> txtMat = new JComboBox<String>();


    private JButton btAnnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Valider");
    private JButton btSupprimer = new JButton("Supprimer");
    private JButton btModifier = new JButton("Modifier");

    private JPanel panelFiltre = new JPanel();
    private JTextField txtFiltre = new JTextField();
    private JButton btFiltrer = new JButton("Filtrer");

    private JTable tableMateriel;
    private JScrollPane scrollMateriel;
    private Tableau unTableau;

    public PanelMateriels(String titre) {
        super(titre);

        // panel filtre
        panelFiltre.setBounds(450,80,460,30);
        panelFiltre.setBackground(Color.gray);
        panelFiltre.setLayout(new GridLayout(1,3,10,10));
        panelFiltre.add(new JLabel("Filtrer : "));
        panelFiltre.add(txtFiltre);
        panelFiltre.add(btFiltrer);
        add(panelFiltre);

        // panel formulaire
        panelForm.setBounds(50,100,300,350);
        panelForm.setBackground(Color.gray);
        panelForm.setLayout(new GridLayout(9,2,10,10));

        panelForm.add(new JLabel("Libelle materiel:"));
        panelForm.add(txtLibelle);

        panelForm.add(new JLabel("Type materiel:"));
        panelForm.add(txtType);

        panelForm.add(new JLabel("Etat materiel :"));
        panelForm.add(txtEtat);

        panelForm.add(new JLabel("Prix materiel :"));
        panelForm.add(txtPrix);
        
        
        panelForm.add(new JLabel("id_mat :"));
        panelForm.add(txtMat);

        
        

        panelForm.add(btAnnuler);
        panelForm.add(btValider);
        panelForm.add(btSupprimer);
        panelForm.add(btModifier);

        btSupprimer.setEnabled(true);
        btModifier.setEnabled(true);

        add(panelForm);

        btAnnuler.addActionListener(this);
        btValider.addActionListener(this);
        btModifier.addActionListener(this);
        btSupprimer.addActionListener(this);
        btFiltrer.addActionListener(this);
        txtFiltre.addActionListener(this);

        String entetes[] = {"ID", "Libelle", "Type", "Etat", "Prix","id_mat"};

        unTableau = new Tableau(obtenirDonnees(""), entetes);
        tableMateriel = new JTable(unTableau);

        scrollMateriel = new JScrollPane(tableMateriel);
        scrollMateriel.setBounds(450,120,450,260);
        add(scrollMateriel);

        tableMateriel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = tableMateriel.getSelectedRow();

                txtLibelle.setText(unTableau.getValueAt(i,1).toString());
                txtType.setText(unTableau.getValueAt(i,2).toString());
                txtEtat.setText(unTableau.getValueAt(i,3).toString());
                txtPrix.setText(unTableau.getValueAt(i,4).toString());
             

                btModifier.setEnabled(true);
                btSupprimer.setEnabled(true);
            }
        });
        remplirIdMat();
    }

    public Object[][] obtenirDonnees(String filtre){
        ArrayList<Materiel> lesMateriels = Controleur.selectAllMateriels(filtre);
        Object[][] mat = new Object[lesMateriels.size()][5];

        int i=0;
        for(Materiel m : lesMateriels){
            mat[i][0]=m.getId_mat();
            mat[i][1]=m.getLibelle_mat();
            mat[i][2]=m.getType_mat();
            mat[i][3]=m.getEtat();
            mat[i][4]=m.getPrix_jour();
            mat[i][5]=m.getId_mat();
            
           
            
            i++;
        }
        return mat;
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==btAnnuler){
            vider();
        }

        else if(e.getSource()==btValider){
            insertMateriel();
        }

        else if(e.getSource()==btModifier){
            updateMateriel();
        }

        else if(e.getSource()==btSupprimer){
            deleteMateriel();
        }

        else if(e.getSource()==btFiltrer || e.getSource()==txtFiltre){
            unTableau.setDonnees(obtenirDonnees(txtFiltre.getText()));
        }
    }

    public void vider(){
        txtLibelle.setText("");
        txtType.setText("");
        txtEtat.setText("");
        txtPrix.setText("");
     
        btModifier.setEnabled(false);
        btSupprimer.setEnabled(false);
        
    }

    public void insertMateriel(){

        if(txtLibelle.getText().equals("") || txtType.getText().equals("") || txtEtat.getText().equals("") || txtPrix.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Veuillez remplir tous les champs");
            return;
        }

        Materiel m = new Materiel(
                txtLibelle.getText(),
                txtType.getText(),
                txtEtat.getText(),
                Float.parseFloat(txtPrix.getText()),
                Integer.parseInt(txtMat.getSelectedItem().toString())
        );


        Controleur.insertMateriel(m);
        unTableau.setDonnees(obtenirDonnees(""));
        vider();
    }


    public void updateMateriel(){

        int i = tableMateriel.getSelectedRow();
        int id = Integer.parseInt(unTableau.getValueAt(i,0).toString());

        Materiel m = new Materiel(
                id,
                txtLibelle.getText(),
                txtType.getText(),
                txtEtat.getText(),
                Float.parseFloat(txtPrix.getText()),
                Integer.parseInt(txtMat.getSelectedItem().toString())
        );

        Controleur.updateMateriel(m);
        unTableau.setDonnees(obtenirDonnees(""));
        vider();
    }

    public void deleteMateriel(){

        int i = tableMateriel.getSelectedRow();
        int id = Integer.parseInt(unTableau.getValueAt(i,0).toString());

        int r = JOptionPane.showConfirmDialog(this,"Supprimer materiel ?");

        if(r==0){
            Controleur.deleteMateriel(id);
            unTableau.setDonnees(obtenirDonnees(""));
            vider();
        }
    }

	public static void remplirIdMat() {
	 //remplir le combo box de materiel
		ArrayList<Materiel> lesMateriels=Controleur .selectAllMateriels("");
		txtMat.removeAllItems();
		for(Materiel  unMateriel :lesMateriels) {
			txtMat.addItem(unMateriel.getId_mat()+"-"+unMateriel.getLibelle_mat());
		}
		
	}
}
