package vue;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import controleur.Appartement;
import controleur.Contrat;
import controleur.Controleur;
import controleur.Tableau;

public class PanelContrat extends PanelPrincipal implements ActionListener {

    private JPanel panelForm = new JPanel();

    private JTextField txtDateDebut = new JTextField();
    private JTextField txtDateFin = new JTextField();
    private JTextField txtTarif = new JTextField();
    private JCheckBox cbArchive = new JCheckBox("Archivé");

    private JComboBox<String> cbAppart = new JComboBox<>();

    private JButton btAnnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Valider");
    private JButton btSupprimer = new JButton("Supprimer");
    private JButton btModifier = new JButton("Modifier");

    private JPanel panelFiltre = new JPanel();
    private JTextField txtFiltre = new JTextField();
    private JButton btFiltrer = new JButton("Filtrer");

    private JTable tableContrats;
    private JScrollPane scrollContrats;
    private Tableau unTableau;

    public PanelContrat(String titre) {
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

        panelForm.add(new JLabel("Tarif saison :"));
        panelForm.add(txtTarif);

        panelForm.add(new JLabel("Appartement :"));
        panelForm.add(cbAppart);

        panelForm.add(new JLabel("Archivé :"));
        panelForm.add(cbArchive);

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

        // Remplir comboBox appartements
        remplirAppart();

        // Table
        String entetes[] = {"ID", "Début", "Fin", "Tarif", "Archivé", "Appartement"};
        unTableau = new Tableau(obtenirDonnees(""), entetes);
        tableContrats = new JTable(unTableau);

        scrollContrats = new JScrollPane(tableContrats);
        scrollContrats.setBounds(450, 120, 450, 260);
        add(scrollContrats);

        tableContrats.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = tableContrats.getSelectedRow();

                txtDateDebut.setText(unTableau.getValueAt(i, 1).toString());
                txtDateFin.setText(unTableau.getValueAt(i, 2).toString());
                txtTarif.setText(unTableau.getValueAt(i, 3).toString());
                cbArchive.setSelected(unTableau.getValueAt(i, 4).toString().equals("true"));

                cbAppart.setSelectedItem(unTableau.getValueAt(i, 5).toString());

                btModifier.setEnabled(true);
                btSupprimer.setEnabled(true);
            }
        });
    }

    public  void remplirAppart() {
        ArrayList<Appartement> lesApparts = Controleur.selectAllAppartements("");
        cbAppart.removeAllItems();
        for (Appartement a : lesApparts) {
            cbAppart.addItem(a.getId_appart() + "-" + a.getNum_appart());
        }
    }

    public Object[][] obtenirDonnees(String filtre) {
        ArrayList<Contrat> lesContrats = Controleur.selectAllContrats(filtre);
        Object[][] mat = new Object[lesContrats.size()][6];

        int i = 0;
        for (Contrat c : lesContrats) {
            mat[i][0] = c.getId_contrat();
            mat[i][1] = c.getDate_debut();
            mat[i][2] = c.getDate_fin();
            mat[i][3] = c.getTarif_saison();
            mat[i][4] = c.getStatut_archive();
            mat[i][5] = c.getId_appart();
            i++;
        }
        return mat;
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btAnnuler) {
            vider();
        }

        else if (e.getSource() == btValider) {
            insertContrat();
        }

        else if (e.getSource() == btModifier) {
            updateContrat();
        }

        else if (e.getSource() == btSupprimer) {
            deleteContrat();
        }

        else if (e.getSource() == btFiltrer || e.getSource() == txtFiltre) {
            unTableau.setDonnees(obtenirDonnees(txtFiltre.getText()));
        }
    }

    public void vider() {
        txtDateDebut.setText("");
        txtDateFin.setText("");
        txtTarif.setText("");
        cbArchive.setSelected(false);
        cbAppart.setSelectedIndex(0);

        btModifier.setEnabled(false);
        btSupprimer.setEnabled(false);
    }

    public void insertContrat() {

        String dd = txtDateDebut.getText();
        String df = txtDateFin.getText();
        float tarif = Float.parseFloat(txtTarif.getText());
        boolean archive = cbArchive.isSelected();
        int id_appart = Integer.parseInt(cbAppart.getSelectedItem().toString().split("-")[0]);

        Contrat c = new Contrat(dd, df, tarif, archive, id_appart);

        Controleur.insertContrat(c);
        unTableau.setDonnees(obtenirDonnees(""));
        vider();
    }

    public void updateContrat() {

        int i = tableContrats.getSelectedRow();
        int id = Integer.parseInt(unTableau.getValueAt(i, 0).toString());

        String dd = txtDateDebut.getText();
        String df = txtDateFin.getText();
        float tarif = Float.parseFloat(txtTarif.getText());
        boolean archive = cbArchive.isSelected();
        int id_appart = Integer.parseInt(cbAppart.getSelectedItem().toString().split("-")[0]);

        Contrat c = new Contrat(id, dd, df, tarif, archive, id_appart);

        Controleur.updateContrat(c);
        unTableau.setDonnees(obtenirDonnees(""));
        vider();
    }

    public void deleteContrat() {

        int i = tableContrats.getSelectedRow();
        int id = Integer.parseInt(unTableau.getValueAt(i, 0).toString());

        int r = JOptionPane.showConfirmDialog(this, "Supprimer contrat ?");

        if (r == 0) {
            Controleur.deleteContrat(id);
            unTableau.setDonnees(obtenirDonnees(""));
            vider();
        }
    }
}