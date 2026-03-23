package controleur;

import vue.VueConnexion;
import vue.VueGenerale;

public class Neige_soleil {
	private static VueConnexion uneVueConnexion;
	private static VueGenerale uneVueGenerale;

	public static void main(String[] args) {
		uneVueConnexion = new VueConnexion();
	

	}
	public static void rendreVisibleVueConnexion(boolean action) {
		uneVueConnexion.setVisible(action);
	}
	
	public static  void creerDetruireVueGenerale(boolean action) {
		if(action==true) {
			uneVueGenerale = new VueGenerale();
		}else {
			uneVueGenerale.dispose();
		}
	}

}
