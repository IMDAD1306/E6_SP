<?php
    session_start();
    require_once("Controleur/controleur.class.php");
    $unControleur = new Controleur();
 
    $page = (isset($_GET['page'])) ? $_GET['page'] : 'accueil';
 
    switch($page) {
        case 'accueil':
            $appartementsVedettes = $unControleur->getAppartementsVedettes();
            break;
 
        case 'connexion':
            if (isset($_POST['btnConnexion'])) {
                $email = $_POST['email'];
                $mdp   = $_POST['mdp'];
                $user  = $unControleur->login($email, $mdp);
                if ($user) {
                    $_SESSION['id_user'] = $user['id_perso'];
                    header('Location: index.php?page=accueil');
                    exit();
                } else {
                    $erreur = "Identifiants incorrects.";
                }
            }
            break;
 
        case 'detail':
            $unAppart = (isset($_GET['id'])) ? $unControleur->getAppartement($_GET['id']) : null;
            break;
 
        case 'appartements':
            $appartements = $unControleur->afficherCatalogue();
            break;
 
        case 'ajout_panier':
            if(isset($_POST['id_appart'], $_POST['date_debut'], $_POST['date_fin'])) {
                $unControleur->ajouterAuPanier($_SESSION['id_user'], $_POST['id_appart'], $_POST['date_debut'], $_POST['date_fin']);
                header('Location: index.php?page=panier');
                exit();
            }
            break;
 
        case 'panier':
            if (isset($_GET['action']) && $_GET['action'] == 'supprimer' && isset($_GET['id_reser'])) {
                $unControleur->supprimerReservation($_GET['id_reser']);
                header('Location: index.php?page=panier');
                exit();
            }
            $lesReservations = $unControleur->getPanierByClient($_SESSION['id_user']);
            break;
 
        case 'profil':
            $user       = $unControleur->getUserById($_SESSION['id_user']);
            $historique = $unControleur->getHistorique($_SESSION['id_user']);
            break;
 
        case 'valider_resa':
            if(isset($_POST['id_reser']) && isset($_POST['montant'])) {
                $lesIds       = $_POST['id_reser']; // tableau id_reser[]
                $montantTotal = $_POST['montant'];
                $unControleur->validerReservationEtFacturer($lesIds, $montantTotal);
                $_SESSION['panier'] = array();
                header('Location: index.php?page=profil');
                exit();
            }
            break;
 
        case 'facture':
            // On recupere la facture via l'id de reservation
            $id_reser  = isset($_GET['id']) ? (int)$_GET['id'] : 0;
            $laFacture = $unControleur->getFacture($id_reser);
            break;
 
        case 'deconnexion':
            session_unset();
            session_destroy();
            header('Location: index.php?page=accueil');
            exit();
    }
?>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Neige &amp; Soleil</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="stylesheet" href="Style/style_index.css">
</head>
<body>
 
    <?php if ($page != 'connexion' && $page != 'inscription'): ?>
        <?php require_once("Vue/vue_header.php"); ?>
        <section class="hero-container">
            <a href="index.php?page=accueil" class="hero-link">
                <img src="images/background-montagne.jpg" alt="Montagnes enneigees">
            </a>
        </section>
    <?php endif; ?>
 
    <main>
        <?php
            $file = "Vue/vue_" . $page . ".php";
            if(file_exists($file)) {
                include($file);
            } else {
                echo "<div style='text-align:center; padding:80px 24px; color:#5a6a7e;'>
                    <p style='font-size:3rem;'>🏔</p>
                    <p>Page introuvable.</p>
                    <a href='index.php?page=accueil' style='color:#1e4d7b;font-weight:600;'>Retour accueil</a>
                </div>";
            }
        ?>
    </main>
 
</body>
</html>