package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDD 
{
 
 //classe BDD : etablissement de la connexion/deconnexion avec le serveur BDD

 private String user, mdp, bdd, serveur ;
 private Connection maConnexion ;
 public BDD(String user, String mdp, String bdd, String serveur) {
  super();
  this.user = user;
  this.mdp = mdp;
  this.bdd = bdd;
  this.serveur = serveur;
  this.maConnexion = null ;
 }
 public void chargerPilote () {
  // JDBC : Java Data Base Connector
  try {
   Class.forName("com.mysql.cj.jdbc.Driver");
  }
  catch (ClassNotFoundException exp) {
   System.out.println("Absence du pilote JDBC");
   
  }
  
 }
 public void seConnecter () {
  String url ="jdbc:mysql://" + this.serveur + "/" + this.bdd;
  this.chargerPilote();
  try {
   this.maConnexion = DriverManager.getConnection(url, this.user, this.mdp);
  }
  catch (SQLException exp) {
   System.out.println("Impossible de se connecter a : "  + url);
  }
  
 }
 public void SeDeConnecter () {
  try {
   if (this.maConnexion !=null) {
    this.maConnexion.close();
    
   }
  } 
 
  catch (SQLException exp) {
   System.out.println("Impossible de se déconnecter du serveur BDD.");
  }
  
 }
 public Connection getMaConnection () {
  //récupérer la connexion
  return this.maConnexion;
  
 }
 
 
}
 