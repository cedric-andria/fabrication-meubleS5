package com.nante.app.model;

import java.util.List;

import com.nante.app.crud.model.GenericModel;

public class Style extends GenericModel {
    int id ;
    String nom ;
    List<Materiau> matieres ;
    
    public List<Materiau> getMatieres() {
        return matieres;
    }
    public void setMatieres(List<Materiau> matieres) {
        this.matieres = matieres;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
}
