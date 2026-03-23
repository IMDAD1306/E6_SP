package vue;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import controleur.Paiement;
import controleur.Reservation;
import controleur.Controleur;
import controleur.Tableau;

public class PanelPaiement extends PanelPrincipal implements ActionListener {

    private JPanel panelForm = new JPanel();

    private JTextField txtMontant = new JTextField();
    private JTextField txtDate = new JTextField();
    private JTextField txtMode = new JTextField();

    private JComboBox<String> cbReservation = new JComboBox<>();

    private JButton btAnnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Valider");
    private JButton btSupprimer = new JButton("Supprimer");
    private JButton btModifier = new JButton("Modifier");

    private JPanel panelFiltre = new JPanel();
    private JTextField txtFiltre = new JTextField();
    private JButton btFiltrer = new JButton("Filtrer");

    private JTable tablePaiements;
    private JScrollPane scrollPaiements;
    private Tableau unTableau;

    public PanelPaiement(String titre) {
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

        panelForm.add(new JLabel("Montant :"));
        panelForm.add(txtMontant);

        panelForm.add(new JLabel("Date paiement (AAAA-MM-JJ) :"));
        panelForm.add(txtDate);

        panelForm.add(new JLabel("Mode paiement :"));
        panelForm.add(txtMode);

        panelForm.add(new JLabel("Réservation :"));
        panelForm.add(cbReservation);

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

        // Remplir comboBox réservations
        remplirReservations();

        // Table
        String entetes[] = {"ID", "Montant", "Date", "Mode", "Réservation"};
        unTableau = new Tableau(obtenirDonnees(""), entetes);
        tablePaiements = new JTable(unTableau);

        scrollPaiements = new JScrollPane(tablePaiements);
        scrollPaiements.setBounds(450, 120, 450, 260);
        add(scrollPaiements);

        tablePaiements.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = tablePaiements.getSelectedRow();

                txtMontant.setText(unTableau.getValueAt(i, 1).toString());
                txtDate.setText(unTableau.getValueAt(i, 2).toString());
                txtMode.setText(unTableau.getValueAt(i, 3).toString());

                cbReservation.setSelectedItem(unTableau.getValueAt(i, 4).toString());

                btModifier.setEnabled(true);
                btSupprimer.setEnabled(true);
            }
        });
    }

    public void remplirReservations() {
        ArrayList<Reservation> lesReservations = Controleur.selectAllReservations("");
        cbReservation.removeAllItems();
        for (Reservation r : lesReservations) {
            cbReservation.addItem(r.getId_reser() + "");
        }
    }

    public Object[][] obtenirDonnees(String filtre) {
        ArrayList<Paiement> lesPaiements = Controleur.selectAllPaiements(filtre);
        Object[][] mat = new Object[lesPaiements.size()][5];

        int i = 0;
        for (Paiement p : lesPaiements) {
            mat[i][0] = p.getId_paiement();
            mat[i][1] = p.getMontant();
            mat[i][2] = p.getDate_paiement();
            mat[i][3] = p.getMode_paiement();
            mat[i][4] = p.getId_reser();
            i++;
        }
        return mat;
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btAnnuler) {
            vider();
        }

        else if (e.getSource() == btValider) {
            insertPaiement();
        }

        else if (e.getSource() == btModifier) {
            updatePaiement();
        }

        else if (e.getSource() == btSupprimer) {
            deletePaiement();
        }

        else if (e.getSource() == btFiltrer || e.getSource() == txtFiltre) {
            unTableau.setDonnees(obtenirDonnees(txtFiltre.getText()));
        }
    }

    public void vider() {
        txtMontant.setText("");
        txtDate.setText("");
        txtMode.setText("");
        cbReservation.setSelectedIndex(0);

        btModifier.setEnabled(false);
        btSupprimer.setEnabled(false);
    }

    public void insertPaiement() {

        float montant = Float.parseFloat(txtMontant.getText());
        String date = txtDate.getText();
        String mode = txtMode.getText();
        int id_reser = Integer.parseInt(cbReservation.getSelectedItem().toString());

        Paiement p = new Paiement(montant, date, mode, id_reser);

        Controleur.insertPaiement(p);
        unTableau.setDonnees(obtenirDonnees(""));
        vider();
    }

    public void updatePaiement() {

        int i = tablePaiements.getSelectedRow();
        int id = Integer.parseInt(unTableau.getValueAt(i, 0).toString());

        float montant = Float.parseFloat(txtMontant.getText());
        String date = txtDate.getText();
        String mode = txtMode.getText();
        int id_reser = Integer.parseInt(cbReservation.getSelectedItem().toString());

        Paiement p = new Paiement(id, montant, date, mode, id_reser);

        Controleur.updatePaiement(p);
        unTableau.setDonnees(obtenirDonnees(""));
        vider();
    }

    public void deletePaiement() {

        int i = tablePaiements.getSelectedRow();
        int id = Integer.parseInt(unTableau.getValueAt(i, 0).toString());

        int r = JOptionPane.showConfirmDialog(this, "Supprimer paiement ?");

        if (r == 0) {
            Controleur.deletePaiement(id);
            unTableau.setDonnees(obtenirDonnees(""));
            vider();
        }
    }
}