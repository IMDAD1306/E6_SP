package controleur;

import java.util.ArrayList;

import modele.Modele;

public class Controleur 
{
	public static User selectWhereUser(String email,String mdp) {
		
		return Modele.selectWhereUser(email,mdp);
	}
	
	
	//------------------Propritaire ------------------//
	public static void insertProprietaire(Proprietaire unProprietaire) {
		Modele.insertProprietaire(unProprietaire);
	}
	
	
	public static ArrayList<Proprietaire> selectAllProprietaires(String filtre) {
        return Modele.selectAllProprietaires(filtre);
    }
    
    
    public static void deleteProprietaire(int id_proprio) {
    	Modele.deleteProprietaire(id_proprio);
    }
       
    public static void updateProprietaire(Proprietaire unProprieataire) {
    	Modele.updateProprietaire(unProprieataire);
    }
		
	
	
	//--------------------Appartement---------------//
	public static void insertAppartement(Appartement unAppartement) {
		Modele.insertAppartement(unAppartement);
	}
	
	
	public static ArrayList<Appartement> selectAllAppartements(String filtre) {
        return Modele.SelectAllAppartements(filtre);
    }
    
    
    public static void deleteAppartement (int id_appart) {
    	Modele.deleteAppartement(id_appart);
    }
       
    public static void updateAppartement(Appartement unAppartement) {
    	Modele.updateAppartement(unAppartement);
    }
    
    
    
  //--------------------Materiel---------------//
  	public static void insertMateriel(Materiel unMateriel) {
  		Modele.insertMateriel(unMateriel);
  	}
  	
  	
  	public static ArrayList<Materiel> selectAllMateriels(String filtre) {
          return Modele.SelectAllMateriels(filtre);
      }
      
      
      public static void deleteMateriel (int id_mat) {
      	Modele.deleteMateriel(id_mat);
      }
         
      public static void updateMateriel(Materiel unMateriel) {
      	Modele.updateMateriel(unMateriel);
      }
      
    //--------------------Client---------------//
      public static void insertClient(Client unClient) {
          Modele.insertClient(unClient);
      }

      public static ArrayList<Client> selectAllClients(String filtre) {
          return Modele.selectAllClients(filtre);
      }

      public static void deleteClient(int id_client) {
          Modele.deleteClient(id_client);
      }

      public static void updateClient(Client unClient) {
          Modele.updateClient(unClient);
      }
      
    //--------------------Contrat---------------//
      public static void insertContrat(Contrat unContrat) {
          Modele.insertContrat(unContrat);
      }

      public static ArrayList<Contrat> selectAllContrats(String filtre) {
          return Modele.selectAllContrats(filtre);
      }

      public static void deleteContrat(int id_contrat) {
          Modele.deleteContrat(id_contrat);
      }

      public static void updateContrat(Contrat unContrat) {
          Modele.updateContrat(unContrat);
      }

    //--------------------Reservation---------------//
      public static void insertReservation(Reservation r) {
          Modele.insertReservation(r);
      }

      public static ArrayList<Reservation> selectAllReservations(String filtre) {
          return Modele.selectAllReservations(filtre);
      }

      public static void deleteReservation(int id_reser) {
          Modele.deleteReservation(id_reser);
      }

      public static void updateReservation(Reservation r) {
          Modele.updateReservation(r);
      }

    //--------------------Paiement---------------//
      public static void insertPaiement(Paiement p) {
          Modele.insertPaiement(p);
      }

      public static ArrayList<Paiement> selectAllPaiements(String filtre) {
          return Modele.selectAllPaiements(filtre);
      }

      public static void deletePaiement(int id_paiement) {
          Modele.deletePaiement(id_paiement);
      }

      public static void updatePaiement(Paiement p) {
          Modele.updatePaiement(p);
      }

    //--------------------Equipement---------------//
      public static void insertEquipement(Equipement e) {
          Modele.insertEquipement(e);
      }

      public static ArrayList<Equipement> selectAllEquipements(String filtre) {
          return Modele.selectAllEquipements(filtre);
      }

      public static void deleteEquipement(int id_equip) {
          Modele.deleteEquipement(id_equip);
      }

      public static void updateEquipement(Equipement e) {
          Modele.updateEquipement(e);
      }

    //--------------------Louer Materiel---------------//
      public static void insertLouerMateriel(LouerMateriel lm) {
          Modele.insertLouerMateriel(lm);
      }

      public static ArrayList<LouerMateriel> selectAllLouerMateriel(String filtre) {
          return Modele.selectAllLouerMateriel(filtre);
      }

      public static void deleteLouerMateriel(int id_loc_mat) {
          Modele.deleteLouerMateriel(id_loc_mat);
      }

      public static void updateLouerMateriel(LouerMateriel lm) {
          Modele.updateLouerMateriel(lm);
      }

    //--------------------Disposer---------------//
      public static void insertDisposer(Disposer d) {
          Modele.insertDisposer(d);
      }

      public static ArrayList<Disposer> selectAllDisposer(String filtre) {
          return Modele.selectAllDisposer(filtre);
      }

      public static void deleteDisposer(int id_appart, int id_equip) {
          Modele.deleteDisposer(id_appart, id_equip);
      }
      
    //--------------------Personnel---------------//
      public static void insertPersonnel(Personnel p) {
          Modele.insertPersonnel(p);
      }

      public static ArrayList<Personnel> selectAllPersonnel(String filtre) {
          return Modele.selectAllPersonnel(filtre);
      }

      public static void deletePersonnel(int id_employe) {
          Modele.deletePersonnel(id_employe);
      }

      public static void updatePersonnel(Personnel p) {
          Modele.updatePersonnel(p);
      }

	

}

