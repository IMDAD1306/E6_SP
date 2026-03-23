package controleur;

public class LouerMateriel {
    private int id_loc_mat, id_client, id_mat;
    private String date_debut, date_fin;

    
    public LouerMateriel(int id_loc_mat, String date_debut, String date_fin, int id_client, int id_mat) {
        this.id_loc_mat = id_loc_mat;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.id_client = id_client;
        this.id_mat = id_mat;
    }

    
    public LouerMateriel(String date_debut, String date_fin, int id_client, int id_mat) {
        this.id_loc_mat = 0;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.id_client = id_client;
        this.id_mat = id_mat;
    }

    public int getId_loc_mat() { 
    	return id_loc_mat; 
    	}
    
    public String getDate_debut() { 
    	return date_debut; 
    	}
    
    public String getDate_fin() {
    	return date_fin; 
    	}
    
    public int getId_client() {
    	return id_client; 
    	}
    
    public int getId_mat() { 
    	return id_mat; 
    	}
}
