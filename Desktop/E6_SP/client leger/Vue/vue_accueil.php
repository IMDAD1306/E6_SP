<link rel="stylesheet" href="Style/style_accueil.css">
 
<!-- HERO TEXT -->
<div class="accueil-intro">
    <h1>Votre séjour à la <span>montagne</span><br>commence ici</h1>
    <p>Découvrez nos appartements et chalets soigneusement sélectionnés au cœur des Alpes. Confort, authenticité et grand air garantis.</p>
    <a href="index.php?page=appartements" class="btn-discover">
        <i class="fas fa-mountain"></i> Explorer nos logements
    </a>
</div>
 
<!-- BANDEAU FEATURES -->
<div class="features-strip">
    <div class="features-inner">
        <div class="feature-item">
            <i class="fas fa-skiing"></i>
            <span>Accès direct aux pistes</span>
        </div>
        <div class="feature-item">
            <i class="fas fa-home"></i>
            <span>Logements de qualité</span>
        </div>
        <div class="feature-item">
            <i class="fas fa-tools"></i>
            <span>Location de matériel</span>
        </div>
        <div class="feature-item">
            <i class="fas fa-headset"></i>
            <span>Support 7j/7</span>
        </div>
        <div class="feature-item">
            <i class="fas fa-shield-alt"></i>
            <span>Réservation sécurisée</span>
        </div>
    </div>
</div>
 
<!-- APPARTEMENTS VEDETTES -->
<div class="vedettes-section">
    <h2 class="section-title">Nos coups de cœur</h2>
    <p class="section-sub">Une sélection de nos meilleurs logements disponibles cette saison</p>
 
    <div class="vedettes-grid">
        <?php if (!empty($appartementsVedettes)): ?>
            <?php foreach ($appartementsVedettes as $appart): ?>
                <article class="appart-card">
                    <div class="appart-image">
                        <span class="price">
                            <strong><?= number_format($appart['prix_hebdo'] / 7, 0, ',', ' ') ?> €</strong> / nuit
                        </span>
                        <img src="images/chalets/<?= htmlspecialchars($appart['image']) ?>"
                             alt="<?= htmlspecialchars($appart['type_appart']) ?>"
                             onerror="this.src='images/background-montagne.jpg'">
                    </div>
                    <div class="appart-details">
                        <h3><?= htmlspecialchars($appart['type_appart']) ?> — <?= htmlspecialchars($appart['num_appart']) ?></h3>
                        <p class="location"><i class="fas fa-map-marker-alt"></i> Station Neige &amp; Soleil</p>
                        <div class="specs">
                            <span><i class="fas fa-expand"></i> <?= $appart['surface'] ?> m²</span>
                            <span><i class="fas fa-users"></i> <?= $appart['capacite_accueil'] ?> pers.</span>
                        </div>
                        <div class="price-info-low">
                            Soit <?= number_format($appart['prix_hebdo'], 0, ',', ' ') ?> € / semaine
                        </div>
                        <a href="index.php?page=detail&id=<?= $appart['id_appart'] ?>" class="btn-view">
                            <i class="fas fa-search-plus"></i> Voir les détails
                        </a>
                    </div>
                </article>
            <?php endforeach; ?>
        <?php else: ?>
            <p style="color:#8a9bb0;text-align:center;grid-column:1/-1;padding:40px;">
                Aucun appartement disponible pour le moment.
            </p>
        <?php endif; ?>
    </div>
 
    <div style="text-align:center;margin-top:30px;">
        <a href="index.php?page=appartements" class="btn-discover">
            <i class="fas fa-list"></i> Voir tous les appartements
        </a>
    </div>
</div>
 
<!-- CTA MATERIEL -->
<div class="cta-section">
    <h2>Besoin de matériel de ski ?</h2>
    <p>Skis, snowboards, casques, chaussures… Louez tout sur place en quelques clics.</p>
    <a href="index.php?page=materiel" class="btn-or">
        <i class="fas fa-skiing"></i> Voir le matériel disponible
    </a>
</div>