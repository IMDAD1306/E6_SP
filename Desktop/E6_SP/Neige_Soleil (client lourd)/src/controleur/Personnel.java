package controleur;

public class Personnel {
    private int id_employe;
    private String nom, prenom, email, tel, role;

    
    public Personnel(int id_employe, String nom, String prenom, String email, String tel, String role) {
        this.id_employe = id_employe;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.tel = tel;
        this.role = role;
    }

    
    public Personnel(String nom, String prenom, String email, String tel, String role) {
        this.id_employe = 0;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.tel = tel;
        this.role = role;
    }

    public int getId_employe() { 
    	return id_employe;
    	}
    
    public String getNom() { 
    	return nom; 
    	}
    
    public String getPrenom() { 
    	return prenom; 
    	}
    
    public String getEmail() {
    	return email; 
    	}
    
    public String getTel() {
    	return tel; 
    	}
    
    public String getRole() {
    	return role;
    	}
}
