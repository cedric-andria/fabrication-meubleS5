package com.nante.app.model;

import java.sql.Date;
import java.time.LocalDate;

import com.nante.app.model.Materiau;
import com.nante.app.model.Formule_meuble;


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
public class Fabrication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    // @ManyToOne
    // @JoinColumn(name="idFormuleMeuble")
    // private Formule_meuble formule_meuble;
    @ManyToOne
    @JoinColumn(name="idCategorie")
    private Categorie categorie;
    @ManyToOne
    @JoinColumn(name="idStyle")
    private Style style;
    @ManyToOne
    @JoinColumn(name="idVolume")
    private Volume volume;

    private int quantite;
    public Fabrication(Categorie categorie, Style style, Volume volume, int quantite) {
        this.setCategorie(categorie);
        this.setStyle(style);
        this.setVolume(volume);
        this.setQuantite(quantite);
    }
    // public Fabrication(Formule_meuble formule_meuble, int quantite) {
    //     this.formule_meuble = formule_meuble;
    //     this.quantite = quantite;
    // }
    // public Fabrication(int id, Formule_meuble formule_meuble, int quantite) {
    //     this.id = id;
    //     this.formule_meuble = formule_meuble;
    //     this.quantite = quantite;
    // }
    public Fabrication() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    // public Formule_meuble getFormule_meuble() {
    //     return formule_meuble;
    // }
    // public void setFormule_meuble(Formule_meuble formule_meuble) {
    //     this.formule_meuble = formule_meuble;
    // }
    public int getQuantite() {
        return quantite;
    }
    public void setQuantite(int quantite) {
        this.quantite = quantite;
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
    public Volume getVolume() {
        return volume;
    }
    public void setVolume(Volume volume) {
        this.volume = volume;
    }
}
