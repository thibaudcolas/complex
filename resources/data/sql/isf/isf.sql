-- il faut mettre le bon chemin vers les fichiers. 
-- doc : pour les COPY FROM : Le chemin est interprété relativement au répertoire de travail du processus serveur (habituellement dans le répertoire des données), pas par rapport au répertoire de travail du client.


DROP TABLE IMPOT;

CREATE TABLE IMPOT (
  CodeINSEE char(5),
  NbRedevables integer,
  PatrimoineMoyen integer,
  ImpotMoyen integer,
  Annee integer,
  PRIMARY KEY (CodeINSEE, Annee)
);




COPY IMPOT
    FROM '/home/mathilde/isf/isf_2006.csv'
    WITH  DELIMITER  AS  E'\t';

COPY IMPOT
    FROM '/home/mathilde/isf/isf_2007.csv'
    WITH  DELIMITER  AS  E'\t';

COPY IMPOT
    FROM '/home/mathilde/isf/isf_2008.csv'
    WITH  DELIMITER  AS  E'\t';

COPY IMPOT
    FROM '/home/mathilde/isf/isf_2009.csv'
    WITH  DELIMITER  AS  E'\t';

COPY IMPOT
    FROM '/home/mathilde/isf/isf_2010.csv'
    WITH  DELIMITER  AS  E'\t';
