package controleur;

public class Client {
 private int idclient ;
 private String nom, prenom, email, tel ;
 
 public Client(int idclient, String nom, String prenom, String email, String tel) {
  super();
  this.idclient = idclient;
  this.nom = nom;
  this.prenom = prenom;
  this.email = email;
  this.tel = tel;                                                                   
  
 }

 public Client( String nom, String prenom, String email, String tel) {
  super();
  this.nom = nom;
  this.prenom = prenom;
  this.email = email;
  this.tel = tel;
 }

 public int getIdclient() {
  return idclient;
 }

 public void setIdclient(int idclient) {
  this.idclient = idclient;
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

 public String getTel() {
  return tel;
 }

 public void setTel(String tel) {
  this.tel = tel;
 }
 
 
 

 }
 