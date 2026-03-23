package controleur;

public class Equipement {
    private int id_equip;
    private String libelle_equip;

    
    public Equipement(int id_equip, String libelle_equip) {
        this.id_equip = id_equip;
        this.libelle_equip = libelle_equip;
    }

    
    public Equipement(String libelle_equip) {
        this.id_equip = 0;
        this.libelle_equip = libelle_equip;
    }

    public int getId_equip() { 
    	return id_equip;
    	}
    
    public String getLibelle_equip() {
    	return libelle_equip; 
    	}

    public void setLibelle_equip(String libelle_equip)
    { 
        this.libelle_equip = libelle_equip; 
    }
}
