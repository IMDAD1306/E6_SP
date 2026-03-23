<link rel="stylesheet" href="Style/style_details.css">

<div class="container-detail">
    <a href="index.php?page=appartements" class="btn-back">
        <i class="fas fa-arrow-left"></i> Retour aux appartements
    </a>

    <div class="detail-grid">
        <div class="detail-gallery">
            <div class="main-photo">
                <img src="images/chalets/<?= $unAppart['id_appart'] ?>.jpg" 
                     alt="Vue principale"
                     onerror="this.src='images/background-montagne.jpg'">
            </div>
            <div class="secondary-photos">
                <?php for($i=1; $i<=2; $i++): ?>
                <div class="photo-item">
                    <img src="images/chalets/details/<?= $unAppart['id_appart'] ?>/<?= $i ?>.jpg" 
                         onerror="this.parentElement.style.display='none'">
                </div>
                <?php endfor; ?>
            </div>
        </div>

        <div class="detail-side-bar">
            <h1><?= htmlspecialchars($unAppart['type_appart']) ?> - <?= htmlspecialchars($unAppart['num_appart']) ?></h1>
            <p class="location"><i class="fas fa-map-marker-alt"></i> Secteur Queyras, Alpes</p>

            <div class="price-tag">
                <span class="amount"><?= number_format($unAppart['prix_hebdo'] / 7, 2, ',', ' ') ?> €</span> / nuit
                <div style="font-size: 0.85rem; color: #666; font-weight: normal; margin-top: 5px;">
                    Base : <?= number_format($unAppart['prix_hebdo'], 0, ',', ' ') ?> € / semaine
                </div>
            </div>

            <hr>

            <?php if (isset($_SESSION['erreur_resa'])): ?>
                <div class="alert-error" style="color: #721c24; background-color: #f8d7da; border: 1px solid #f5c6cb; padding: 12px; border-radius: 8px; margin-bottom: 20px; font-size: 0.9rem;">
                    <i class="fas fa-exclamation-circle"></i> <?= $_SESSION['erreur_resa']; unset($_SESSION['erreur_resa']); ?>
                </div>
            <?php endif; ?>

            <form action="index.php?page=ajout_panier" method="POST" class="form-resa">
                <input type="hidden" name="id_appart" value="<?= $unAppart['id_appart'] ?>">
                
                <div class="input-group">
                    <label><i class="far fa-calendar-plus"></i> Arrivée</label>
                    <input type="date" name="date_debut" required min="<?= date('Y-m-d') ?>">
                </div>
                
                <div class="input-group">
                    <label><i class="far fa-calendar-minus"></i> Départ</label>
                    <input type="date" name="date_fin" required min="<?= date('Y-m-d') ?>">
                </div>

                <?php if(isset($_SESSION['id_user'])): ?>
                    <button type="submit" name="btnReserver" class="btn-reserve">
                        <i class="fas fa-shopping-cart"></i> Ajouter au panier
                    </button>
                <?php else: ?>
                    <a href="index.php?page=connexion" class="btn-warn" style="display:block; text-align:center; text-decoration:none; background: #e67e22; color: white; padding: 12px; border-radius: 5px;">
                        Connectez-vous pour réserver
                    </a>
                <?php endif; ?>
            </form>
        </div>
    </div>

    <div class="detail-description">
        <h2>À propos de ce logement</h2>
        <div class="specs-icons" style="display: flex; gap: 20px; margin-bottom: 25px; color: #2c3e50;">
            <span><i class="fas fa-expand"></i> <strong><?= $unAppart['surface'] ?> m²</strong></span>
            <span><i class="fas fa-users"></i> <strong><?= $unAppart['capacite_accueil'] ?> pers.</strong></span>
            <span><i class="fas fa-mountain"></i> <strong>Vue dégagée</strong></span>
        </div>
        
        <div class="rooms-list" style="display: grid; grid-template-columns: 1fr 1fr; gap: 15px;">
            <div class="room-item"><i class="fas fa-wifi"></i> Wi-Fi Haut débit</div>
            <div class="room-item"><i class="fas fa-parking"></i> Parking gratuit</div>
            <div class="room-item"><i class="fas fa-snowflake"></i> Casier à skis</div>
            <div class="room-item"><i class="fas fa-coffee"></i> Cafetière & Cuisine équipée</div>
        </div>
    </div>
</div>

<script>
    const inputDebut = document.getElementsByName('date_debut')[0];
    const inputFin = document.getElementsByName('date_fin')[0];

    inputDebut.addEventListener('change', function() {
        if (this.value) {
            let dateMinFin = new Date(this.value);
            dateMinFin.setDate(dateMinFin.getDate() + 1);
            let formattedDate = dateMinFin.toISOString().split('T')[0];
            inputFin.min = formattedDate;
            if (inputFin.value && inputFin.value < formattedDate) {
                inputFin.value = formattedDate;
            }
        }
    });
</script>