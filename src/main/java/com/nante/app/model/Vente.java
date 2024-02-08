package com.nante.app.model;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;

@Entity
public class Vente {
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
    @ManyToOne
    @JoinColumn(name="idGenre")
    private Genre genre; 
    private int quantite;
    private LocalDateTime date_vente;

    public LocalDateTime getDate_vente() {
        return date_vente;
    }
    public void setDate_vente(LocalDateTime date_vente) {
        this.date_vente = date_vente;
    }
    public Vente(Categorie categorie, Style style, Volume volume, Genre genre, int quantite, LocalDateTime date_vente) {
        this.categorie = categorie;
        this.style = style;
        this.volume = volume;
        this.genre = genre;
        this.quantite = quantite;
        this.date_vente = date_vente;
    }
    public Vente(int id, Categorie categorie, Style style, Volume volume, Genre genre, int quantite, LocalDateTime date_vente) {
        this.id = id;
        this.categorie = categorie;
        this.style = style;
        this.volume = volume;
        this.genre = genre;
        this.quantite = quantite;
        this.date_vente = date_vente;

    }
    public Vente() {
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
    public Genre getGenre() {
        return genre;
    }
    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    public int getQuantite() {
        return quantite;
    }
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
