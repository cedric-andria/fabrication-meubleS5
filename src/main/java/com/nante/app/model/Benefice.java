package com.nante.app.model;

import com.nante.app.model.Categorie;
import com.nante.app.model.Materiau;
import com.nante.app.model.Style;
import com.nante.app.model.Volume;

public class Benefice {
    private Categorie categorie;
    private Style style;
    private Volume volume;
    private double montant;

    public Benefice(Categorie categorie, Style style, Volume volume, double montant) {
        this.categorie = categorie;
        this.style = style;
        this.volume = volume;
        this.montant = montant;
    }
    public Benefice() {
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
    public double getMontant() {
        return montant;
    }
    public void setMontant(double montant) {
        this.montant = montant;
    }

}
