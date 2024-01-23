package com.nante.app.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;

@Entity
public class Style_Materiau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int idStyle;
    private int idMateriau;
    // @ManyToOne
    // @JoinColumn(name = "idStyle")
    // private Style style;

    // @ManyToOne
    // @JoinColumn(name = "idMateriau")
    // private Materiau materiau;
   
    public Style_Materiau(int idStyle, int idMateriau) {
        this.setIdStyle(idStyle);
        this.setIdMateriau(idMateriau);
    }

    public Style_Materiau() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdStyle() {
        return idStyle;
    }

    public void setIdStyle(int idStyle) {
        this.idStyle = idStyle;
    }

    public int getIdMateriau() {
        return idMateriau;
    }

    public void setIdMateriau(int idMateriau) {
        this.idMateriau = idMateriau;
    }

    // public Style getStyle() {
    //     return style;
    // }

    // public void setStyle(Style style) {
    //     this.style = style;
    // }

    // public Materiau getMateriau() {
    //     return materiau;
    // }

    // public void setMateriau(Materiau materiau) {
    //     this.materiau = materiau;
    // }
  
}
