var_dump($_GET['id']);
var_dump($laFacture);
die();

<link rel="stylesheet" href="Style/style_facture.css">



<div class="facture-wrapper">

    <div class="facture-actions">
        <a href="index.php?page=profil" class="btn-back">
            <i class="fas fa-arrow-left"></i> Retour au profil
        </a>
        <button onclick="window.print()" class="btn-print">
            <i class="fas fa-print"></i> Imprimer / PDF
        </button>
    </div>

    <?php if(!$laFacture): ?>
        <p style="text-align:center;color:#8a9bb0;padding:60px;">Facture introuvable.</p>
    <?php else: ?>

    <div class="facture-document" id="facture-print">

        <!-- EN-TETE -->
        <div class="facture-header">
            <div class="facture-logo">
                <h1>❄ Neige &amp; Soleil</h1>
                <p>Agence de location en montagne</p>
                <p>Secteur Queyras, Alpes</p>
                <p>contact@neige-soleil.fr</p>
            </div>
            <div class="facture-meta">
                <div class="facture-num">FACTURE</div>
                <table class="meta-table">
                    <tr>
                        <td>N° facture</td>
                        <td><strong><?= htmlspecialchars($laFacture['num_facture']) ?></strong></td>
                    </tr>
                    <tr>
                        <td>Date d'émission</td>
                        <td><?= date('d/m/Y', strtotime($laFacture['date_emission'])) ?></td>
                    </tr>
                    <tr>
                        <td>Référence résa</td>
                        <td>#<?= $laFacture['id_reser'] ?? '' ?></td>
                    </tr>
                </table>
            </div>
        </div>

        <hr class="facture-divider">

        <!-- CLIENT -->
        <div class="facture-parties">
            <div class="partie">
                <h4>Émetteur</h4>
                <p><strong>Neige &amp; Soleil SARL</strong></p>
                <p>Secteur Queyras, Alpes</p>
                <p>SIRET : 123 456 789 00012</p>
                <p>TVA : FR12345678900</p>
            </div>
            <div class="partie">
                <h4>Client</h4>
                <p><strong><?= htmlspecialchars($laFacture['prenom'] . ' ' . $laFacture['nom']) ?></strong></p>
                <p><?= htmlspecialchars($laFacture['email']) ?></p>
                <?php if(!empty($laFacture['tel'])): ?>
                <p><?= htmlspecialchars($laFacture['tel']) ?></p>
                <?php endif; ?>
            </div>
        </div>

        <hr class="facture-divider">

        <!-- DETAIL PRESTATION -->
        <h4 class="section-label">Détail de la prestation</h4>
        <table class="facture-table">
            <thead>
                <tr>
                    <th>Désignation</th>
                    <th>Arrivée</th>
                    <th>Départ</th>
                    <th>Durée</th>
                    <th>Prix / nuit</th>
                    <th>Total HT</th>
                </tr>
            </thead>
            <tbody>
                <?php
                    $dateDebut  = new DateTime($laFacture['date_debut_loc']);
                    $dateFin    = new DateTime($laFacture['date_fin_loc']);
                    $nbJours    = max(1, $dateDebut->diff($dateFin)->days);
                    $prixNuit   = $laFacture['prix_hebdo'] / 7;
                    $montantHT  = round($laFacture['montant_ttc'] / 1.20, 2); // TVA 20%
                ?>
                <tr>
                    <td>
                        <strong><?= htmlspecialchars($laFacture['type_appart']) ?> — <?= htmlspecialchars($laFacture['num_appart']) ?></strong><br>
                        <span style="font-size:0.82rem;color:#8a9bb0;"><?= $laFacture['surface'] ?> m² · <?= $laFacture['capacite_accueil'] ?> pers. max</span>
                    </td>
                    <td><?= $dateDebut->format('d/m/Y') ?></td>
                    <td><?= $dateFin->format('d/m/Y') ?></td>
                    <td><?= $nbJours ?> nuit<?= $nbJours > 1 ? 's' : '' ?></td>
                    <td><?= number_format($prixNuit, 2, ',', ' ') ?> €</td>
                    <td><?= number_format($montantHT, 2, ',', ' ') ?> €</td>
                </tr>
            </tbody>
        </table>

        <!-- TOTAUX -->
        <div class="facture-totaux">
            <table class="totaux-table">
                <tr>
                    <td>Montant HT</td>
                    <td><?= number_format($montantHT, 2, ',', ' ') ?> €</td>
                </tr>
                <tr>
                    <td>TVA (20%)</td>
                    <td><?= number_format($laFacture['montant_ttc'] - $montantHT, 2, ',', ' ') ?> €</td>
                </tr>
                <tr class="total-ttc">
                    <td><strong>Total TTC</strong></td>
                    <td><strong><?= number_format($laFacture['montant_ttc'], 2, ',', ' ') ?> €</strong></td>
                </tr>
            </table>
        </div>

        <!-- PIED DE PAGE -->
        <div class="facture-footer">
            <p>Merci pour votre confiance. Neige &amp; Soleil vous souhaite un excellent séjour !</p>
            <p style="font-size:0.78rem;color:#aaa;margin-top:8px;">
                Document généré le <?= date('d/m/Y à H:i') ?> · Neige &amp; Soleil SARL · SIRET 123 456 789 00012
            </p>
        </div>

    </div>
    <?php endif; ?>
</div>
