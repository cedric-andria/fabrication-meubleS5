package com.nante.app.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Volume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id ;
    String nom ;

    public Volume(int id , String nom) {
        setId(id);
        setNom(nom);
    } 
    public Volume(){}

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
