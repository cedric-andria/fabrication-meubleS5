create database fabricationmeuble;
create user fabricationmeuble with password 'fabricationmeuble';
grant all privileges on database fabricationmeuble to fabricationmeuble;
\c fabricationmeuble fabricationmeuble;

create table Materiau(
    id serial primary key,
    nom varchar(100)
);
create table Categorie(
    id serial primary key,
    nom varchar(100)
);
create table Volume(
    id serial primary key,
    nom VARCHAR(50)
);
create table Style(
    id serial primary key,
    nom varchar(100)
);
create table Style_Materiau(
    id serial primary key,
    idStyle int references Style(id),
    idMateriau int references Materiau(id),
    constraint id_style_materiau_combination UNIQUE (idStyle, idMateriau)
);
create table Ouvrier(
    id serial primary key,
    description varchar(50),
    taux_horaire int
);
create table Volume_Nb_Ouvrier (
    id serial primary key,
    idVolume int references Volume(id),
    nb_ouvrier int
);

create table Formule_meuble(
    id serial primary key,
    idCategorie int references Categorie(id),
    idStyle int references Style(id),
    idVolume int references Volume(id),
    idMateriau int references Materiau(id),
    quantite double precision,
    duree_travail double precision,
    idOuvrier int references Ouvrier(id)
);

-- insert into Ouvrier(description, taux_horaire) values ("Rabe", 5000);
-- insert into Ouvrier(description, taux_horaire) values ("Rakoto", 8000);
-- insert into Ouvrier(description, taux_horaire) values ("Jean", 20000);


insert into Materiau(nom) values('Bois');
insert into Materiau(nom) values('Metal');
insert into Materiau(nom) values('Plastique');
insert into Materiau(nom) values('Vernis');
insert into Materiau(nom) values('Polystyrene');
-- insert into Materiau(nom) values('Tissu');
-- insert into Materiau(nom) values('Perle');
-- insert into Materiau(nom) values('Chaine');

insert into Categorie(nom) values('Chaise');
insert into Categorie(nom) values('Table');
insert into Categorie(nom) values('Lit');
-- insert into Categorie(nom) values('Canape');

insert into Style(nom) values ('Royal');
-- insert into Style(nom) values ('Boheme');
insert into Style(nom) values ('Scandinave');

-- insert into volume(nom) values ('petit');
-- insert into volume(nom) values ('moyen');
-- insert into volume(nom) values ('grand');


insert into Style_Materiau(idStyle, idMateriau) values (1,1);
insert into Style_Materiau(idStyle, idMateriau) values (1,2);
insert into Style_Materiau(idStyle, idMateriau) values (1,3);

-- insert into Style_Materiau(idStyle, idMateriau) values (2,6);
-- insert into Style_Materiau(idStyle, idMateriau) values (2,7);
-- insert into Style_Materiau(idStyle, idMateriau) values (2,8);

insert into Style_Materiau(idStyle, idMateriau) values (3,1);
insert into Style_Materiau(idStyle, idMateriau) values (3,4);


-- insert into Formule_meuble(idCategorie, idStyle, idVolume, idMateriau, quantite) values (4, 2, 1, 6, 2);
-- insert into Formule_meuble(idCategorie, idStyle, idVolume, idMateriau, quantite) values (4, 2, 1, 7, 10);
-- insert into Formule_meuble(idCategorie, idStyle, idVolume, idMateriau, quantite) values (4, 2, 1, 8, 3);



create table PrixUnitaireMateriau(
    id serial primary key,
    idMateriau int references Materiau(id),
    montant double precision,
    submit_date timestamp DEFAULT current_timestamp
);
insert into PrixUnitaireMateriau (idMateriau, montant) values (1, 500);
insert into PrixUnitaireMateriau (idMateriau, montant) values (2, 700);
insert into PrixUnitaireMateriau (idMateriau, montant) values (3, 1500);

-- insert into PrixUnitaireMateriau (idMateriau, montant) values (6, 200);
-- insert into PrixUnitaireMateriau (idMateriau, montant) values (7, 500);
-- insert into PrixUnitaireMateriau (idMateriau, montant) values (8, 800);

-- SELECT fm.idcategorie,                                                  
-- fm.idstyle,                                                          
-- fm.idvolume,                                                         
-- sum((fm.quantite * pum.montant)) AS cout                             
-- FROM (formule_meuble fm JOIN prixunitairemateriau pum ON ((fm.idmateriau = pum.idmateriau)))
-- GROUP BY fm.idcategorie, fm.idstyle, fm.idvolume;

create or replace view MeubleCostView as SELECT fm.idCategorie, fm.idStyle, fm.idVolume, 
       SUM(fm.quantite * pum.montant) AS cout 
FROM Formule_meuble fm 
JOIN PrixUnitaireMateriau pum ON fm.idMateriau = pum.idMateriau 
JOIN (
    SELECT idMateriau, MAX(submit_date) AS maxDate 
    FROM PrixUnitaireMateriau 
    GROUP BY idMateriau
) latestPrice ON pum.idMateriau = latestPrice.idMateriau AND pum.submit_date = latestPrice.maxDate 
GROUP BY fm.idCategorie, fm.idStyle, fm.idVolume;
--tsy asiana cout humain fa any anaty metier

select distinct on (idcategorie, idstyle, idvolume) id, idcategorie, idstyle, idvolume, idmateriau, quantite from formule_meuble;


create table PrixVente(
    id serial primary key,
    idCategorie int references Categorie(id),
    idStyle int references Style(id),
    idVolume int references Volume(id),
    prix double precision,
    submit_date timestamp
);
-- alea 16/01

create table AchatMateriau(
    id serial PRIMARY KEY,
    idMateriau int REFERENCES Materiau(id),
    quantite int,
    date_achat timestamp
);

CREATE table StockMateriau(
    id serial primary key,
    idMateriau int REFERENCES Materiau(id),
    stock int,
    date_stock timestamp 
);

SELECT DISTINCT ON (idMateriau) id, idMateriau, stock, date_stock FROM StockMateriau ORDER BY idMateriau DESC;

create table Fabrication(
    id SERIAL primary key,
    idCategorie int REFERENCES Categorie(id),
    idStyle int REFERENCES Style(id),
    idVolume int REFERENCES Volume(id),
    quantite int
);