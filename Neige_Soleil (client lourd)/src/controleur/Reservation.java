package controleur;

public class Reservation {
    private int id_reser, nb_personnes, id_client, id_appart, id_employe;
    private String date_debut_loc, date_fin_loc;

   
    public Reservation(int id_reser, String date_debut_loc, String date_fin_loc,
                       int nb_personnes, int id_client, int id_appart, int id_employe) {
        this.id_reser = id_reser;
        this.date_debut_loc = date_debut_loc;
        this.date_fin_loc = date_fin_loc;
        this.nb_personnes = nb_personnes;
        this.id_client = id_client;
        this.id_appart = id_appart;
        this.id_employe = id_employe;
    }

  
    public Reservation(String date_debut_loc, String date_fin_loc,
                       int nb_personnes, int id_client, int id_appart, int id_employe) {
        this.id_reser = 0;
        this.date_debut_loc = date_debut_loc;
        this.date_fin_loc = date_fin_loc;
        this.nb_personnes = nb_personnes;
        this.id_client = id_client;
        this.id_appart = id_appart;
        this.id_employe = id_employe;
    }

    public int getId_reser() { 
    	return id_reser;
    	}
    
    public String getDate_debut_loc() { 
    	return date_debut_loc; 
    	}
    
    public String getDate_fin_loc() { 
    	return date_fin_loc; 
    	}
    
    public int getNb_personnes() { 
    	return nb_personnes;
    	}
    
    public int getId_client() { 
    	return id_client; 
    	}
    
    public int getId_appart() { 
    	return id_appart;
    	}
    
    
    public int getId_employe() { 
    	return id_employe; 
    	}
}