<div class="container">
    <section class="intro">
        <h1>Location de Matériel</h1>
        <p>Équipez-vous avec le meilleur matériel pour vos sorties en montagne.</p>
    </section>

    <div class="chalet-list">
        <?php if (isset($lesMateriels) && count($lesMateriels) > 0): ?>
            <?php foreach ($lesMateriels as $unMateriel): ?>
                <article class="card">
                    <img src="images/materiels/<?= $unMateriel['type_materiel'] ?>.jpg" alt="<?= $unMateriel['nom_materiel'] ?>" onerror="this.src='images/default-ski.jpg'">
                    
                    <div class="card-body">
                        <h3><?= $unMateriel['nom_materiel'] ?></h3>
                        <p class="description"><?= $unMateriel['description'] ?></p>
                        <div class="price-tag"><?= $unMateriel['prix_journalier'] ?>€ / jour</div>
                        
                        <a href="index.php?page=panier&action=add&id=<?= $unMateriel['id_materiel'] ?>" class="btn-blue">
                            Louer ce matériel
                        </a>
                    </div>
                </article>
            <?php endforeach; ?>
        <?php else: ?>
            <p>Aucun matériel disponible pour le moment.</p>
        <?php endif; ?>
    </div>
</div>