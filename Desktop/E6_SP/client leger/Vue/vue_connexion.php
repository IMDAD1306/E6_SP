<div class="container connexion-page">
    <link rel="stylesheet" href="Style/style_connexion.css">
    <h1>Connexion</h1>

    <?php if (isset($erreur)) echo "<p class='msg'>$erreur</p>"; ?>

    <form method="POST">
        <label>Email :</label>
        <input type="email" name="email" required placeholder="votre email">

        <label>Mot de passe :</label>
        <input type="password" name="mdp" required placeholder="********">

        <button type="submit" name="btnConnexion" class="btn-primary">Se connecter</button>
    </form>

    <p>Pas de compte ? <a href="index.php?page=inscription">Créer un compte</a></p>
</div>