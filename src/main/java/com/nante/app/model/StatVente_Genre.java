package com.nante.app.model;

import java.util.HashMap;

public class StatVente_Genre {
    private Categorie categorie;
    private Style style;
    private Volume volume;
    private Genre genre;
    private double stat;

    public StatVente_Genre(Categorie categorie, Style style, Volume volume, Genre genre, double stat) {
        this.categorie = categorie;
        this.style = style;
        this.volume = volume;
        this.genre = genre;
        this.stat = stat;
    }
    public Genre getGenre() {
        return genre;
    }
    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    // public StatVente_Genre(Categorie categorie, Style style, Volume volume, ) {
    //     this.categorie = categorie;
    //     this.style = style;
    //     this.volume = volume;
    //     this.stats = stats;
    // }
    public StatVente_Genre() {
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
    // public HashMap<Genre, Object> getStats() {
    //     return stats;
    // }
    // public void setStats(HashMap<Genre, Object> stats) {
    //     this.stats = stats;
    // }
    public double getStat() {
        return stat;
    }
    public void setStat(double stat) {
        this.stat = stat;
    }
    
}
