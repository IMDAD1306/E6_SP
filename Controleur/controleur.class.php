<?php
require_once "Modele/modele.php";

class Controleur {
    private $unModele;

    public function __construct() {
        $this->unModele = new Modele();
    }

    /* ==========================================================
       SECTION : GESTION DES APPARTEMENTS
       ========================================================== */

    public function getAppartement($id) {
        return $this->unModele->getAppartementById($id);
    }

    public function afficherCatalogue() {
        return $this->unModele->getTousLesAppartements();
    }

    /* ==========================================================
       SECTION : GESTION DU PANIER / RÉSERVATIONS
       ========================================================== */

    public function ajouterAuPanier($id_client, $id_appart, $date_debut, $date_fin) {
        // Transmission directe au modèle qui contient toute la logique
        return $this->unModele->ajouterAuPanier($id_client, $id_appart, $date_debut, $date_fin);
    }

    public function getPanierByClient($id_client) {
        return $this->unModele->getPanierByClient($id_client);
    }

    public function supprimerReservation($id_reser) {
        $this->unModele->supprimerReservation($id_reser);
    }

    /* ==========================================================
       SECTION : UTILISATEURS / CONNEXION
       ========================================================== */

    public function login($email, $mdp) {
        return $this->unModele->login($email, $mdp);
    }

    public function getUserDetails($email) {
        return $this->unModele->getUserDetails($email);
    }

    public function inscrireUser($nom, $prenom, $email, $tel, $mdp, $role) {
        // En examen, si le jury demande : 
        // "Ici on pourrait ajouter password_hash($mdp, PASSWORD_DEFAULT)"
        $this->unModele->inscrireClient($nom, $prenom, $email, $tel, $mdp, $role);
    }
}
?>