package vue;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import controleur.Client;
import controleur.Materiel;
import controleur.LouerMateriel;
import controleur.Controleur;
import controleur.Tableau;

public class PanelLouerMateriel extends PanelPrincipal implements ActionListener {

    private JPanel panelForm = new JPanel();

    private JTextField txtDateDebut = new JTextField();
    private JTextField txtDateFin = new JTextField();

    private JComboBox<String> cbClient = new JComboBox<>();
    private JComboBox<String> cbMateriel = new JComboBox<>();

    private JButton btAnnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Valider");
    private JButton btSupprimer = new JButton("Supprimer");
    private JButton btModifier = new JButton("Modifier");

    private JPanel panelFiltre = new JPanel();
    private JTextField txtFiltre = new JTextField();
    private JButton btFiltrer = new JButton("Filtrer");

    private JTable tableLocations;
    private JScrollPane scrollLocations;
    private Tableau unTableau;

    public PanelLouerMateriel(String titre) {
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

        panelForm.add(new JLabel("Date début (AAAA-MM-JJ) :"));
        panelForm.add(txtDateDebut);

        panelForm.add(new JLabel("Date fin (AAAA-MM-JJ) :"));
        panelForm.add(txtDateFin);

        panelForm.add(new JLabel("Client :"));
        panelForm.add(cbClient);

        panelForm.add(new JLabel("Matériel :"));
        panelForm.add(cbMateriel);

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

        // Remplir comboBox
        remplirClients();
        remplirMateriels();

        // Table
        String entetes[] = {"ID", "Début", "Fin", "Client", "Matériel"};
        unTableau = new Tableau(obtenirDonnees(""), entetes);
        tableLocations = new JTable(unTableau);

        scrollLocations = new JScrollPane(tableLocations);
        scrollLocations.setBounds(450, 120, 450, 260);
        add(scrollLocations);

        tableLocations.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = tableLocations.getSelectedRow();

                txtDateDebut.setText(unTableau.getValueAt(i, 1).toString());
                txtDateFin.setText(unTableau.getValueAt(i, 2).toString());

                cbClient.setSelectedItem(unTableau.getValueAt(i, 3).toString());
                cbMateriel.setSelectedItem(unTableau.getValueAt(i, 4).toString());

                btModifier.setEnabled(true);
                btSupprimer.setEnabled(true);
            }
        });
    }

    public void remplirClients() {
        ArrayList<Client> lesClients = Controleur.selectAllClients("");
        cbClient.removeAllItems();
        for (Client c : lesClients) {
            cbClient.addItem(c.getIdclient() + "-" + c.getNom());
        }
    }

    public void remplirMateriels() {
        ArrayList<Materiel> lesMateriels = Controleur.selectAllMateriels("");
        cbMateriel.removeAllItems();
        for (Materiel m : lesMateriels) {
            cbMateriel.addItem(m.getId_mat() + "-" + m.getLibelle_mat());
        }
    }

    public Object[][] obtenirDonnees(String filtre) {
        ArrayList<LouerMateriel> lesLocations = Controleur.selectAllLouerMateriel(filtre);
        Object[][] mat = new Object[lesLocations.size()][5];

        int i = 0;
        for (LouerMateriel lm : lesLocations) {
            mat[i][0] = lm.getId_loc_mat();
            mat[i][1] = lm.getDate_debut();
            mat[i][2] = lm.getDate_fin();
            mat[i][3] = lm.getId_client();
            mat[i][4] = lm.getId_mat();
            i++;
        }
        return mat;
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btAnnuler) {
            vider();
        }

        else if (e.getSource() == btValider) {
            insertLouerMateriel();
        }

        else if (e.getSource() == btModifier) {
            updateLouerMateriel();
        }

        else if (e.getSource() == btSupprimer) {
            deleteLouerMateriel();
        }

        else if (e.getSource() == btFiltrer || e.getSource() == txtFiltre) {
            unTableau.setDonnees(obtenirDonnees(txtFiltre.getText()));
        }
    }

    public void vider() {
        txtDateDebut.setText("");
        txtDateFin.setText("");
        cbClient.setSelectedIndex(0);
        cbMateriel.setSelectedIndex(0);

        btModifier.setEnabled(false);
        btSupprimer.setEnabled(false);
    }

    public void insertLouerMateriel() {

        String dd = txtDateDebut.getText();
        String df = txtDateFin.getText();

        int id_client = Integer.parseInt(cbClient.getSelectedItem().toString().split("-")[0]);
        int id_mat = Integer.parseInt(cbMateriel.getSelectedItem().toString().split("-")[0]);

        LouerMateriel lm = new LouerMateriel(dd, df, id_client, id_mat);

        Controleur.insertLouerMateriel(lm);
        unTableau.setDonnees(obtenirDonnees(""));
        vider();
    }

    public void updateLouerMateriel() {

        int i = tableLocations.getSelectedRow();
        int id = Integer.parseInt(unTableau.getValueAt(i, 0).toString());

        String dd = txtDateDebut.getText();
        String df = txtDateFin.getText();

        int id_client = Integer.parseInt(cbClient.getSelectedItem().toString().split("-")[0]);
        int id_mat = Integer.parseInt(cbMateriel.getSelectedItem().toString().split("-")[0]);

        LouerMateriel lm = new LouerMateriel(id, dd, df, id_client, id_mat);

        Controleur.updateLouerMateriel(lm);
        unTableau.setDonnees(obtenirDonnees(""));
        vider();
    }

    public void deleteLouerMateriel() {

        int i = tableLocations.getSelectedRow();
        int id = Integer.parseInt(unTableau.getValueAt(i, 0).toString());

        int r = JOptionPane.showConfirmDialog(this, "Supprimer location matériel ?");

        if (r == 0) {
            Controleur.deleteLouerMateriel(id);
            unTableau.setDonnees(obtenirDonnees(""));
            vider();
        }
    }
}