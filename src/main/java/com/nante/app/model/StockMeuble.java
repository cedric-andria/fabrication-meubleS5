package com.nante.app.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class StockMeuble {
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
    private int quantite;
    private LocalDateTime date_stock;
    
    

    public StockMeuble(Categorie categorie, Style style, Volume volume, int quantite, LocalDateTime date_stock) {
        this.categorie = categorie;
        this.style = style;
        this.volume = volume;
        this.quantite = quantite;
        this.date_stock = date_stock;
    }
    public StockMeuble() {
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
    // public Materiau getMateriau() {
    //     return materiau;
    // }
    // public void setMateriau(Materiau materiau) {
    //     this.materiau = materiau;
    // }
    public Volume getVolume() {
        return volume;
    }
    public void setVolume(Volume volume) {
        this.volume = volume;
    }
    public int getQuantite() {
        return quantite;
    }
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    public LocalDateTime getDate_stock() {
        return date_stock;
    }
    public void setDate_stock(LocalDateTime date_stock) {
        this.date_stock = date_stock;
    }
    
}
