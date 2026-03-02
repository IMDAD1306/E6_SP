<header>
    <div class="logo">Neige & Soleil</div>
    <nav>
        <ul>
            <li><a href="index.php?page=accueil">Accueil</a></li>
            <li><a href="index.php?page=appartements">Appartements</a></li>
            <li><a href="index.php?page=materiel">Matériel</a></li>

            <?php if (isset($_SESSION['id_user'])): ?>
                <?php 
                    // Sécurité : on vérifie si le rôle existe, sinon on met 'client' par défaut
                    $role = isset($_SESSION['role']) ? $_SESSION['role'] : 'client'; 
                ?>

                <?php if ($role == 'proprietaire'): ?>
                    <li><a href="index.php?page=mes_apparts">Mes Appartements</a></li>
                    <li><a href="index.php?page=contrats">Mes Contrats</a></li>
                <?php else: ?>
                    <li><a href="index.php?page=panier">
                        <i class="fas fa-shopping-cart"></i> Mon Panier
                    </a></li>
                <?php endif; ?>

                <li><a href="index.php?page=profil" class="btn-nav">
                    <i class="fas fa-user-circle"></i> Mon Profil
                </a></li>
                <li><a href="index.php?page=deconnexion" class="btn-connexion logout">
                    <i class="fas fa-sign-out-alt"></i> Déconnexion
                </a></li>
            
            <?php else: ?>
                <li><a href="index.php?page=connexion" class="btn-connexion">
                    <i class="fas fa-user"></i> Connexion / Inscription
                </a></li>
            <?php endif; ?>
        </ul>
    </nav>
</header>