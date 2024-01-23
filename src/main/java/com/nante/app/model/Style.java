package com.nante.app.model;

import jakarta.persistence.Transient;
import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;

@Entity
public class Style {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    
    @ManyToMany
    @JoinTable(
        name = "Style_Materiau", joinColumns = @JoinColumn(name = "idStyle"), inverseJoinColumns = @JoinColumn(name = "idMateriau")
    )
    private List<Materiau> materiaux;
    
    // @ManyToMany
    // @JoinTable(
    //     name = "Style_Nb_Ouvrier", joinColumns = @JoinColumn(name = "idStyle"), inverseJoinColumns = @JoinColumn(name = "Ouvrier")
    // )
    // private int nb_ouvrier;

    public Style(int id) {
        this.setId(id);
    }
    public Style() {
    }
    public Style(String nom) {
        this.nom = nom;
    }
    public List<Materiau> getMateriaux() {
        return materiaux;
    }
    public void setMateriaux(List<Materiau> materiaux) {
        this.materiaux = materiaux;
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
