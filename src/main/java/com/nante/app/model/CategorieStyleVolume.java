package com.nante.app.model;

public class CategorieStyleVolume {
    private Categorie categorie;
    private Style style;
    private Volume volume;
    public CategorieStyleVolume(Categorie categorie, Style style, Volume volume) {
        this.categorie = categorie;
        this.style = style;
        this.volume = volume;
    }
    public CategorieStyleVolume() {
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
