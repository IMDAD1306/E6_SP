package controleur;

public class Contrat {
    private int id_contrat;
    private String date_debut, date_fin;
    private float tarif_saison;
    private boolean statut_archive;
    private int id_appart;

   
    public Contrat(int id_contrat, String date_debut, String date_fin, float tarif_saison, boolean statut_archive, int id_appart) {
        this.id_contrat = id_contrat;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.tarif_saison = tarif_saison;
        this.statut_archive = statut_archive;
        this.id_appart = id_appart;
    }

   
    public Contrat(String date_debut, String date_fin, float tarif_saison, boolean statut_archive, int id_appart) {
        this.id_contrat = 0;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.tarif_saison = tarif_saison;
        this.statut_archive = statut_archive;
        this.id_appart = id_appart;
    }

    public int getId_contrat() { 
    	return id_contrat; 
    	}
    
    public String getDate_debut() { 
    	return date_debut; 
    	}
    
    public String getDate_fin() { 
    	return date_fin; 
    	}
    
    public float getTarif_saison() { 
    	return tarif_saison;
    	}
    
    public boolean getStatut_archive() { 
    	return statut_archive; 
    	}
    
    public int getId_appart() { return id_appart; }

    public void setDate_debut(String date_debut) {
    	this.date_debut = date_debut;
    	}
    
    public void setDate_fin(String date_fin) { 
    	this.date_fin = date_fin; 
    	}
    
    public void setTarif_saison(float tarif_saison) {
    	this.tarif_saison = tarif_saison;
    	}
    
    public void setStatut_archive(boolean statut_archive) { 
    	this.statut_archive = statut_archive; 
    	}
    public void setId_appart(int id_appart) { 
    	this.id_appart = id_appart; 
    	}
}