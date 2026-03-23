package vue;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import controleur.Appartement;
import controleur.Equipement;
import controleur.Disposer;
import controleur.Controleur;
import controleur.Tableau;

public class PanelDisposer extends PanelPrincipal implements ActionListener {

    private JPanel panelForm = new JPanel();

    private JComboBox<String> cbAppart = new JComboBox<>();
    private JComboBox<String> cbEquip = new JComboBox<>();

    private JButton btAnnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Associer");
    private JButton btModifier = new JButton("Modifier");
    private JButton btSupprimer = new JButton("Supprimer");

    private JTable tableDisposer;
    private JScrollPane scrollDisposer;
    private Tableau unTableau;

    public PanelDisposer(String titre) {
        super(titre);

        // Panel formulaire
        panelForm.setBounds(50,100,300,350);
        panelForm.setBackground(Color.gray);
        panelForm.setLayout(new GridLayout(9,2,10,10));

        panelForm.add(new JLabel("Appartement :"));
        panelForm.add(cbAppart);

        panelForm.add(new JLabel("Équipement :"));
        panelForm.add(cbEquip);

        panelForm.add(btAnnuler);
        panelForm.add(btModifier);
        panelForm.add(btValider);
        panelForm.add(btSupprimer);

        btSupprimer.setEnabled(true);
        btModifier.setEnabled(true);

        add(panelForm);

        btAnnuler.addActionListener(this);
        btValider.addActionListener(this);
        btSupprimer.addActionListener(this);

        // Remplir comboBox
        remplirAppart();
        remplirEquip();

        // Table
        String entetes[] = {"Appartement", "Équipement"};
        unTableau = new Tableau(obtenirDonnees(""), entetes);
        tableDisposer = new JTable(unTableau);

        scrollDisposer = new JScrollPane(tableDisposer);
        scrollDisposer.setBounds(450, 120, 450, 260);
        add(scrollDisposer);

        tableDisposer.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = tableDisposer.getSelectedRow();

                cbAppart.setSelectedItem(unTableau.getValueAt(i, 0).toString());
                cbEquip.setSelectedItem(unTableau.getValueAt(i, 1).toString());

                btSupprimer.setEnabled(true);
            }
        });
    }

    public void remplirAppart() {
        ArrayList<Appartement> lesApparts = Controleur.selectAllAppartements("");
        cbAppart.removeAllItems();
        for (Appartement a : lesApparts) {
            cbAppart.addItem(a.getId_appart() + "-" + a.getNum_appart());
        }
    }

    public void remplirEquip() {
        ArrayList<Equipement> lesEquip = Controleur.selectAllEquipements("");
        cbEquip.removeAllItems();
        for (Equipement e : lesEquip) {
            cbEquip.addItem(e.getId_equip() + "-" + e.getLibelle_equip());
        }
    }

    public Object[][] obtenirDonnees(String filtre) {
        ArrayList<Disposer> lesRelations = Controleur.selectAllDisposer(filtre);
        Object[][] mat = new Object[lesRelations.size()][2];

        int i = 0;
        for (Disposer d : lesRelations) {
            mat[i][0] = d.getId_appart();
            mat[i][1] = d.getId_equip();
            i++;
        }
        return mat;
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btAnnuler) {
            cbAppart.setSelectedIndex(0);
            cbEquip.setSelectedIndex(0);
            btSupprimer.setEnabled(false);
        }

        else if (e.getSource() == btValider) {
            insertDisposer();
        }

        else if (e.getSource() == btSupprimer) {
            deleteDisposer();
        }
    }

    public void insertDisposer() {

        int id_appart = Integer.parseInt(cbAppart.getSelectedItem().toString().split("-")[0]);
        int id_equip = Integer.parseInt(cbEquip.getSelectedItem().toString().split("-")[0]);

        Disposer d = new Disposer(id_appart, id_equip);

        Controleur.insertDisposer(d);
        unTableau.setDonnees(obtenirDonnees(""));
    }

    public void deleteDisposer() {

        int id_appart = Integer.parseInt(cbAppart.getSelectedItem().toString().split("-")[0]);
        int id_equip = Integer.parseInt(cbEquip.getSelectedItem().toString().split("-")[0]);

        int r = JOptionPane.showConfirmDialog(this, "Supprimer cette association ?");

        if (r == 0) {
            Controleur.deleteDisposer(id_appart, id_equip);
            unTableau.setDonnees(obtenirDonnees(""));
        }
    }
}
