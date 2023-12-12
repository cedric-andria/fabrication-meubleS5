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