package controleur;

public class Paiement {
    private int id_paiement, id_reser;
    private float montant;
    private String date_paiement, mode_paiement;

    
    public Paiement(int id_paiement, float montant, String date_paiement, String mode_paiement, int id_reser) {
        this.id_paiement = id_paiement;
        this.montant = montant;
        this.date_paiement = date_paiement;
        this.mode_paiement = mode_paiement;
        this.id_reser = id_reser;
    }

    
    public Paiement(float montant, String date_paiement, String mode_paiement, int id_reser) {
        this.id_paiement = 0;
        this.montant = montant;
        this.date_paiement = date_paiement;
        this.mode_paiement = mode_paiement;
        this.id_reser = id_reser;
    }

    public int getId_paiement() { 
    	return id_paiement; 
    	}
    
    public float getMontant() { 
    	return montant; 
    	}
    
    public String getDate_paiement() {
    	return date_paiement; 
    	}
    
    public String getMode_paiement() { 
    	return mode_paiement; 
    	}
    
    public int getId_reser() {
    	return id_reser;
    	}
}