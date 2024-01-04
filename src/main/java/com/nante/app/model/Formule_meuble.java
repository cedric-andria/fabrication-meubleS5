package com.nante.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;

@Entity
public class Formule_meuble {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name="idCategorie")
    private Categorie categorie;
    @ManyToOne
    @JoinColumn(name="idStyle")
    private Style style;
    @ManyToOne
    @JoinColumn(name="idMateriau")
    private Materiau materiau;
    @ManyToOne
    @JoinColumn(name="idVolume")
    private Volume volume;
    private double quantite;


    public Formule_meuble(Categorie categorie, Style style, Materiau materiau, Volume volume, double quantite) {
        this.setCategorie(categorie);
        this.setStyle(style);
        this.setMateriau(materiau);
        this.setVolume(volume);
        this.setQuantite(quantite);
    }


    public Formule_meuble() {
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Categorie getCategorie() {
        return categorie;
    }
    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
    public Style getStyle() {
        return style;
    }
    public void setStyle(Style style) {
        this.style = style;
    }
    public Materiau getMateriau() {
        return materiau;
    }
    public void setMateriau(Materiau materiau) {
        this.materiau = materiau;
    }
    public Volume getVolume() {
        return volume;
    }
    public void setVolume(Volume volume) {
        this.volume = volume;
    }
    public double getQuantite() {
        return quantite;
    }
    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    

}
