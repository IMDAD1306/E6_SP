<link rel="stylesheet" href="Style/style_appartement.css">

<div class="container">
    <div class="apparts-grid">
        
        <?php foreach ($appartements as $unAppart) : ?>
            
            <article class="appart-card">
                
                <div class="appart-image">
                    <span class="price">
                        <strong><?= number_format($unAppart['prix_hebdo'] / 7, 1, ',', ' '); ?> €</strong> / nuit
                    </span>
                    
                    <img src="images/chalets/<?= htmlspecialchars($unAppart['image']); ?>" 
                         alt="Appartement <?= htmlspecialchars($unAppart['num_appart']); ?>" 
                         onerror="this.src='images/background-montagne.jpg';">
                </div>

                <div class="appart-details">
                    <h3>
                        <?= htmlspecialchars($unAppart['type_appart']); ?> - 
                        <?= htmlspecialchars($unAppart['num_appart']); ?>
                    </h3>

                    <p class="location"><i class="fas fa-map-marker-alt"></i> Station Neige & Soleil</p>
                    
                    <div class="specs">
                        <span>
                            <i class="fas fa-expand"></i> 
                            <?= $unAppart['surface']; ?> m²
                        </span>

                        <span>
                            <i class="fas fa-users"></i> 
                            <?= $unAppart['capacite_accueil']; ?> pers.
                        </span>
                    </div>
                    
                    <div class="price-info-low" style="font-size: 0.8rem; color: #777; margin-bottom: 10px;">
                        Soit <?= number_format($unAppart['prix_hebdo'], 0, ',', ' '); ?> € la semaine
                    </div>

                    <a href="index.php?page=detail&id=<?= $unAppart['id_appart']; ?>" 
                       class="btn-view">
                        <i class="fas fa-search-plus"></i> Voir les détails
                    </a>
                </div>
                
            </article>

        <?php endforeach; ?>

    </div>
</div>