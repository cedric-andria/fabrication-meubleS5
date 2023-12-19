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
    description varchar(500),
    longueur double precision,
    largeur double precision,
    hauteur double precision
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
