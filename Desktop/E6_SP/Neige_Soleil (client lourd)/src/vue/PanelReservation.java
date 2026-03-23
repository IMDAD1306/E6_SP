package vue;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import controleur.Client;
import controleur.Appartement;
import controleur.Personnel;
import controleur.Reservation;
import controleur.Controleur;
import controleur.Tableau;

public class PanelReservation extends PanelPrincipal implements ActionListener {

    private JPanel panelForm = new JPanel();

    private JTextField txtDateDebut = new JTextField();
    private JTextField txtDateFin = new JTextField();
    private JTextField txtNbPers = new JTextField();

    private JComboBox<String> cbClient = new JComboBox<>();
    private JComboBox<String> cbAppart = new JComboBox<>();
    private JComboBox<String> cbEmploye = new JComboBox<>();

    private JButton btAnnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Valider");
    private JButton btSupprimer = new JButton("Supprimer");
    private JButton btModifier = new JButton("Modifier");

    private JPanel panelFiltre = new JPanel();
    private JTextField txtFiltre = new JTextField();
    private JButton btFiltrer = new JButton("Filtrer");

    private JTable tableReservations;
    private JScrollPane scrollReservations;
    private Tableau unTableau;

    public PanelReservation(String titre) {
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

        panelForm.add(new JLabel("Nb personnes :"));
        panelForm.add(txtNbPers);

        panelForm.add(new JLabel("Client :"));
        panelForm.add(cbClient);

        panelForm.add(new JLabel("Appartement :"));
        panelForm.add(cbAppart);

        panelForm.add(new JLabel("Employé :"));
        panelForm.add(cbEmploye);

        panelForm.add(btAnnuler);
        panelForm.add(btValider);
        panelForm.add(btSupprimer);
        panelForm.add(btModifier);

        btSupprimer.setEnabled(true);
        btModifier.setEnabled(true);

        add(panelForm);

        // Listeners
        btAnnuler.addActionListener(this);
        btValider.addActionListener(this);
        btModifier.addActionListener(this);
        btSupprimer.addActionListener(this);
        btFiltrer.addActionListener(this);
        txtFiltre.addActionListener(this);

        // Remplir les combos
        remplirClients();
        remplirApparts();
        remplirEmployes();

        // Table
        String entetes[] = {"ID", "Début", "Fin", "Nb Pers", "Client", "Appartement", "Employé"};
        unTableau = new Tableau(obtenirDonnees(""), entetes);
        tableReservations = new JTable(unTableau);

        scrollReservations = new JScrollPane(tableReservations);
        scrollReservations.setBounds(450, 120, 450, 260);
        add(scrollReservations);

        // MouseListener
        tableReservations.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = tableReservations.getSelectedRow();

                txtDateDebut.setText(unTableau.getValueAt(i, 1).toString());
                txtDateFin.setText(unTableau.getValueAt(i, 2).toString());
                txtNbPers.setText(unTableau.getValueAt(i, 3).toString());

                cbClient.setSelectedItem(unTableau.getValueAt(i, 4).toString());
                cbAppart.setSelectedItem(unTableau.getValueAt(i, 5).toString());
                cbEmploye.setSelectedItem(unTableau.getValueAt(i, 6).toString());

                btModifier.setEnabled(true);
                btSupprimer.setEnabled(true);
            }
        });
    }

    // Remplissage des combos
    public void remplirClients() {
        ArrayList<Client> lesClients = Controleur.selectAllClients("");
        cbClient.removeAllItems();
        for (Client c : lesClients) {
            cbClient.addItem(c.getIdclient() + "-" + c.getNom());
        }
    }

    public void remplirApparts() {
        ArrayList<Appartement> lesApparts = Controleur.selectAllAppartements("");
        cbAppart.removeAllItems();
        for (Appartement a : lesApparts) {
            cbAppart.addItem(a.getId_appart() + "-" + a.getNum_appart());
        }
    }

    public void remplirEmployes() {
        ArrayList<Personnel> lesEmployes = Controleur.selectAllPersonnel("");
        cbEmploye.removeAllItems();
        for (Personnel p : lesEmployes) {
            cbEmploye.addItem(p.getId_employe() + "-" + p.getNom());
        }
    }

    // Données pour la JTable
    public Object[][] obtenirDonnees(String filtre) {
        ArrayList<Reservation> lesReservations = Controleur.selectAllReservations(filtre);
        Object[][] mat = new Object[lesReservations.size()][7];

        int i = 0;
        for (Reservation r : lesReservations) {
            mat[i][0] = r.getId_reser();
            mat[i][1] = r.getDate_debut_loc();
            mat[i][2] = r.getDate_fin_loc();
            mat[i][3] = r.getNb_personnes();
            mat[i][4] = r.getId_client();
            mat[i][5] = r.getId_appart();
            mat[i][6] = r.getId_employe();
            i++;
        }
        return mat;
    }

    // Actions
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btAnnuler) {
            vider();
        }

        else if (e.getSource() == btValider) {
            insertReservation();
        }

        else if (e.getSource() == btModifier) {
            updateReservation();
        }

        else if (e.getSource() == btSupprimer) {
            deleteReservation();
        }

        else if (e.getSource() == btFiltrer || e.getSource() == txtFiltre) {
            unTableau.setDonnees(obtenirDonnees(txtFiltre.getText()));
        }
    }

    public void vider() {
        txtDateDebut.setText("");
        txtDateFin.setText("");
        txtNbPers.setText("");
        cbClient.setSelectedIndex(0);
        cbAppart.setSelectedIndex(0);
        cbEmploye.setSelectedIndex(0);

        btModifier.setEnabled(false);
        btSupprimer.setEnabled(false);
    }

    public void insertReservation() {

        String dd = txtDateDebut.getText();
        String df = txtDateFin.getText();
        int nb = Integer.parseInt(txtNbPers.getText());

        int id_client = Integer.parseInt(cbClient.getSelectedItem().toString().split("-")[0]);
        int id_appart = Integer.parseInt(cbAppart.getSelectedItem().toString().split("-")[0]);
        int id_employe = Integer.parseInt(cbEmploye.getSelectedItem().toString().split("-")[0]);

        Reservation r = new Reservation(dd, df, nb, id_client, id_appart, id_employe);

        Controleur.insertReservation(r);
        unTableau.setDonnees(obtenirDonnees(""));
        vider();
    }

    public void updateReservation() {

        int i = tableReservations.getSelectedRow();
        int id = Integer.parseInt(unTableau.getValueAt(i, 0).toString());

        String dd = txtDateDebut.getText();
        String df = txtDateFin.getText();
        int nb = Integer.parseInt(txtNbPers.getText());

        int id_client = Integer.parseInt(cbClient.getSelectedItem().toString().split("-")[0]);
        int id_appart = Integer.parseInt(cbAppart.getSelectedItem().toString().split("-")[0]);
        int id_employe = Integer.parseInt(cbEmploye.getSelectedItem().toString().split("-")[0]);

        Reservation r = new Reservation(id, dd, df, nb, id_client, id_appart, id_employe);

        Controleur.updateReservation(r);
        unTableau.setDonnees(obtenirDonnees(""));
        vider();
    }

    public void deleteReservation() {

        int i = tableReservations.getSelectedRow();
        int id = Integer.parseInt(unTableau.getValueAt(i, 0).toString());

        int r = JOptionPane.showConfirmDialog(this, "Supprimer réservation ?");

        if (r == 0) {
            Controleur.deleteReservation(id);
            unTableau.setDonnees(obtenirDonnees(""));
            vider();
        }
    }
}