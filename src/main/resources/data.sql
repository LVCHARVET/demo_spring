-- Insertion des départements
INSERT INTO Departement (code, nom_departement) VALUES 
('75', 'Paris'),
('69', 'Rhone'),
('13', 'Bouches-du-Rhône'),
('06', 'Alpes-Maritimes');

-- Insertion des villes
INSERT INTO Ville (nom_ville, nb_habitants, departement_id) VALUES 
('Paris', 2000000, 1),
('Lyon', 500000, 2),
('Marseille', 850000, 3),
('Nice', 350000, 4); 