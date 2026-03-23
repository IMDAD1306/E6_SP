package modele;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.SQLException;

import controleur.Appartement;
import controleur.Client;
import controleur.Contrat;
import controleur.Disposer;
import controleur.Equipement;
import controleur.LouerMateriel;
import controleur.Materiel;
import controleur.Paiement;
import controleur.Personnel;
import controleur.Proprietaire;
import controleur.Reservation;
import controleur.User;

public class Modele 
{
	private static BDD uneBdd=new BDD("root","","neige_soleil","localhost");
	public static User selectWhereUser (String email,String mdp) {
	
	//verifie si il y a un user dans la table User identifie par mail et mdp
	
	User unUser=null;
	String requete ="select * from user where email='"+email+"' and mdp='"+mdp+"';";
	 
	try {
		uneBdd.seConnecter();
		Statement unStat = uneBdd.getMaConnection().createStatement();//objet de preparation de la requete 
		ResultSet unResultat = unStat.executeQuery(requete);//fetch:execution de la reuete 
		if(unResultat.next()) {
			
			unUser= new User (unResultat.getInt("id_perso"), unResultat.getString("nom"),
					unResultat.getString("prenom"),unResultat.getString("email"),
							unResultat.getString("mdp"),unResultat.getString("tel"),unResultat.getString("role"));
		}
		
		unStat.close ();
		uneBdd.SeDeConnecter();
		
	}
	catch(SQLException exp) {
		System.out.println("Erreur d'exécution de la requete :"+requete);
	}

		
	
	return unUser;

  }
	/*****************Requete sur la table Appartement****************/
	public static void insertAppartement(Appartement unAppartement) {

	    String requete = "INSERT INTO appartement VALUES (NULL,'"
	            + unAppartement.getNum_appart() + "','"
	            + unAppartement.getType_appart() + "','"
	            + unAppartement.getSurface() + "','"
	            + unAppartement.getCapacite_accueil() + "',"
	            + unAppartement.getIdproprio() + ");";

	    executerRequete1(requete);
	}

	
	public static void deleteAppartement(int id_appart) {
	
		
	}
	public static void updateAppartement(Proprietaire unProprieataire) {
		}
	
	
	
	public  static ArrayList<Appartement> SelectAllAppartements (String filtre){
		ArrayList<Appartement> lesAppartements = new ArrayList<Appartement>();
		String requete ;
		
	

		if (filtre.equals("")) {
		    requete = "select * from Appartement;";
		} else {
		    requete = "select * from Appartement where "
		            + "type_appart like '%" + filtre + "%' "
		            + "or exposition like '%" + filtre + "%' "
		            + "or surface like '%" + filtre + "%';";
		}

		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnection().createStatement();
		ResultSet desResultats = unStat.executeQuery(requete);
		
		while(desResultats.next()) { 
			Appartement unAppartement = new Appartement(
				    desResultats.getInt("id_appart"),
				    desResultats.getString("num_appart"),
				    desResultats.getString("type_appart"),
				    desResultats.getFloat("surface"),
				    desResultats.getInt("capacite_accueil"),
				    desResultats.getString("exposition"),
				    desResultats.getInt("distance_pistes"),
				    desResultats.getFloat("prix_hebdo"),
				    desResultats.getString("image"),
				    desResultats.getInt("id_proprio")
				);

			
					lesAppartements.add(unAppartement);
		}
		unStat.close();
		uneBdd.SeDeConnecter();
	}
	catch(SQLException exp) {
		System.out.println("Erreur d'execution de la requete :"+requete);
	}

		return lesAppartements;
		
	}
	
	public static void executerRequete1(String requete) {
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnection().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.SeDeConnecter();
			}
		catch (SQLException exp) {
			System.out.println("Erreur d'exécution de la requete :"+requete);
		}
	}
	
	
	/********************Requete sur la table Propriétaire ****************/
	
	public static void insertProprietaire(Proprietaire unProprietaire) {

	    String requete = "INSERT INTO proprietaire VALUES (NULL,'"
	            + unProprietaire.getNom() + "','"
	            + unProprietaire.getPrenom() + "','"
	            + unProprietaire.getEmail() + "','"
	            + unProprietaire.getAdresse() + "','"
	            + unProprietaire.getTel() + "','"
	            + unProprietaire.getIban() + "');";

	    executerRequete1(requete);
	
	    


		  try {
		   uneBdd.seConnecter();
		   Statement unStat = uneBdd.getMaConnection().createStatement(); //objet de préparation
		   unStat.execute(requete);
		   unStat.close();
		   uneBdd.SeDeConnecter();
		  }
		  catch (SQLException exp) {
		   System.out.println("Erreur d'éxécution de la requete :" +requete);
		  }
		 }
		 
		 public static ArrayList <Proprietaire> selectAllProprietaires(String filtre){
		  ArrayList<Proprietaire> lesProprietaires = new ArrayList <Proprietaire>();
		  String requete = "Select * from proprietaire;";
		  try {
		   uneBdd.seConnecter();
		   Statement unStat = uneBdd.getMaConnection().createStatement(); // objet de preparation
		   ResultSet desResultats = unStat.executeQuery(requete);
		   while (desResultats.next()) {
		    Proprietaire unProprietaire = new Proprietaire(desResultats.getInt("id_proprio"),desResultats.getString("nom")
		      , desResultats.getString("prenom"),desResultats.getString("email"),desResultats.getString("tel"),
		      desResultats.getString("adresse"), desResultats.getString("iban"));
		    lesProprietaires.add(unProprietaire);
		   }
		   unStat.close();
		   
		   uneBdd.seConnecter();
		  }
		  catch (SQLException exp) {
		   System.out.println("Erreur d'execution " + requete);
		  }
		   
		  return lesProprietaires;
		  
		 }
		 public static void deleteProprietaire(int id_proprio) {
			// TODO Auto-generated method stub
			
		 }
		 public static void updateProprietaire(Proprietaire unProprieataire) {
			// TODO Auto-generated method stub
			
		 }
		 public static void updateAppartement(Appartement unAppartement) {
			// TODO Auto-generated method stub
			
		 }
		 
		 
		 
		 /*****************Requete sur la table Materiel****************/
			public static void insertMateriel(Materiel unMateriel) {

			    String requete = "INSERT INTO materiel VALUES (NULL,'"
			            + unMateriel.getLibelle_mat() + "','"
			            + unMateriel.getType_mat() + "','"
			            + unMateriel.getEtat() + "','"
			            + unMateriel.getPrix_jour() + "',";

			    executerRequete1(requete);
			}

			
			public static void deleteMateriel(int id_mat) {
			
				
			}
			public static void updateMateriel(Materiel unMateriel) {
				}
			
			
			
			public  static ArrayList<Materiel> SelectAllMateriels (String filtre){
				ArrayList<Materiel> lesMateriels = new ArrayList<Materiel>();
				String requete ;
				
			

				if (filtre.equals("")) {
				    requete = "select * from Materiel;";
				} else {
				    requete = "select * from Materiel where "
				            + "libelle_mat '%" + filtre + "%' "
				            + "or type_mat like '%" + filtre + "%' "
				            + "or etat like '%" + filtre + "%';"
				    		+ "or prix_jour like '%" + filtre + "%';";
				}

				
				try {
					uneBdd.seConnecter();
					Statement unStat = uneBdd.getMaConnection().createStatement();
				ResultSet desResultats = unStat.executeQuery(requete);
				
				while(desResultats.next()) { 
					Materiel unMateriel = new Materiel (desResultats.getInt("id_Mat"),
							desResultats.getString("libelle_mat"),
							desResultats.getString("type_mat"),
							desResultats.getString("etat"), 
							desResultats.getFloat("prix_jour"), 
							desResultats.getInt("idmat"));
					
							lesMateriels.add(unMateriel);
				}
				unStat.close();
				uneBdd.SeDeConnecter();
			}
			catch(SQLException exp) {
				System.out.println("Erreur d'execution de la requete :"+requete);
			}

				return lesMateriels;
				
			}
			
			public static void executerRequete(String requete) {
				try {
					uneBdd.seConnecter();
					Statement unStat = uneBdd.getMaConnection().createStatement();
					unStat.execute(requete);
					unStat.close();
					uneBdd.SeDeConnecter();
					}
				catch (SQLException exp) {
					System.out.println("Erreur d'exécution de la requete :"+requete);
				}
			}
				
				/*****************Requete sur la table Client****************/	
				
			public static void insertClient(Client unClient) {
			    String requete = "INSERT INTO CLIENT VALUES (NULL, '"
			            + unClient.getNom() + "', '"
			            + unClient.getPrenom() + "', '"
			            + unClient.getEmail() + "', '"
			            + unClient.getTel() + "');";

			    try {
			        uneBdd.seConnecter();
			        Statement unStat = uneBdd.getMaConnection().createStatement();
			        unStat.execute(requete);
			        unStat.close();
			        uneBdd.SeDeConnecter();
			    } catch (SQLException exp) {
			        System.out.println("Erreur insertClient : " + exp);
			        System.out.println("Requête : " + requete);
			    }
			}

			
			public static ArrayList<Client> selectAllClients(String filtre) {
			    ArrayList<Client> lesClients = new ArrayList<Client>();
			    String requete = "SELECT * FROM CLIENT WHERE nom LIKE '%" + filtre + "%' OR prenom LIKE '%" + filtre + "%';";

			    try {
			        uneBdd.seConnecter();
			        Statement unStat = uneBdd.getMaConnection().createStatement();
			        ResultSet rs = unStat.executeQuery(requete);

			        while (rs.next()) {
			            Client unClient = new Client(
			                rs.getInt("id_client"),
			                rs.getString("nom"),
			                rs.getString("prenom"),
			                rs.getString("email"),
			                rs.getString("tel")
			            );
			            lesClients.add(unClient);
			        }

			        unStat.close();
			        uneBdd.SeDeConnecter();

			    } catch (SQLException exp) {
			        System.out.println("Erreur selectAllClients : " + exp);
			        System.out.println("Requête : " + requete);
			    }

			    return lesClients;
			}

		
			public static void deleteClient(int id_client) {
			    String requete = "DELETE FROM CLIENT WHERE id_client=" + id_client + ";";
			    try {
			        uneBdd.seConnecter();
			        Statement unStat = uneBdd.getMaConnection().createStatement();
			        unStat.executeUpdate(requete);
			        unStat.close();
			        uneBdd.SeDeConnecter();
			    } catch (SQLException e) {
			        System.out.println("Erreur deleteClient : " + e);
			    }
			}

			
			public static void updateClient(Client unClient) {
			    String requete = "UPDATE CLIENT SET nom='"
			            + unClient.getNom() + "', prenom='"
			            + unClient.getPrenom() + "', email='"
			            + unClient.getEmail() + "', tel='"
			            + unClient.getTel() + "' WHERE id_client="
			            + unClient.getIdclient() + ";";

			    try {
			        uneBdd.seConnecter();
			        Statement unStat = uneBdd.getMaConnection().createStatement();
			        unStat.executeUpdate(requete);
			        unStat.close();
			        uneBdd.SeDeConnecter();
			    } catch (SQLException exp) {
			        System.out.println("Erreur updateClient : " + exp);
			        System.out.println("Requête : " + requete);
			    }
			}

				
					
			/*************** CONTRAT *****************/

			public static void insertContrat(Contrat unContrat) {
			    String requete = "INSERT INTO CONTRAT VALUES (NULL, '"
			            + unContrat.getDate_debut() + "', '"
			            + unContrat.getDate_fin() + "', "
			            + unContrat.getTarif_saison() + ", "
			            + (unContrat.getStatut_archive() ? 1 : 0) + ", "
			            + unContrat.getId_appart() + ");";

			    try {
			        uneBdd.seConnecter();
			        Statement unStat = uneBdd.getMaConnection().createStatement();
			        unStat.execute(requete);
			        unStat.close();
			        uneBdd.SeDeConnecter();
			    } catch (SQLException exp) {
			        System.out.println("Erreur insertContrat : " + exp);
			        System.out.println("Requête : " + requete);
			    }
			}

			public static ArrayList<Contrat> selectAllContrats(String filtre) {
			    ArrayList<Contrat> lesContrats = new ArrayList<>();
			    String requete = "SELECT * FROM CONTRAT;";

			    try {
			        uneBdd.seConnecter();
			        Statement unStat = uneBdd.getMaConnection().createStatement();
			        ResultSet rs = unStat.executeQuery(requete);

			        while (rs.next()) {
			            Contrat c = new Contrat(
			                rs.getInt("id_contrat"),
			                rs.getString("date_debut"),
			                rs.getString("date_fin"),
			                rs.getFloat("tarif_saison"),
			                rs.getBoolean("statut_archive"),
			                rs.getInt("id_appart")
			            );
			            lesContrats.add(c);
			        }

			        unStat.close();
			        uneBdd.SeDeConnecter();

			    } catch (SQLException exp) {
			        System.out.println("Erreur selectAllContrats : " + exp);
			    }

			    return lesContrats;
			}

			public static void deleteContrat(int id_contrat) {
			    String requete = "DELETE FROM CONTRAT WHERE id_contrat=" + id_contrat + ";";

			    try {
			        uneBdd.seConnecter();
			        Statement unStat = uneBdd.getMaConnection().createStatement();
			        unStat.executeUpdate(requete);
			        unStat.close();
			        uneBdd.SeDeConnecter();
			    } catch (SQLException exp) {
			        System.out.println("Erreur deleteContrat : " + exp);
			    }
			}

			public static void updateContrat(Contrat unContrat) {
			    String requete = "UPDATE CONTRAT SET date_debut='"
			            + unContrat.getDate_debut() + "', date_fin='"
			            + unContrat.getDate_fin() + "', tarif_saison="
			            + unContrat.getTarif_saison() + ", statut_archive="
			            + (unContrat.getStatut_archive() ? 1 : 0)
			            + ", id_appart=" + unContrat.getId_appart()
			            + " WHERE id_contrat=" + unContrat.getId_contrat() + ";";

			    try {
			        uneBdd.seConnecter();
			        Statement unStat = uneBdd.getMaConnection().createStatement();
			        unStat.executeUpdate(requete);
			        unStat.close();
			        uneBdd.SeDeConnecter();
			    } catch (SQLException exp) {
			        System.out.println("Erreur updateContrat : " + exp);
			    }
			}			 
				
			
			/*************** RESERVATION *****************/

			public static void insertReservation(Reservation r) {
			    String requete = "INSERT INTO RESERVATION VALUES (NULL, '"
			            + r.getDate_debut_loc() + "', '"
			            + r.getDate_fin_loc() + "', "
			            + r.getNb_personnes() + ", "
			            + r.getId_client() + ", "
			            + r.getId_appart() + ", "
			            + r.getId_employe() + ");";

			    try {
			        uneBdd.seConnecter();
			        Statement unStat = uneBdd.getMaConnection().createStatement();
			        unStat.execute(requete);
			        unStat.close();
			        uneBdd.SeDeConnecter();
			    } catch (SQLException exp) {
			        System.out.println("Erreur insertReservation : " + exp);
			    }
			}

			public static ArrayList<Reservation> selectAllReservations(String filtre) {
			    ArrayList<Reservation> lesReservations = new ArrayList<>();
			    String requete = "SELECT * FROM RESERVATION;";

			    try {
			        uneBdd.seConnecter();
			        Statement unStat = uneBdd.getMaConnection().createStatement();
			        ResultSet rs = unStat.executeQuery(requete);

			        while (rs.next()) {
			            Reservation r = new Reservation(
			                rs.getInt("id_reser"),
			                rs.getString("date_debut_loc"),
			                rs.getString("date_fin_loc"),
			                rs.getInt("nb_personnes"),
			                rs.getInt("id_client"),
			                rs.getInt("id_appart"),
			                rs.getInt("id_employe")
			            );
			            lesReservations.add(r);
			        }

			        unStat.close();
			        uneBdd.SeDeConnecter();

			    } catch (SQLException exp) {
			        System.out.println("Erreur selectAllReservations : " + exp);
			    }

			    return lesReservations;
			}

			public static void deleteReservation(int id_reser) {
			    String requete = "DELETE FROM RESERVATION WHERE id_reser=" + id_reser + ";";

			    try {
			        uneBdd.seConnecter();
			        Statement unStat = uneBdd.getMaConnection().createStatement();
			        unStat.executeUpdate(requete);
			        unStat.close();
			        uneBdd.SeDeConnecter();
			    } catch (SQLException exp) {
			        System.out.println("Erreur deleteReservation : " + exp);
			    }
			}

			public static void updateReservation(Reservation r) {
			    String requete = "UPDATE RESERVATION SET date_debut_loc='"
			            + r.getDate_debut_loc() + "', date_fin_loc='"
			            + r.getDate_fin_loc() + "', nb_personnes="
			            + r.getNb_personnes() + ", id_client="
			            + r.getId_client() + ", id_appart="
			            + r.getId_appart() + ", id_employe="
			            + r.getId_employe()
			            + " WHERE id_reser=" + r.getId_reser() + ";";

			    try {
			        uneBdd.seConnecter();
			        Statement unStat = uneBdd.getMaConnection().createStatement();
			        unStat.executeUpdate(requete);
			        unStat.close();
			        uneBdd.SeDeConnecter();
			    } catch (SQLException exp) {
			        System.out.println("Erreur updateReservation : " + exp);
			    }
			}
			
			/*************** PAIEMENT *****************/

			public static void insertPaiement(Paiement p) {
			    String requete = "INSERT INTO PAIEMENT VALUES (NULL, "
			            + p.getMontant() + ", '"
			            + p.getDate_paiement() + "', '"
			            + p.getMode_paiement() + "', "
			            + p.getId_reser() + ");";

			    try {
			        uneBdd.seConnecter();
			        Statement unStat = uneBdd.getMaConnection().createStatement();
			        unStat.execute(requete);
			        unStat.close();
			        uneBdd.SeDeConnecter();
			    } catch (SQLException exp) {
			        System.out.println("Erreur insertPaiement : " + exp);
			    }
			}

			public static ArrayList<Paiement> selectAllPaiements(String filtre) {
			    ArrayList<Paiement> lesPaiements = new ArrayList<>();
			    String requete = "SELECT * FROM PAIEMENT;";

			    try {
			        uneBdd.seConnecter();
			        Statement unStat = uneBdd.getMaConnection().createStatement();
			        ResultSet rs = unStat.executeQuery(requete);

			        while (rs.next()) {
			            Paiement p = new Paiement(
			                rs.getInt("id_paiement"),
			                rs.getFloat("montant"),
			                rs.getString("date_paiement"),
			                rs.getString("mode_paiement"),
			                rs.getInt("id_reser")
			            );
			            lesPaiements.add(p);
			        }

			        unStat.close();
			        uneBdd.SeDeConnecter();

			    } catch (SQLException exp) {
			        System.out.println("Erreur selectAllPaiements : " + exp);
			    }

			    return lesPaiements;
			}

			public static void deletePaiement(int id_paiement) {
			    String requete = "DELETE FROM PAIEMENT WHERE id_paiement=" + id_paiement + ";";

			    try {
			        uneBdd.seConnecter();
			        Statement unStat = uneBdd.getMaConnection().createStatement();
			        unStat.executeUpdate(requete);
			        unStat.close();
			        uneBdd.SeDeConnecter();
			    } catch (SQLException exp) {
			        System.out.println("Erreur deletePaiement : " + exp);
			    }
			}

			public static void updatePaiement(Paiement p) {
			    String requete = "UPDATE PAIEMENT SET montant="
			            + p.getMontant() + ", date_paiement='"
			            + p.getDate_paiement() + "', mode_paiement='"
			            + p.getMode_paiement() + "', id_reser="
			            + p.getId_reser()
			            + " WHERE id_paiement=" + p.getId_paiement() + ";";

			    try {
			        uneBdd.seConnecter();
			        Statement unStat = uneBdd.getMaConnection().createStatement();
			        unStat.executeUpdate(requete);
			        unStat.close();
			        uneBdd.SeDeConnecter();
			    } catch (SQLException exp) {
			        System.out.println("Erreur updatePaiement : " + exp);
			    }
			}
			
			/*************** EQUIPEMENT *****************/

			public static void insertEquipement(Equipement e) {
			    String requete = "INSERT INTO EQUIPEMENT VALUES (NULL, '"
			            + e.getLibelle_equip() + "');";

			    try {
			        uneBdd.seConnecter();
			        Statement unStat = uneBdd.getMaConnection().createStatement();
			        unStat.execute(requete);
			        unStat.close();
			        uneBdd.SeDeConnecter();
			    } catch (SQLException exp) {
			        System.out.println("Erreur insertEquipement : " + exp);
			    }
			}

			public static ArrayList<Equipement> selectAllEquipements(String filtre) {
			    ArrayList<Equipement> lesEquipements = new ArrayList<>();
			    String requete = "SELECT * FROM EQUIPEMENT WHERE libelle_equip LIKE '%" + filtre + "%';";

			    try {
			        uneBdd.seConnecter();
			        Statement unStat = uneBdd.getMaConnection().createStatement();
			        ResultSet rs = unStat.executeQuery(requete);

			        while (rs.next()) {
			            Equipement e = new Equipement(
			                rs.getInt("id_equip"),
			                rs.getString("libelle_equip")
			            );
			            lesEquipements.add(e);
			        }

			        unStat.close();
			        uneBdd.SeDeConnecter();

			    } catch (SQLException exp) {
			        System.out.println("Erreur selectAllEquipements : " + exp);
			    }

			    return lesEquipements;
			}

			public static void deleteEquipement(int id_equip) {
			    String requete = "DELETE FROM EQUIPEMENT WHERE id_equip=" + id_equip + ";";

			    try {
			        uneBdd.seConnecter();
			        Statement unStat = uneBdd.getMaConnection().createStatement();
			        unStat.executeUpdate(requete);
			        unStat.close();
			        uneBdd.SeDeConnecter();
			    } catch (SQLException exp) {
			        System.out.println("Erreur deleteEquipement : " + exp);
			    }
			}

			public static void updateEquipement(Equipement e) {
			    String requete = "UPDATE EQUIPEMENT SET libelle_equip='"
			            + e.getLibelle_equip()
			            + "' WHERE id_equip=" + e.getId_equip() + ";";

			    try {
			        uneBdd.seConnecter();
			        Statement unStat = uneBdd.getMaConnection().createStatement();
			        unStat.executeUpdate(requete);
			        unStat.close();
			        uneBdd.SeDeConnecter();
			    } catch (SQLException exp) {
			        System.out.println("Erreur updateEquipement : " + exp);
			    }
			}
			
			/*************** LOUER MATERIEL *****************/

			public static void insertLouerMateriel(LouerMateriel lm) {
			    String requete = "INSERT INTO LOUER_MATERIEL VALUES (NULL, '"
			            + lm.getDate_debut() + "', '"
			            + lm.getDate_fin() + "', "
			            + lm.getId_client() + ", "
			            + lm.getId_mat() + ");";

			    try {
			        uneBdd.seConnecter();
			        Statement unStat = uneBdd.getMaConnection().createStatement();
			        unStat.execute(requete);
			        unStat.close();
			        uneBdd.SeDeConnecter();
			    } catch (SQLException exp) {
			        System.out.println("Erreur insertLouerMateriel : " + exp);
			    }
			}

			public static ArrayList<LouerMateriel> selectAllLouerMateriel(String filtre) {
			    ArrayList<LouerMateriel> lesLocations = new ArrayList<>();
			    String requete = "SELECT * FROM LOUER_MATERIEL;";

			    try {
			        uneBdd.seConnecter();
			        Statement unStat = uneBdd.getMaConnection().createStatement();
			        ResultSet rs = unStat.executeQuery(requete);

			        while (rs.next()) {
			            LouerMateriel lm = new LouerMateriel(
			                rs.getInt("id_loc_mat"),
			                rs.getString("date_debut"),
			                rs.getString("date_fin"),
			                rs.getInt("id_client"),
			                rs.getInt("id_mat")
			            );
			            lesLocations.add(lm);
			        }

			        unStat.close();
			        uneBdd.SeDeConnecter();

			    } catch (SQLException exp) {
			        System.out.println("Erreur selectAllLouerMateriel : " + exp);
			    }

			    return lesLocations;
			}

			public static void deleteLouerMateriel(int id_loc_mat) {
			    String requete = "DELETE FROM LOUER_MATERIEL WHERE id_loc_mat=" + id_loc_mat + ";";

			    try {
			        uneBdd.seConnecter();
			        Statement unStat = uneBdd.getMaConnection().createStatement();
			        unStat.executeUpdate(requete);
			        unStat.close();
			        uneBdd.SeDeConnecter();
			    } catch (SQLException exp) {
			        System.out.println("Erreur deleteLouerMateriel : " + exp);
			    }
			}

			public static void updateLouerMateriel(LouerMateriel lm) {
			    String requete = "UPDATE LOUER_MATERIEL SET date_debut='"
			            + lm.getDate_debut() + "', date_fin='"
			            + lm.getDate_fin() + "', id_client="
			            + lm.getId_client() + ", id_mat="
			            + lm.getId_mat()
			            + " WHERE id_loc_mat=" + lm.getId_loc_mat() + ";";

			    try {
			        uneBdd.seConnecter();
			        Statement unStat = uneBdd.getMaConnection().createStatement();
			        unStat.executeUpdate(requete);
			        unStat.close();
			        uneBdd.SeDeConnecter();
			    } catch (SQLException exp) {
			        System.out.println("Erreur updateLouerMateriel : " + exp);
			    }
			}
			
			/*************** PERSONNEL *****************/

			public static void insertPersonnel(Personnel p) {
			    String requete = "INSERT INTO PERSONNEL VALUES (NULL, '"
			            + p.getNom() + "', '"
			            + p.getPrenom() + "', '"
			            + p.getEmail() + "', '"
			            + p.getTel() + "', '"
			            + p.getRole() + "');";

			    try {
			        uneBdd.seConnecter();
			        Statement unStat = uneBdd.getMaConnection().createStatement();
			        unStat.execute(requete);
			        unStat.close();
			        uneBdd.SeDeConnecter();
			    } catch (SQLException exp) {
			        System.out.println("Erreur insertPersonnel : " + exp);
			    }
			}

			public static ArrayList<Personnel> selectAllPersonnel(String filtre) {
			    ArrayList<Personnel> lesEmployes = new ArrayList<>();
			    String requete = "SELECT * FROM PERSONNEL WHERE nom LIKE '%" + filtre + "%' OR prenom LIKE '%" + filtre + "%';";

			    try {
			        uneBdd.seConnecter();
			        Statement unStat = uneBdd.getMaConnection().createStatement();
			        ResultSet rs = unStat.executeQuery(requete);

			        while (rs.next()) {
			            Personnel p = new Personnel(
			                rs.getInt("id_employe"),
			                rs.getString("nom"),
			                rs.getString("prenom"),
			                rs.getString("email"),
			                rs.getString("tel"),
			                rs.getString("role")
			            );
			            lesEmployes.add(p);
			        }

			        unStat.close();
			        uneBdd.SeDeConnecter();

			    } catch (SQLException exp) {
			        System.out.println("Erreur selectAllPersonnel : " + exp);
			    }

			    return lesEmployes;
			}

			public static void deletePersonnel(int id_employe) {
			    String requete = "DELETE FROM PERSONNEL WHERE id_employe=" + id_employe + ";";

			    try {
			        uneBdd.seConnecter();
			        Statement unStat = uneBdd.getMaConnection().createStatement();
			        unStat.executeUpdate(requete);
			        unStat.close();
			        uneBdd.SeDeConnecter();
			    } catch (SQLException exp) {
			        System.out.println("Erreur deletePersonnel : " + exp);
			    }
			}

			public static void updatePersonnel(Personnel p) {
			    String requete = "UPDATE PERSONNEL SET nom='"
			            + p.getNom() + "', prenom='"
			            + p.getPrenom() + "', email='"
			            + p.getEmail() + "', tel='"
			            + p.getTel() + "', role='"
			            + p.getRole()
			            + "' WHERE id_employe=" + p.getId_employe() + ";";

			    try {
			        uneBdd.seConnecter();
			        Statement unStat = uneBdd.getMaConnection().createStatement();
			        unStat.executeUpdate(requete);
			        unStat.close();
			        uneBdd.SeDeConnecter();
			    } catch (SQLException exp) {
			        System.out.println("Erreur updatePersonnel : " + exp);
			    }
			}
			
			/*************** DISPOSER (Appartement <-> Équipement) *****************/

			public static void insertDisposer(Disposer d) {
			    String requete = "INSERT INTO DISPOSER VALUES ("
			            + d.getId_appart() + ", "
			            + d.getId_equip() + ");";

			    try {
			        uneBdd.seConnecter();
			        Statement unStat = uneBdd.getMaConnection().createStatement();
			        unStat.execute(requete);
			        unStat.close();
			        uneBdd.SeDeConnecter();
			    } catch (SQLException exp) {
			        System.out.println("Erreur insertDisposer : " + exp);
			    }
			}

			public static ArrayList<Disposer> selectAllDisposer(String filtre) {
			    ArrayList<Disposer> lesRelations = new ArrayList<>();
			    String requete = "SELECT * FROM DISPOSER;";

			    try {
			        uneBdd.seConnecter();
			        Statement unStat = uneBdd.getMaConnection().createStatement();
			        ResultSet rs = unStat.executeQuery(requete);

			        while (rs.next()) {
			            Disposer d = new Disposer(
			                rs.getInt("id_appart"),
			                rs.getInt("id_equip")
			            );
			            lesRelations.add(d);
			        }

			        unStat.close();
			        uneBdd.SeDeConnecter();

			    } catch (SQLException exp) {
			        System.out.println("Erreur selectAllDisposer : " + exp);
			    }

			    return lesRelations;
			}

			public static void deleteDisposer(int id_appart, int id_equip) {
			    String requete = "DELETE FROM DISPOSER WHERE id_appart=" + id_appart
			            + " AND id_equip=" + id_equip + ";";

			    try {
			        uneBdd.seConnecter();
			        Statement unStat = uneBdd.getMaConnection().createStatement();
			        unStat.executeUpdate(requete);
			        unStat.close();
			        uneBdd.SeDeConnecter();
			    } catch (SQLException exp) {
			        System.out.println("Erreur deleteDisposer : " + exp);
			    }
			}
				
			}
			
			
			
			

	 