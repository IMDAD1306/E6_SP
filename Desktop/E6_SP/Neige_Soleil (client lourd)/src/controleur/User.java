package controleur;

public class User {
	private int id_perso;
	private String nom,prenom ,email,mdp,tel,role ;
	public User(int iduser, String nom, String prenom, String email, String mdp,String tel,String role) {
		
		this.id_perso = iduser;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.mdp = mdp;
		this.tel = tel;
		this.role=role;
	}
	
public User( String nom, String prenom, String email, String mdp, String tel) {
		
		this.id_perso = 0;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.mdp = mdp;
		this.tel = tel;
		this.role="";
	}

public int getIduser() {
	return id_perso;
}

public void setIduser(int iduser) {
	this.id_perso = iduser;
}

public String getNom() {
	return nom;
}

public void setNom(String nom) {
	this.nom = nom;
}

public String getPrenom() {
	return prenom;
}

public void setPrenom(String prenom) {
	this.prenom = prenom;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getMdp() {
	return mdp;
}

public void setMdp(String mdp) {
	this.mdp = mdp;
}

public String getTel() {
	return tel;
}

public void setTel(String tel) {
	this.tel = tel;
}


	
	

}
