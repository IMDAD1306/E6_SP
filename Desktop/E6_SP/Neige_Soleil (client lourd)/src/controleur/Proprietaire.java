 
package controleur;
public class Proprietaire {
 private int idProprio ;
 private String nom, prenom, adresse, email, tel , iban;
 
 
 public Proprietaire(int idProprio, String nom, String prenom, String adresse, String email, String tel,
   String iban) {
  super();
  this.idProprio = idProprio;
  this.nom = nom;
  this.prenom = prenom;
  this.adresse = adresse;
  this.email = email;
  this.tel = tel;
  this.iban = iban;
 }
 
 public Proprietaire( String nom, String prenom, String adresse, String email, String tel,
   String iban) {
  super();
  this.nom = nom;
  this.prenom = prenom;
  this.adresse = adresse;
  this.email = email;
  this.tel = tel;
  this.iban = iban;
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
 public String getAdresse() {
  return adresse;
 }
 public void setAdresse(String adresse) {
  this.adresse = adresse;
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
 public String getIban() {
  return iban;
 }
 public void setIban(String iban) {
  this.iban = iban;
 }

 public int getIdProprio() {
	return idProprio;
 }

 public void setIdProprio(int idProprio) {
	this.idProprio = idProprio;
 }
 
 
}
 
 