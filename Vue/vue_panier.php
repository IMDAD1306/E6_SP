<link rel="stylesheet" href="Style/style_panier.css">

<div class="container mt-5">
    <h1 class="mb-4"><i class="fas fa-shopping-basket"></i> Mon Panier</h1>

    <?php if (empty($lesReservations)) : ?>
        <div class="alert-empty">
            <p>Votre panier est vide pour le moment.</p>
            <a href="index.php?page=appartements" class="btn-blue">Voir nos locations</a>
        </div>
    <?php else : ?>
        <?php $grandTotal = 0; ?>

        <div class="panier-grid">
            <div class="panier-items">
                <?php foreach ($lesReservations as $uneResa) : 
                    // CALCULS DES DATES
                    $date1 = new DateTime($uneResa['date_debut_loc']);
                    $date2 = new DateTime($uneResa['date_fin_loc']);
                    $diff = $date1->diff($date2);
                    $nbJours = $diff->days;

                    if ($nbJours == 0) $nbJours = 1; 
                    
                    // CALCUL AU PRORATA
                    $prixLigne = ($uneResa['prix_hebdo'] / 7) * $nbJours;
                    $grandTotal += $prixLigne;
                ?>
                    <div class="panier-card">
                        <img src="images/chalets/<?= $uneResa['id_appart'] ?>.jpg" 
                             alt="Appart" 
                             onerror="this.src='images/background-montagne.jpg'">
                        
                        <div class="panier-info">
                            <h3><?= htmlspecialchars($uneResa['type_appart']) ?> - <?= htmlspecialchars($uneResa['num_appart']) ?></h3>
                            <p><i class="far fa-calendar-alt"></i> Du <?= $date1->format('d/m/Y') ?> au <?= $date2->format('d/m/Y') ?></p>
                            <p><i class="fas fa-clock"></i> Durée : <?= $nbJours ?> jour(s)</p>
                            <p style="font-size: 0.85rem; color: #777;">Base hebdo : <?= number_format($uneResa['prix_hebdo'], 2, ',', ' ') ?> €</p>
                            <p><strong>Sous-total : <?= number_format($prixLigne, 2, ',', ' ') ?> €</strong></p>
                        </div>
                        
                        <div class="panier-actions">
                            <a href="index.php?page=panier&action=supprimer&id_reser=<?= $uneResa['id_reser'] ?>" 
                               class="btn-delete" onclick="return confirm('Voulez-vous vraiment retirer cette location ?')">
                                <i class="fas fa-trash"></i>
                            </a>
                        </div>
                    </div>
                <?php endforeach; ?>
            </div>

            <div class="panier-summary">
                <h3>Récapitulatif</h3>
                <p>Locations : <strong><?= count($lesReservations) ?></strong></p>
                <hr>
                <p style="font-size: 1.1rem;">Montant total : <br>
                   <strong style="color: #2ecc71; font-size: 1.6rem;">
                       <?= number_format($grandTotal, 2, ',', ' ') ?> €
                   </strong>
                </p>
                <hr>
                <button class="btn-pay" onclick="alert('Redirection vers la banque...')">
                    <i class="fas fa-credit-card"></i> Confirmer et Payer
                </button>
            </div>
        </div>
    <?php endif; ?>
</div>