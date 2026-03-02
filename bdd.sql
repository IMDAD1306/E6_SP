-- Création de la base de données
CREATE DATABASE IF NOT EXISTS neige_soleil;
USE neige_soleil;

-- 1. Table PERSONNEL (Les employés de l'agence)
CREATE TABLE User (
    id_perso INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    email VARCHAR(50),
    mdp VARCHAR(50),
    prenom VARCHAR(50) NOT NULL,
    role VARCHAR(50) -- Directeur, Secrétaire, Commercial, Ouvrier
);
ALTER TABLE User ADD COLUMN tel VARCHAR(20);

-- 2. Table PROPRIETAIRE
CREATE TABLE PROPRIETAIRE (
    
    id_proprio INT PRIMARY KEY  ,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    adresse VARCHAR(150),
    tel VARCHAR(20),
    email VARCHAR(100) UNIQUE,
    iban VARCHAR(34)
);
ALTER TABLE APPARTEMENT 
ADD COLUMN exposition VARCHAR(50) AFTER capacite_accueil,
ADD COLUMN distance_pistes INT AFTER exposition;

-- 3. Table APPARTEMENT
CREATE TABLE APPARTEMENT (
    id_appart INT AUTO_INCREMENT PRIMARY KEY,
    num_appart VARCHAR(10),
    type_appart VARCHAR(50),
    surface DECIMAL(5,2),
    capacite_accueil INT,
    id_proprio INT,
    FOREIGN KEY (id_proprio) REFERENCES PROPRIETAIRE(id_proprio)
);
 ALTER TABLE APPARTEMENT
    -> ADD COLUMN prix_hebdo DECIMAL(10,2) DEFAULT 450.00;

ALTER TABLE APPARTEMENT ADD COLUMN image VARCHAR(100) DEFAULT 'default.jpg';

-- 4. Table CONTRAT (Mandat de gestion)
CREATE TABLE CONTRAT (
    id_contrat INT AUTO_INCREMENT PRIMARY KEY,
    date_debut DATE NOT NULL, -- Doit être le 1er Octobre
    date_fin DATE NOT NULL,   -- Doit être le 30 Septembre
    tarif_saison DECIMAL(10,2),
    statut_archive BOOLEAN DEFAULT FALSE, -- Pour la conservation de 5 ans
    id_appart INT UNIQUE, -- 1 Contrat = 1 Appartement
    FOREIGN KEY (id_appart) REFERENCES APPARTEMENT(id_appart)
);

-- 5. Table EQUIPEMENT
CREATE TABLE EQUIPEMENT (
    id_equip INT AUTO_INCREMENT PRIMARY KEY,
    libelle_equip VARCHAR(100) NOT NULL
);

-- 6. Table CLIENT (Les vacanciers)
CREATE TABLE CLIENT (
    id_client INT  PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE,
    tel VARCHAR(20)
);
CREATE TABLE PERSONNEL (
    id_employe INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    tel VARCHAR(20),
    role VARCHAR(50) 
);

-- 7. Table RESERVATION
CREATE TABLE RESERVATION (
    id_reser INT AUTO_INCREMENT PRIMARY KEY,
    date_debut_loc DATE NOT NULL,
    date_fin_loc DATE NOT NULL,
    nb_personnes INT,
    id_client INT,
    id_appart INT,
    id_employe INT,
    FOREIGN KEY (id_client) REFERENCES CLIENT(id_client),
    FOREIGN KEY (id_appart) REFERENCES APPARTEMENT(id_appart),
    FOREIGN KEY (id_employe) REFERENCES PERSONNEL(id_employe)
);

-- 8. Table PAIEMENT
CREATE TABLE PAIEMENT (
    id_paiement INT AUTO_INCREMENT PRIMARY KEY,
    montant DECIMAL(10,2) NOT NULL,
    date_paiement DATE,
    mode_paiement VARCHAR(50),
    id_reser INT UNIQUE,
    -- Ajout du CASCADE pour éviter les blocages
    FOREIGN KEY (id_reser) REFERENCES RESERVATION(id_reser) ON DELETE CASCADE
);
-- 9. Table MATERIEL (Ski, Rando)
CREATE TABLE MATERIEL (
    id_mat INT AUTO_INCREMENT PRIMARY KEY,
    libelle_mat VARCHAR(100),
    type_mat VARCHAR(50),
    etat VARCHAR(50),
    prix_jour DECIMAL(5,2)
);

-- 10. Table de liaison : DISPOSER (Appartement <-> Equipement)
CREATE TABLE DISPOSER (
    id_appart INT,
    id_equip INT,
    PRIMARY KEY (id_appart, id_equip),
    FOREIGN KEY (id_appart) REFERENCES APPARTEMENT(id_appart),
    FOREIGN KEY (id_equip) REFERENCES EQUIPEMENT(id_equip)
);

-- 11. Table de liaison : LOUER_MATERIEL (Client <-> Materiel)
CREATE TABLE LOUER_MATERIEL (
    id_loc_mat INT AUTO_INCREMENT PRIMARY KEY,
    date_debut DATE,
    date_fin DATE,
    id_client INT,
    id_mat INT,
    FOREIGN KEY (id_client) REFERENCES CLIENT(id_client),
    FOREIGN KEY (id_mat) REFERENCES MATERIEL(id_mat)
);


 





INSERT INTO APPARTEMENT (num_appart, type_appart, surface, capacite_accueil, id_proprio, image) 
VALUES 
('A101', 'Chalet', 60.00, 6, 1, '1.jpg'),
('A102', 'Studio', 25.00, 2, 1, '2.jpg'),
('A103', 'Appartement', 45.00, 4, 1, '3.jpg'),
('A104', 'Studio', 22.00, 2, 1, '4.jpg'),
('A105', 'Appartement', 75.00, 8, 1, '5.jpg'),
('A106', 'Chalet', 150.00, 12, 1, '6.jpg'),
('A107', 'Studio', 20.00, 2, 1, '7.jpg'),
('A108', 'T3', 55.00, 5, 1, '8.jpg'),
('A109', 'Chalet', 90.00, 8, 1, '9.jpg'),
('A110', 'Appartement', 40.00, 4, 1, '10.jpg');


DROP TRIGGER IF EXISTS insert_client;
DELIMITER //
CREATE TRIGGER insert_client
BEFORE INSERT ON CLIENT
FOR EACH ROW
BEGIN
    -- Calcul de l'ID manuel (Méthode Prof)
    IF NEW.id_client IS NULL OR NEW.id_client IN (SELECT id_perso FROM User) OR NEW.id_client = 0 THEN
        SET NEW.id_client = IFNULL((SELECT MAX(id_perso) FROM User), 0) + 1;
    END IF;

    -- On pousse les infos dans la table mère
    INSERT INTO User (id_perso, nom, prenom, email, tel, role)
    VALUES (NEW.id_client, NEW.nom, NEW.prenom, NEW.email, NEW.tel, 'client');
END //
DELIMITER ;


DROP TRIGGER IF EXISTS update_client;
DELIMITER //
CREATE TRIGGER update_client
BEFORE UPDATE ON CLIENT
FOR EACH ROW
BEGIN
    UPDATE User 
    SET nom = NEW.nom, 
        prenom = NEW.prenom, 
        email = NEW.email, 
        tel = NEW.tel
    WHERE id_perso = OLD.id_client;
END //
DELIMITER ;


DROP TRIGGER IF EXISTS delete_client;
DELIMITER //
CREATE TRIGGER delete_client
BEFORE DELETE ON CLIENT
FOR EACH ROW
BEGIN
    DELETE FROM User WHERE id_perso = OLD.id_client;
END //
DELIMITER ;

DROP TRIGGER IF EXISTS insert_proprio;
DELIMITER //
CREATE TRIGGER insert_proprio
BEFORE INSERT ON PROPRIETAIRE
FOR EACH ROW
BEGIN
    -- Calcul de l'ID manuel (Méthode Prof)
    IF NEW.id_proprio IS NULL OR NEW.id_proprio IN (SELECT id_perso FROM User) OR NEW.id_proprio = 0 THEN
        SET NEW.id_proprio = IFNULL((SELECT MAX(id_perso) FROM User), 0) + 1;
    END IF;

    -- Insertion automatique dans la table mère User
    INSERT INTO User (id_perso, nom, prenom, email, tel, role)
    VALUES (NEW.id_proprio, NEW.nom, NEW.prenom, NEW.email, NEW.tel, 'proprietaire');
END //
DELIMITER ;

DROP TRIGGER IF EXISTS update_proprio;
DELIMITER //
CREATE TRIGGER update_proprio
BEFORE UPDATE ON PROPRIETAIRE
FOR EACH ROW
BEGIN
    UPDATE User 
    SET nom = NEW.nom, 
        prenom = NEW.prenom, 
        email = NEW.email, 
        tel = NEW.tel
    WHERE id_perso = OLD.id_proprio;
END //
DELIMITER ;


DROP TRIGGER IF EXISTS delete_proprio;
DELIMITER //
CREATE TRIGGER delete_proprio
BEFORE DELETE ON PROPRIETAIRE
FOR EACH ROW
BEGIN
    DELETE FROM User WHERE id_perso = OLD.id_proprio;
END //
DELIMITER ;


INSERT INTO APPARTEMENT (num_appart, type_appart, surface, capacite_accueil, id_proprio, image) 
VALUES 
('A101', 'Chalet', 60.00, 6, 1, '1.jpg'),
('A102', 'Studio', 25.00, 2, 1, '2.jpg'),
('A103', 'Appartement', 45.00, 4, 1, '3.jpg'),
('A104', 'Studio', 22.00, 2, 1, '4.jpg'),
('A105', 'Appartement', 75.00, 8, 1, '5.jpg'),
('A106', 'Chalet', 150.00, 12, 1, '6.jpg'),
('A107', 'Studio', 20.00, 2, 1, '7.jpg'),
('A108', 'T3', 55.00, 5, 1, '8.jpg'),
('A109', 'Chalet', 90.00, 8, 1, '9.jpg'),
('A110', 'Appartement', 40.00, 4, 1, '10.jpg');