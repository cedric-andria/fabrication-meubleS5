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
    idMateriau int references Materiau(id)
);
insert into Materiau(nom) values('materiau1');
insert into Categorie(nom) values('Categorie1');
insert into Volume(description,longueur,largeur,hauteur) values ('desc1',2,4,5);
insert into Style(nom) values ('style1');

insert into Style_Materiau(idStyle,idMateriau) values (1,1);