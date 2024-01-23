package com.nante.app.model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.nante.app.model.Materiau;
import com.nante.app.model.Volume;
import com.nante.app.model.Categorie;
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
public class PrixVente {
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
    @JoinColumn(name="idVolume")
    private Volume volume;
    private double prix;
    private LocalDateTime submit_date;
    
   
    public PrixVente(int id, Categorie categorie, Style style, Volume volume, double prix, LocalDateTime submit_date) {
        this.id = id;
        this.categorie = categorie;
        this.style = style;
        this.volume = volume;
        this.prix = prix;
        this.submit_date = submit_date;
    }
    public PrixVente(Categorie categorie, Style style, Volume volume, double prix, LocalDateTime submit_date) {
        this.categorie = categorie;
        this.style = style;
        this.volume = volume;
        this.prix = prix;
        this.submit_date = submit_date;
    }
    public PrixVente() {
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
    public Volume getVolume() {
        return volume;
    }
    public void setVolume(Volume volume) {
        this.volume = volume;
    }
    public double getPrix() {
        return prix;
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }
    public LocalDateTime getSubmit_date() {
        return submit_date;
    }
    public void setSubmit_date(LocalDateTime submit_date) {
        this.submit_date = submit_date;
    }
    


}
