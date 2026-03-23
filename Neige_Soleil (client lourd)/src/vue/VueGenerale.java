package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controleur.Neige_soleil;

public class VueGenerale extends JFrame implements ActionListener {

    private JPanel panelMenu = new JPanel();
    private JPanel panelContenu = new JPanel();

    private JButton btProfil = new JButton("Profil");
    private JButton btProprietaire = new JButton("Propriétaire");
    private JButton btClients = new JButton("Clients");
    private JButton btAppartement = new JButton("Appartements");
    private JButton btReservation = new JButton("Réservation");
    private JButton btMateriel = new JButton("Matériel");
    private JButton btLouerMat = new JButton("Louer Matériel");
    private JButton btDisposer = new JButton("Disposer");
    private JButton btPersonnel = new JButton("Personnel");
    private JButton btQuitter = new JButton("Quitter");

    // Tous les panels
    private PanelAppartements panelAppart = new PanelAppartements("Appartements");
    private PanelProprietaire panelProprio = new PanelProprietaire("Propriétaires");
    private PanelClient panelClient = new PanelClient("Clients");
    private PanelReservation panelReservation = new PanelReservation("Réservations");
    private PanelMateriels panelMateriel = new PanelMateriels("Matériel");
    private PanelLouerMateriel panelLouerMat = new PanelLouerMateriel("Location Matériel");
    private PanelDisposer panelDisposer = new PanelDisposer("Équipements par Appartement");
    private PanelPersonnel panelPersonnel = new PanelPersonnel("Personnel");

    public VueGenerale() {

        this.setTitle("Client Lourd Neige & Soleil");
        this.setBounds(100, 10, 1100, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.getContentPane().setBackground(Color.gray);

        // MENU
        panelMenu.setBounds(40, 20, 1000, 30);
        panelMenu.setBackground(Color.gray);
        panelMenu.setLayout(new GridLayout(1, 10, 5, 5));

        panelMenu.add(btProfil);
        panelMenu.add(btProprietaire);
        panelMenu.add(btClients);
        panelMenu.add(btAppartement);
        panelMenu.add(btReservation);
        panelMenu.add(btMateriel);
        panelMenu.add(btLouerMat);
        panelMenu.add(btDisposer);
        panelMenu.add(btPersonnel);
        panelMenu.add(btQuitter);

        this.add(panelMenu);

        // PANEL CONTENU
        panelContenu.setBounds(10, 60, 960, 500);
        panelContenu.setLayout(null);
        panelContenu.setBackground(Color.gray);
        this.add(panelContenu);

        // Listeners
        btProfil.addActionListener(this);
        btProprietaire.addActionListener(this);
        btClients.addActionListener(this);
        btAppartement.addActionListener(this);
        btReservation.addActionListener(this);
        btMateriel.addActionListener(this);
        btLouerMat.addActionListener(this);
        btDisposer.addActionListener(this);
        btPersonnel.addActionListener(this);
        btQuitter.addActionListener(this);

        this.setVisible(true);
    }

    // Méthode pour afficher un panel
    public void afficherPanel(JPanel panel) {
        panelContenu.removeAll();
        panelContenu.add(panel);
        panel.setVisible(true);
        panelContenu.repaint();
        panelContenu.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btQuitter) {
            Neige_soleil.rendreVisibleVueConnexion(true);
            Neige_soleil.creerDetruireVueGenerale(false);
        }
        else if (e.getSource() == btAppartement) {
            afficherPanel(panelAppart);
        }
        else if (e.getSource() == btProprietaire) {
            afficherPanel(panelProprio);
        }
        else if (e.getSource() == btClients) {
            afficherPanel(panelClient);
        }
        else if (e.getSource() == btReservation) {
            afficherPanel(panelReservation);
        }
        else if (e.getSource() == btMateriel) {
            afficherPanel(panelMateriel);
        }
        else if (e.getSource() == btLouerMat) {
            afficherPanel(panelLouerMat);
        }
        else if (e.getSource() == btDisposer) {
            afficherPanel(panelDisposer);
        }
        else if (e.getSource() == btPersonnel) {
            afficherPanel(panelPersonnel);
        }
    }
}