CREATE VIEW v_pointage_secteur AS
    SELECT s.*, daty, heure_debut, heure_fin, SUM(nombre_personne) nombre_personne FROM batiment b 
        JOIN secteur s 
            ON b.id_secteur = s.id_secteur
        JOIN pointage p
            ON p.id_batiment = b.id_batiment
        GROUP BY s.id_secteur,daty,heure_debut,heure_fin;   