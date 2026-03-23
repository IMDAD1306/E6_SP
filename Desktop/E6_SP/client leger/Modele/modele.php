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
 
    public function getAppartementsVedettes() {
        $requete = "SELECT * FROM APPARTEMENT ORDER BY id_appart ASC LIMIT 3";
        $select = $this->pdo->prepare($requete);
        $select->execute();
        return $select->fetchAll();
    }
 
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
 
    public function ajouterAuPanier($id_client, $id_appart, $date_debut, $date_fin) {
        $debut = new DateTime($date_debut);
        $fin   = new DateTime($date_fin);
 
        if ($debut >= $fin) {
            return "Erreur : La date de depart doit etre apres la date d'arrivee.";
        }
 
        // Verif chevauchement uniquement sur les reservations validees
        $sqlVerif = "SELECT COUNT(*) as nb FROM RESERVATION 
                     WHERE id_appart = ? 
                     AND statut = 'validee'
                     AND NOT (date_fin_loc <= ? OR date_debut_loc >= ?)";
        $stmtVerif = $this->pdo->prepare($sqlVerif);
        $stmtVerif->execute([$id_appart, $date_debut, $date_fin]);
        $res = $stmtVerif->fetch();
 
        if ($res['nb'] > 0) {
            return "Erreur : Cet appartement est deja reserve sur cette periode.";
        }
 
        // Insertion avec statut 'panier' par defaut
        $sql  = "INSERT INTO RESERVATION (date_debut_loc, date_fin_loc, id_client, id_appart, statut) VALUES (?, ?, ?, ?, 'panier')";
        $stmt = $this->pdo->prepare($sql);
        $stmt->execute([$date_debut, $date_fin, $id_client, $id_appart]);
 
        return "ok";
    }
 
    // Panier = uniquement les reservations avec statut 'panier'
    public function getPanierByClient($id_client) {
        $sql = "SELECT r.*, a.type_appart, a.num_appart, a.image, a.surface, a.prix_hebdo, a.id_appart
                FROM RESERVATION r
                JOIN APPARTEMENT a ON r.id_appart = a.id_appart
                WHERE r.id_client = ?
                AND r.statut = 'panier'";
        $stmt = $this->pdo->prepare($sql);
        $stmt->execute([$id_client]);
        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }
 
    // Suppression physique uniquement depuis le panier
    public function supprimerReservation($id_reser) {
        $sql  = "DELETE FROM RESERVATION WHERE id_reser = ?";
        $stmt = $this->pdo->prepare($sql);
        $stmt->execute([$id_reser]);
    }
 
    public function login($email, $mdp) {
        $stmt = $this->pdo->prepare("SELECT * FROM User WHERE email = ?");
        $stmt->execute([$email]);
        $user = $stmt->fetch(PDO::FETCH_ASSOC);
        if ($user && ($mdp == $user['mdp'])) {
            return $user;
        }
        return false;
    }
 
    public function getUserById($id) {
        $requete = "SELECT * FROM User WHERE id_perso = :id";
        $select  = $this->pdo->prepare($requete);
        $select->execute(['id' => $id]);
        return $select->fetch();
    }
 
    public function insererFacture($id_reser, $montant) {
        // Verif doublon
        $stmtVerif = $this->pdo->prepare("SELECT COUNT(*) FROM FACTURE WHERE id_reser = ?");
        $stmtVerif->execute([$id_reser]);
        if ($stmtVerif->fetchColumn() > 0) {
            return true;
        }
        $num_facture = "FAC-" . date('Y') . "-" . $id_reser;
        $requete     = "INSERT INTO FACTURE (num_facture, montant_ttc, id_reser) VALUES (?, ?, ?)";
        $stmt        = $this->pdo->prepare($requete);
        return $stmt->execute([$num_facture, $montant, $id_reser]);
    }
 
    // Maintenant cette fonction fait vraiment quelque chose !
    public function validerStatutReservation($id_reser) {
        $sql  = "UPDATE RESERVATION SET statut = 'validee' WHERE id_reser = ?";
        $stmt = $this->pdo->prepare($sql);
        $stmt->execute([$id_reser]);
    }
 
    // Historique = uniquement les reservations validees
    public function getHistoriqueComplet($id_client) {
        $requete = "SELECT 
                        r.id_reser,
                        'Appart' AS type,
                        a.type_appart AS designation,
                        r.date_debut_loc AS date_debut,
                        r.date_fin_loc AS date_fin,
                        f.montant_ttc AS montant,
                        f.num_facture,
                        CASE 
                            WHEN f.num_facture IS NULL THEN 'En attente'
                            ELSE 'Termine'
                        END AS libelle_statut
                    FROM RESERVATION r
                    JOIN APPARTEMENT a ON r.id_appart = a.id_appart
                    LEFT JOIN FACTURE f ON r.id_reser = f.id_reser
                    WHERE r.id_client = :id_client
                    AND r.statut = 'validee'
                    ORDER BY r.date_debut_loc DESC";
        $stmt = $this->pdo->prepare($requete);
        $stmt->execute(['id_client' => $id_client]);
        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }
 
    public function getFactureByReser($id_reser) {
        $sql = "SELECT 
                    f.id_facture,
                    f.num_facture,
                    f.date_emission,
                    f.montant_ttc,
                    f.id_reser,
                    r.date_debut_loc,
                    r.date_fin_loc,
                    r.nb_personnes,
                    a.type_appart,
                    a.num_appart,
                    a.surface,
                    a.capacite_accueil,
                    a.prix_hebdo,
                    u.nom,
                    u.prenom,
                    u.email,
                    u.tel
                FROM FACTURE f
                JOIN RESERVATION r ON f.id_reser = r.id_reser
                JOIN APPARTEMENT a ON r.id_appart = a.id_appart
                JOIN User u ON r.id_client = u.id_perso
                WHERE f.id_reser = :id_reser";
        $stmt = $this->pdo->prepare($sql);
        $stmt->execute(['id_reser' => $id_reser]);
        return $stmt->fetch(PDO::FETCH_ASSOC);
    }
}
?>