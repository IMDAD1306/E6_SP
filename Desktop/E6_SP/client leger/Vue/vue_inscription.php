<?php
    // Traitement de l'inscription
    if (isset($_POST['btnInscription'])) {
        // Connexion à ta base neige_soleil
        $db = new PDO('mysql:host=localhost;dbname=neige_soleil', 'root', '');

        $nom = $_POST['nom'];
        $prenom = $_POST['prenom'];
        $email = $_POST['email'];
        $tel = $_POST['tel'];
        $mdp = $_POST['mdp'];
        $role = $_POST['role']; // 'client' ou 'proprietaire'

        if ($role == 'client') {
            // L'insertion ici déclenche le trigger insert_client (Héritage)
            $req = $db->prepare("INSERT INTO CLIENT (nom, prenom, email, tel) VALUES (?, ?, ?, ?)");
            $req->execute([$nom, $prenom, $email, $tel]);
        } else {
            // L'insertion ici déclenche le trigger insert_proprio (Héritage)
            $req = $db->prepare("INSERT INTO PROPRIETAIRE (nom, prenom, email, tel) VALUES (?, ?, ?, ?)");
            $req->execute([$nom, $prenom, $email, $tel]);
        }

        // On met à jour le MDP dans User car il n'est pas dans les tables filles
        $up = $db->prepare("UPDATE User SET mdp = ? WHERE email = ?");
        $up->execute([$mdp, $email]);

        echo "<script>alert('Inscription réussie !'); window.location.href='index.php?page=connexion';</script>";
    }
?>

<div class="container connexion-page">
    <link rel="stylesheet" href="Style/style_connexion.css">
    
    <h1>Créer un compte</h1>

    <form method="POST" action="">
        <div class="form-row">
            <div class="form-group">
                <label>Nom :</label>
                <input type="text" name="nom" required placeholder="Dupont">
            </div>
            <div class="form-group">
                <label>Prénom :</label>
                <input type="text" name="prenom" required placeholder="Jean">
            </div>
        </div>

        <label>Email :</label>
        <input type="email" name="email" required placeholder="jean.dupont@mail.com">

        <label>Téléphone :</label>
        <input type="text" name="tel" required placeholder="0601020304">

        <label>Mot de passe :</label>
        <input type="password" name="mdp" required placeholder="********">

        <div class="role-selection">
            <label>Je souhaite :</label>
            <div class="radio-group">
                <input type="radio" name="role" value="client" id="role_client" checked>
                <label for="role_client">Louer un logement</label>
                
                <input type="radio" name="role" value="proprietaire" id="role_proprio">
                <label for="role_proprio">Mettre en location</label>
            </div>
        </div>

        <button type="submit" name="btnInscription" class="btn-primary">S'inscrire</button>
    </form>

    <p>Déjà un compte ? <a href="index.php?page=connexion">Se connecter</a></p>
</div>