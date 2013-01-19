DROP TABLE IMPOT;

CREATE TABLE IMPOT (
  CodeINSEE char(5),
  NbRedevables integer,
  PatrimoineMoyen integer,
  ImpotMoyen integer,
  Annee integer,
  PRIMARY KEY (CodeINSEE, Annee)
);


