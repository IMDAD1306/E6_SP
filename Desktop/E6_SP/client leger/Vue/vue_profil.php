<div class="container profil-container">
 
    <!-- CARTE PROFIL -->
    <div class="profil-header-card">
        <div class="user-info">
            <div class="user-avatar">
                <?= strtoupper(substr($user['prenom'], 0, 1) . substr($user['nom'], 0, 1)); ?>
            </div>
            <div class="user-text">
                <h1><?= htmlspecialchars($user['prenom'] . ' ' . $user['nom']); ?></h1>
                <p class="user-email"><i class="fas fa-envelope" style="color:#c9a84c;margin-right:5px;"></i><?= htmlspecialchars($user['email']); ?></p>
                <?php if(!empty($user['tel'])): ?>
                <p class="user-email" style="margin-top:4px;"><i class="fas fa-phone" style="color:#4a90c4;margin-right:5px;"></i><?= htmlspecialchars($user['tel']); ?></p>
                <?php endif; ?>
            </div>
        </div>
    </div>
 
    <!-- HISTORIQUE -->
    <div class="historique-section">
        <h2><i class="fas fa-history" style="color:#c9a84c;margin-right:10px;"></i>Historique des réservations</h2>
 
        <?php if(empty($historique)): ?>
            <p style="color:#8a9bb0;text-align:center;padding:40px;">Aucune réservation validée pour le moment.</p>
        <?php else: ?>
        <table class="profil-table">
            <thead>
                <tr>
                    <th>Type</th>
                    <th>Désignation</th>
                    <th>Arrivée</th>
                    <th>Départ</th>
                    <th>Montant</th>
                    <th>Statut</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <?php foreach($historique as $resa): ?>
                <tr>
                    <td><?= ($resa['type'] == 'Appart') ? '🏠 Appart' : '🎿 Matériel'; ?></td>
                    <td><strong><?= htmlspecialchars($resa['designation']); ?></strong></td>
                    <td><?= date('d/m/Y', strtotime($resa['date_debut'])); ?></td>
                    <td><?= date('d/m/Y', strtotime($resa['date_fin'])); ?></td>
                    <td>
                        <?php if(!is_null($resa['montant'])): ?>
                            <strong><?= number_format($resa['montant'], 2, ',', ' '); ?> €</strong>
                        <?php else: ?>
                            <span style="color:#8a9bb0;">—</span>
                        <?php endif; ?>
                    </td>
                    <td>
                        <!-- FIX : on utilise libelle_statut et non statut -->
                        <span class="status-<?= ($resa['libelle_statut'] == 'Termine') ? 'done' : 'pending'; ?>">
                            <?= ($resa['libelle_statut'] == 'Termine') ? '✓ Terminé' : '⏳ En attente'; ?>
                        </span>
                    </td>
                    <td>
                        <?php if ($resa['libelle_statut'] == 'Termine' && !empty($resa['num_facture'])): ?>
                            <a href="index.php?page=facture&id=<?= $resa['id_reser']; ?>"
                               style="color:#1e4d7b;text-decoration:none;font-weight:600;font-size:0.88rem;">
                                <i class="fas fa-file-invoice"></i> Facture
                            </a>
                        <?php else: ?>
                            <span style="color:#c0c8d0;font-size:0.85rem;">—</span>
                        <?php endif; ?>
                    </td>
                </tr>
                <?php endforeach; ?>
            </tbody>
        </table>
        <?php endif; ?>
    </div>
</div>