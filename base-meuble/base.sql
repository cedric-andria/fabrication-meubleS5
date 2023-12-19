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
    idMateriau int references Materiau(id)
);
create table Formule_meuble(
    id serial primary key,
    idCategorie int references Categorie(id),
    idStyle int references Style(id),
    idVolume int references Volume(id),
    idMateriau int references Materiau(id),
    quantite double precision
);
insert into Materiau(nom) values('materiau1');
insert into Categorie(nom) values('categorie1');
insert into Volume(nom) values ('volume1');
insert into Style(nom) values ('style1');
insert into Style_Materiau(idStyle,idMateriau) values (1,1);
insert into Formule_meuble(idCategorie,idStyle,idVolume,idMateriau,quantite) values (1,1,1,1,2);