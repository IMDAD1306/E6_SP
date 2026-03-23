package vue;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import controleur.Equipement;
import controleur.Controleur;
import controleur.Tableau;

public class PanelEquipement extends PanelPrincipal implements ActionListener {

    private JPanel panelForm = new JPanel();

    private JTextField txtLibelle = new JTextField();

    private JButton btAnnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Valider");
    private JButton btSupprimer = new JButton("Supprimer");
    private JButton btModifier = new JButton("Modifier");

    private JPanel panelFiltre = new JPanel();
    private JTextField txtFiltre = new JTextField();
    private JButton btFiltrer = new JButton("Filtrer");

    private JTable tableEquipements;
    private JScrollPane scrollEquipements;
    private Tableau unTableau;

    public PanelEquipement(String titre) {
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

        panelForm.add(new JLabel("Libellé équipement :"));
        panelForm.add(txtLibelle);

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
        String entetes[] = {"ID", "Libellé"};
        unTableau = new Tableau(obtenirDonnees(""), entetes);
        tableEquipements = new JTable(unTableau);

        scrollEquipements = new JScrollPane(tableEquipements);
        scrollEquipements.setBounds(450, 120, 450, 260);
        add(scrollEquipements);

        tableEquipements.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = tableEquipements.getSelectedRow();

                txtLibelle.setText(unTableau.getValueAt(i, 1).toString());

                btModifier.setEnabled(true);
                btSupprimer.setEnabled(true);
            }
        });
    }

    public Object[][] obtenirDonnees(String filtre) {
        ArrayList<Equipement> lesEquipements = Controleur.selectAllEquipements(filtre);
        Object[][] mat = new Object[lesEquipements.size()][2];

        int i = 0;
        for (Equipement e : lesEquipements) {
            mat[i][0] = e.getId_equip();
            mat[i][1] = e.getLibelle_equip();
            i++;
        }
        return mat;
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btAnnuler) {
            vider();
        }

        else if (e.getSource() == btValider) {
            insertEquipement();
        }

        else if (e.getSource() == btModifier) {
            updateEquipement();
        }

        else if (e.getSource() == btSupprimer) {
            deleteEquipement();
        }

        else if (e.getSource() == btFiltrer || e.getSource() == txtFiltre) {
            unTableau.setDonnees(obtenirDonnees(txtFiltre.getText()));
        }
    }

    public void vider() {
        txtLibelle.setText("");

        btModifier.setEnabled(false);
        btSupprimer.setEnabled(false);
    }

    public void insertEquipement() {

        String libelle = txtLibelle.getText();

        if (libelle.equals("")) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir le libellé");
            return;
        }

        Equipement e = new Equipement(libelle);

        Controleur.insertEquipement(e);
        unTableau.setDonnees(obtenirDonnees(""));
        vider();
    }

    public void updateEquipement() {

        int i = tableEquipements.getSelectedRow();
        int id = Integer.parseInt(unTableau.getValueAt(i, 0).toString());

        String libelle = txtLibelle.getText();

        Equipement e = new Equipement(id, libelle);

        Controleur.updateEquipement(e);
        unTableau.setDonnees(obtenirDonnees(""));
        vider();
    }

    public void deleteEquipement() {

        int i = tableEquipements.getSelectedRow();
        int id = Integer.parseInt(unTableau.getValueAt(i, 0).toString());

        int r = JOptionPane.showConfirmDialog(this, "Supprimer équipement ?");

        if (r == 0) {
            Controleur.deleteEquipement(id);
            unTableau.setDonnees(obtenirDonnees(""));
            vider();
        }
    }
}