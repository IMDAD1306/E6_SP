<?php
class Modele {
    private $pdo;

    public function __construct() {
        try {
            $this->pdo = new PDO("mysql:host=localhost;dbname=neige_soleil;charset=utf8", "root", "");
            $this->pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        } catch (Exception $e) {
            die("Erreur de connexion : " . $e->getMessage());
        }
    }

    /* ==========================================================
       SECTION : APPARTEMENTS
       ========================================================== */

    public function getTousLesAppartements() {
        $stmt = $this->pdo->prepare("SELECT * FROM APPARTEMENT");
        $stmt->execute();
        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }

    public function getAppartementById($id) {
        $stmt = $this->pdo->prepare("SELECT * FROM APPARTEMENT WHERE id_appart = ?");
        $stmt->execute([$id]);
        return $stmt->fetch(PDO::FETCH_ASSOC);
    }

    /* ==========================================================
       SECTION : PANIER / RÉSERVATIONS
       ========================================================== */

    public function ajouterAuPanier($id_client, $id_appart, $date_debut, $date_fin) {
        $debut = new DateTime($date_debut);
        $fin = new DateTime($date_fin);
        
        if ($debut >= $fin) {
            return "Erreur : La date de départ doit être après la date d'arrivée.";
        }

        // --- VÉRIFICATION DE DISPONIBILITÉ (Version complète) ---
        // On vérifie si une réservation existe déjà qui chevauche les dates choisies
        $sqlVerif = "SELECT COUNT(*) as nb FROM RESERVATION 
                     WHERE id_appart = ? 
                     AND NOT (date_fin_loc <= ? OR date_debut_loc >= ?)";
        
        $stmtVerif = $this->pdo->prepare($sqlVerif);
        $stmtVerif->execute([$id_appart, $date_debut, $date_fin]);
        $res = $stmtVerif->fetch();

        if ($res['nb'] > 0) {
            return "Erreur : Cet appartement est déjà réservé sur cette période.";
        }

        // --- INSERTION ---
        $sql = "INSERT INTO RESERVATION (date_debut_loc, date_fin_loc, id_client, id_appart) VALUES (?, ?, ?, ?)";
        $stmt = $this->pdo->prepare($sql);
        $stmt->execute([$date_debut, $date_fin, $id_client, $id_appart]);
        
        return "ok";
    }

    public function getPanierByClient($id_client) {
        $sql = "SELECT r.*, a.type_appart, a.num_appart, a.image, a.surface, a.prix_hebdo, a.id_appart
                FROM RESERVATION r
                JOIN APPARTEMENT a ON r.id_appart = a.id_appart
                WHERE r.id_client = ?";
        $stmt = $this->pdo->prepare($sql);
        $stmt->execute([$id_client]);
        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }

    public function supprimerReservation($id_reser) {
        $sql = "DELETE FROM RESERVATION WHERE id_reser = ?";
        $stmt = $this->pdo->prepare($sql);
        $stmt->execute([$id_reser]);
    }

    /* ==========================================================
       SECTION : UTILISATEURS
       ========================================================== */

    public function login($email, $mdp) {
        // En examen, précise que le mdp devrait être haché avec password_verify
        $stmt = $this->pdo->prepare("SELECT * FROM User WHERE email = ? AND mdp = ?");
        $stmt->execute([$email, $mdp]);
        return $stmt->fetch(PDO::FETCH_ASSOC);
    }

    public function getUserDetails($email) {
        $stmt = $this->pdo->prepare("SELECT * FROM User WHERE email = ?");
        $stmt->execute([$email]);
        return $stmt->fetch(PDO::FETCH_ASSOC);
    }
}
?>