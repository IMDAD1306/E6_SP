package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import controleur.Appartement;
 
import controleur.Controleur;
import controleur.Proprietaire;
import controleur.Tableau;

public class PanelAppartements extends PanelPrincipal implements ActionListener {

    private JPanel panelForm = new JPanel();

    private JTextField txtNum = new JTextField();
    private JTextField txtType = new JTextField();
    private JTextField txtSurface = new JTextField();
    private JTextField txtExpo = new JTextField();
    private JTextField txtDistance = new JTextField();
    private JTextField txtCapacite = new JTextField();
    private JTextField txtPrix = new JTextField();      
    private JTextField txtImage = new JTextField();
    private JLabel lblImage = new JLabel();
    private static JComboBox<String> txtProprio = new JComboBox<String>();


    private JButton btAnnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Valider");
    private JButton btSupprimer = new JButton("Supprimer");
    private JButton btModifier = new JButton("Modifier");

    private JPanel panelFiltre = new JPanel();
    private JTextField txtFiltre = new JTextField();
    private JButton btFiltrer = new JButton("Filtrer");

    private JTable tableAppart;
    private JScrollPane scrollAppart;
    private Tableau unTableau;

    public PanelAppartements(String titre) {
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
        panelForm.setBounds(50, 100, 300, 350);
        panelForm.setBackground(Color.gray);
        panelForm.setLayout(new GridLayout(10, 2, 10, 10));

        
        lblImage.setBounds(450, 400, 200, 200);
        lblImage.setBorder(BorderFactory.createLineBorder(Color.black));
        add(lblImage);


        panelForm.add(new JLabel("Num appart :"));
        panelForm.add(txtNum);

        panelForm.add(new JLabel("Type appart :"));
        panelForm.add(txtType);

        panelForm.add(new JLabel("Surface :"));
        panelForm.add(txtSurface);

        panelForm.add(new JLabel("Exposition :"));
        panelForm.add(txtExpo);

        panelForm.add(new JLabel("Distance pistes :"));
        panelForm.add(txtDistance);

        panelForm.add(new JLabel("Capacité :"));
        panelForm.add(txtCapacite);
        
        panelForm.add(new JLabel("Prix hebdo :"));
        panelForm.add(txtPrix);

        panelForm.add(new JLabel("Image :"));
        panelForm.add(txtImage);


        
        
        panelForm.add(new JLabel("id_proprietaire :"));
        panelForm.add(txtProprio);

        
        

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

        String entetes[] = {"ID", "Num", "Type", "Surface", "Expo", "Distance", "Capacité","Prix", "Image", "id_proprio"};

        unTableau = new Tableau(obtenirDonnees(""), entetes);
        tableAppart = new JTable(unTableau);

        scrollAppart = new JScrollPane(tableAppart);
        scrollAppart.setBounds(450,120,450,260);
        add(scrollAppart);

        tableAppart.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = tableAppart.getSelectedRow();

                txtNum.setText(unTableau.getValueAt(i,1).toString());
                txtType.setText(unTableau.getValueAt(i,2).toString());
                txtSurface.setText(unTableau.getValueAt(i,3).toString());
                txtExpo.setText(unTableau.getValueAt(i,4).toString());
                txtDistance.setText(unTableau.getValueAt(i,5).toString());
                txtCapacite.setText(unTableau.getValueAt(i,6).toString());
                txtPrix.setText(unTableau.getValueAt(i,7).toString());
                txtImage.setText(unTableau.getValueAt(i,8).toString());
                afficherImage(txtImage.getText());



                btModifier.setEnabled(true);
                btSupprimer.setEnabled(true);
            }
        });
        
        remplirIdProprio() ;
    }

    public Object[][] obtenirDonnees(String filtre){
        ArrayList<Appartement> lesApparts = Controleur.selectAllAppartements(filtre);
        Object[][] mat = new Object[lesApparts.size()][10];
;

        int i = 0;
        for (Appartement a : lesApparts) {
            mat[i][0] = a.getId_appart();
            mat[i][1] = a.getNum_appart();
            mat[i][2] = a.getType_appart();
            mat[i][3] = a.getSurface();
            mat[i][4] = a.getExposition();
            mat[i][5] = a.getDistance_pistes();
            mat[i][6] = a.getCapacite_accueil();
            mat[i][7] = a.getPrix_hebdo();
            mat[i][8] = a.getImage();
            mat[i][9] = a.getIdproprio();
            i++;

            
           
            
           
        }
        return mat;
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==btAnnuler){
            vider();
        }

        else if(e.getSource()==btValider){
            insertAppartement();
        }

        else if(e.getSource()==btModifier){
            updateAppartement();
        }

        else if(e.getSource()==btSupprimer){
            deleteAppartement();
        }

        else if(e.getSource()==btFiltrer || e.getSource()==txtFiltre){
            unTableau.setDonnees(obtenirDonnees(txtFiltre.getText()));
        }
    }

    public void vider(){
        txtNum.setText("");
        txtType.setText("");
        txtSurface.setText("");
        txtExpo.setText("");
        txtDistance.setText("");
        txtCapacite.setText("");
        txtPrix.setText("");
        txtImage.setText("");
        lblImage.setIcon(null);

       
        btModifier.setEnabled(false);
        btSupprimer.setEnabled(false);
        
    }

    public void insertAppartement(){

        if(txtNum.getText().equals("") || txtType.getText().equals("") || txtSurface.getText().equals("")|| txtPrix.getText().equals("") || lblImage.getText().equals("") 
        		){
            JOptionPane.showMessageDialog(this,"Veuillez remplir tous les champs");
            return;
        }

        Appartement a = new Appartement(
                txtNum.getText(),
                txtType.getText(),
                Float.parseFloat(txtSurface.getText()),
                Integer.parseInt(txtCapacite.getText()),
                txtExpo.getText(),
                Integer.parseInt(txtDistance.getText()),
                Float.parseFloat(txtPrix.getText()),
                txtImage.getText(),
                Integer.parseInt(txtProprio.getSelectedItem().toString().split("-")[0])
        );


        Controleur.insertAppartement(a);
        unTableau.setDonnees(obtenirDonnees(""));
        vider();
    }

    public void afficherImage(String nomImage) {
        try {
            ImageIcon icon = new ImageIcon("src/images/" + nomImage);
            Image img = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            lblImage.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            lblImage.setIcon(null);
        }
        
        
    }

    

    public void updateAppartement(){

        int i = tableAppart.getSelectedRow();
        int id = Integer.parseInt(unTableau.getValueAt(i,0).toString());

        Appartement a = new Appartement(
                id,
                txtNum.getText(),
                txtType.getText(),
                Float.parseFloat(txtSurface.getText()),
                Integer.parseInt(txtCapacite.getText()),
                txtExpo.getText(),
                Integer.parseInt(txtDistance.getText()),
                Float.parseFloat(txtPrix.getText()),
                txtImage.getText(),
                Integer.parseInt(txtProprio.getSelectedItem().toString().split("-")[0])
        );


        Controleur.updateAppartement(a);
        unTableau.setDonnees(obtenirDonnees(""));
        vider();
    }

    public void deleteAppartement(){

        int i = tableAppart.getSelectedRow();
        int id = Integer.parseInt(unTableau.getValueAt(i,0).toString());

        int r = JOptionPane.showConfirmDialog(this,"Supprimer appartement ?");

        if(r==0){
            Controleur.deleteAppartement(id);
            unTableau.setDonnees(obtenirDonnees(""));
            vider();
        }
    }

	public static void remplirIdProprio() {
	 //remplir le combo box de proprio
		ArrayList<Proprietaire> lesProprios=Controleur .selectAllProprietaires("");
		txtProprio.removeAllItems();
		for(Proprietaire  unProprio :lesProprios) {
			txtProprio.addItem(unProprio.getIdProprio()+"-"+unProprio.getNom());
		}
		
	}
}
