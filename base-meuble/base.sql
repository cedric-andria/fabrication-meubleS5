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
create table Formule_meuble(
    id serial primary key,
    idCategorie int references Categorie(id),
    idStyle int references Style(id),
    idVolume int references Volume(id),
    idMateriau int references Materiau(id),
    quantite double precision
);
insert into Materiau(nom) values('Bois');
insert into Materiau(nom) values('Metal');
insert into Materiau(nom) values('Plastique');
insert into Materiau(nom) values('Vernis');
insert into Materiau(nom) values('Polystyrene');

insert into Categorie(nom) values('Chaise');
insert into Categorie(nom) values('Table');
insert into Categorie(nom) values('Lit');

insert into Volume(description,longueur,largeur,hauteur) values ('desc1',2,4,5);


insert into Style(nom) values ('Royal');
insert into Style(nom) values ('Boheme');
insert into Style(nom) values ('Scandinave');


insert into Style_Materiau(idStyle, idMateriau) values (1,1);
insert into Style_Materiau(idStyle, idMateriau) values (1,2);
insert into Style_Materiau(idStyle, idMateriau) values (1,3);

insert into Style_Materiau(idStyle, idMateriau) values (2,1);
insert into Style_Materiau(idStyle, idMateriau) values (2,3);
insert into Style_Materiau(idStyle, idMateriau) values (2,4);
insert into Style_Materiau(idStyle, idMateriau) values (2,5);

insert into Style_Materiau(idStyle, idMateriau) values (3,1);
insert into Style_Materiau(idStyle, idMateriau) values (3,4);

-- insert into Formule_meuble(idCategorie, idStyle, idVolume, idMateriau, quantite) values (3, 2, 1, 7, 2);

create table PrixUnitaireMateriau(
    id serial primary key,
    idMateriau int references Materiau(id),
    montant double precision,
    submit_date timestamp DEFAULT current_timestamp
);
insert into PrixUnitaireMateriau (idMateriau, montant) values (7, 200);
insert into PrixUnitaireMateriau (idMateriau, montant) values (8, 500);
insert into PrixUnitaireMateriau (idMateriau, montant) values (9, 1000);

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