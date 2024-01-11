package com.nante.app.model;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Transient;
import jakarta.persistence.ManyToOne;

import java.util.List;

import com.nante.app.model.Categorie;
import com.nante.app.model.Materiau;
import com.nante.app.model.Volume;

public class MeubleCostView {
    private int idCategorie;
    private int idStyle;
    private int idVolume;
    private double cout;

    @Transient
    private Categorie categorie;
    @Transient
    private Style style;
    @Transient
    private Volume volume;

    public MeubleCostView(int idCategorie, int idStyle, int idVolume, double cout) {
        this.setIdCategorie(idCategorie);
        this.setIdStyle(idStyle);
        this.setIdVolume(idVolume);
        this.setCout(cout);
    }
    // public MeubleCostView(Categorie categorie, Style style, Volume volume, double cout) {
    //     this.setCategorie(categorie);
    //     this.setStyle(style);
    //     this.setVolume(volume);
    //     this.setCout(cout);
    // }
    public MeubleCostView() {
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
    public double getCout() {
        return cout;
    }
    public void setCout(double cout) {
        this.cout = cout;
    }
    public int getIdCategorie() {
        return idCategorie;
    }
    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }
    public int getIdStyle() {
        return idStyle;
    }
    public void setIdStyle(int idStyle) {
        this.idStyle = idStyle;
    }
    public int getIdVolume() {
        return idVolume;
    }
    public void setIdVolume(int idVolume) {
        this.idVolume = idVolume;
    }
    
}
