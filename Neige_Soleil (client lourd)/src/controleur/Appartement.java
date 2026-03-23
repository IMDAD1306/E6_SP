package controleur;

public class Appartement {
	private int id_appart;
	private int capacite_accueil;
	private String num_appart,type_appart,exposition;
	float surface;
	
	private int distance_pistes;
	private float prix_hebdo;
	private String image;
	private int idproprio;
	public Appartement(int id_appart,String num_appart,String type_appart,float surface,int capacite_accueil,String exposition,int distance_pistes,float prix_hebdo,
	        String image,
	        int idproprio){
		super();
		this.id_appart = id_appart;
	    this.num_appart = num_appart;
	    this.type_appart = type_appart;
	    this.surface = surface;
	    this.capacite_accueil = capacite_accueil;
	    this.exposition = exposition;
	    this.distance_pistes = distance_pistes;
	    this.prix_hebdo = prix_hebdo;
	    this.image = image;
	    this.idproprio = idproprio;

	} 
	
	public Appartement(String num_appart,String type_appart,float surface,int capacite_accueil,String exposition,int distance_pistes,float prix_hebdo,
	        String image,
	        int idproprio) {
		super();
		this.id_appart = 0; // auto-incrément
	    this.num_appart = num_appart;
	    this.type_appart = type_appart;
	    this.surface = surface;
	    this.capacite_accueil = capacite_accueil;
	    this.exposition = exposition;
	    this.distance_pistes = distance_pistes;
	    this.prix_hebdo = 450.00f;
	    this.image = "default.jpg";
	    this.idproprio = idproprio;

	}
	
	public int getId_appart() {
		return id_appart;
	}

	public void setId_appart(int id_appart) {
		this.id_appart = id_appart;
	}

	public int getCapacite_accueil() {
		return capacite_accueil;
	}

	public void setCapacite_accueil(int capacite_accueil) {
		this.capacite_accueil = capacite_accueil;
	}

	public String getNum_appart() {
		return num_appart;
	}

	public void setNum_appart(String num_appart) {
		this.num_appart = num_appart;
	}

	public String getType_appart() {
		return type_appart;
	}

	public void setType_appart(String type_appart) {
		this.type_appart = type_appart;
	}

	public String getExposition() {
		return exposition;
	}

	public void setExposition(String exposition) {
		this.exposition = exposition;
	}

	public float getSurface() {
		return surface;
	}

	public void setSurface(float surface) {
		this.surface = surface;
	}

	public int getDistance_pistes() {
		return distance_pistes;
	}

	public void setDistance_pistes(int distance_pistes) {
		this.distance_pistes = distance_pistes;
	}

	public float getPrix_hebdo() {
		return prix_hebdo;
	}

	public void setPrix_hebdo(float prix_hebdo) {
		this.prix_hebdo = prix_hebdo;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getIdproprio() {
		return idproprio;
	}

	public void setId_proprio(int idproprio) {
		this.idproprio = idproprio;
	}

	

	

	

	
	
}
