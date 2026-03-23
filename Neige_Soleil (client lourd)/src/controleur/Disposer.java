package controleur;

public class Disposer {
    private int id_appart, id_equip;

    public Disposer(int id_appart, int id_equip) {
        this.id_appart = id_appart;
        this.id_equip = id_equip;
    }

    public int getId_appart() { 
    	return id_appart; 
    	}
    public int getId_equip() { 
    	return id_equip; 
    	}
}