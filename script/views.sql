    CREATE VIEW v_pointage_secteur AS
        SELECT s.*, daty, heure_debut, heure_fin, SUM(nombre_personne) nombre_personne FROM salle b 
            JOIN secteur s 
                ON b.id_secteur = s.id_secteur
            JOIN pointage p
                ON p.id_salle = b.id_salle
            GROUP BY s.id_secteur,daty,heure_debut,heure_fin;   

CREATE VIEW v_besoin_secteur_moyenne AS
    SELECT AVG(nombre_personne_matin) nombre_matin, AVG(nombre_personne_apres_midi) nombre_apres_midi, AVG(puissance_moyenne) besoin, id_secteur, daty FROM besoin_secteur
         GROUP BY id_secteur,daty;