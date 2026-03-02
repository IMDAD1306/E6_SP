<?php
    session_start();
    
    // 1. CHARGEMENT DU CONTROLEUR
    require_once("Controleur/controleur.class.php"); 
    $unControleur = new Controleur(); 

    // 2. GESTION DE LA VARIABLE PAGE
    $page = (isset($_GET['page'])) ? $_GET['page'] : 'accueil';

    // 3. LOGIQUE DE DÉCONNEXION
    if ($page == 'deconnexion') {
        session_destroy(); 
        header("Location: index.php?page=connexion"); 
        exit();
    }

    // 4. SÉCURITÉ PROFIL ET PANIER
    if (($page == 'profil' || $page == 'panier') && !isset($_SESSION['id_user'])) {
        header("Location: index.php?page=connexion");
        exit();
    }

    // --- TRAITEMENT DE LA CONNEXION ---
    if (isset($_POST['btnConnexion'])) {
        $user = $unControleur->login($_POST['email'], $_POST['mdp']);
        if ($user) {
            $_SESSION['id_user'] = $user['id_perso']; 
            $_SESSION['role']    = $user['role']; 
            $_SESSION['email']   = $user['email'];
            $_SESSION['nom']     = $user['nom'];
            $_SESSION['prenom']  = $user['prenom'];
            header("Location: index.php?page=accueil");
            exit();
        }
    }

    // --- TRAITEMENT DE L'INSCRIPTION ---
    if (isset($_POST['btnInscription'])) {
        $unControleur->inscrireUser(
            $_POST['nom'], $_POST['prenom'], $_POST['email'], 
            $_POST['tel'], $_POST['mdp'], $_POST['role']
        );
        header("Location: index.php?page=connexion");
        exit();
    }

    // --- LOGIQUE DE SUPPRESSION DU PANIER (Indépendante) ---
    if ($page == 'panier' && isset($_GET['action']) && $_GET['action'] == 'supprimer' && isset($_GET['id_reser'])) {
        $unControleur->supprimerReservation($_GET['id_reser']);
        $_SESSION['success'] = "La réservation a été retirée du panier.";
        header("Location: index.php?page=panier");
        exit();
    }

    // --- TRAITEMENT DE L'AJOUT AU PANIER ---
    if (isset($_POST['btnReserver'])) {
        if (isset($_SESSION['id_user'])) {
            $resultat = $unControleur->ajouterAuPanier(
                $_SESSION['id_user'], 
                $_POST['id_appart'], 
                $_POST['date_debut'], 
                $_POST['date_fin']
            );

            if ($resultat == "ok") {
                $_SESSION['success'] = "Réservation ajoutée !";
                header("Location: index.php?page=panier");
            } else {
                $_SESSION['erreur_resa'] = $resultat;
                header("Location: index.php?page=detail&id=" . $_POST['id_appart']);
            }
            exit();
        }
    }
?>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Neige & Soleil</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="stylesheet" href="Style/style_index.css">
    <link rel="stylesheet" href="Style/style_connexion.css"> 
    <link rel="stylesheet" href="Style/style_appartement.css">
    <link rel="stylesheet" href="Style/style_details.css"> 
    <link rel="stylesheet" href="Style/style_panier.css"> 
</head>
<body>

    <?php 
    if ($page != 'connexion' && $page != 'inscription') {
        require_once("Vue/vue_header.php"); 
        ?>
        <section class="hero-container">
            <a href="index.php?page=accueil" class="hero-link">
                <img src="images/background-montagne.jpg" alt="Montagnes enneigées">
            </a>
        </section>
        <?php 
    } 
    ?>

    <main>
        <?php if (isset($_SESSION['success'])): ?>
            <div class="container" style="margin-top:20px;">
                <p class="msg-success" style="background:#d4edda; color:#155724; padding:15px; border-radius:5px;">
                    <?= $_SESSION['success']; unset($_SESSION['success']); ?>
                </p>
            </div>
        <?php endif; ?>

        <?php if ($page == 'accueil'): ?>
            <section class="intro">
                <h1>Nos Locations de Vacances</h1>
            </section>
        <?php else: ?>
            <?php 
            switch($page) {
                case 'detail':
                    if (isset($_GET['id'])) {
                        $unAppart = $unControleur->getAppartement($_GET['id']);
                    } else {
                        header("Location: index.php?page=appartements");
                    }
                    break;
                
                case 'appartements':
                    $appartements = $unControleur->afficherCatalogue();
                    break;

                case 'panier':
                    $lesReservations = $unControleur->getPanierByClient($_SESSION['id_user']);
                    break;

                case 'profil':
                    $infosUser = $unControleur->getUserDetails($_SESSION['email']);
                    break;

                case 'materiel':
                    $lesMateriels = $unControleur->getMateriels();
                    break;
            }

            $file = "Vue/vue_" . $page . ".php";
            if(file_exists($file)) {
                include($file); 
            } else {
                echo "<center><h2 style='margin-top:50px;'>Page non disponible.</h2></center>";
            }
            ?>
        <?php endif; ?>
    </main>
</body>
</html>