CREATE SEQUENCE seq_source START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_secteur START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_salle START WITH 1 INCREMENT BY 1;

CREATE TABLE meteo(
   id SERIAL,
   date_meteo DATE,
   heure_debut TIME,
   heure_fin TIME,
   luminosite DOUBLE PRECISION ,
   PRIMARY KEY(id)
);

CREATE TABLE Secteur(
   id_secteur VARCHAR(50) ,
   nom VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id_secteur)
);

CREATE TABLE Source_solaire(
   id_source VARCHAR(50) ,
   puissance_max DOUBLE PRECISION  NOT NULL,
   reserve_max_batterie DOUBLE PRECISION  NOT NULL,
   id_secteur VARCHAR(50) ,
   PRIMARY KEY(id_source),
   UNIQUE(id_secteur),
   FOREIGN KEY(id_secteur) REFERENCES Secteur(id_secteur)
);

CREATE TABLE salle(
   id_salle VARCHAR(50) ,
   nom VARCHAR(50)  NOT NULL,
   capacite_max INTEGER NOT NULL,
   id_secteur VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id_salle),
   FOREIGN KEY(id_secteur) REFERENCES Secteur(id_secteur)
);

CREATE TABLE details(
   id SERIAL,
   date_details DATE,
   heure TIME,
   secteur VARCHAR(50) NOT NULL,
   puissance_delivree DOUBLE PRECISION ,
   puissance_delivree_batterie DOUBLE PRECISION ,
   reserve_batterie DOUBLE PRECISION ,
   besoin DOUBLE PRECISION ,
   PRIMARY KEY(id)
);

ALTER TABLE details ADD COLUMN etat VARCHAR(50);



CREATE TABLE besoin_secteur(
   id_besoin SERIAL,
   daty DATE,
   nombre_personne_matin INTEGER,
   nombre_personne_apres_midi INTEGER,
   puissance_moyenne DOUBLE PRECISION,
   id_secteur VARCHAR REFERENCES secteur(id_secteur),
   PRIMARY KEY(id_besoin)
);

CREATE TABLE pointage(
   id SERIAL,
   id_salle VARCHAR REFERENCES salle(id_salle),
   daty DATE,
   heure_debut TIME,
   heure_fin TIME,
   nombre_personne INT
);

CREATE FUNCTION f_get_day_of_week(daty DATE)
   RETURNS VARCHAR(50)
   LANGUAGE  plpgsql
   AS $$
   DECLARE
      day_name VARCHAR(50);
   BEGIN
      SELECT TO_CHAR(DATE daty, 'DAY');
      RETURN day_name;
   END;
   $$ ;

SELECT * FROM besoin_secteur WHERE 
