<link rel="stylesheet" href="Style/style_panier.css">
 
<div class="panier-wrapper">
    <h1><i class="fas fa-shopping-basket"></i> Mon Panier</h1>
 
    <?php if (empty($lesReservations)) : ?>
        <div class="alert-empty">
            <p style="font-size:3rem; margin-bottom:16px;">🛒</p>
            <p>Votre panier est vide pour le moment.</p>
            <a href="index.php?page=appartements" class="btn-blue">Voir nos locations</a>
        </div>
    <?php else : ?>
        <?php $grandTotal = 0; ?>
 
        <div class="panier-grid">
            <div class="panier-items">
                <?php foreach ($lesReservations as $uneResa) :
                    $date1   = new DateTime($uneResa['date_debut_loc']);
                    $date2   = new DateTime($uneResa['date_fin_loc']);
                    $diff    = $date1->diff($date2);
                    $nbJours = ($diff->days == 0) ? 1 : $diff->days;
                    $prixLigne = ($uneResa['prix_hebdo'] / 7) * $nbJours;
                    $grandTotal += $prixLigne;
                ?>
                    <div class="panier-card">
                        <img src="images/chalets/<?= $uneResa['id_appart'] ?>.jpg"
                             alt="Appart"
                             onerror="this.src='images/background-montagne.jpg'">
 
                        <div class="panier-info">
                            <h3><?= htmlspecialchars($uneResa['type_appart']) ?> — <?= htmlspecialchars($uneResa['num_appart']) ?></h3>
                            <p><i class="far fa-calendar-check"></i> Arrivée : <?= $date1->format('d/m/Y') ?></p>
                            <p><i class="far fa-calendar-times"></i> Départ : <?= $date2->format('d/m/Y') ?></p>
                            <p><i class="fas fa-moon"></i> <?= $nbJours ?> nuit<?= $nbJours > 1 ? 's' : '' ?></p>
                            <p class="sous-total"><?= number_format($prixLigne, 2, ',', ' ') ?> €</p>
                        </div>
 
                        <div class="panier-actions">
                            <a href="index.php?page=panier&action=supprimer&id_reser=<?= $uneResa['id_reser'] ?>"
                               class="btn-delete"
                               onclick="return confirm('Retirer cette réservation ?')">
                                <i class="fas fa-trash"></i>
                            </a>
                        </div>
                    </div>
                <?php endforeach; ?>
            </div>
 
            <div class="panier-summary">
                <h3>Récapitulatif</h3>
 
                <?php foreach ($lesReservations as $r):
                    $d1 = new DateTime($r['date_debut_loc']);
                    $d2 = new DateTime($r['date_fin_loc']);
                    $nj = max(1, $d1->diff($d2)->days);
                    $pl = ($r['prix_hebdo'] / 7) * $nj;
                ?>
                <div class="summary-line">
                    <span><?= htmlspecialchars($r['num_appart']) ?> (<?= $nj ?> nuit<?= $nj > 1 ? 's' : '' ?>)</span>
                    <span><?= number_format($pl, 2, ',', ' ') ?> €</span>
                </div>
                <?php endforeach; ?>
 
                <div class="summary-total">
                    <span>Total</span>
                    <span class="total-amount"><?= number_format($grandTotal, 2, ',', ' ') ?> €</span>
                </div>
 
                <form action="index.php?page=valider_resa" method="POST">
                    <?php foreach ($lesReservations as $r): ?>
                        <input type="hidden" name="id_reser[]" value="<?= $r['id_reser']; ?>">
                    <?php endforeach; ?>
                    <input type="hidden" name="montant" value="<?= $grandTotal; ?>">
                    <button type="submit" class="btn-pay">
                        <i class="fas fa-check-circle"></i> Valider &amp; Payer
                    </button>
                </form>
            </div>
        </div>
    <?php endif; ?>
</div>