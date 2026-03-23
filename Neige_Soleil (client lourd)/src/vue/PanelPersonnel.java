package vue;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import controleur.Personnel;
import controleur.Controleur;
import controleur.Tableau;

public class PanelPersonnel extends PanelPrincipal implements ActionListener {

    private JPanel panelForm = new JPanel();

    private JTextField txtNom = new JTextField();
    private JTextField txtPrenom = new JTextField();
    private JTextField txtEmail = new JTextField();
    private JTextField txtTel = new JTextField();
    private JTextField txtRole = new JTextField();

    private JButton btAnnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Valider");
    private JButton btSupprimer = new JButton("Supprimer");
    private JButton btModifier = new JButton("Modifier");

    private JPanel panelFiltre = new JPanel();
    private JTextField txtFiltre = new JTextField();
    private JButton btFiltrer = new JButton("Filtrer");

    private JTable tablePersonnel;
    private JScrollPane scrollPersonnel;
    private Tableau unTableau;

    public PanelPersonnel(String titre) {
        super(titre);

        // Panel filtre
        panelFiltre.setBounds(450, 80, 460, 30);
        panelFiltre.setBackground(Color.gray);
        panelFiltre.setLayout(new GridLayout(1, 3, 10, 10));
        panelFiltre.add(new JLabel("Filtrer : "));
        panelFiltre.add(txtFiltre);
        panelFiltre.add(btFiltrer);
        add(panelFiltre);

        // Panel formulaire
        panelForm.setBounds(50,100,300,350);
        panelForm.setBackground(Color.gray);
        panelForm.setLayout(new GridLayout(9,2,10,10));

        panelForm.add(new JLabel("Nom :"));
        panelForm.add(txtNom);

        panelForm.add(new JLabel("Prénom :"));
        panelForm.add(txtPrenom);

        panelForm.add(new JLabel("Email :"));
        panelForm.add(txtEmail);

        panelForm.add(new JLabel("Téléphone :"));
        panelForm.add(txtTel);

        panelForm.add(new JLabel("Rôle :"));
        panelForm.add(txtRole);

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

        // Table
        String entetes[] = {"ID", "Nom", "Prénom", "Email", "Téléphone", "Rôle"};
        unTableau = new Tableau(obtenirDonnees(""), entetes);
        tablePersonnel = new JTable(unTableau);

        scrollPersonnel = new JScrollPane(tablePersonnel);
        scrollPersonnel.setBounds(450, 120, 450, 260);
        add(scrollPersonnel);

        tablePersonnel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = tablePersonnel.getSelectedRow();

                txtNom.setText(unTableau.getValueAt(i, 1).toString());
                txtPrenom.setText(unTableau.getValueAt(i, 2).toString());
                txtEmail.setText(unTableau.getValueAt(i, 3).toString());
                txtTel.setText(unTableau.getValueAt(i, 4).toString());
                txtRole.setText(unTableau.getValueAt(i, 5).toString());

                btModifier.setEnabled(true);
                btSupprimer.setEnabled(true);
            }
        });
    }

    public Object[][] obtenirDonnees(String filtre) {
        ArrayList<Personnel> lesEmployes = Controleur.selectAllPersonnel(filtre);
        Object[][] mat = new Object[lesEmployes.size()][6];

        int i = 0;
        for (Personnel p : lesEmployes) {
            mat[i][0] = p.getId_employe();
            mat[i][1] = p.getNom();
            mat[i][2] = p.getPrenom();
            mat[i][3] = p.getEmail();
            mat[i][4] = p.getTel();
            mat[i][5] = p.getRole();
            i++;
        }
        return mat;
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btAnnuler) {
            vider();
        }

        else if (e.getSource() == btValider) {
            insertPersonnel();
        }

        else if (e.getSource() == btModifier) {
            updatePersonnel();
        }

        else if (e.getSource() == btSupprimer) {
            deletePersonnel();
        }

        else if (e.getSource() == btFiltrer || e.getSource() == txtFiltre) {
            unTableau.setDonnees(obtenirDonnees(txtFiltre.getText()));
        }
    }

    public void vider() {
        txtNom.setText("");
        txtPrenom.setText("");
        txtEmail.setText("");
        txtTel.setText("");
        txtRole.setText("");

        btModifier.setEnabled(false);
        btSupprimer.setEnabled(false);
    }

    public void insertPersonnel() {

        String nom = txtNom.getText();
        String prenom = txtPrenom.getText();
        String email = txtEmail.getText();
        String tel = txtTel.getText();
        String role = txtRole.getText();

        if (nom.equals("") || prenom.equals("") || email.equals("") || tel.equals("") || role.equals("")) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs");
            return;
        }

        Personnel p = new Personnel(nom, prenom, email, tel, role);

        Controleur.insertPersonnel(p);
        unTableau.setDonnees(obtenirDonnees(""));
        vider();
    }

    public void updatePersonnel() {

        int i = tablePersonnel.getSelectedRow();
        int id = Integer.parseInt(unTableau.getValueAt(i, 0).toString());

        String nom = txtNom.getText();
        String prenom = txtPrenom.getText();
        String email = txtEmail.getText();
        String tel = txtTel.getText();
        String role = txtRole.getText();

        Personnel p = new Personnel(id, nom, prenom, email, tel, role);

        Controleur.updatePersonnel(p);
        unTableau.setDonnees(obtenirDonnees(""));
        vider();
    }

    public void deletePersonnel() {

        int i = tablePersonnel.getSelectedRow();
        int id = Integer.parseInt(unTableau.getValueAt(i, 0).toString());

        int r = JOptionPane.showConfirmDialog(this, "Supprimer employé ?");

        if (r == 0) {
            Controleur.deletePersonnel(id);
            unTableau.setDonnees(obtenirDonnees(""));
            vider();
        }
    }
}
