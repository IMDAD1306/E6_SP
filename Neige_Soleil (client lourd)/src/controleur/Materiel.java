package controleur;

public class Materiel {
	private int id_mat;
	private String libelle_mat;
	private String type_mat, etat;
	float prix_jour;
	
	private int idproprio ;

	public Materiel(int id_mat, String libelle_mat, String type_mat, String etat,float prix_jour, int idproprio) {
		super();
		this.id_mat = id_mat;
		this.libelle_mat = libelle_mat;
		this.type_mat = type_mat;
		this.etat = etat;
		this.prix_jour = prix_jour;
		this.idproprio = idproprio;
	}
	
	public Materiel( String libelle_mat, String type_mat, String etat, float prix_jour, int idproprio) {
		super();
		this.id_mat = 0;
		this.libelle_mat = libelle_mat;
		this.type_mat = type_mat;
		this.etat = etat;
		this.prix_jour = prix_jour;
		this.idproprio = idproprio;
	}

	public int getId_mat() {
		return id_mat;
	}

	public void setId_mat(int id_mat) {
		this.id_mat = id_mat;
	}

	public String getLibelle_mat() {
		return libelle_mat;
	}

	public void setLibelle_mat(String libelle_mat) {
		this.libelle_mat = libelle_mat;
	}

	public String getType_mat() {
		return type_mat;
	}

	public void setType_mat(String type_mat) {
		this.type_mat = type_mat;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public float getPrix_jour() {
		return prix_jour;
	}

	public void setPrix_jour(float prix_jour) {
		this.prix_jour = prix_jour;
	}

	public int getIdproprio() {
		return idproprio;
	}

	public void setIdproprio(int idproprio) {
		this.idproprio = idproprio;
	}
	
	
	

}
