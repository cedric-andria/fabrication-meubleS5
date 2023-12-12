package com.nante.app.model;

import com.nante.app.crud.model.GenericModel;

public class Materiau extends GenericModel {
    int id ;
    String nom ;
    
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
