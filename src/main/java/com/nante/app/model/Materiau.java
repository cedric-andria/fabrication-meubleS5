package com.nante.app.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import java.util.List;

@Entity
public class Materiau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;

    // @ManyToMany(mappedBy = "materiaux")
    // private List<Style> styles;
    
    public Materiau(String nom) {
        this.setNom(nom);
    }
    public Materiau() {
    }
    // public List<Style> getStyles() {
    //     return styles;
    // }
    // public void setStyles(List<Style> styles) {
    //     this.styles = styles;
    // }
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
